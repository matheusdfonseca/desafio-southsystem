package com.southsystem.voting.service;

import com.southsystem.voting.domain.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getAll();
    Topic getById(Long id);
    Topic save(Topic topic);
    void delete(Topic topic);
}
