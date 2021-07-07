package com.example.restdocs.dto.request;

public class ReviewCreateRequest {
    private Long studentId;
    private Long teacherId;
    private String title;
    private String content;
    private String prUrl;

    public ReviewCreateRequest() {
    }

    public ReviewCreateRequest(Long studentId, Long teacherId, String title, String content, String prUrl) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.title = title;
        this.content = content;
        this.prUrl = prUrl;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPrUrl() {
        return prUrl;
    }
}
