package ua.dudka.dijkstra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import ua.dudka.dijkstra.model.Graph;
import ua.dudka.dijkstra.service.exception.BadFileFormatException;

import static org.junit.Assert.assertFalse;

public class HomeServiceTest {
    private static final String FILENAME = "data";
    private static final String DATA_PATH = "data.json";
    private static final String BAD_DATA_PATH = "bad_data.json";

    private final HomeService homeService = new HomeServiceImpl(new ObjectMapper());

    @Test
    public void uploadGraphFromFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile(FILENAME, new ClassPathResource(DATA_PATH).getInputStream());

        Graph graph = homeService.uploadGraphFromFile(file);

        assertFalse(graph.getNodes().isEmpty());
        assertFalse(graph.getNodes().isEmpty());

    }

    @Test(expected = BadFileFormatException.class)
    public void uploadGraphFromFileWithWrongStructureShouldThrowException() throws Exception {
        MockMultipartFile file = new MockMultipartFile(FILENAME, new ClassPathResource(BAD_DATA_PATH).getInputStream());

        homeService.uploadGraphFromFile(file);
    }

}