package com.himanshi.hospital.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException ex){
        ApiError apiError=new ApiError("Username not found with username: "+ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex){
        ApiError apiError=new ApiError("Authentication Failed: "+ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return  new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(AuthenticationException ex){
        ApiError apiError= new ApiError("Invalid JWT Token: "+ ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return  new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AuthenticationException ex){
        ApiError apiError= new ApiError("Access Denied: Insufficient permissions "+ ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return  new ResponseEntity<>(apiError,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(AuthenticationException ex){
        ApiError apiError= new ApiError("An unexpected error occured: "+ ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return  new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
