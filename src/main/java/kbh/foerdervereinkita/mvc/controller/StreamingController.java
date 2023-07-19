package kbh.foerdervereinkita.mvc.controller;

import kbh.foerdervereinkita.service.StreamingService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class StreamingController {

  private StreamingService service;

  @ResponseBody
  @GetMapping(path = "/videos/{filename}", produces = "video/mp4")
  public Mono<Resource> videosGet(@PathVariable("filename") String filename) {
    return service.fetchVideo(filename);
  }
}
