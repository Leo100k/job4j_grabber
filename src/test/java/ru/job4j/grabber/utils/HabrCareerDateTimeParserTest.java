package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

class HabrCareerDateTimeParserTest {

    @Test
    public void convertDateToLDTSuccess() {
        String dateStr = "2024-01-23T12:44:01+03:00";
        String dateStrTest = "2024-01-23T12:44:01";
        DateTimeParser convertDate = new HabrCareerDateTimeParser();
        LocalDateTime dateLDT = convertDate.parse(dateStr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateStrTest, formatter);
        assertThat(dateLDT).isEqualTo(localDateTime);
    }

}