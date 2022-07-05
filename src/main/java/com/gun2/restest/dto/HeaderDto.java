package com.gun2.restest.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class HeaderDto implements Serializable {
    private final Long id;
    private final String key;
    private final String value;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updateAt;
}