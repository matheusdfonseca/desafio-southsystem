package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.enums.PermissionVoteEnum;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.AssociateRepository;
import com.southsystem.voting.service.AssociateService;
import com.southsystem.voting.service.PermissionVoteService;
import com.southsystem.voting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateServiceImpl implements AssociateService {
    @Autowired
    AssociateRepository repository;
    @Autowired
    PermissionVoteService permissionVoteService;
    @Autowired
    VoteService voteService;

    public List<Associate> getAll() {
        return repository.findAll();
    }

    @Override
    public Associate getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new VotingException("Not found Associate"));
    }

    @Override
    public Associate save(Associate associate) {
        return repository.save(associate);
    }

    @Override
    public void delete(Associate associate) {
        repository.delete(associate);
    }

    public Boolean  isPermissionVote(Associate associate) {
        String cpf = associate.getCpf();
        PermissionVoteEnum status = PermissionVoteEnum.valueOf(permissionVoteService.getStatus(cpf));

        if (status.equals(PermissionVoteEnum.UNABLE_TO_VOTE))
            throw new VotingException("Not allowed to vote");

        return true;

    }


}
