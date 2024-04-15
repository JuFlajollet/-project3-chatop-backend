package com.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageDTO {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("rental_id")
    private Long rentalId;
    private String message;
}
