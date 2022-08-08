package school.sptech.server.service;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
    private MultipartFile file;

    public UploadFile() {
    }

    public UploadFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
