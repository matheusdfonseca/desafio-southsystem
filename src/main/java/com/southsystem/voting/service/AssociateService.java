package com.southsystem.voting.service;

import com.southsystem.voting.domain.Associate;

import java.util.List;

public interface AssociateService {
    List<Associate> getAll();
    Associate getById(Long id);
    Associate save(Associate associate);
    void delete(Associate associate);
    Boolean isPermissionVote(Associate associate);
}
