package ua.dudka.dijkstra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocumentationController {

    @RequestMapping("/theory")
    public String getTheoryPage() {
        return "theory";
    }

    @RequestMapping("/instruction")
    public String getInstructionPage() {
        return "instruction";
    }

    @RequestMapping("/about-developer")
    public String getDeveloperPage() {
        return "developer";
    }


    @RequestMapping("/description")
    public String getProgramPage() {
        return "description";
    }
}