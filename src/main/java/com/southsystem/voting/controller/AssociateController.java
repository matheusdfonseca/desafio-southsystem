package com.southsystem.voting.controller;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.dto.request.AssociateRequest;
import com.southsystem.voting.dto.response.AssociateResponse;
import com.southsystem.voting.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/associates")
public class AssociateController {
    @Autowired
    AssociateService service;

    @GetMapping
    public ResponseEntity<List<AssociateResponse>> getAll() {
        return ResponseEntity.ok(AssociateResponse.toListAssociateResponse(service.getAll()));
    }

    @PostMapping
    public ResponseEntity<AssociateResponse> save(@RequestBody AssociateRequest request, UriComponentsBuilder uriBuilder) {
        Associate associate = service.save(request.toAssociate());
        URI uri = uriBuilder.path("/{id}").buildAndExpand(associate.getId()).toUri();
        return ResponseEntity.created(uri).body(new AssociateResponse(associate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociateResponse> getById(@PathVariable Long id) {
        Associate associate = service.getById(id);
        return ResponseEntity.ok(new AssociateResponse(associate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociateResponse> update(@PathVariable Long id, @RequestBody @Valid AssociateRequest request) {
        Associate associate = service.getById(id);
        Associate savedAssociate = service.save(request.toAssociateUpdate(associate));
        return ResponseEntity.ok(new AssociateResponse(savedAssociate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Associate associate = service.getById(id);
        service.delete(associate);
        return ResponseEntity.noContent().build();

    }


}
