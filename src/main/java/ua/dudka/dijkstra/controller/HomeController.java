package ua.dudka.dijkstra.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.dudka.dijkstra.model.Answer;
import ua.dudka.dijkstra.model.Graph;
import ua.dudka.dijkstra.service.DijkstraAlgorithm;
import ua.dudka.dijkstra.service.HomeService;
import ua.dudka.dijkstra.service.exception.BadFileFormatException;
import ua.dudka.dijkstra.service.exception.NodeExistsException;
import ua.dudka.dijkstra.service.exception.NodesNotConnectedException;
import ua.dudka.dijkstra.service.exception.NotFoundException;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private static final String REDIRECT_TO_INDEX = "redirect:/";
    private static final String INDEX_PATH = "index";
    private final Graph graph = new Graph();
    private final HomeService homeService;

    private DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
    private Answer answer;

    @ModelAttribute
    public Graph graph() {
        return graph;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/add-node")
    public String addNode(@RequestParam String name) {
        graph.addNode(name);

        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/remove-node")
    public String removeNode(@RequestParam String name) {
        graph.removeNode(name);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/update-node")
    public String updateCoordinates(@RequestParam String name, @RequestParam Double x, @RequestParam Double y) {
        graph.updateNodeCoordinates(name, x, y);
        return REDIRECT_TO_INDEX;
    }


    @PostMapping("/add-edge")
    public String addEdge(@RequestParam String start, @RequestParam String end, @RequestParam int weight) {
        graph.addEdge(start, end, weight);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/remove-edge")
    public String removeEdge(@RequestParam Integer id) {
        graph.removeEdge(id);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("dijkstra")
    public String doDijkstra(@RequestParam String start, @RequestParam String end, Model model) {
        answer = dijkstraAlgorithm.execute(graph, start, end);

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


    @ExceptionHandler({NodeExistsException.class, NotFoundException.class, NodesNotConnectedException.class, BadFileFormatException.class})
    public ModelAndView handleNodeExistsException(Exception e) {
        ModelAndView modelAndView = new ModelAndView(INDEX_PATH);
        modelAndView.addObject("graph", graph);
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }

}