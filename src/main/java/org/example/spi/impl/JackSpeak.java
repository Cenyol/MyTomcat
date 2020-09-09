package org.example.spi.impl;

import org.example.spi.Speak;

public class JackSpeak implements Speak {
    @Override
    public void speak(String content) {
        System.out.println("Jack Speak: " + content);
    }
}
