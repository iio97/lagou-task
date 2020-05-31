package com.lagou.edu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    public static final String DEFAULT_SUBJECT = "Your Register Code!";
    public static final String DEFAULT_CONTENT = "Thanks for register. Your code is :";

    private String to;
    private String from;
    private String subject;
    private String content;

}
