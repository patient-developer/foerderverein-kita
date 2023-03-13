package kbh.foerdervereinkita.mvc.controller;

import java.util.Collection;
import kbh.foerdervereinkita.dto.EventRegistrationDto;
import kbh.foerdervereinkita.mapper.EventRegistrationMapper;
import kbh.foerdervereinkita.mvc.form.EventRegistrationForm;
import kbh.foerdervereinkita.mvc.model.EventRegistrationModel;
import kbh.foerdervereinkita.service.EventRegistrationService;
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
public class EventRegistrationController {
  private final EventRegistrationService service;
  private final EventRegistrationMapper mapper;

  @GetMapping(path = "/event-registrations")
  ModelAndView eventRegistrationGet(Model model) {

    model.addAttribute("eventRegistrationForm", new EventRegistrationForm());
    model.addAttribute("registrationVacanciesCount", service.registrationVacanciesCount());

    return new ModelAndView("event-registration");
  }

  @PostMapping(path = "/event-registrations")
  ModelAndView eventRegistrationPost(
      @Validated(ValidationSequence.class) @ModelAttribute("eventRegistrationForm")
          EventRegistrationForm form,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      return new ModelAndView("event-registration");
    }

    EventRegistrationDto eventRegistration = mapper.toDto(form);

    service.register(eventRegistration);

    String successMessage = concatSuccessMessage(form);

    redirectAttributes.addFlashAttribute("success", successMessage);

    return new ModelAndView(new RedirectView("/event-registrations", true));
  }

  @GetMapping(path = "/internal/event-registrations")
  ModelAndView eventRegistrationAllGet(Model model) {

    Collection<EventRegistrationDto> eventRegistrationDtos = service.fetchAll();

    Collection<EventRegistrationModel> eventRegistrationModels =
        eventRegistrationDtos.stream().map(mapper::toModel).toList();

    model.addAttribute("eventRegistrationModels", eventRegistrationModels);

    return new ModelAndView("internal-event-registration");
  }

  private String concatSuccessMessage(EventRegistrationForm form) {

    return "Vielen Dank "
        + form.getFullName()
        + ". Sie haben sich mit Ihrer E-Mail "
        + form.getEMail()
        + " erfolgreich angemeledet.";
  }
}
