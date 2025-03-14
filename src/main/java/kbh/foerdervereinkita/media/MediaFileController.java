package kbh.foerdervereinkita.media;

import java.io.IOException;
import kbh.foerdervereinkita.constants.MessageType;
import kbh.foerdervereinkita.media.exception.MediaFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
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
    model.addAttribute("form", new MediaFileForm());
    return new ModelAndView("media");
  }

  @ResponseBody
  @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  ModelAndView mediaDetailsGetById(@PathVariable("id") long id, Model model) throws IOException {
    var dto = service.fetch(id);
    var mediaFile = mapper.toModel(dto);
    model.addAttribute("mediaFile", mediaFile);
    return new ModelAndView("media-details");
  }

  @GetMapping(value = "/download/{id}")
  ResponseEntity<Resource> downloadGetById(@PathVariable("id") long id) throws IOException {
    var resource = service.fetchResource(id);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentDisposition(
        ContentDisposition.inline().filename(resource.getFilename()).build());
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentLength(resource.contentLength());
    return ResponseEntity.ok().headers(headers).body(resource);
  }

  @GetMapping(value = "/edit/{id}")
  ModelAndView mediaEditGetById(@PathVariable("id") long id, Model model) {
    var dto = service.fetch(id);
    var mediaFile = mapper.toModel(dto);
    model.addAttribute("mediaFile", mediaFile);
    model.addAttribute("form", new MediaFileForm());
    return new ModelAndView("media-edit");
  }

  @PostMapping(value = "/edit")
  ModelAndView mediaEditPost(
      @ModelAttribute("form") MediaFileEditForm form, RedirectAttributes attributes) {
    service.updateDescription(form.getId(), form.getDescription());
    attributes.addFlashAttribute(MessageType.SUCCESS, editSuccessMessage());
    return new ModelAndView(new RedirectView("/media", true));
  }

  @GetMapping(value = "/delete/{id}")
  ModelAndView deleteById(@PathVariable("id") long id, RedirectAttributes attributes) {
    var filename = service.remove(id);
    attributes.addFlashAttribute(MessageType.SUCCESS, deleteSuccessMessage(filename));
    return new ModelAndView(new RedirectView("/media", true));
  }

  @PostMapping
  ModelAndView mediaPost(@ModelAttribute("form") MediaFileForm form, RedirectAttributes attributes)
      throws IOException {
    var dto = mapper.toDTO(form);
    service.persist(dto);
    attributes.addFlashAttribute(MessageType.SUCCESS, uploadSuccessMessage(form));
    return new ModelAndView(new RedirectView("/media", true));
  }

  @ExceptionHandler(MediaFileException.class)
  public ModelAndView handleMediaFileException(
      MediaFileException exception, RedirectAttributes attributes) {
    log.error(exception.getMessage(), exception);
    attributes.addFlashAttribute(MessageType.ERROR, exception.getMessage());
    return new ModelAndView(new RedirectView("/media", true));
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleException(Exception exception, RedirectAttributes attributes) {
    log.error(exception.getMessage(), exception);
    attributes.addFlashAttribute(MessageType.ERROR, "Ups, da ist was schief gegangen.");
    return new ModelAndView(new RedirectView("/media", true));
  }

  private static String uploadSuccessMessage(MediaFileForm form) {
    return String.format(
        "Datei '%s' erfolgreich hochgeladen.", form.getFile().getOriginalFilename());
  }

  private static String deleteSuccessMessage(String fileName) {
    return String.format("Datei '%s' erfolgreich gelöscht.", fileName);
  }

  private static String editSuccessMessage() {
    return "Änderung erfolgreich gespeichert.";
  }
}
