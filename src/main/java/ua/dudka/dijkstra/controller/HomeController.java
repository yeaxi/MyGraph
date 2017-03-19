package ua.dudka.dijkstra.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.dudka.dijkstra.model.Answer;
import ua.dudka.dijkstra.model.Graph;
import ua.dudka.dijkstra.service.DijkstraAlgorithm;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final Graph graph = new Graph();

    private DijkstraAlgorithm dijkstraAlgorithm;

    @GetMapping
    public String index(Model model) {
        setGraph(model);
        return "index";
    }

    @PostMapping("/nodes/add")
    public String addNode(@RequestParam String name, Model model) {
        graph.addNode(name);
        return "redirect:/";
    }

    @PostMapping("/nodes/remove")
    public String removeNode(@RequestParam String name, Model model) {
        graph.removeNode(name);
        return "redirect:/";
    }

    @PostMapping("/nodes/update")
    public String updateCoordinates(@RequestParam String name, @RequestParam Double x, @RequestParam Double y) {
        graph.updateNodeCoordinates(name, x, y);
        return "redirect:/";
    }


    @PostMapping("/edges/add")
    public String addEdge(@RequestParam String start, @RequestParam String end, @RequestParam int weight) {
        graph.addEdge(start, end, weight);
        return "redirect:/";
    }

    @PostMapping("/edges/remove")
    public String removeEdge(@RequestParam Integer id) {
        graph.removeEdge(id);
        return "redirect:/";
    }

    @PostMapping("dijkstra")
    public String doDijkstra(@RequestParam String start, @RequestParam String end, Model model) {
        Answer answer = new DijkstraAlgorithm(graph, start, end).getAnswer();
        model.addAttribute("answer", answer);
        setGraph(model);
        return "index";
    }

    private void setGraph(Model model) {
        model.addAttribute("graph", graph);
    }

    @PostMapping("/clear")
    public String clearGraph() {
        graph.clear();
        return "redirect:/";
    }
}