package ua.dudka.dijkstra.service;

import org.springframework.web.multipart.MultipartFile;
import ua.dudka.dijkstra.model.Answer;
import ua.dudka.dijkstra.model.Graph;

import javax.servlet.http.HttpServletResponse;

public interface HomeService {
    Graph uploadGraphFromFile(MultipartFile file);

    void saveAnswerToFile(Answer answer, HttpServletResponse response);
}