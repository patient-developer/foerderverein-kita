package kbh.foerdervereinkita.controller;

import kbh.foerdervereinkita.service.SitemapService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class SitemapController {

  private final SitemapService sitemapService;

  @ResponseBody
  @GetMapping(value = "/sitemap-file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public FileSystemResource sitemapFileGet() {
    return sitemapService.getSitemapFile();
  }
}
