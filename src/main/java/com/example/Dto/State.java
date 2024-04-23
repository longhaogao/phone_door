package com.example.Dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class State {
    private int state_code;
    private String state_msg;

    // Convert object to JSON string
    public String toJSONString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"state_code\": 500, \"state_msg\": \"Failed to serialize state object to JSON.\"}";
        }
    }

}
