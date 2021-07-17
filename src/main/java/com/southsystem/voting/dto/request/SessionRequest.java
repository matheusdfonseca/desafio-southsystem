package com.southsystem.voting.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SessionRequest {
    @NotNull
    private Long duration;
    @NotNull @JsonProperty("topic_id")
    private Long topic;

}
