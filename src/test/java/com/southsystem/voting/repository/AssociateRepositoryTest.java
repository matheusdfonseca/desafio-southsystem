package com.southsystem.voting.repository;


import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.util.AssociateCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Associate repository")
@ActiveProfiles("test")
class AssociateRepositoryTest {
    @Autowired
    private AssociateRepository repository;

    @Test
    void save_PersistAssociate_WhenSuccessful(){
        Associate associate = AssociateCreator.createAssociate();
        Associate associateSaved = repository.save(associate);

        Assertions.assertThat(associateSaved).isNotNull();
        Assertions.assertThat(associateSaved.getId()).isNotNull();
        Assertions.assertThat(associateSaved.getName()).isEqualTo(associate.getName());
        Assertions.assertThat(associateSaved.getCpf()).isEqualTo(associate.getCpf());
    }

    @Test
    void save_UpdateAssociate_WhenSuccessful(){
        Associate associate = AssociateCreator.createAssociate();
        Associate associateSaved = repository.save(associate);
        associateSaved.setName("Name Example Update");
        associateSaved.setCpf("222222222");
        Associate associateUpdated = repository.save(associateSaved);

        Assertions.assertThat(associateSaved).isNotNull();
        Assertions.assertThat(associateSaved.getId()).isNotNull();
        Assertions.assertThat(associateUpdated.getName()).isEqualTo(associateSaved.getName());
        Assertions.assertThat(associateUpdated.getCpf()).isEqualTo(associateSaved.getCpf());
    }

    @Test
    void delete_RemovesAssociate_WhenSuccessful(){
        Associate associate = AssociateCreator.createAssociate();
        Associate associateSaved = repository.save(associate);
        repository.delete(associateSaved);

        Optional<Associate> associateOptional = repository.findById(associateSaved.getId());
        Assertions.assertThat(associateOptional.isPresent()).isFalse();
    }


    @Test
    void find_FindAssociateById_WhenSuccessful(){
        Associate associate = AssociateCreator.createAssociate();
        Associate associateSaved = repository.save(associate);

        Optional<Associate> associateOptional = repository.findById(associateSaved.getId());
        Assertions.assertThat(associateOptional.isPresent()).isTrue();
    }

    @Test
    void save_ThrowsConstraintViolationException_WhenNameOrCpfIsEmpty(){
        Associate associate = AssociateCreator.createAssociate();
        Associate associateSaved = repository.save(associate);

        Assertions.assertThat(associateSaved).isNotNull();
        Assertions.assertThat(associateSaved.getId()).isNotNull();
        Assertions.assertThat(associateSaved.getName()).isEqualTo(associate.getName());
        Assertions.assertThat(associateSaved.getCpf()).isEqualTo(associate.getCpf());
    }



}
