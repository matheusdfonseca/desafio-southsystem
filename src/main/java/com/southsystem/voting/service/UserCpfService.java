package com.southsystem.voting.service;

import com.southsystem.voting.dto.response.PermissionVoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${url.users.cpf}", name = "validationCpf")
public interface UserCpfService {

    @GetMapping("/{cpf}")
    PermissionVoteResponse getPermissionVote(@PathVariable String cpf);
}
