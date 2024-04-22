package com.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RentalDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String name;
    private String surface;
    private String price;
    @JsonProperty(value = "picture", access = JsonProperty.Access.READ_ONLY)
    private String pictureUrl;
    private String description;
    @JsonProperty(value = "owner_id", access = JsonProperty.Access.READ_ONLY)
    private Integer ownerId;
    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    private String createdAt;
    @JsonProperty(value = "updated_at", access = JsonProperty.Access.READ_ONLY)
    private String updatedAt;
}
