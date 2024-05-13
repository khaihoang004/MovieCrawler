package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModifyJsonFile {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<ObjectNode> movies = objectMapper.readValue(new File("movies.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, ObjectNode.class));
            System.out.println(movies.size());
            List<ObjectNode> movies4 = new ArrayList<>();

            // Lọc các phim có ID lớn hơn 15000 và thêm vào danh sách movies4
            for (ObjectNode movie : movies) {
                int id = movie.get("id").asInt();
                if (id > 12000) {
                    movies4.add(movie);
                }
            }

            // Ghi ra file "movies_4.json"
            objectMapper.writeValue(new File("movies_4.json"), movies4);

            System.out.println("Đã ghi ra file thành công!");
        } catch (IOException e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với file: " + e.getMessage());
        }
    }
}
