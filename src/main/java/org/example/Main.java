package org.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        File file = new File("moviesDB_2.json");
        final String baseURL = "https://www.themoviedb.org/movie/";

        final int STARTING_POINT = 36998;
        final int NUMBER_OF_MOVIES = 1287680;
        final int NUMBER_OF_EXISTING_MOVIES = 12000 + CountNumberOfMovies.getMovieCount(file);

//
//        String proxyAddress = "47.74.152.29:8888";
//        String[] p = proxyAddress.split(":");
//        String proxyHost = p[0];
//        int proxyPort = Integer.parseInt(p[1]);
//
//        // Proxy object
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));


        OutputStream outputStream = null;
        JsonGenerator jsonGenerator = null;
        Movie.nbMovies = NUMBER_OF_EXISTING_MOVIES;

        try {
            outputStream = new FileOutputStream(file, true);
            ObjectMapper mapper = new ObjectMapper();
            jsonGenerator = mapper.getFactory().createGenerator(outputStream);
            boolean isNewMovieFound = false;

            for (int i = STARTING_POINT; i < NUMBER_OF_MOVIES; i++) {
                System.out.print(i);
                String URL = baseURL + i;
                Movie movie = DataCrawler.DataCrawl(URL);

                if (movie != null) {
                    jsonGenerator.writeRaw(",\n");
                    if (!isNewMovieFound) {
                        jsonGenerator.writeRaw(" ");
                        isNewMovieFound = true;
                    }
                    mapper.writeValue(jsonGenerator, movie);
                }

                if (i == NUMBER_OF_MOVIES - 1) {
                    jsonGenerator.writeRaw("\n]");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}