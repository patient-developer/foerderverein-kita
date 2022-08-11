package kbh.foerdervereinkita.controller;

import kbh.foerdervereinkita.commons.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LandingPageController {

  @GetMapping("/")
  ModelAndView rootGet() {
    return new ModelAndView(Views.INDEX);
  }

  @GetMapping("/index")
  ModelAndView indexGet() {
    return new ModelAndView(Views.INDEX);
  }
}
