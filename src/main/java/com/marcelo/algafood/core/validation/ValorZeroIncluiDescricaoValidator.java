package com.marcelo.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Objects;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<seValorZeroIncluiDescricao, Object> {

    private String checarCampo;
    private String verificarDescricaoNoCampo;
    private String seContemAdescricaoObrigatoria;

    @Override
    public void initialize(seValorZeroIncluiDescricao constraint) {
        this.checarCampo = constraint.checarCampo();
        this.verificarDescricaoNoCampo = constraint.verificarDescricaoNoCampo();
        this.seContemAdescricaoObrigatoria = constraint.seContemAdescricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoParaValidacao, ConstraintValidatorContext context) {

        boolean valido = true;


        try {
//			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField)
//					.getReadMethod().invoke(objetoValidacao);
//
//			String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField)
//					.getReadMethod().invoke(objetoValidacao);

            BigDecimal valor = (BigDecimal) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(objetoParaValidacao.getClass(), checarCampo))
                    .getReadMethod().invoke(objetoParaValidacao);

            String descricao = (String) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(objetoParaValidacao.getClass(), verificarDescricaoNoCampo))
                    .getReadMethod().invoke(objetoParaValidacao);

            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(this.seContemAdescricaoObrigatoria.toLowerCase());
            }
            return valido;
        } catch (Exception e) {
            valido = false;
            throw new ValidationException(e);
        }
    }
}
