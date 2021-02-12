package br.com.itau.renegociacao.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 7:03 PM
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private static final String MSG_ERRO = "Ocorreu um erro interno inesperado no sistema. "
            + "Tente novamente e se o problema persistir, entre em contato "
            + "com o administrador do sistema.";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    @ExceptionHandler({ ValidacaoException.class })
    public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    private List<Problema.Campo> extrairCampos(final BindingResult bindingResult) {
        final List<Problema.Campo> fields = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String nome = objectError.getObjectName();
                    if(objectError instanceof FieldError) {
                        nome = ((FieldError) objectError).getField();
                    }

                    return Problema.Campo.builder()
                            .nome(nome)
                            .mensagem(mensagem)
                            .build();
                })
                .collect(Collectors.toList());
        return fields;
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String detalhe = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<Problema.Campo> campos = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String nome = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        nome = ((FieldError) objectError).getField();
                    }

                    return Problema.Campo.builder()
                            .nome(nome)
                            .mensagem(mensagem)
                            .build();
                })
                .collect(Collectors.toList());

        Problema problema = criarProblemaBuilder(status, detalhe)
                .mensagem(detalhe)
                .campos(campos)
                .build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ex.printStackTrace();
        Problema problema = criarProblemaBuilder(status,
                MSG_ERRO).build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detalhe = String.format("O recurso %s, que você tentou acessar, é inexistente.",
                ex.getRequestURL());

        Problema problema = criarProblemaBuilder(status, detalhe, MSG_ERRO).build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Problema problema = criarProblemaBuilder(status, ex.getMessage(), MSG_ERRO).build();
        return handleExceptionInternal(ex, problema, headers, status, request);
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

        String detalhe = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Problema problema = criarProblemaBuilder(status, detalhe, MSG_ERRO).build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(e);

        if(rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);

        } else if(rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        String detalhe = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
        Problema problema = criarProblemaBuilder(status, detalhe, MSG_ERRO).build();

        return handleExceptionInternal(e, problema, headers, status, request);

    }

    private ResponseEntity<Object> handleInvalidFormatException(
            InvalidFormatException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(e.getPath());

        String detalhe = String.format("A propriedade '%s' recebeu o valor '%s', " +
                        "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, e.getValue(), e.getTargetType().getSimpleName());
        Problema problema = criarProblemaBuilder(status, detalhe, MSG_ERRO).build();

        return handleExceptionInternal(e, problema, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

    private ResponseEntity<Object> handlePropertyBindingException(
            PropertyBindingException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(e.getPath());

        String detalhe = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);
        Problema problema = criarProblemaBuilder(status, detalhe, MSG_ERRO).build();

        return handleExceptionInternal(e, problema, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(
            EntityNotFoundException e, WebRequest webRequest) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        Problema problema = criarProblemaBuilder(status, e.getMessage()).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(
            EntidadeEmUsoException e, WebRequest webRequest) {

        HttpStatus status = HttpStatus.CONFLICT;
        Problema problema = criarProblemaBuilder(status, e.getMessage()).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(),
                status, webRequest);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(
            NegocioException e, WebRequest webRequest) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problema problema = criarProblemaBuilder(status, e.getMessage(), MSG_ERRO).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, webRequest);
    }

    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException e,
            HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        final Problema problema = criarProblemaBuilder(status, e.getMessage()).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(),
                status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(body == null || body instanceof String) {
            body = Problema.builder()
                    .status(status.value())
                    .mensagem(MSG_ERRO)
                    .data(LocalDateTime.now())
                    .build();
        }
        
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder criarProblemaBuilder(
            HttpStatus status, String detalhe) {
        return  criarProblemaBuilder(status, detalhe, detalhe);
    }

    private Problema.ProblemaBuilder criarProblemaBuilder(
            HttpStatus status, String detalhe, String mensagem) {
        return Problema.builder()
                .status(status.value())
                .mensagem(mensagem)
                .data(LocalDateTime.now())
                .detalhe(detalhe);
    }

}