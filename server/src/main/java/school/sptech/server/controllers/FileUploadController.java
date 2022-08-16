package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.server.model.Media;
import school.sptech.server.repository.MediaRepository;
import school.sptech.server.response.FileUploadResponse;
import school.sptech.server.service.FileUploadUtil;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private MediaRepository dbMidiaRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/uploadFile")
    public ResponseEntity<HttpStatus> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        FileUploadResponse response = new FileUploadResponse();


        if (!(dbMidiaRepository.existsByPath("./Files-Upload/" + fileName))) {
            String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

            response.setFileName(fileName);
            response.setSize(size);
            response.setDownloadUri("/downloadFile/" + filecode);
            Media media = new Media(multipartFile.getContentType(), String.format("./Files-Upload/%s", multipartFile.getOriginalFilename()));

            dbMidiaRepository.save(media);
        } else {
            return ResponseEntity.status(409).build();
        }


        return ResponseEntity.status(200).build();
    }

    @GetMapping("/files")
    public ResponseEntity<List<Media>> getAllFiles() {
        List<Media> mediaList = dbMidiaRepository.findAll();
        if (mediaList.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(mediaList);
        }
    }

    
}
