package org.example;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;

public class DataCrawler {
    public static void main(String[] args) throws IOException, InterruptedException {
        String URL = "https://www.themoviedb.org/movie/29228";
        Movie movie = DataCrawl(URL);
        movie.printData();

    }

    public static Movie DataCrawl(String URL) throws IOException {

        try {
            Connection.Response res = Jsoup.connect(URL + "?language=en-US").execute();
            String currentUrl = res.url().toString();
            String type = currentUrl.substring(currentUrl.indexOf(".org/") + 5);
            type = type.substring(0, type.indexOf("/"));
            System.out.println("\t\t" + currentUrl);

            boolean banned = false;
            Document doc = res.parse();

            try {
                Element element = doc.selectFirst("body > div > div > h2");
                if (element.text().equals("Wait and try again")){
                    banned = true;
                    System.out.println("Banned!!!!!!!!");
                }
            }
            catch (Exception e){
            }

            if (type.equals("movie")) {

                Movie m = new Movie();
                try {
                    //Title
                    Element title = doc.selectFirst("#original_header > div.header_poster_wrapper.false > section > div.title.ott_false > h2 > a");
                    m.setTitle(title.text());
                    System.out.print("\t\t" + m.getTitle());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    //ReleaseDate
                    ArrayList<Integer> releaseDate = new ArrayList<>();
                    Element releaseDateElement = doc.getElementsByClass("release").get(0);
                    String fullReleaseDate = releaseDateElement.text();

                    int year = Integer.parseInt(fullReleaseDate.substring(0, fullReleaseDate.indexOf("-")));
                    releaseDate.add(year);
                    fullReleaseDate = fullReleaseDate.substring(fullReleaseDate.indexOf("-") + 1);

                    int month = Integer.parseInt(fullReleaseDate.substring(0, fullReleaseDate.indexOf("-")));
                    releaseDate.add(month);
                    fullReleaseDate = fullReleaseDate.substring(fullReleaseDate.indexOf("-") + 1);

                    int date = Integer.parseInt(fullReleaseDate.substring(0, fullReleaseDate.indexOf(" ")));
                    releaseDate.add(date);

                    m.setRelease_date(releaseDate);
                } catch (Exception e) {
                    System.out.print("\tNo Date");
                }

                try {
                    //Genre
                    ArrayList<String> Genre = new ArrayList<>();
                    Elements genreElement = doc.select("#original_header > div.header_poster_wrapper.false > section > div.title.ott_false > div > span.genres > a");
                    for (Element g : genreElement) {
                        Genre.add(g.text());
                    }
                    m.setGenre(Genre);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    //MPAA Tag
                    Element MPAATagElement = doc.selectFirst("#original_header > div.header_poster_wrapper.false > section > div.title.ott_false > div > span.certification");
                    m.setMPAA_tag(MPAATagElement.text());
                } catch (Exception e) {
                    System.out.print("\tNo MPAA");
                }

                try {
                    //Duration
                    Element durationElement = doc.selectFirst("#original_header > div.header_poster_wrapper.false > section > div.title.ott_false > div > span.runtime");
                    String duration = durationElement.text();
                    int duration_minute = 0;
                    if (duration.contains("h")) {
                        duration_minute = Integer.parseInt(duration.substring(0, duration.indexOf("h"))) * 60
                                + Integer.parseInt(duration.substring(duration.indexOf("h") + 2, duration.indexOf("m")));
                    } else {
                        duration_minute = Integer.parseInt(duration.substring(0, duration.indexOf("m")));
                    }
                    m.setDuration(duration_minute);
                } catch (Exception e) {
                    System.out.print("\tNo Duration");
                }

                try {
                    //Director
                    ArrayList<String> directors = new ArrayList<>();

                    Elements directorsElement = doc.select("#original_header > div.header_poster_wrapper.false > section > div.header_info > ol > li");
                    for (Element d : directorsElement) {
                        String job = d.selectFirst("p.character").text();
                        if (job.contains("Director")) {
                            String d_name = d.selectFirst("p:nth-child(1) > a").text();
                            directors.add(d_name);
                        }
                    }
                    m.setDirectors(directors);
                } catch (Exception e) {
                    System.out.print("\tNo Directors");
                }

                try {
                    //Actors
                    ArrayList<String> actors = new ArrayList<>();
                    Document cast_document = Jsoup.connect(URL + "/cast").get();
                    Elements actorsElement = cast_document.select("#media_v4 > div > div > section:nth-child(1) > ol > li");
                    for (Element a : actorsElement) {
                        String actor_name = a.select("div > div > p:nth-child(1) > a").text();
                        actors.add(actor_name);
                    }
                    m.setActors(actors);

                } catch (Exception e) {
                    System.out.print("\tNo Actors");
                }

                try {
                    //Overview
                    Elements overviewElement = doc.select("#original_header > div.header_poster_wrapper.false > section > div.header_info > div > p");
                    m.setOverview(overviewElement.text());

                } catch (Exception e) {
                    System.out.print("\tNo Overview");
                }

                try {
                    //User Score
                    Element userScoreElement = doc.selectFirst("#consensus_pill > div > div.consensus.details > div > div");
                    int user_score = Integer.parseInt(userScoreElement.attr("data-percent"));
                    m.setUser_score(user_score);
                } catch (Exception e) {
                    System.out.print("\tNo Score");

                }

                try {
                    //Number of votes
                    String ratingDeatails_requestURL = URL + "/remote/rating/details?translate=false&language=en-US&mobile=false";
                    Connection.Response response = Jsoup.connect(ratingDeatails_requestURL)
                            .ignoreContentType(true)
                            .execute();

                    String line = readNumberOfVotes.findLineContaining(response, "Ratings</h3>");
                    line = line.substring(line.indexOf(">") + 1, line.indexOf("R") - 1);
                    m.setNumber_of_vote(CustomisedAction.parseStringToInt(line));

                } catch (Exception e) {
                    System.out.print("\tNo Votes");
                }

                try {
                    Elements facts = doc.select("#media_v4 > div > div > div.grey_column > div > section > div:nth-child(1) > div > section.facts.left_column > p");
                    for (Element f : facts) {
                        //Budget
                        try {
                            Element factElement = f.selectFirst("strong > bdi");
                            //Budget
                            if (factElement.text().equals("Budget")) {
                                String budget_String = f.text();
                                m.setBudget(CustomisedAction.parseStringToInt(budget_String.substring(budget_String.indexOf("$") + 1)));
                            }
                            if (factElement.text().equals("Revenue")) {
                                String revenue_String = f.text();
                                m.setRevenue(CustomisedAction.parseStringToInt(revenue_String.substring(revenue_String.indexOf("$") + 1)));
                            }
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return m;
            } else {
                System.out.print("\t\tNot a movie");
            }
        } catch (Exception e) {
            System.out.println("\t\tNothing there!");
//            Connection.Response res = Jsoup.connect(URL + "?language=en-US").execute();
//            String currentUrl = res.url().toString();
//            if (currentUrl.equals(URL + "?language=en-US")){
//                System.out.println("Banned");
//            }
        } finally {
            System.out.println();
        }
        return null;
    }
}
