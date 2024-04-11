package com.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RentalDTO {
    private Long id;
    private String name;
    private Integer surface;
    private Integer price;
    //TODO: Check if string is good type for picture url/uri
    private String picture;
    private String description;
    @JsonProperty("owner_id")
    private Long ownerId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
}
