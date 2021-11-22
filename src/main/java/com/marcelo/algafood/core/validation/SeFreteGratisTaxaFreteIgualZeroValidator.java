package com.marcelo.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Objects;

public class SeFreteGratisTaxaFreteIgualZeroValidator implements ConstraintValidator<seFreteGratisTaxaFreteIgualZero, Object> {

    private String checarCampo;
    private String verificarDescricaoNoCampo;
    private boolean valido = true;

    @Override
    public void initialize(seFreteGratisTaxaFreteIgualZero constraint) {
        this.checarCampo = constraint.checarCampo();
        this.verificarDescricaoNoCampo = constraint.verificarDescricaoNoCampo();
    }

    @Override
    public boolean isValid(Object objetoParaValidacao, ConstraintValidatorContext context) {

        try {
            BigDecimal valor = (BigDecimal) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(objetoParaValidacao.getClass(), checarCampo))
                    .getReadMethod().invoke(objetoParaValidacao);
            String descricao = (String) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(objetoParaValidacao.getClass(), verificarDescricaoNoCampo))
                    .getReadMethod().invoke(objetoParaValidacao);
            if (BigDecimal.ZERO.compareTo(valor) != 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(this.verificarDescricaoNoCampo.toLowerCase());

                //    acertar isto, quando taxa frete é gratis, valor do frete tem que estar zerado.
            }
            return valido;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
