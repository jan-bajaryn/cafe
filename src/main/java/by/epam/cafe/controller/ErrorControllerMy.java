package by.epam.cafe.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControllerMy implements ErrorController {


    @RequestMapping("/error")
    public String err() {
        return "/errors/something_went_wrong";
    }

//    @GetMapping("/error")
//    public String errorPost() {
//        return "redirect:/error";
//    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
