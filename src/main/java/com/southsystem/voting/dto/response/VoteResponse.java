package com.southsystem.voting.dto.response;

import com.southsystem.voting.domain.Vote;
import lombok.Data;

@Data
public class VoteResponse {
    private String vote;
    private String associate;
    private String topic;

    public VoteResponse(Vote vote) {
        this.vote = vote.getVote().toString();
        this.associate = vote.getAssociate().getName();
        this.topic = vote.getSession().getTopic().getName();
    }
}
