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

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private MediaRepository dbMidiaRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + filecode);

        System.out.println(multipartFile.getContentType());

        Media media = new Media(1,multipartFile.getContentType(), String.format("./Files-Upload/%s", multipartFile.getName()));

        dbMidiaRepository.save(media);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
