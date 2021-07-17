package com.southsystem.voting.dto.response;

import com.southsystem.voting.domain.Associate;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AssociateResponse {
    private Long id;
    private String name;
    private String cpf;

    public AssociateResponse(Associate associate){
        this.id = associate.getId();
        this.name = associate.getName();
        this.cpf = associate.getCpf();
    }

    public static List<AssociateResponse> toListAssociateResponse(List<Associate> associates){
        return associates
                .stream()
                .map(AssociateResponse::new)
                .collect(Collectors.toList());
    }
}
