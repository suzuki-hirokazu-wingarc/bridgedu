package wat.f.bridgedu.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;

@Controller
@RequestMapping("public")
@NoArgsConstructor
public class PublicResourceController {
    @GetMapping("favicon")
    public ResponseEntity<Resource> serveStaticFile() {
        Resource resource = new ClassPathResource("static/favicon.png");
        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
