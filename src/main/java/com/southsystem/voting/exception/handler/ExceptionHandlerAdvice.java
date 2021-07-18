package com.southsystem.voting.exception.handler;

import com.southsystem.voting.dto.response.VotingExceptionResponse;
import com.southsystem.voting.exception.VotingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(VotingException.class)
    public ResponseEntity<VotingExceptionResponse> handlerVotingException(VotingException exception){
        VotingExceptionResponse error = new VotingExceptionResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(LocalDateTime.now());
        error.setDetails(exception.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
}
