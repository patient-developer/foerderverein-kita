package kbh.foerdervereinkita.parentcommittee;

import static java.util.stream.Collectors.groupingBy;

import jakarta.validation.Valid;
import kbh.foerdervereinkita.commons.Views;
import kbh.foerdervereinkita.constants.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/kindergarten")
public class ParentCommitteeController {

  private final ParentCommitteeService service;
  private final ParentCommitteeMapper mapper;

  @GetMapping(path = "/elternausschuss")
  public ModelAndView parentCommitteeGet(Model model) {
    var members = service.fetchParentCommitteeMembers();
    var models = members.stream().map(mapper::toModel).toList();
    var modelsByGroup = models.stream().collect(groupingBy(ParentCommitteeMemberModel::groupName));
    model.addAttribute("modelsByGroup", modelsByGroup);
    return new ModelAndView(Views.PARENT_COMMITTEE);
  }

  @GetMapping(path = "/elternausschuss/nachricht")
  public ModelAndView parentCommitteeMessageGet(Model model) {
    model.addAttribute("form", new ParentCommitteeMessageForm());
    return new ModelAndView(Views.PARENT_COMMITTEE_MESSAGE);
  }

  @PostMapping(path = "/elternausschuss/nachricht")
  public ModelAndView parentCommitteeMessagePost(
      @Valid @ModelAttribute("form") ParentCommitteeMessageForm form,
      RedirectAttributes attributes) {

    if (!StringUtils.hasText(form.getContent())) {
      throw new IllegalArgumentException("Bitte keine leere Nachricht abschicken.");
    }

    log.info("Received parent committee message: '{}'", form.getContent());
    var message = mapper.toMessage(form);
    service.persist(message);
    attributes.addFlashAttribute(MessageType.SUCCESS, "Vielen Dank für Ihre Nachricht.");
    return new ModelAndView(new RedirectView("/kindergarten/elternausschuss/nachricht"));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ModelAndView handleIllegalArgumentException(
      IllegalArgumentException exception, RedirectAttributes attributes) {
    log.error(exception.getMessage(), exception);
    attributes.addFlashAttribute(MessageType.ERROR, exception.getMessage());
    return new ModelAndView(new RedirectView("/kindergarten/elternausschuss/nachricht"));
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleAllExceptions(Exception exception, RedirectAttributes attributes) {
    log.error(exception.getMessage(), exception);
    attributes.addFlashAttribute(MessageType.ERROR, "Sorry, da ist was schief gegangen.");
    return new ModelAndView(new RedirectView("/kindergarten/elternausschuss/nachricht"));
  }
}
