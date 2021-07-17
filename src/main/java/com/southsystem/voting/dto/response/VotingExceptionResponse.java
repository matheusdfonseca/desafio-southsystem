package com.southsystem.voting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class VotingExceptionResponse {
    private Integer status;
    private String Details;
    private LocalDateTime timestamp;
}
