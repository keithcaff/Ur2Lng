package com.keithcaff.Ur2Lng.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter @Setter @NoArgsConstructor
public class UrlDto {
    @NotBlank
    private String longUrl;
}
