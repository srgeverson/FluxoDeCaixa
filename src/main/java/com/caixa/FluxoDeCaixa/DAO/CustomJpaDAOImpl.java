package com.caixa.FluxoDeCaixa.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.Predicate;

public class CustomJpaDAOImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaDAO<T, ID> {

	private EntityManager manager;

	public CustomJpaDAOImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);

		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		var jpql = "from " + getDomainClass().getName();

		T entity = manager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();

		return Optional.ofNullable(entity);
	}

	@Override
	public void detach(T entity) {
		manager.detach(entity);
	}

	@SuppressWarnings("null")
	@Override
	public Specification<T> usandoFiltro(Object filtro, List<?> relacionamentos) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();
			if (filtro.getClass().equals(query.getResultType())) {
				for (Object object : relacionamentos) {
					if (object instanceof String) {
						root.fetch(String.valueOf(object));
					} else {
						@SuppressWarnings("unchecked")
						HashMap<String, String> subRelacionamentos = (HashMap<String, String>) object;
						subRelacionamentos.forEach((relacionamentoDaEntidade, entidade) -> {
							root.fetch((String) entidade).fetch((String) relacionamentoDaEntidade);
						});
					}
				}
			}
			var campos = filtro.getClass().getDeclaredFields();
			for (Field campo : campos) {
				String nome = null;
				try {
					nome = campo.getName();
					Method metodo = getMethodByString(filtro, nome);
					if (metodo == null)
						continue;
					var valor = metodo.invoke(filtro);
					if (valor != null
							|| (valor != null && valor.getClass().isArray() && Arrays.asList(valor).size() > 0)) {
						if (valor instanceof String) {
							predicates.add(builder.like(root.get(nome), "%".concat(String.valueOf(valor)).concat("%")));
						} else if (campoEData(nome) && valorEDoTipoData(valor)) {
							predicates.add(builder.greaterThanOrEqualTo(root.get(nome), (OffsetDateTime) valor));
						} else if (campoEData(nome) && valorEDoTipoData(valor)) {
							predicates.add(builder.lessThanOrEqualTo(root.get(nome), (OffsetDateTime) valor));
						} else {
							predicates.add(builder.equal(root.get(nome), valor));
						}
					}
				} catch (Exception e) {
					// e.printStackTrace();
					System.out.println(String.format("Erro ao tratar dados do campo %s", nome));
				}
			}
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

	private boolean campoEData(String nome) {
		return nome.toLowerCase().contains("datainicio") || nome.toLowerCase().contains("datafim")
				|| nome.toLowerCase().contains("dataoperacao") || nome.toLowerCase().contains("datacadastro");
	}

	private boolean valorEDoTipoData(Object valor) {
		return valor instanceof OffsetDateTime || valor instanceof Date;
	}

	private Method getMethodByString(Object obj, String attribut) throws ClassNotFoundException {
		try {
			Class<?> classe = Class.forName(obj.getClass().getName());
			return classe.getDeclaredMethod("get".concat(StringUtils.capitalize(attribut)));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			throw new ClassNotFoundException("ERRO AO TENTAR OBTER O MÉTODO: ".concat(attribut));
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findObjectsByQuery(String sql) {
		Query query = manager.createNativeQuery(sql, Tuple.class);
		List<Tuple> queryRows = query.getResultList();

		List<Map<String, Object>> formattedRows = new ArrayList<>();

		queryRows.forEach(row -> {
			Map<String, Object> formattedRow = new HashMap<>();

			row.getElements().forEach(column -> {
				String columnName = column.getAlias();
				Object columnValue = row.get(column);

				formattedRow.put(columnName, columnValue);
			});

			formattedRows.add(formattedRow);
		});

		return formattedRows;
	}

}
