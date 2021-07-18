package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.dto.response.AssociateResponse;
import com.southsystem.voting.repository.AssociateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.southsystem.voting.util.AssociateCreator.createValidAssociate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class AssociateServiceImplTest {
    @InjectMocks
    private AssociateServiceImpl associateService;
    @Mock
    private AssociateRepository associateRepository;

    @BeforeEach
    void setUp(){
        List<Associate> listGetAll = Arrays.asList(createValidAssociate());

        Optional<Associate> associateOptional = Optional.of(createValidAssociate());

        BDDMockito.when(associateRepository.findAll())
                .thenReturn(listGetAll);

        BDDMockito.when(associateRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(associateOptional);

        BDDMockito.when(associateRepository.save(ArgumentMatchers.any()))
                .thenReturn(createValidAssociate());

    }

    @Test
    @DisplayName("Method Get All - Find All Associates")
    void getAll_ReturnsListOfAssociates_WhenSuccessful() {
        String expectedName= createValidAssociate().getName();

        List<Associate> associates = associateService.getAll();

        assertThat(associates)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(associates.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void isPermissionVote() {
    }
}
