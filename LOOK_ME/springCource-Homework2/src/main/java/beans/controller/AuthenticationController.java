package beans.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AuthenticationController {

    @GetMapping
    public String start(Model model){
        return "index";
    }

    @GetMapping
    public String loginPage(Model model){
        return "login";
    }



}
