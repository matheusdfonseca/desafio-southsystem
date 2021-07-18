package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.dto.response.AssociateResponse;
import com.southsystem.voting.enums.PermissionVoteEnum;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.AssociateRepository;
import com.southsystem.voting.service.PermissionVoteService;
import com.southsystem.voting.service.VoteService;
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

import static com.southsystem.voting.util.AssociateCreator.createAssociate;
import static com.southsystem.voting.util.AssociateCreator.createValidAssociate;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class AssociateServiceImplTest {
    @InjectMocks
    private AssociateServiceImpl associateService;

    @Mock
    private AssociateRepository associateRepositoryMock;
    @Mock
    private PermissionVoteService permissionVoteServiceMock;
    @Mock
    private VoteService voteServiceMock;

    @BeforeEach
    void setUp(){
        List<Associate> listGetAll = Arrays.asList(createValidAssociate());

        Optional<Associate> associateOptional = Optional.of(createValidAssociate());

        BDDMockito.when(associateRepositoryMock.findAll())
                .thenReturn(listGetAll);

        BDDMockito.when(associateRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(associateOptional);

        BDDMockito.when(associateRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(createValidAssociate());

        BDDMockito.when(permissionVoteServiceMock.getStatus(ArgumentMatchers.any()))
                .thenReturn("ABLE_TO_VOTE");

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
    @DisplayName("Method Get by Id - Associate By Id")
    void getById_ReturnsAssociateById_WhenSuccessful() {
        Long expectedId = createValidAssociate().getId();
        Optional<Associate> associateOptional = associateRepositoryMock.findById(1L);

        assertThat(associateOptional)
                .isNotNull()
                .isNotEmpty();
        assertThat(associateOptional.get().getId())
                .isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Method Get by Id - Associate By Id Not Found")
    void getById_ThrowVotingException_WhenAssociateNotFound() {
        BDDMockito.when(associateRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Optional<Associate> associateOptional = associateRepositoryMock.findById(1L);

        assertThat(associateOptional).isEmpty();
        assertThatExceptionOfType(VotingException.class)
                .isThrownBy(() -> associateService.getById(1L));

    }

    @Test
    @DisplayName("Method Save - Save Associate")
    void save_ReturnsAssociate_WhenSuccessfull() {
        Associate expectedAssociate = createValidAssociate();
        Associate associate = associateRepositoryMock.save(createAssociate());
        assertThat(associate)
                .isNotNull()
                .isEqualTo(expectedAssociate);
    }

    @Test
    @DisplayName("Method delete - Delete Associate")
    void delete_NoReturns_WhenSuccessful() {
        assertThatCode(() -> associateService.delete(createValidAssociate())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Method Is Permission Vote - Associate can vote")
    void isPermissionVote_ReturnsBoolean_WhenSuccessful() {
        String cpf = createValidAssociate().getCpf();
        String status = permissionVoteServiceMock.getStatus(cpf);
        PermissionVoteEnum value = PermissionVoteEnum.valueOf(status);

        assertThat(value)
                .isNotNull()
                .isEqualTo(PermissionVoteEnum.ABLE_TO_VOTE);
    }

    @Test
    @DisplayName("Method Is Permission Vote - Associate can't vote")
    void isPermissionVote_ThrowVotingException_WhenAssociateUnableToVote() {
        BDDMockito.when(permissionVoteServiceMock.getStatus(ArgumentMatchers.any()))
                .thenReturn("UNABLE_TO_VOTE");

        String cpf = createValidAssociate().getCpf();
        String status = permissionVoteServiceMock.getStatus(cpf);
        PermissionVoteEnum value = PermissionVoteEnum.valueOf(status);

        assertThat(value)
                .isNotNull()
                .isEqualTo(PermissionVoteEnum.UNABLE_TO_VOTE);

        assertThatExceptionOfType(VotingException.class)
                .isThrownBy(() -> associateService.isPermissionVote(createValidAssociate()));


    }
}
