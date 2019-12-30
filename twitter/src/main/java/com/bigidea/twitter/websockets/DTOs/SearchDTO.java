package com.bigidea.twitter.websockets.DTOs;

import com.bigidea.twitter.classes.User.User;
import java.util.List;

public class SearchDTO {
    private int position;
    private String element;
    private List<User> result;

    public SearchDTO(){}

    public SearchDTO(List<User> result) {
        this.result = result;
    }

    public SearchDTO(int position, String element) {
        this.position = position;
        this.element = element;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
