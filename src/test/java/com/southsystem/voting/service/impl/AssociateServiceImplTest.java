package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Associate;
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

import java.util.List;
import java.util.Optional;

import static com.southsystem.voting.util.AssociateCreator.*;
import static org.assertj.core.api.Assertions.*;


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
        BDDMockito.when(associateRepositoryMock.findAll())
                .thenReturn(createListValidAssociate());

        BDDMockito.when(associateRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidOptionalAssociate());

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
        Associate associate = associateService.getById(1L);

        assertThat(associate)
                .isNotNull();
        assertThat(associate.getId())
                .isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Method Get by Id - Associate By Id Not Found")
    void getById_ThrowVotingException_WhenAssociateNotFound() {
        BDDMockito.when(associateRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(VotingException.class)
                .isThrownBy(() -> associateService.getById(1L))
                .withMessage("Not found Associate");

    }

    @Test
    @DisplayName("Method Save - Save Associate")
    void save_ReturnsAssociate_WhenSuccessfull() {
        Associate expectedAssociate = createValidAssociate();

        Associate associate = associateService.save(createAssociate());

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
        Boolean permissionVote = associateService.isPermissionVote(createValidAssociate());

        assertThat(permissionVote)
                .isTrue()
                .isNotNull();
    }

    @Test
    @DisplayName("Method Is Permission Vote - Associate can't vote")
    void isPermissionVote_ThrowVotingException_WhenAssociateUnableToVote() {
        BDDMockito.when(permissionVoteServiceMock.getStatus(ArgumentMatchers.any()))
                .thenReturn("UNABLE_TO_VOTE");

        assertThatExceptionOfType(VotingException.class)
                .isThrownBy(() -> associateService.isPermissionVote(createValidAssociate()))
                .withMessage("Not allowed to vote");;


    }
}
