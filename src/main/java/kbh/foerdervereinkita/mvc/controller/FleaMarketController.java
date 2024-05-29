package kbh.foerdervereinkita.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class FleaMarketController {

  @GetMapping(path = "/flohmarkt")
  ModelAndView fleaMarketGet() {
    return new ModelAndView("flea-market");
  }
}
