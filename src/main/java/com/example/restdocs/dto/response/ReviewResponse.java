package com.example.restdocs.dto.response;


import com.example.restdocs.domain.Progress;

public class ReviewResponse {
    private Long id;
    private String title;
    private String content;
    private Progress progress;
    private ProfileResponse teacherProfile;
    private ProfileResponse studentProfile;

    public ReviewResponse() {
    }

    public ReviewResponse(Long id, String title, String content, Progress progress, ProfileResponse teacherProfile, ProfileResponse studentProfile) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.progress = progress;
        this.teacherProfile = teacherProfile;
        this.studentProfile = studentProfile;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Progress getProgress() {
        return progress;
    }

    public ProfileResponse getTeacherProfile() {
        return teacherProfile;
    }

    public ProfileResponse getStudentProfile() {
        return studentProfile;
    }
}
