package com.southsystem.voting.controller;

import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.dto.request.TopicRequest;
import com.southsystem.voting.dto.response.TopicResponse;
import com.southsystem.voting.service.TopicService;
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

import java.util.List;

import static com.southsystem.voting.util.TopicCreator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)
class TopicControllerTest {
    @InjectMocks
    private TopicController topicController;
    @Mock
    private TopicService topicServiceMock;

    private final UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

    @BeforeEach
    void setUp() {
        BDDMockito.when(topicServiceMock.getAll())
                .thenReturn(createListValidTopic());

        BDDMockito.when(topicServiceMock.getById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidTopic());

        BDDMockito.when(topicServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(createValidTopic());
    }

    @Test
    @DisplayName("Method Get All - Find All Topics")
    void getAll_ReturnsListOfTopicsResponse_WhenSuccessful() {
        Topic expectedTopic = createValidTopic();

        List<TopicResponse> topicResponses = topicController.getAll().getBody();

        assertThat(topicResponses)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertThat(topicResponses.get(0)).isEqualTo(new TopicResponse(expectedTopic));
    }

    @Test
    @DisplayName("Method Save - Save Topic")
    void save_ReturnsTopicResponse_WhenSuccesful() {
        TopicRequest topicRequest = createTopicRequest();
        TopicResponse topicResponse = topicController.save(topicRequest, builder).getBody();

        assertThat(topicResponse)
                .isNotNull()
                .isEqualTo(new TopicResponse(createValidTopic()));
    }

    @Test
    @DisplayName("Method Get By Id - Find Topic By Id")
    void getById_ReturnsAssociate_WhenSuccessful() {
        Topic expectedTopic = createValidTopic();
        TopicResponse topicResponse = topicController.getById(1L).getBody();

        assertThat(topicResponse).isEqualTo(new TopicResponse(expectedTopic));
        assertThat(topicResponse).isNotNull();

    }

    @Test
    @DisplayName("Method Update - Update Topic By Id")
    void update_ReturnsAssociate_WhenSuccessful() {
        TopicRequest topicRequest = createUpdatedTopicRequest();
        ResponseEntity<TopicResponse> topic = topicController.update(1L, topicRequest);

        assertThatCode(() -> topicController.update(1L, topicRequest)).doesNotThrowAnyException();
        assertThat(topic.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(topic).isNotNull();
    }

    @Test
    @DisplayName("Method Delete - Delete Topic By Id")
    void delete_RemovesAssociate_WhenSuccessful() {
        ResponseEntity<?> topic = topicController.delete(1L);

        assertThatCode(() -> topicController.delete(1L)).doesNotThrowAnyException();
        assertThat(topic.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(topic).isNotNull();
    }
}
