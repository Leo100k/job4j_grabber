package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HabrCareerDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(parse, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static void main(String[] args) {
        HabrCareerDateTimeParser dateToLD = new HabrCareerDateTimeParser();
        LocalDateTime datePrint = dateToLD.parse("2024-01-23T12:44:01+03:00");
        System.out.println(datePrint);
    }
}