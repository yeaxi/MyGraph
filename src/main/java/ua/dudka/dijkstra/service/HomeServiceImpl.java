package ua.dudka.dijkstra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.dudka.dijkstra.model.Answer;
import ua.dudka.dijkstra.model.Graph;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private static final String LENGTH_MESSAGE = "Distance - ";
    private static final String PATH_MESSAGE = "Path - ";
    private final ObjectMapper objectMapper;

    @Override
    public Graph uploadGraphFromFile(MultipartFile file) {
        Graph graph = new Graph();

        try {
            graph = objectMapper.readValue(file.getBytes(), Graph.class);
        } catch (Exception ignored) {
        }

        return graph;
    }

    @SneakyThrows
    @Override
    public void saveAnswerToFile(Answer answer, HttpServletResponse response) {
        Path tempFile = Files.createTempFile("pref", ".txt");

        writeAnswerToFile(answer, tempFile);

        response.setContentType("text/html");
        response.setHeader("Content-Disposition", "attachment; filename=answer");
        IOUtils.copy(Files.newInputStream(tempFile), response.getOutputStream());
    }

    private void writeAnswerToFile(Answer answer, Path tempFile) throws IOException {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(tempFile);
        bufferedWriter.write(LENGTH_MESSAGE + answer.distance);
        bufferedWriter.newLine();
        bufferedWriter.write(PATH_MESSAGE + answer.getPathStr());
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}