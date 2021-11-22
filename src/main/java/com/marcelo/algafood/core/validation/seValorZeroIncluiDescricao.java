package com.marcelo.algafood.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class })
public @interface seValorZeroIncluiDescricao {

	String message() default "Valor taxa frete é zero, descrição Frete Grátis é obrigatória.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String checarCampo();
	
	String verificarDescricaoNoCampo();
	
	String seContemAdescricaoObrigatoria();
	
}
