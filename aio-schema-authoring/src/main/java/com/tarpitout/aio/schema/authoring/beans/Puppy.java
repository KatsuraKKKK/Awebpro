package com.tarpitout.aio.schema.authoring.beans;

import org.springframework.beans.factory.annotation.Value;

public class Puppy {
    @Value("${puppy.name}")
    private String name;
    @Value("${puppy.color}")
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
