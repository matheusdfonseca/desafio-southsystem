package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.domain.Session;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.SessionRepository;
import com.southsystem.voting.service.TopicService;
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
import static com.southsystem.voting.util.SessionCreator.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class SessionServiceImplTest {
    @InjectMocks
    private SessionServiceImpl sessionService;
    @Mock
    private SessionRepository sessionRepositoryMock;
    @Mock
    private TopicService topicServiceMock;

    @BeforeEach
    void setUp(){
        
        BDDMockito.when(sessionRepositoryMock.findAll())
                .thenReturn(createListValidSession());

        BDDMockito.when(sessionRepositoryMock.findAllByIsClosedIsFalseAndAndTimeEndIsAfter(ArgumentMatchers.any()))
                .thenReturn(createListValidSession());

        BDDMockito.when(sessionRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidOptionalSession());

        BDDMockito.when(sessionRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(createValidSession());

        BDDMockito.when(topicServiceMock.getById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidSession().getTopic());
    }
    
    @Test
    @DisplayName("Method Get All - Find All Sessions")
    void getAll_ReturnsListOfSession_WhenSuccessful() {
        Session expectedSession = createValidSession();

        List<Session> sessions = sessionService.getAll();

        assertThat(sessions)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(sessions.get(0)).isEqualTo(expectedSession);
    }

    @Test
    @DisplayName("Method Get All - Find All Sessions Not Closed")
    void getAllNotClosed_ReturnsListOfSessionNotClosed_WhenSuccessful() {
        Session expectedSession = createValidSession();
        List<Session> sessions = sessionService.getAll();

        assertThat(sessions)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertThat(sessions.get(0).getIsClosed())
                .isFalse();
    }

    @Test
    @DisplayName("Method Open - Open Session")
    void open_ReturnsSession_WhenSuccessful() {
        Session expectedSession = createValidSession();
        Session session = sessionService.open(createSessionRequest());

        assertThat(session)
                .isNotNull()
                .isEqualTo(expectedSession);
    }

    @Test
    @DisplayName("Method Get by Id - Session By Id")
    void getById_ReturnsSessionById_WhenSuccessful() {
        Long expectedId = createValidSession().getId();
        Session session = sessionService.getById(1L);

        assertThat(session)
                .isNotNull();

        assertThat(session.getId())
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Method Get by Id - Session By Id Not Found")
    void getById_ThrowVotingException_WhenSessionNotFound() {
        BDDMockito.when(sessionRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(VotingException.class)
                .isThrownBy(() -> sessionService.getById(1L))
                .withMessage("Not found session");

    }

    @Test
    @DisplayName("Method Save - Save Session")
    void save() {
        Session expectedSession = createValidSession();
        Session session = sessionService.save(createSession());

        assertThat(session)
                .isNotNull()
                .isEqualTo(expectedSession);
    }

    @Test
    @DisplayName("Method delete - Delete Session")
    void delete_NoReturns_WhenSuccessful() {
        assertThatCode(() -> sessionService.delete(createValidSession())).doesNotThrowAnyException();
    }
}
