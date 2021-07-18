package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Session;
import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.dto.request.SessionRequest;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.SessionRepository;
import com.southsystem.voting.service.SessionService;
import com.southsystem.voting.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository repository;
    @Autowired
    TopicService topicService;

    @Override
    public List<Session> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Session> getAllNotClosed() {
        return repository.findAllByIsClosedIsFalseAndAndTimeEndIsAfter(LocalDateTime.now());
    }

    @Override
    public Session open(SessionRequest request) {
        Topic topic = topicService.getById(request.getTopic());
        Long duration = request.getDuration();
        Session session = new Session(duration, topic);
        session.setTimeStart(LocalDateTime.now());
        session.updateTimeEnd();

        return save(session);
    }

    @Override
    public Session getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new VotingException("Not found session"));
    }

    @Override
    public Session save(Session session) {
        return repository.save(session);
    }

    @Override
    public void delete(Session session) {
        repository.delete(session);
    }
}
