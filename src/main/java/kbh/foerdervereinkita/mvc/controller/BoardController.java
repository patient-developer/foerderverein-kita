package kbh.foerdervereinkita.mvc.controller;

import java.util.List;
import kbh.foerdervereinkita.commons.Views;
import kbh.foerdervereinkita.mapper.BoardMemberMapper;
import kbh.foerdervereinkita.mvc.model.BoardMemberModel;
import kbh.foerdervereinkita.storage.model.BoardMemberEntity;
import kbh.foerdervereinkita.storage.repository.BoardMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BoardController {

  private final BoardMemberRepository repository;
  private final BoardMemberMapper mapper;

  @GetMapping("/board")
  ModelAndView boardGet(Model model) {

    List<BoardMemberEntity> entities = repository.findAll(Sort.by(Sort.Direction.ASC, "rank"));

    List<BoardMemberModel> models = entities.stream().map(mapper::toBoardMemberModel).toList();

    model.addAttribute("boardMembers", models);

    return new ModelAndView(Views.BOARD);
  }
}
