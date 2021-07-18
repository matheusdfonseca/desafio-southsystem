package com.southsystem.voting.service;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.domain.Session;
import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.domain.Vote;
import com.southsystem.voting.dto.request.VoteRequest;

import java.util.List;

public interface VoteService {
    List<Vote> getAll();
    List<Vote> getAllByTopic(Topic topic);
    Vote save(Vote vote);
    Vote vote(VoteRequest request);
    Boolean validateVote(Associate associate, Session session);
    Boolean isAssociateVoted(Associate associate, Session session);
}
