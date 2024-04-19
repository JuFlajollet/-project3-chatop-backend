package com.chatop.service;

import com.chatop.dto.DBUserDTO;
import com.chatop.dto.MessageDTO;
import com.chatop.dto.RentalDTO;
import com.chatop.mapper.MessageMapper;
import com.chatop.model.Message;
import com.chatop.repository.MessageRepository;
import com.chatop.util.DateFormatterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private DBUserService dbUserService;
    @Autowired
    private RentalsService rentalsService;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private DateFormatterUtils dateFormatterUtils;

    private final static Logger logger = LoggerFactory.getLogger(MessageService.class);

    public void createMessage(MessageDTO newMessageDTO) {
        Message newMessage = messageMapper.toMessage(newMessageDTO);

        Timestamp formattedDate = dateFormatterUtils.formatCurrentDateForDB();

        newMessage.setCreatedAt(formattedDate);
        newMessage.setUpdatedAt(formattedDate);

        RentalDTO rental = rentalsService.getRental(newMessage.getRentalId());
        DBUserDTO messageOwner = dbUserService.findUserById(newMessage.getUserId());
        DBUserDTO rentalOwner = dbUserService.findUserById(rental.getOwnerId());

        messageRepository.save(newMessage);
        logger.info("Message successfully saved into db");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@chatop.com");
        message.setTo(rentalOwner.getEmail());
        message.setSubject("Message about your rental: " + rental.getName());
        message.setText(newMessageDTO.getMessage() + "\n\n" + "You can reply to your contact from Châtop at the following email address: " + messageOwner.getEmail());

        emailSender.send(message);
        logger.info("Message successfully sent");
    }
}
