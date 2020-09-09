package org.example.spi;

import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpeakTest {

    @Test
    public void testSpeak() {
        ServiceLoader<Speak> speakServiceLoader = ServiceLoader.load(Speak.class);
        Iterator<Speak> search = speakServiceLoader.iterator();
        while (search.hasNext()) {
            Speak speak = search.next();
            speak.speak("hello!");
        }
    }
}
