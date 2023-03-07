package kbh.foerdervereinkita.mvc.controller;

import kbh.foerdervereinkita.commons.Views;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LandingPageController {

  @Value("${ASSOCIATION_LOGO_FILENAME}")
  private final String associationEmblemFilename;

  @Value("${KINDERGARTEN_LOGO_FILENAME}")
  private final String kindergartenEmblemFilename;

  @GetMapping("/")
  ModelAndView rootGet(Model model) {

    model.addAttribute("associationEmblemFilename", associationEmblemFilename);
    model.addAttribute("kindergartenEmblemFilename", kindergartenEmblemFilename);

    return new ModelAndView(Views.INDEX);
  }

  @GetMapping("/index")
  ModelAndView indexGet(Model model) {

    model.addAttribute("associationEmblemFilename", associationEmblemFilename);
    model.addAttribute("kindergartenEmblemFilename", kindergartenEmblemFilename);

    return new ModelAndView(Views.INDEX);
  }
}
