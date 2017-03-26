package ua.dudka.dijkstra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeveloperController {

    @RequestMapping("/about-developer")
    public String getPage() {
        return "developer";
    }
}