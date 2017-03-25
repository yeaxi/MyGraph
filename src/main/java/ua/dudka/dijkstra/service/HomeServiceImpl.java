package ua.dudka.dijkstra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.dudka.dijkstra.model.Answer;
import ua.dudka.dijkstra.model.Graph;
import ua.dudka.dijkstra.service.exception.BadFileFormatException;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static ua.dudka.dijkstra.service.JsonKeys.*;

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
            JSONObject object = new JSONObject(getJsonString(file));
            readNodes(graph, object);
            readEdges(graph, object);
        } catch (Exception e) {
            throw new BadFileFormatException(e.getMessage());
        }
        return graph;
    }

    private String getJsonString(MultipartFile file) throws IOException {
        return objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(objectMapper.readTree(file.getBytes()));
    }

    private void readNodes(Graph graph, JSONObject object) throws JSONException {
        JSONArray nodes = object.getJSONArray(NODES_KEY);
        for (int i = 0; i < nodes.length(); i++) {
            JSONObject node = nodes.getJSONObject(i);
            String name = node.getString(NODE_NAME_KEY);
            graph.addNode(name);
        }
    }

    private void readEdges(Graph graph, JSONObject object) throws JSONException {
        JSONArray edges = object.getJSONArray(EDGES_KEY);
        for (int i = 0; i < edges.length(); i++) {
            JSONObject edgesJSONObject = edges.getJSONObject(i);
            String startNodeName = edgesJSONObject.getString(EDGE_START_KEY);
            String endNodeName = edgesJSONObject.getString(EDGE_END_KEY);
            int weight = edgesJSONObject.getInt(EDGE_WEIGHT_KEY);
            graph.addEdge(startNodeName, endNodeName, weight);

        }
    }

    @SneakyThrows
    @Override
    public void saveAnswerToFile(Answer answer, HttpServletResponse response) {
        Path tempFile = Files.createTempFile("pref", ".txt");

        writeAnswerToTempFile(answer, tempFile);

        response.setContentType("text/html");
        response.setHeader("Content-Disposition", "attachment; filename=answer");
        IOUtils.copy(Files.newInputStream(tempFile), response.getOutputStream());

        Files.deleteIfExists(tempFile);
    }

    private void writeAnswerToTempFile(Answer answer, Path tempFile) throws IOException {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(tempFile);
        bufferedWriter.write(LENGTH_MESSAGE + answer.distance);
        bufferedWriter.newLine();
        bufferedWriter.write(PATH_MESSAGE + answer.getPathStr());
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}