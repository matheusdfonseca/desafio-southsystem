package com.southsystem.voting.controller;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.dto.request.AssociateRequest;
import com.southsystem.voting.dto.request.TopicRequest;
import com.southsystem.voting.dto.response.AssociateResponse;
import com.southsystem.voting.dto.response.TopicResponse;
import com.southsystem.voting.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController @RequestMapping("/topics")
public class TopicController {
    @Autowired
    TopicService service;

    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAll(){
        return ResponseEntity.ok(TopicResponse.toList(service.getAll()));
    }

    @PostMapping
    public  ResponseEntity<TopicResponse> save(@RequestBody @Valid TopicRequest request, UriComponentsBuilder uriBuilder){
        Topic topic = service.save(request.ToTopic());
        URI uri = uriBuilder.path("/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicResponse(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getById(@PathVariable Long id) {
        Topic topic = service.getById(id);
        return ResponseEntity.ok(new TopicResponse(topic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> update(@PathVariable Long id, @RequestBody @Valid TopicRequest request) {
        Topic topic = service.getById(id);
        Topic savedTopic = service.save(request.toTopicUpdate(topic));
        return ResponseEntity.ok(new TopicResponse(savedTopic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Topic topic = service.getById(id);
        service.delete(topic);
        return ResponseEntity.noContent().build();
    }
}
