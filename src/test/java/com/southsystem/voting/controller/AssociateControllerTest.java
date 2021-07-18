package com.southsystem.voting.controller;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.dto.request.AssociateRequest;
import com.southsystem.voting.dto.response.AssociateResponse;
import com.southsystem.voting.service.AssociateService;
import com.southsystem.voting.util.AssociateCreator;
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

import java.util.Arrays;
import java.util.List;

import static com.southsystem.voting.util.AssociateCreator.*;
import static com.southsystem.voting.util.AssociateCreator.createUpdatedAssociateRequest;
import static com.southsystem.voting.util.AssociateCreator.createValidAssociate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


@ExtendWith(SpringExtension.class)
class AssociateControllerTest {
    @InjectMocks
    private AssociateController associateController;

    @Mock
    private AssociateService associateServiceMock;

    private final UriComponentsBuilder builder =  UriComponentsBuilder.newInstance();

    @BeforeEach
    void setUp(){
        List<Associate> listGetAll = Arrays.asList(createValidAssociate());

        BDDMockito.when(associateServiceMock.getAll())
                .thenReturn(listGetAll);

        BDDMockito.when(associateServiceMock.getById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidAssociate());

        BDDMockito.when(associateServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(createValidAssociate());

    }

    @Test
    @DisplayName("Method Get All - Find All Associates")
    void getAll_ReturnsListOfAssociatesResponse_WhenSuccessful() {
        String expectedName= createValidAssociate().getName();

        List<AssociateResponse> associates = associateController.getAll().getBody();

        assertThat(associates)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(associates.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Method Save - Save Associates")
    void save_ReturnsAssociateResponse_WhenSuccesful() {
        AssociateRequest associateRequest = createAssociateRequest();
        AssociateResponse associateResponse = associateController.save(associateRequest, builder).getBody();

        assertThat(associateResponse)
                .isNotNull()
                .isEqualTo(new AssociateResponse(createValidAssociate()));

    }

    @Test
    @DisplayName("Method Get By Id - Find Associate By Id")
    void getById_ReturnsAssociate_WhenSuccessful() {
        Long expectedId = createValidAssociate().getId();
        AssociateResponse associateResponse = associateController.getById(1L).getBody();

        assertThat(associateResponse.getId()).isEqualTo(expectedId);
        assertThat(associateResponse).isNotNull();

    }

    @Test
    @DisplayName("Method Update - Update Associate By Id")
    void update_ReturnsAssociate_WhenSuccessful() {
        AssociateRequest associateRequest = createUpdatedAssociateRequest();
        ResponseEntity<AssociateResponse> associate = associateController.update(1L, associateRequest);

        assertThatCode(() -> associateController.update(1L, associateRequest)).doesNotThrowAnyException();
        assertThat(associate.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(associate).isNotNull();
    }

    @Test
    @DisplayName("Method Delete - Delete Associate By Id")
    void delete_RemovesAssociate_WhenSuccessful() {
        ResponseEntity<?> associate = associateController.delete(1L);

        assertThatCode(() -> associateController.delete(1L)).doesNotThrowAnyException();
        assertThat(associate.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(associate).isNotNull();
    }
}
