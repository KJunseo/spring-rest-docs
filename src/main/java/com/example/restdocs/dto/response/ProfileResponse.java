package com.example.restdocs.dto.response;

public class ProfileResponse {
    private Long id;
    private String name;
    private String imageUrl;

    public ProfileResponse() {
    }

    public ProfileResponse(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
