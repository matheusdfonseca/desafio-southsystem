package com.southsystem.voting.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor
public class SessionRequest {
    @NotNull
    private Long duration;
    @NotNull @JsonProperty("topic_id")
    private Long topic;

}
