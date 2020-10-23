package com.keithcaff.Ur2Lng.service;

import com.keithcaff.Ur2Lng.entity.Url;

public interface UrlKeyGenerator {

    String encode(Url url);
    long decode(String encodeId);
}
