package com.southsystem.voting.util;

import com.southsystem.voting.domain.Vote;
import com.southsystem.voting.dto.request.VoteRequest;
import com.southsystem.voting.enums.VoteValue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.southsystem.voting.util.AssociateCreator.createValidAssociate;
import static com.southsystem.voting.util.SessionCreator.createValidSession;

public class VoteCreator {
    public static Vote createVote(){
        Vote vote = new Vote();
        vote.setVote(VoteValue.YES);
        vote.setAssociate(createValidAssociate());
        vote.setSession(createValidSession());
        return vote;
    }

    public static Vote createValidVote(){
        Vote vote = createVote();
        vote.setId(1L);
        return vote;
    }

    public static Optional<Vote> createValidOptionalVote(){
        Vote vote = createVote();
        vote.setId(1L);
        return Optional.of(vote);
    }

    public static List<Vote> createListValidVote(){
        return Arrays.asList(createValidVote());
    }

    public static VoteRequest createVoteRequest(){
        return new VoteRequest("YES", 1L, 1L);
    }
}
