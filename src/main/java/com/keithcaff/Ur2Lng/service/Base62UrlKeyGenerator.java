package com.keithcaff.Ur2Lng.service;

import com.keithcaff.Ur2Lng.entity.Url;
import org.springframework.stereotype.Service;

@Service
public class Base62UrlKeyGenerator implements UrlKeyGenerator {

    public static final String BASE_62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final char[] BASE_62_CHARS = BASE_62.toCharArray();
    public static final long BASE_VALUE = 62L;

    @Override
    public String encode(Url url) {
        StringBuilder encodedId = new StringBuilder();
        long id = url.getId();

        if(id == 0) {
            return String.valueOf(BASE_62_CHARS[0]);
        }

        while (id > 0) {
            encodedId.append(BASE_62_CHARS[(int) (id % BASE_VALUE)]);
            id = id / BASE_VALUE;
        }

        return encodedId.reverse().toString();
    }

    @Override
    public long decode(String encodedId) {
        char[] characters = encodedId.toCharArray();
        int length = characters.length;

        long decoded = 0;

        //counter is used to avoid reversing input string
        int counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += BASE_62.indexOf(characters[i]) * Math.pow(BASE_VALUE, length - counter);
            counter++;
        }
        return decoded;
    }

}