package com.example.restdocs.restdocs.common;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.restdocs.domain.Progress;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnumController {

    @GetMapping("/progress")
    public Progresses findAll() {
        return new Progresses(Arrays.stream(Progress.values())
                     .collect(Collectors.toMap(Progress::name, Progress::getDescription)));
    }

    static class Progresses {
        private final Map<String, String> progresses;

        public Progresses(Map<String, String> progresses) {
            this.progresses = progresses;
        }

        public Map<String, String> getProgresses() {
            return progresses;
        }
    }
}
