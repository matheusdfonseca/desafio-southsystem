package com.southsystem.voting.service;

import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.domain.Vote;

import java.util.List;
import java.util.Set;

public interface TopicService {
    List<Topic> getAll();
    Topic getById(Long id);
    Topic save(Topic topic);
    void delete(Topic topic);
    void addVotes(Set<Vote> votes, Topic topic);
}
