package com.marcelo.algafood.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.marcelo.algafood.core.validation.ValidacaoException;
import com.marcelo.algafood.domain.exception.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL
            = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";

    @Autowired
    private MessageSource messageSource;


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        MessagesTypes messagesTypes = MessagesTypes.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindingResult = ex.getBindingResult();

        List<ErrorDetails.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {

                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return ErrorDetails.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                }).collect(Collectors.toList());

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }

//    @ExceptionHandler({ValidacaoException.class})
//    public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
//        return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(),
//                HttpStatus.BAD_REQUEST, request);
//    }


    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request, BindingResult bindingResult) {

        MessagesTypes messagesTypes = MessagesTypes.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<ErrorDetails.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return ErrorDetails.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        MessagesTypes messagesTypes = MessagesTypes.ERRO_DE_SISTEMA;
        String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

        ex.printStackTrace();

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {

        MessagesTypes problemType = MessagesTypes.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.",
                ex.getRequestURL());

        ErrorDetails errorDetails = createErrorDetails(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        MessagesTypes messagesTypes = MessagesTypes.PARAMETRO_INVALIDO;

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        MessagesTypes messagesTypes = MessagesTypes.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        MessagesTypes messagesTypes = MessagesTypes.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        MessagesTypes messagesTypes = MessagesTypes.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        MessagesTypes messagesTypes = MessagesTypes.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex,
                                                           WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        MessagesTypes messagesTypes = MessagesTypes.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        MessagesTypes messagesTypes = MessagesTypes.ENTIDADE_EM_USO;
        String detail = ex.getMessage();

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleTipoDeObjetoJaCadastrado(ConstraintViolationException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        MessagesTypes messageTypes = MessagesTypes.TIPO_DE_OBJETO_JA_CADASTRADO;
        String detail = ex.getLocalizedMessage();

        ErrorDetails errorDetails = createErrorDetails(status, messageTypes, ex.getMessage())
                .userMessage(detail)
                .build();
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        MessagesTypes messagesTypes = MessagesTypes.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<?> handleEmailException(EmailException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        MessagesTypes messagesTypes = MessagesTypes.EMAIL_ERRO_NO_ENVIO;
        String detail = ex.getMessage();
        ErrorDetails errorDetails = createErrorDetails(status, messagesTypes, detail)
                .userMessage(detail)
                .build();
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ErrorDetails.builder()
                    .timestamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        } else if (body instanceof String) {
            body = ErrorDetails.builder()
                    .timestamp(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }


//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
//        ValidationErrorResponse error = new ValidationErrorResponse();
//        for (ConstraintViolation violation : e.getConstraintViolations()) {
//            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
//        }
//        return error;
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        ValidationErrorResponse error = new ValidationErrorResponse();
//        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
//            error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
//        }
//        return error;
//    }


    private ErrorDetails.ErrorDetailsBuilder createErrorDetails(HttpStatus status,
                                                                MessagesTypes messageTypes, String detail) {

        return ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(messageTypes.getUri())
                .title(messageTypes.getTitle())
                .detail(detail);
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

}
