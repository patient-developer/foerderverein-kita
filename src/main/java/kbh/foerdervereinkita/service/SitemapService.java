package kbh.foerdervereinkita.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SitemapService {

  @Value("${SITEMAP_FILENAME}")
  private final String sitemapFilename;

  public Resource getSitemapFile() {
    return new ClassPathResource(sitemapFilename);
  }
}
