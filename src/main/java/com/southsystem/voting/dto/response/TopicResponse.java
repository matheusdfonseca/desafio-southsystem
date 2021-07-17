package com.southsystem.voting.dto.response;

import com.southsystem.voting.domain.Topic;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data @NoArgsConstructor
public class TopicResponse {
    private String name;

    public TopicResponse(Topic topic){
        this.name = topic.getName();
    }

    public static List<TopicResponse> toList(List<Topic> topics){
        return topics.stream().map(TopicResponse::new).collect(Collectors.toList());
    }
}
