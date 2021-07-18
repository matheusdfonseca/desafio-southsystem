package com.southsystem.voting.util;

import com.southsystem.voting.domain.Session;
import com.southsystem.voting.dto.request.SessionRequest;

import java.time.LocalDateTime;

import static com.southsystem.voting.util.TopicCreator.createTopic;
import static com.southsystem.voting.util.TopicCreator.createValidTopic;

public class SessionCreator {

    public static Session createValidSession(){
        Session session = new Session();
        session.setId(1L);
        session.setDuration(10L);
        session.setTimeStart(LocalDateTime.of(1995,7,27,7,30));
        session.setTimeEnd(LocalDateTime.of(1995,7,27,7,40));

        session.setTopic(createValidTopic());
        return session;
    }
    public static SessionRequest createSessionRequest(){
        return new SessionRequest(1L, 1L);
    }
}
