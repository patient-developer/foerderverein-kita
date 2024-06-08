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
import org.springframework.web.multipart.MultipartFile;
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
    return new ModelAndView("media");
  }

  @ResponseBody
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  Resource mediaGetById(@PathVariable("id") long id) {
    return service.fetch(id);
  }

  @GetMapping(value = "/download/{id}")
  ResponseEntity<Resource> downloadGetById(@PathVariable("id") long id) throws IOException {
    var resource = service.fetch(id);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentDisposition(
            ContentDisposition.inline().filename(resource.getFilename()).build());
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentLength(resource.contentLength());
    return ResponseEntity.ok().headers(headers).body(resource);
  }

  @GetMapping(value = "/delete/{id}")
  ModelAndView deleteById(@PathVariable("id") long id, RedirectAttributes attributes) {
    var filename = service.remove(id);
    attributes.addFlashAttribute(MessageType.SUCCESS, deleteSuccessMessage(filename));
    return new ModelAndView(new RedirectView("/media", true));
  }

  @PostMapping
  ModelAndView mediaPost(
      @RequestParam("mediaFile") MultipartFile file, RedirectAttributes attributes) {
    service.persist(file);
    attributes.addFlashAttribute(MessageType.SUCCESS, uploadSuccessMessage(file));
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

  private static String uploadSuccessMessage(MultipartFile file) {
    return String.format("Datei '%s' erfolgreich hochgeladen.", file.getOriginalFilename());
  }

  private static String deleteSuccessMessage(String fileName) {
    return String.format("Datei '%s' erfolgreich gelöscht.", fileName);
  }
}
