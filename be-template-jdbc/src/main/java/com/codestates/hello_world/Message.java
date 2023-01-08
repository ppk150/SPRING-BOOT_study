package com.codestates.hello_world;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
public class Message {//1

    @Id//2
    private long messageId;
    private String message;
}
