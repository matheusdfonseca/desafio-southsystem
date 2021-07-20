package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.domain.Session;
import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.domain.Vote;
import com.southsystem.voting.dto.request.VoteRequest;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.VoteRepository;
import com.southsystem.voting.service.AssociateService;
import com.southsystem.voting.service.SessionService;
import com.southsystem.voting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteRepository repository;
    @Autowired
    SessionService sessionService;
    @Autowired
    AssociateService associateService;



    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Vote> getAllByTopic(Topic topic) {
        return repository.findAllBySession_Topic(topic);
    }

    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public Vote vote(VoteRequest request) {
        Vote vote = null;

        Session session = sessionService.getById(request.getSession());
        if (isSessionClosed(session))
            throw new VotingException("closed session");

        Associate associate = associateService.getById(request.getAssociate());
        if (validateVote(associate, session)) {
            sessionService.closeSession(session);
            vote = save(request.toVote(associate, session));
        }

        return vote;
    }

    private Boolean isSessionClosed(Session session) {
        return LocalDateTime.now().isAfter(session.getTimeEnd());
    }

    @Override
    public Boolean validateVote(Associate associate, Session session) {
        return associateService.isPermissionVote(associate)
                && !isAssociateVoted(associate, session);
    }

    @Override
    public Boolean isAssociateVoted(Associate associate, Session session) {
        Topic topic = session.getTopic();
        Optional<Vote> isAssociateVoted = repository.findByAssociateAndSession_Topic(associate, topic);
        if (isAssociateVoted.isPresent())
            throw new VotingException("Associate has already voted for this topic");
        return isAssociateVoted.isPresent();
    }


}
