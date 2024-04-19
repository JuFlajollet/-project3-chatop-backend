package com.chatop.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class DateFormatterUtils {

    public Timestamp formatCurrentDateForDB(){
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        LocalDateTime currentDate = LocalDateTime.now(zoneId);

        return Timestamp.valueOf(currentDate);
    }

    public String formatDateForDisplay(Timestamp currentDate){
        return new SimpleDateFormat("yyyy/MM/dd").format(currentDate);
    }
}
