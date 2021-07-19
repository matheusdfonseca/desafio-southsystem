package com.southsystem.voting.util;

import com.southsystem.voting.domain.Session;
import com.southsystem.voting.dto.request.SessionRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.southsystem.voting.util.TopicCreator.createValidTopic;

public class SessionCreator {

    public static Session createSession(){
        Session session = new Session();
        session.setDuration(10L);
        session.setTimeStart(LocalDateTime.of(1995,7,27,7,30));
        session.setTimeEnd(LocalDateTime.of(2995,7,27,7,30));

        session.setTopic(createValidTopic());
        return session;
    }

    public static Session createValidSession(){
        Session session = createSession();
        session.setId(1L);
        return session;
    }

    public static Session createSessionClosed(){
        Session session = new Session();
        session.setDuration(10L);
        session.setTimeStart(LocalDateTime.of(1995,7,27,7,30));
        session.setTimeEnd(LocalDateTime.of(1995,7,27,7,31));

        session.setTopic(createValidTopic());
        return session;
    }

    public static Session createValidSessionClosed(){
        Session session = createSessionClosed();
        session.setId(1L);
        return session;
    }

    public static SessionRequest createSessionRequest(){
        return new SessionRequest(1L, 1L);
    }

    public static Optional<Session> createValidOptionalSession(){
        return Optional.of(createValidSession());
    }

    public static List<Session> createListValidSession(){
        return Arrays.asList(createValidSession());
    }
}
