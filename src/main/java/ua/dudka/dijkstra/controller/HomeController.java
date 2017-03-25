package ua.dudka.dijkstra.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.dudka.dijkstra.model.Answer;
import ua.dudka.dijkstra.model.Graph;
import ua.dudka.dijkstra.service.DijkstraAlgorithm;
import ua.dudka.dijkstra.service.HomeService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    public static final String REDIRECT_TO_INDEX = "redirect:/";
    public static final String INDEX_PATH = "index";
    private final Graph graph = new Graph();
    private final HomeService homeService;

    private DijkstraAlgorithm dijkstraAlgorithm;
    private Answer answer;

    @ModelAttribute
    public Graph graph() {
        return graph;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/nodes/add")
    public String addNode(@RequestParam String name) {
        graph.addNode(name);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/nodes/remove")
    public String removeNode(@RequestParam String name) {
        graph.removeNode(name);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/nodes/update")
    public String updateCoordinates(@RequestParam String name, @RequestParam Double x, @RequestParam Double y) {
        graph.updateNodeCoordinates(name, x, y);
        return REDIRECT_TO_INDEX;
    }


    @PostMapping("/edges/add")
    public String addEdge(@RequestParam String start, @RequestParam String end, @RequestParam int weight) {
        graph.addEdge(start, end, weight);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/edges/remove")
    public String removeEdge(@RequestParam Integer id) {
        graph.removeEdge(id);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("dijkstra")
    public String doDijkstra(@RequestParam String start, @RequestParam String end, Model model) {
        if (start == null || end == null) {
            return REDIRECT_TO_INDEX;
        }
        answer = new DijkstraAlgorithm(graph, start, end).getAnswer();
        model.addAttribute("answer", answer);
        return INDEX_PATH;
    }

    @PostMapping("/random-fill")
    public String fillGraphRandomly() {
        graph.fillRandomly();
        return REDIRECT_TO_INDEX;
    }


    @PostMapping("/upload-from-file")
    public String uploadGraphFromFile(@RequestParam MultipartFile file) {
        graph.createFrom(homeService.uploadGraphFromFile(file));
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/save-answer-to-file")
    public void saveAnswerToFile(HttpServletResponse response) {
        homeService.saveAnswerToFile(answer, response);
    }


    @PostMapping("/clear")
    public String clearGraph() {
        graph.clear();
        return REDIRECT_TO_INDEX;
    }
}