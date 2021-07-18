package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.TopicRepository;
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
import static com.southsystem.voting.util.TopicCreator.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TopicServiceImplTest {
    @InjectMocks
    private TopicServiceImpl  topicService;
    @Mock
    private TopicRepository topicRepositoryMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(topicRepositoryMock.findAll())
                .thenReturn(createListValidTopic());

        BDDMockito.when(topicRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidOptionalTopic());

        BDDMockito.when(topicRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(createValidTopic());
    }

    @Test
    @DisplayName("Method Get All - Find All Topics")
    void getAll_ReturnsListOfTopics_WhenSuccessful() {
        String expectedName= createValidTopic().getName();

        List<Topic> topics = topicService.getAll();

        assertThat(topics)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(topics.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Method Get by Id - Topic By Id")
    void getById_ReturnsTopicById_WhenSuccessful() {
        Long expectedId = createValidAssociate().getId();
        Topic topic = topicService.getById(1L);

        assertThat(topic)
                .isNotNull();
        assertThat(topic.getId())
                .isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Method Get by Id - Topic By Id Not Found")
    void getById_ThrowVotingException_WhenTopicNotFound() {
        BDDMockito.when(topicRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(VotingException.class)
                .isThrownBy(() -> topicService.getById(1L))
                .withMessage("Not found Topic");

    }

    @Test
    @DisplayName("Method Save - Save Topic")
    void save_ReturnsTopic_WhenSuccessfull() {
        Topic expectedTopic = createValidTopic();

        Topic topic = topicService.save(createTopic());

        assertThat(topic)
                .isNotNull()
                .isEqualTo(expectedTopic);
    }

    @Test
    @DisplayName("Method delete - Delete Topic")
    void delete_NoReturns_WhenSuccessful() {
        assertThatCode(() -> topicService.delete(createValidTopic())).doesNotThrowAnyException();
    }

}
