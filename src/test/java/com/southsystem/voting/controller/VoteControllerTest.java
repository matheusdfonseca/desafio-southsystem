package com.southsystem.voting.controller;

import com.southsystem.voting.dto.response.VoteResponse;
import com.southsystem.voting.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import static com.southsystem.voting.util.VoteCreator.*;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
class VoteControllerTest {
    @InjectMocks
    private VoteController voteController;
    @Mock
    private VoteService voteServiceMock;

    private final UriComponentsBuilder builder =  UriComponentsBuilder.newInstance();
    @BeforeEach
    void setUp() {
        BDDMockito.when(voteServiceMock.vote(ArgumentMatchers.any()))
                .thenReturn(createValidVote());
    }

    @Test
    @DisplayName("Method Vote - Save Vote")
    void vote_ReturnsVoteResponse_WhenSuccessful() {
        ResponseEntity<VoteResponse> vote = voteController.vote(createVoteRequest(), builder);
        assertThat(vote.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(vote.getBody())
                .isNotNull()
                .isEqualTo(new VoteResponse(createVote()));
    }
}
