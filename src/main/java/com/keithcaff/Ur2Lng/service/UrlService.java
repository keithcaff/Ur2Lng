package com.keithcaff.Ur2Lng.service;

import com.keithcaff.Ur2Lng.entity.Url;

import java.util.Optional;

public interface UrlService {

    Optional<Url> getUrl(long id);
    Url saveUrl(Url student);
}