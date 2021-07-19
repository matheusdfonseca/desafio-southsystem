package com.southsystem.voting.domain;

import com.southsystem.voting.enums.VoteValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "votes")
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name ="associate_id")
    private Associate associate;
    @ManyToOne @JoinColumn(name = "session_id")
    private Session session;
    @Enumerated(EnumType.STRING)
    private VoteValue vote;

    public Vote(VoteValue vote, Associate associate, Session session) {
        this.vote = vote;
        this.associate = associate;
        this.session = session;
    }
}
