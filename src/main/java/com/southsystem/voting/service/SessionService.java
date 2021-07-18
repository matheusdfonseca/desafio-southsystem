package com.southsystem.voting.service;

import com.southsystem.voting.domain.Session;
import com.southsystem.voting.dto.request.SessionRequest;

import java.util.List;

public interface SessionService {
    List<Session> getAll();
    List<Session> getAllNotClosed();
    Session open(SessionRequest request);
    Session getById(Long id);
    Session save(Session session);
    void delete(Session session);

}
