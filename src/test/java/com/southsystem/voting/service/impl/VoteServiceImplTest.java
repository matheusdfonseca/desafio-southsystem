package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Vote;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.VoteRepository;
import com.southsystem.voting.service.AssociateService;
import com.southsystem.voting.service.SessionService;
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

import static com.southsystem.voting.util.AssociateCreator.createValidAssociate;
import static com.southsystem.voting.util.SessionCreator.createValidSession;
import static com.southsystem.voting.util.SessionCreator.createValidSessionClosed;
import static com.southsystem.voting.util.TopicCreator.createValidTopic;
import static com.southsystem.voting.util.VoteCreator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
class VoteServiceImplTest{
    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private VoteRepository voteRepositoryMock;
    @Mock
    private SessionService sessionServiceMock;
    @Mock
    private AssociateService associateServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(voteRepositoryMock.findAll())
                .thenReturn(createListValidVote());

        BDDMockito.when(voteRepositoryMock.findAllBySession_Topic(ArgumentMatchers.any()))
                .thenReturn(createListValidVote());

        BDDMockito.when(voteRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(createValidVote());

        BDDMockito.when(sessionServiceMock.getById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidSession());

        BDDMockito.when(associateServiceMock.getById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidAssociate());

        BDDMockito.when(associateServiceMock.isPermissionVote(ArgumentMatchers.any()))
                .thenReturn(Boolean.TRUE);

        BDDMockito.when(voteRepositoryMock.findByAssociateAndSession_Topic(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

    }

    @Test
    @DisplayName("Method Get All - Find All Votes")
    void getAll_ReturnsListOfVotes_WhenSuccessful() {
        Vote expectedVote = createValidVote();
        List<Vote> votes = voteService.getAll();

        assertThat(votes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertThat(votes.get(0))
                .isEqualTo(expectedVote);
    }

    @Test
    @DisplayName("Method Get All By Topic - Find All Votes By Topic")
    void getAllByTopic_ReturnsListOfVotes_WhenSuccessful() {
        Vote expectedVote = createValidVote();
        List<Vote> votes = voteService.getAllByTopic(createValidTopic());
        assertThat(votes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertThat(votes.get(0))
                .isEqualTo(expectedVote);
    }

    @Test
    @DisplayName("Method Save - Save Vote")
    void save_ReturnsVote_WhenSuccessfull() {
        Vote expectedVote = createValidVote();
        Vote vote = voteService.save(createVote());

        assertThat(vote)
                .isNotNull()
                .isEqualTo(expectedVote);
    }

    @Test
    @DisplayName("Method Vote - Compute Vote")
    void vote_ReturnsVote_WhenSucessful() {
        Vote expectedVote = createValidVote();
        Vote vote = voteService.vote(createVoteRequest());
        assertThat(vote)
                .isNotNull()
                .isEqualTo(expectedVote);

    }

    @Test
    @DisplayName("Method Vote - Session is closed")
    void vote_ThrowVotingException_WhenSessionClosed() {
        BDDMockito.when(sessionServiceMock.getById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidSessionClosed());

        assertThatExceptionOfType(VotingException.class)
                .isThrownBy(() -> voteService.vote(createVoteRequest()))
                .withMessage("closed session");

    }

    @Test
    @DisplayName("Method Validate Vote - Vote Validation")
    void validateVote_ReturnsTrue_WhenSuccessful() {
        Boolean validateVote = voteService.validateVote(createValidAssociate(), createValidSession());
        assertThat(validateVote)
                .isTrue();
    }

    @Test
    @DisplayName("Method Is Associate Voted - Associate Voted In Session")
    void isAssociateVoted_ReturnsFalse_WhenSuccessful() {
        Boolean isAssociateVoted = voteService.isAssociateVoted(createValidAssociate(), createValidSession());
        assertThat(isAssociateVoted)
                .isFalse();
    }

    @Test
    @DisplayName("Method Is Associate Voted - Associate Voted In Session")
    void isAssociateVoted_ThrowVotingException_WhenAssociateVotedInSessionIsTrue() {
        BDDMockito.when(voteRepositoryMock.findByAssociateAndSession_Topic(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(createValidOptionalVote());

        assertThatExceptionOfType(VotingException.class)
                .isThrownBy(() ->voteService.isAssociateVoted(createValidAssociate(), createValidSession()))
                .withMessage("Associate has already voted for this topic");
    }


}
