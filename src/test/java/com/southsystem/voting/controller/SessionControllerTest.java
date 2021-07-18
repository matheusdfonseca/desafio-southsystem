package com.southsystem.voting.controller;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.domain.Session;
import com.southsystem.voting.dto.response.AssociateResponse;
import com.southsystem.voting.dto.response.SessionResponse;
import com.southsystem.voting.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static com.southsystem.voting.util.AssociateCreator.createValidAssociate;
import static com.southsystem.voting.util.SessionCreator.createSessionRequest;
import static com.southsystem.voting.util.SessionCreator.createValidSession;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class SessionControllerTest {
    @InjectMocks
    private SessionController sessionController;
    @Mock
    private SessionService sessionServiceMock;

    private final UriComponentsBuilder builder =  UriComponentsBuilder.newInstance();

    @BeforeEach
    void setUp() {
        List<Session> listGetAll = Arrays.asList(createValidSession());

        BDDMockito.when(sessionServiceMock.getAll())
                .thenReturn(listGetAll);

        BDDMockito.when(sessionServiceMock.open(ArgumentMatchers.any()))
                .thenReturn(createValidSession());



    }

    @Test
    @DisplayName("Method Get All - Find All Sessions")
    void getAll_ReturnsListOfSessionResponse_WhenSuccessful() {
        SessionResponse expectedSession = new SessionResponse(createValidSession());

        List<SessionResponse> sessions = sessionController.getAll().getBody();

        assertThat(sessions)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(sessions.get(0)).isEqualTo(expectedSession);
    }

    @Test
    @DisplayName("Method start - Start session")
    void start_RetunrsSessionResponse_WhenSuccessfull() {
        SessionResponse expectedSessionResponse = new SessionResponse(createValidSession());
        SessionResponse sessionResponse = sessionController.start(createSessionRequest(), builder).getBody();
        assertThat(sessionResponse)
                .isNotNull()
                .isEqualTo(expectedSessionResponse);

    }
}
