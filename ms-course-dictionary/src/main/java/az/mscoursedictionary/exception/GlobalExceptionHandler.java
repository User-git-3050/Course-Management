package az.mscoursedictionary.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static az.mscoursedictionary.enums.ErrorMessages.VALIDATION_FAILED;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse NotFoundExceptionHandler(HttpServletRequest request, NotFoundException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(NOT_FOUND.value())
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public ErrorResponse BadRequestExceptionHandler(HttpServletRequest request, BadRequestException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(NOT_MODIFIED.value())
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse MethodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e){
        ArrayList<String> errors = new ArrayList<>();
        e.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return ErrorResponse.builder()
                .message(VALIDATION_FAILED.getMessage())
                .status(BAD_REQUEST.value())
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse MethodNotAllowedExceptionHandler(HttpServletRequest request, MethodNotAllowedException e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(METHOD_NOT_ALLOWED.value())
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .build();
    }


}
