package com.southsystem.voting.dto.request;

import com.southsystem.voting.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicRequest {
    @NotNull
    private String name;

    public Topic ToTopic() {
        return new Topic(name);
    }

    public Topic toTopicUpdate(Topic topic) {
        topic.setName(name);
        return topic;
    }
}
