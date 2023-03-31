package kbh.foerdervereinkita.mvc.controller;

import java.awt.*;
import kbh.foerdervereinkita.dto.MyFleaMarketDto;
import kbh.foerdervereinkita.mapper.MyFleaMarketMapper;
import kbh.foerdervereinkita.mvc.form.MyRegistrationForm;
import kbh.foerdervereinkita.service.MyFleaMarketService;
import kbh.foerdervereinkita.validate.ValidationSequence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class MyFleaMarketController {

  private MyFleaMarketService service;
  private MyFleaMarketMapper mapper;

  @GetMapping(path = "/flohmarkt/meine-anmeldung")
  ModelAndView fleaMarketMyRegistrationGet(Model model) {

    model.addAttribute("myRegistrationForm", new MyRegistrationForm());

    return new ModelAndView("my-registration");
  }

  @PostMapping(path = "/flohmarkt/meine-anmeldung")
  ModelAndView fleaMarketMyRegistrationPost(
      @Validated(ValidationSequence.class) @ModelAttribute("myRegistrationForm")
          MyRegistrationForm form,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return new ModelAndView("my-registration");
    }

    MyFleaMarketDto myFleaMarketRequest = mapper.toDto(form);

    MyFleaMarketDto myFleaMarketResponse = service.fetch(myFleaMarketRequest);

    model.addAttribute("myFleaMarket", myFleaMarketResponse);

    return new ModelAndView("my-registration-state");
  }
}
