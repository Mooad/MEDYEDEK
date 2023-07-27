package org.sid.services.utilities;

import org.springframework.stereotype.Component;
import java.net.URL;

@Component
public class SomeUsefulMethods {

    public static URL loadFromResources(ClassLoader classLoader) {
        URL resource = classLoader.getResource("Confirmation-Template.txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + "Confirmation-Template.txt");
        }
        return resource;
    }
}

