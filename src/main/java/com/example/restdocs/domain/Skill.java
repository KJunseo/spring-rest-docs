package com.example.restdocs.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "skill")
    private List<TeacherSkill> teachers = new ArrayList<>();

    protected Skill() {
    }
}
