package com.ureka.techpost.domain.test.controller;

import com.ureka.techpost.domain.test.converter.TestConverter;
import com.ureka.techpost.domain.test.dto.TestResponse;
import com.ureka.techpost.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/health")
    public ApiResponse<String> health(){
        return ApiResponse.onSuccess("health check");
    }

    @GetMapping("/test")
    public ApiResponse<TestResponse.TestDTO> testAPI(){

        return ApiResponse.onSuccess(TestConverter.toTestDTO());
    }
}
