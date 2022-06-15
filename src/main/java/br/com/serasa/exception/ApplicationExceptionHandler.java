package br.com.serasa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleValidationExceptions(MethodArgumentNotValidException ex) {
        var responseError = new ResponseError();
        responseError.setCodeStatus(HttpStatus.BAD_REQUEST.value());

        var errorMessage = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(" | "));
        responseError.setMessage(errorMessage);

        return responseError;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseError handleNotFoundException(Exception ex) {
        return new ResponseError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
