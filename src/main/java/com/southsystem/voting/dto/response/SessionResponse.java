package com.southsystem.voting.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.southsystem.voting.domain.Session;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SessionResponse {
    private Long duration;
    @JsonProperty("time_start")
    private LocalDateTime timeStart;
    @JsonProperty("time_end")
    private LocalDateTime timeEnd;

    public SessionResponse(Session session) {
        this.duration = session.getDuration();
        this.timeStart = session.getTimeStart();
        this.timeEnd = session.getTimeEnd();
    }

    public static List<SessionResponse> toList(List<Session> sessions){
        return sessions.stream().map(SessionResponse::new).collect(Collectors.toList());
    }
}
