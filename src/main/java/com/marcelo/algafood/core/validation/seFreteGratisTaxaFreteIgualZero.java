package com.marcelo.algafood.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { SeFreteGratisTaxaFreteIgualZeroValidator.class })
public @interface seFreteGratisTaxaFreteIgualZero {

    String message() default "Atenção : frete grátis, conferir valor taxaFrete.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String checarCampo();

    String verificarDescricaoNoCampo();

}
