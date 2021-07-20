package com.southsystem.voting.service.impl;

import com.southsystem.voting.service.PermissionVoteService;
import com.southsystem.voting.service.UserCpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionVoteServiceImpl implements PermissionVoteService {
    @Autowired
    UserCpfService service;

    /**
     * Método reponsável por capturar o status vindo do método getPermissionVote da Interface UserCpfService.
     * @param cpf
     * @return
     */
    public String getStatus(String cpf){
        return service.getPermissionVote(cpf).getStatus();
    }
}
