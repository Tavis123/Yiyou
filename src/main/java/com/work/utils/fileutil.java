package com.work.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class fileutil {
    public byte[] fileUtil(byte[] image) throws IOException {

        BASE64Decoder base64Decoder = new BASE64Decoder();
        String value = new String(image, StandardCharsets.UTF_8);
        byte[] bytes = base64Decoder.decodeBuffer(value);
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 0) {
                bytes[i] += 256;
            }
        }
        return bytes;
    }

}