package kbh.foerdervereinkita.media;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class MediaFileForm {

    MultipartFile file;
    String description;
}
