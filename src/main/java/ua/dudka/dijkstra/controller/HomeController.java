package ua.dudka.dijkstra.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.dudka.dijkstra.service.HomeService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("graph", homeService.getGraph());
        return "index";
    }


    @PostMapping("/nodes/add")
    public String addNode(@RequestParam String name, Model model) {
        homeService.addNode(name);
        return "redirect:/";
    }

    @PostMapping("/nodes/remove")
    public String removeNode(@RequestParam String name, Model model) {
        homeService.removeNode(name);
        return "redirect:/";
    }
}
