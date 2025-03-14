package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {


    @GetMapping("get")
    String get() {

        return """
                student {
                "id": 1,
                "name": "ahmed"
                }
                """;
    }
}
