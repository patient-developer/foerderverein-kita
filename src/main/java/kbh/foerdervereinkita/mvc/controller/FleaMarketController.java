package kbh.foerdervereinkita.mvc.controller;

import java.util.Collection;
import kbh.foerdervereinkita.config.Config;
import kbh.foerdervereinkita.dto.EventRegistrationDto;
import kbh.foerdervereinkita.mapper.EventRegistrationMapper;
import kbh.foerdervereinkita.mvc.form.EventRegistrationForm;
import kbh.foerdervereinkita.mvc.model.EventRegistrationModel;
import kbh.foerdervereinkita.service.EventRegistrationService;
import kbh.foerdervereinkita.service.StoringPositionLocationService;
import kbh.foerdervereinkita.validate.ValidationSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class FleaMarketController {

  private final Config config;
  private final EventRegistrationService eventRegistrationService;
  private final StoringPositionLocationService storingPositionLocationService;
  private final EventRegistrationMapper mapper;

  @GetMapping(path = "/flohmarkt")
  ModelAndView fleaMarketGet() {
    return new ModelAndView("flea-market");
  }

  @GetMapping(path = "/event-registrations")
  RedirectView eventRegistrationGet() {
    return new RedirectView("/flohmarkt/anmeldung", true);
  }

  @GetMapping(path = "/flohmarkt/anmeldung")
  ModelAndView fleaMarketRegistrationGet(Model model) {

    model.addAttribute("eventRegistrationForm", new EventRegistrationForm());
    model.addAttribute(
        "registrationVacanciesCount", storingPositionLocationService.vacanciesCount());

    return new ModelAndView("flea-market-registration");
  }

  @PostMapping(path = "/flohmarkt/anmeldung")
  ModelAndView fleaMarketRegistrationPost(
      @Validated(ValidationSequence.class) @ModelAttribute("eventRegistrationForm")
          EventRegistrationForm form,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      return new ModelAndView("flea-market-registration");
    }

    EventRegistrationDto eventRegistration = mapper.toDto(form);

    eventRegistrationService.register(eventRegistration);

    String successMessage = concatSuccessMessage(form);

    redirectAttributes.addFlashAttribute("success", successMessage);

    return new ModelAndView(new RedirectView("/flohmarkt/anmeldung", true));
  }

  @GetMapping(path = "/internal/event-registrations")
  ModelAndView eventRegistrationAllGet(Model model) {

    Collection<EventRegistrationDto> eventRegistrationDtos = eventRegistrationService.fetchAll();

    Collection<EventRegistrationModel> eventRegistrationModels =
        eventRegistrationDtos.stream().map(mapper::toModel).toList();

    model.addAttribute("eventRegistrationModels", eventRegistrationModels);

    return new ModelAndView("internal-event-registration");
  }

  private String concatSuccessMessage(EventRegistrationForm form) {

    return """
        Vielen Dank $fullName. Sie haben sich mit Ihrer E-Mail $eMail erfolgreich angemeldet.<br>
        Sie sollten in Kürze eine Bestätigungs E-Mail erhalten.
        """
        .replace("$fullName", form.getFullName())
        .replace("$eMail", form.getEMail())
        .replace("$contactEMail", config.getContactEMail());
  }
}
