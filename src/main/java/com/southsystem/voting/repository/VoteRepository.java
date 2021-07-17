package com.southsystem.voting.repository;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
   List<Vote> findAllBySession_Topic(Topic topic);
   Optional<Vote> findByAssociateAndSession_Topic(Associate associate, Topic topic);
}
