package kbh.foerdervereinkita.mvc.controller;

import kbh.foerdervereinkita.commons.Views;
import kbh.foerdervereinkita.config.PressReleaseConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class EventsController {

  private final PressReleaseConfig config;

  @GetMapping("/events")
  ModelAndView associationGet(Model model) {

    model.addAttribute("pressReleaseInfos", config.getPressReleaseInfos());

    return new ModelAndView(Views.EVENTS);
  }
}
