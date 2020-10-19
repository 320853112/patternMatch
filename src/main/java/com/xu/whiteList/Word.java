package com.xu.whiteList;

import lombok.Data;

@Data
public class Word {

    private String content;

    private Integer score;

    public Word(String content, Integer score) {
        this.content = content;
        this.score = score;
    }
}
