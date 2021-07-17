package com.southsystem.voting.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @NoArgsConstructor
@Table(name = "sessions")
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private Long duration;
    @OneToMany(mappedBy = "session")
    private Set<Vote> votes = new HashSet();
    @ManyToOne @JoinColumn(name = "topic_id")
    private Topic topic;
    private Boolean isClosed = false;

    public Session(Long duration, Topic topic) {
        this.duration = duration != null ? duration: 1L;
        this.topic = topic;
    }

    public void updateTimeEnd() {
        LocalDateTime timeEnd = timeStart.plusMinutes(duration);
        setTimeEnd(timeEnd);
    }
}