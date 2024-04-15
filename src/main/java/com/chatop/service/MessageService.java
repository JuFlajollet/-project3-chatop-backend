package com.chatop.service;

import com.chatop.dto.MessageDTO;
import com.chatop.mapper.MessageMapper;
import com.chatop.model.Message;
import com.chatop.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;

    public void createMessage(MessageDTO newMessageDTO) {
        Message newMessage = messageMapper.toMessage(newMessageDTO);

        messageRepository.save(newMessage);
    }
}
