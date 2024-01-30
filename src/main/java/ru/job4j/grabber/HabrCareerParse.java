package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private static final String SOURCE_LINK = "https://career.habr.com";
    public static final String PREFIX = "/vacancies?page=";
    public static final String SUFFIX = "&q=Java%20developer&type=all";
    private static String description = null;

    public static void main(String[] args) throws IOException {
       HabrCareerParse  leoParser = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> post = leoParser.list(SOURCE_LINK);
        leoParser.printPost(post);
    }

  @Override
    public List<Post> list(String slink) throws IOException {

        List<Post> postInner = new ArrayList<>();
        int pageNumber = 1;
        do {
            System.out.println("Страница " + pageNumber);
            String fullLink = "%s%s%d%s".formatted(slink, PREFIX, pageNumber, SUFFIX);
            Connection connection = Jsoup.connect(fullLink);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                Element dateEliment = row.select(".vacancy-card__date").first();
                Element dateTimeEliment = dateEliment.child(0);
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String dateString = String.format("%s", dateTimeEliment.attr("datetime"));
                try {
                     description = retrieveDescription(link);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                  postInner.add(new Post(1, vacancyName, link, description, dateTimeParser.parse(dateString)));
            });
            pageNumber++;
        } while (pageNumber < 5);

        return postInner;
    }

    private static String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements rows = document.select(".basic-section.basic-section--appearance-vacancy-description");
        rows.forEach(row -> {
            Element titleElement = row.select(".faded-content").first();
            description = titleElement.text();
        });
        return description;
    }

    private void printPost(List<Post> post) {
        if (post != null) {
            System.out.println(post);
        }
    }
}