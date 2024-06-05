package kbh.foerdervereinkita.media;

import kbh.foerdervereinkita.constants.MessageType;
import kbh.foerdervereinkita.media.exception.MediaFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/media")
public class MediaFileController {

    private final MediaFileService service;
    private final MediaFileMapper mapper;

    @GetMapping
    ModelAndView mediaGet(Model model) {
        var mediaFiles = service.fetchAll().stream().map(mapper::toModel).toList();
        model.addAttribute("mediaFiles", mediaFiles);
        return new ModelAndView("media");
    }

    @PostMapping
    ModelAndView mediaPost(
            @RequestParam("mediaFile") MultipartFile file, RedirectAttributes attributes) {
        service.persist(file);
        attributes.addFlashAttribute(MessageType.SUCCESS, successMessage(file));
        return new ModelAndView(new RedirectView("/media", true));
    }

    @ExceptionHandler(MediaFileException.class)
    public ModelAndView UserException(Exception exception, RedirectAttributes attributes) {
        attributes.addFlashAttribute(MessageType.ERROR, exception.getMessage());
        return new ModelAndView(new RedirectView("/media", true));
    }

    private static String successMessage(MultipartFile file) {
        return String.format("Datei '%s' erfolgreich hochgeladen.", file.getOriginalFilename());
    }
}
