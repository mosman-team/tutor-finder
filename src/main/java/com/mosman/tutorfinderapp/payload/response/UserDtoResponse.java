package com.mosman.tutorfinderapp.payload.response;

public class UserDtoResponse {

    private String img;

    public UserDtoResponse(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
