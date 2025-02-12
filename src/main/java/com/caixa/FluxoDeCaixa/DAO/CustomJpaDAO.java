 package com.caixa.FluxoDeCaixa.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaDAO<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	Optional<T> buscarPrimeiro();

	void detach(T entity);

	Specification<T> usandoFiltro(Object filtro, List<?> relacionamentos);

	List<Map<String, Object>> findObjectsByQuery(String sql);

}
