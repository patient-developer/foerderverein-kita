package kbh.foerdervereinkita.parentcommittee;

import kbh.foerdervereinkita.constants.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/kindergarten")
public class ParentCommitteeController {

    @GetMapping(path = "/elternausschuss")
    public ModelAndView parentCommitteeGet() {
        return new ModelAndView("parent-committee");
    }

    @GetMapping(path = "/elternausschuss/nachricht")
    public ModelAndView parentCommitteeMessageGet(Model model) {
        model.addAttribute("form", new ParentCommitteeForm());
        return new ModelAndView("parent-committee-message");
    }

    @PostMapping(path = "/elternausschuss/nachricht")
    public ModelAndView parentCommitteeMessagePost(
            @ModelAttribute("form") ParentCommitteeForm form, RedirectAttributes attributes) {
        log.info("Received message: '{}'", form.getMessage());
        attributes.addFlashAttribute(MessageType.SUCCESS, "Vielen Dank für Ihre Nachricht.");
        return new ModelAndView(new RedirectView("/kindergarten/elternausschuss/nachricht"));
    }
}
