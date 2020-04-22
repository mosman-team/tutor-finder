package com.mosman.tutorfinderapp.dtos;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
