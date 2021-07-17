package com.southsystem.voting.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.domain.Session;
import com.southsystem.voting.domain.Vote;
import com.southsystem.voting.enums.VoteValue;
import com.southsystem.voting.exception.VotingException;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VoteRequest {
    @NotNull
    private String vote;
    @NotNull @JsonProperty("associate_id")
    private Long associate;
    @NotNull @JsonProperty("session_id")
    private Long session;

    public Vote toVote(Associate associate, Session session){
        if(!vote.equals("YES") && !vote.equals("NO") )
            throw new VotingException("Invalid vote");

        return new Vote(VoteValue.valueOf(vote), associate, session);
    }
}
