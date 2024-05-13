package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;

import java.io.IOException;

public class readNumberOfVotes {
    public static void main(String[] args) {

    }

    public static String findLineContaining(Connection.Response response, String searchText) {
        // Get the body of the response
        String responseBody = response.body();

        // Split the response body into lines
        String[] lines = responseBody.split("\\r?\\n");

        // Search for the line containing the specified text
        for (String line : lines) {
            if (line.contains(searchText)) {
                return line;
            }
        }

        // If the text is not found, return null
        return null;
    }
}
