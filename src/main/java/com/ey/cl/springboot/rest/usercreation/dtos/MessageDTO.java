package com.ey.cl.springboot.rest.usercreation.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class MessageDTO
        implements Serializable {

    private String mensaje;
}
