package kbh.foerdervereinkita.mvc.controller;

import kbh.foerdervereinkita.commons.Views;
import kbh.foerdervereinkita.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class SupportController {

  private final MembershipService membershipService;

  @GetMapping("/unterstuetzung")
  ModelAndView membershipGet() {
    return new ModelAndView(Views.SUPPORT);
  }

  @ResponseBody
  @GetMapping(value = "/unterstuetzung/download", produces = MediaType.APPLICATION_PDF_VALUE)
  public Resource declarationOfMembershipDownloadGet() {
    return membershipService.getDeclarationOfMembership();
  }
}
