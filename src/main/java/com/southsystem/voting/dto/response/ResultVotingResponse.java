package com.southsystem.voting.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.southsystem.voting.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ResultVotingResponse {
    @JsonProperty("id_topic")
    private Long idTopic;
    @JsonProperty("total_votes_yes")
    private Integer voteYes;
    @JsonProperty("total_votes_no")
    private Integer voteNo;
    @JsonProperty("total_votes")
    private Integer totalVotes;

    public ResultVotingResponse(Topic topic){
        this.idTopic = topic.getId();
        this.voteYes = topic.getVoteYes();
        this.voteNo = topic.getVoteNo();
        this.totalVotes = topic.getVotesTotal();
    }
}
