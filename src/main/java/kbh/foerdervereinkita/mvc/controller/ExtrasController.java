package kbh.foerdervereinkita.mvc.controller;

import java.io.IOException;
import kbh.foerdervereinkita.commons.Views;
import kbh.foerdervereinkita.service.ExtrasService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/extras")
public class ExtrasController {

  private final ExtrasService service;

  @GetMapping
  ModelAndView extrasGet() {
    return new ModelAndView(Views.EXTRAS);
  }

  @ResponseBody
  @GetMapping(path = "/flyer", produces = MediaType.APPLICATION_PDF_VALUE)
  public Resource flyerGet() {
    return service.getFlyer();
  }

  @GetMapping(path = "/internalProjectTemplate")
  public ResponseEntity<Resource> internalProjectTemplate() throws IOException {
    var resource = service.getInternalProjectTemplate();
    var headers = new HttpHeaders();
    headers.setContentDisposition(
            ContentDisposition.inline().filename(resource.getFilename()).build());
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentLength(resource.contentLength());
    return ResponseEntity.ok().headers(headers).body(resource);
  }
}
