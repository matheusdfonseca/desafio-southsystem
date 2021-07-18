package com.southsystem.voting.util;

import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.dto.request.TopicRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TopicCreator {
    public static Topic createTopic() {
        return new Topic("Name Example");
    }

    public static Topic createValidTopic() {
        return new Topic(1L, "Name Example");
    }

    public static List<Topic> createListValidTopic() {
        return Arrays.asList(createValidTopic());
    }

    public static Optional<Topic> createValidOptionalTopic(){
        return Optional.of(createValidTopic());
    }

    public static TopicRequest createTopicRequest() {
        return new TopicRequest("Name Example");
    }

    public static TopicRequest createUpdatedTopicRequest() {
        return new TopicRequest("Name Example Updated");
    }
}
