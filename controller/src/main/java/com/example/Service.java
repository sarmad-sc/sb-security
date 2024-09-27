package com.example;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@org.springframework.stereotype.Service
public class Service {

    public void create() throws IOException {

        FileReader file = new FileReader("nonexistent.txt");
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = bufferedReader.readLine();
        bufferedReader.close();
    }
}
