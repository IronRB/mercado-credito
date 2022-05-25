package com.mercadocredito.exception;

import com.mercadocredito.exception.Output.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.xml.bind.ValidationException;

/**
 * Clase de manejo de excepciones
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {
    private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseException IllegalArgumentException(IllegalArgumentException e) {
        ResponseException responseException = new ResponseException(400,"Bad Request",e.getMessage());
        logger.error(""+e, e.getMessage());
        return responseException;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseException ResourceNotFoundException(ResourceNotFoundException e) {
        ResponseException responseException = new ResponseException(404,"Not found",e.getMessage());
        logger.error("Recurso no encontrado", e.getCause());
        return responseException;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseException handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        ResponseException responseException = new ResponseException(400,"Bad Request",e.getMessage());
        logger.error("Parámetro de solicitud faltante", e);
        return responseException;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseException handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResponseException responseException = new ResponseException(400,"Bad Request",e.getMessage());
        logger.error("Error al analizar el parámetro", e);
        return responseException;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseException handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("Error de validación de parámetros", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        ResponseException responseException = new ResponseException(400,"Bad Request","El parámetro " + message);
        return responseException;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseException handleBindException(BindException e) {
        logger.error("Error de enlace de parámetros", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        ResponseException responseException = new ResponseException(400,"Bad Request","Error de enlace de parámetro =" + message);
        return responseException;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseException handleValidationException(ValidationException e) {
        ResponseException responseException = new ResponseException(400,"Bad Request",e.getMessage());
        logger.error("Error de validación de parámetros", e.getMessage());
        return responseException;
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseException noHandlerFoundException(NoHandlerFoundException e) {
        ResponseException responseException = new ResponseException(404,"Not found",e.getMessage());
        logger.error("Not Found", e.getCause());
        return responseException;
    }


    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseException handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ResponseException responseException = new ResponseException(405,"Method Not Allowed","request_method_not_supported");
        logger.error("El método de solicitud actual no es compatible", e);
        return responseException;
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseException handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        ResponseException responseException = new ResponseException(415,"Unsupported Media Type","content_type_not_supported");
        logger.error("El tipo de medio actual no es compatible", e);
        return responseException;
    }

/**
 * 500 - Internal Server Error
 */
}