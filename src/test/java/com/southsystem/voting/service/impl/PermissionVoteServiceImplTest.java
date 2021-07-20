package com.southsystem.voting.service.impl;

import com.southsystem.voting.service.UserCpfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.southsystem.voting.util.PermissionVoteCreator.createPermissionVoteResponse;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PermissionVoteServiceImplTest {
    @InjectMocks
    PermissionVoteServiceImpl permissionVoteService;
    @Mock
    UserCpfService userCpfServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userCpfServiceMock.getPermissionVote(ArgumentMatchers.anyString()))
                .thenReturn(createPermissionVoteResponse());
    }

    @Test
    @DisplayName("methos Get Status - Vote Permission Status ")
    void getStatus_ReturnsStatusInString_WhenSuccessful() {
        String expectedStatus = "ABLE_TO_VOTE";
        String cpf = "1111111111";
        String status = permissionVoteService.getStatus(cpf);

        assertThat(status)
                .isEqualTo(expectedStatus)
                .isNotNull();
    }
}
