package com.krzkrusz.volunteer.volunteer.controller;

import com.krzkrusz.volunteer.volunteer.model.ComputationResult;
import com.krzkrusz.volunteer.volunteer.model.FunctionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class TaskController {

    public static String GOLDBACH_PATH = "src/main/resources/goldbach_conjecture.js";
    public static String FLOPS_PATH = "src/main/resources/flops.js";
    public static String BUBBLE_SORT_PATH = "src/main/resources/bubbleSort.js";

    @GetMapping("/goldbach")
    public ResponseEntity<FunctionResponse> getGoldbach() {
        FunctionResponse functionResponse = new FunctionResponse(
                12345L,
                readFileToString(GOLDBACH_PATH),
                "goldbach",
                new Object[]{"50000"});
        return new ResponseEntity<>(functionResponse, HttpStatus.OK);
    }

    @GetMapping("/flops")
    public ResponseEntity<FunctionResponse> getFlops() {
        FunctionResponse functionResponse = new FunctionResponse(
                12345L,
                readFileToString(FLOPS_PATH),
                "flops_main",
                null);
        return new ResponseEntity<>(functionResponse, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<FunctionResponse> getSort() {
        FunctionResponse functionResponse = new FunctionResponse(
                12345L,
                readFileToString(BUBBLE_SORT_PATH),
                "sort",
                null);
        return new ResponseEntity<>(functionResponse, HttpStatus.OK);
    }

    @PostMapping("/result")
    public ResponseEntity<ComputationResult> newComputationResult(@RequestBody ComputationResult newComputationResult) {
        return new ResponseEntity<>(newComputationResult, HttpStatus.OK);
    }

    private static String readFileToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
