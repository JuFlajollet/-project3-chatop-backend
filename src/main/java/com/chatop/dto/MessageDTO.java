package com.chatop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private Long userId;
    private Long rentalId;
    private String message;
}
