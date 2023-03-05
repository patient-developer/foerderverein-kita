package kbh.foerdervereinkita.mvc.controller;

import kbh.foerdervereinkita.commons.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImprintController {

  @GetMapping("/imprint")
  ModelAndView imprintGet() {
    return new ModelAndView(Views.IMPRINT);
  }
}
