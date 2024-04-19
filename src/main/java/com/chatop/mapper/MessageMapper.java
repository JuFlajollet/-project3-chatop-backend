package com.chatop.mapper;

import com.chatop.dto.MessageDTO;
import com.chatop.model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageDTO toMessageDTO(Message message) {
        if(message == null){
            return null;
        }

        return MessageDTO.builder()
                .userId(message.getUserId())
                .rentalId(message.getRentalId())
                .message(message.getMessage())
                .build();
    }

    public Message toMessage(MessageDTO messageDTO){
        if(messageDTO == null){
            return null;
        }

        return Message.builder()
                .userId(messageDTO.getUserId())
                .rentalId(messageDTO.getRentalId())
                .message(messageDTO.getMessage())
                .build();
    }
}
