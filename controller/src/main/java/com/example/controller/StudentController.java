package com.example.controller;

import com.example.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {

    private final Service service;

    @GetMapping("get")
    String get() throws IOException {

        service.create();

        return """
                student {
                "id": 1,
                "name": "ahmed"
                }
                """;
    }
}
