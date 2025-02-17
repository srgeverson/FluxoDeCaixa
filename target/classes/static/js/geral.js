function validarForm(){
	// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function () {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms)
				.forEach(function (form) {
					form.addEventListener('submit', function (event) {
						if (!form.checkValidity()) {
							event.preventDefault()
							event.stopPropagation()
						}

						form.classList.add('was-validated')
					}, false)
				})
		})()
}

function valorMoeda(idComponente){
	$(`#${idComponente}`).mask('#.###.##0,00', {
			reverse: true
		});
}

function testeFomrm(idForm){
	$(`#${idForm}`).click(function(event) {
			event.preventDefault(); // Impede o envio padrão do formulário

                // Captura os dados do formulário
                var dadosFormulario = $('form').serializeArray();

                // Exibe os dados no console
                console.log(dadosFormulario);

                /*
                Exemplo: acessar valores específicos
                var nome = $("#nome").val();
                var email = $("#email").val();
                var idade = $("#idade").val();
                alert("Nome: " + nome + "\nEmail: " + email + "\nIdade: " + idade);
                */ 

		})
}