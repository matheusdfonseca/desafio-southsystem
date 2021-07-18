package com.southsystem.voting.controller;

import com.southsystem.voting.domain.Session;
import com.southsystem.voting.dto.request.SessionRequest;
import com.southsystem.voting.dto.response.SessionResponse;
import com.southsystem.voting.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController @RequestMapping("/sessions")
public class SessionController {
    @Autowired
    SessionService service;

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getAll(){
        return ResponseEntity.ok(SessionResponse.toList(service.getAll()));
    }

    @PostMapping("/start")
    public ResponseEntity<SessionResponse> start(@RequestBody @Valid SessionRequest request, UriComponentsBuilder uriBuilder){
        Session session = service.open(request);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(session.getId()).toUri();
        return ResponseEntity.created(uri).body(new SessionResponse(session));
    }
}
