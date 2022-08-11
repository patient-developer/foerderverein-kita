package kbh.foerdervereinkita.controller;

import kbh.foerdervereinkita.commons.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrivacyController {

  @GetMapping("/privacy")
  ModelAndView privacyGet() {
    return new ModelAndView(Views.PRIVACY);
  }
}
