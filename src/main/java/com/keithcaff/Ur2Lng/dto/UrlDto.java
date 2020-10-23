package com.keithcaff.Ur2Lng.dto;

import com.keithcaff.Ur2Lng.validators.UrlConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @EqualsAndHashCode
public class UrlDto implements Serializable {

    public UrlDto(String longUrl) {
        this.longUrl = longUrl;
    }

    @UrlConstraint(message = "Invalid Url!")
    private String longUrl;
}
