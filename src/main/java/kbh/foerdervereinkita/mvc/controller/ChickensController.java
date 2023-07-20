package kbh.foerdervereinkita.mvc.controller;

import java.util.Collection;
import kbh.foerdervereinkita.mapper.PictureMapper;
import kbh.foerdervereinkita.mvc.model.PictureModel;
import kbh.foerdervereinkita.storage.model.PictureEntity;
import kbh.foerdervereinkita.storage.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ChickensController {

  private static final String EVENT_KEY = "kueken-2023";

  private final PictureRepository repository;
  private final PictureMapper mapper;

  @GetMapping("/kueken")
  ModelAndView chicksGet(Model model) {

    Collection<PictureEntity> entities = repository.findAllByEventKeyOrderByRank(EVENT_KEY);

    Collection<PictureModel> pictures = entities.stream().map(mapper::toModel).toList();

    model.addAttribute("pictures", pictures);

    return new ModelAndView("chickens");
  }
}
