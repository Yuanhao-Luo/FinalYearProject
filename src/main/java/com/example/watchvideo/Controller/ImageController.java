package com.example.watchvideo.Controller;

import com.example.watchvideo.Model.ImageTable;
import com.example.watchvideo.DAO.ImageTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    private ImageTableRepository imageTR;

    @Value("${file.upload.path}")
    private String storage_path;

    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String id) throws IOException {
        System.out.println("id: " + id);
        ImageTable imageTable = imageTR.findByIdEquals(Integer.parseInt(id));
        String path = storage_path + imageTable.getFilePath() + "\\" + imageTable.getFileName();


        Resource fileResource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .contentLength(fileResource.contentLength())
                .body(fileResource);
    }
}
