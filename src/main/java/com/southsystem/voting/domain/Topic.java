package com.southsystem.voting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "TOPICS")
public class Topic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer voteYes = 0;
    private Integer voteNo = 0;
    private Integer votesTotal = 0;


    public Topic(String name) {
        this.name = name;
    }

    public Topic(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addVotesTotal(Integer value) {
        this.votesTotal+= value;
    }

    public void addVoteYes(Integer value) {
        this.voteYes+= value;
    }

    public void addVoteNo(Integer value) {
        this.voteNo+= value;
    }
}
