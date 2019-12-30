package com.bigidea.twitter.websockets.DTOs;

public class MsgDTO {
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MsgDTO() {}

    public MsgDTO(int id, String content) {
        this.id = id;
        this.content = content;
    }
}
