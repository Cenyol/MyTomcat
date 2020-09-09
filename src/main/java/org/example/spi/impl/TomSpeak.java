package org.example.spi.impl;

import org.example.spi.Speak;

public class TomSpeak implements Speak {
    @Override
    public void speak(String content) {
        System.out.println("Tom Speak: " + content);
    }
}
