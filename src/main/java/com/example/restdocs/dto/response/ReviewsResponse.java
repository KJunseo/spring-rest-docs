package com.example.restdocs.dto.response;

import java.util.List;

public class ReviewsResponse {
    private List<ReviewResponse> reviews;

    public ReviewsResponse() {
    }

    public ReviewsResponse(List<ReviewResponse> reviews) {
        this.reviews = reviews;
    }

    public List<ReviewResponse> getReviews() {
        return reviews;
    }
}
