package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HabrCareerDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        String strParse = parse.substring(0, 19);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(strParse, formatter);
    }

    public static void main(String[] args) {
        HabrCareerDateTimeParser dateToLD = new HabrCareerDateTimeParser();
        LocalDateTime datePrint = dateToLD.parse("2024-01-23T12:44:00+03:00");
        System.out.println(datePrint);

    }
}