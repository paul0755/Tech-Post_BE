package com.ureka.techpost.domain.test.converter;

import com.ureka.techpost.domain.test.dto.TestResponse;

public class TestConverter {

    public static TestResponse.TestDTO toTestDTO(){
        return TestResponse.TestDTO.builder()
                .testString("This is Test!")
                .build();
    }
}
