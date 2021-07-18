package com.southsystem.voting.util;

import com.southsystem.voting.domain.Topic;

public class TopicCreator {
    public static Topic createTopic(){
        return new Topic("Name Example");
    }

    public static Topic createValidTopic(){
        return new Topic(1L, "Name Example");
    }
}
