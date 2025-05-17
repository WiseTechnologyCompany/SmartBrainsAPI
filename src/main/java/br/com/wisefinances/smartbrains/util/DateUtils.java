package br.com.wisefinances.smartbrains.util;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtils {

    public static String getCurrentDateTimeFormatted() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
    }
}