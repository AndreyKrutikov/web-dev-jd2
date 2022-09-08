package by.krutikov.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class UUIDGenerator {
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
