package com.riya.rediscachedapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class ResponseDTO {

    @NonNull
    private String name;

    @NonNull
    private int age;
}
