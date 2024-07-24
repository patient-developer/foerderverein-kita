package kbh.foerdervereinkita.mvc.controller;

import kbh.foerdervereinkita.service.KindergartenService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/kindergarten")
public class KindergartenController {

    private final KindergartenService service;

    @GetMapping
    public ModelAndView kindergartenGet() {
        return new ModelAndView("kindergarten");
    }

    @ResponseBody
    @GetMapping(value = "/video", produces = "video/mp4")
    public Mono<Resource> kindergartenVideoGet() {
        return service.fetchVideo();
    }
}
