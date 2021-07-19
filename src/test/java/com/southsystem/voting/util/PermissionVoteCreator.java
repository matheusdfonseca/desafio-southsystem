package com.southsystem.voting.util;

import com.southsystem.voting.dto.response.PermissionVoteResponse;

public class PermissionVoteCreator {
    public static PermissionVoteResponse createPermissionVoteResponse(){
        return new PermissionVoteResponse("ABLE_TO_VOTE");
    }
}
