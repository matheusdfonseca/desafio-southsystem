package com.southsystem.voting.controller;

import com.southsystem.voting.domain.Vote;
import com.southsystem.voting.dto.request.VoteRequest;
import com.southsystem.voting.dto.response.VoteResponse;
import com.southsystem.voting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    VoteService service;

    @PostMapping
    public ResponseEntity<VoteResponse> vote(@RequestBody @Valid VoteRequest request, UriComponentsBuilder uriBuilder) {
        Vote vote = service.vote(request);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uri).body(new VoteResponse(vote));
    }
}
