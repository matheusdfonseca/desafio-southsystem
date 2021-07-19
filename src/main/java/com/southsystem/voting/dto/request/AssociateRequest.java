package com.southsystem.voting.dto.request;

import com.southsystem.voting.domain.Associate;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data  @AllArgsConstructor
public class AssociateRequest {
    @NotNull
    private String name;
    @NotNull
    private String cpf;

    public Associate toAssociate(){
        return new Associate(name, cpf);
    }

    public Associate toAssociateUpdate(Associate associate) {
        associate.setCpf(cpf);
        associate.setName(name);
        return associate;
    }
}
