package com.southsystem.voting.controller;

import com.southsystem.voting.domain.Session;
import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.dto.response.ResultVotingResponse;
import com.southsystem.voting.service.SessionService;
import com.southsystem.voting.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.callback.TextInputCallback;

@RestController @RequestMapping("/result")
public class ResultVotingController {
    @Autowired
    TopicService topicService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/{id}")
    public ResponseEntity<ResultVotingResponse> a(@PathVariable Long id){
        Session session = sessionService.getById(id);
        Topic topic =  session.getTopic();
        topicService.addVotes(session.getVotes(), topic);
        return ResponseEntity.ok(new ResultVotingResponse(topic));
    }
}
