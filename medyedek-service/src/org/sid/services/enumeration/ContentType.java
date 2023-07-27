package org.sid.services.enumeration;


public enum ContentType {
    TEXT, IMAGE, VIDEO, FILE;

    public static ContentType getById(String id) {
        if (id.equals("1")) {
            return IMAGE;
        }
        return TEXT;
    }
}
