package com.southsystem.voting.util;

import com.southsystem.voting.domain.Associate;
import com.southsystem.voting.dto.request.AssociateRequest;

public class AssociateCreator {

    public static Associate createAssociate(){
        return new Associate("Name Example", "1111111111");
    }

    public static Associate createValidAssociate(){
        return new Associate(1L,"Name Example", "1111111111");
    }

    public static Associate createValidUpdatedAssociate(){
        return new Associate(1L,"Name Example Updated", "2222222222");
    }

    public static AssociateRequest createUpdatedAssociateRequest(){
        return new AssociateRequest("Name Example Updated", "2222222222");
    }

    public static AssociateRequest createAssociateRequest(){
        return new AssociateRequest("Name Example", "1111111111");
    }

}
