package kbh.foerdervereinkita.controller;

import kbh.foerdervereinkita.commons.Views;
import kbh.foerdervereinkita.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MembershipController {

  private final MembershipService membershipService;

  @GetMapping("/membership")
  ModelAndView membershipGet() {
    return new ModelAndView(Views.MEMBERSHIP);
  }

  @ResponseBody
  @GetMapping(value = "/membership/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public FileSystemResource declarationOfMembershipDownloadGet() {
    return membershipService.getDeclarationOfMembership();
  }
}
