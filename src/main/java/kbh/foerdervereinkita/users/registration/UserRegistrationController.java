package kbh.foerdervereinkita.users.registration;

import kbh.foerdervereinkita.constants.MessageType;
import kbh.foerdervereinkita.security.Authority;
import kbh.foerdervereinkita.users.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRegistrationController {

  private final UserRegistrationService service;
  private final UserRegistrationMapper mapper;

  @GetMapping
  ModelAndView usersGet(Model model) {

    model.addAttribute("userForm", new UserForm());
    model.addAttribute("authorities", Authority.values());

    return new ModelAndView("users");
  }

  @PostMapping("/new")
  ModelAndView usersNewPost(
          @ModelAttribute("userForm") UserForm userForm, RedirectAttributes attributes)
          throws UserException {

    var user = mapper.toDTO(userForm);

    service.newUserAccount(user);

    attributes.addFlashAttribute(MessageType.SUCCESS, successMessage(userForm));

    return new ModelAndView(new RedirectView("/users", true));
  }

  @ExceptionHandler(UserException.class)
  public ModelAndView UserException(Exception exception, RedirectAttributes attributes) {

    attributes.addFlashAttribute(MessageType.ERROR, exception.getMessage());

    return new ModelAndView(new RedirectView("/users", true));
  }

  private static String successMessage(UserForm userForm) {
    return String.format(
            "Successfully created new user account '%s' with authority '%s'.",
            userForm.getName(), userForm.getAuthority());
  }
}
