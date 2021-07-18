package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.TopicRepository;
import com.southsystem.voting.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepository repository;


    @Override
    public List<Topic> getAll() {
        return repository.findAll();
    }

    @Override
    public Topic getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new VotingException("Not found Topic"));
    }

    @Override
    public Topic save(Topic topic) {
        return repository.save(topic);
    }


    @Override
    public void delete(Topic topic) {
        repository.delete(topic);
    }
}
