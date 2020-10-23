package com.keithcaff.Ur2Lng.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keithcaff.Ur2Lng.controller.UrlController;
import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;
import com.keithcaff.Ur2Lng.exceptions.UrlNotFoundException;
import com.keithcaff.Ur2Lng.service.UrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UrlController.class)
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;

    @Test
    @DisplayName("Post /ur2lng with valid URL responds with encodedId")
    public void shortenValidUrl() throws Exception {
        //given
        UrlDto dto = new UrlDto("https://github.com/keithcaff");
        String encodedId = "d";
        when(urlService.shortenUrl(dto)).thenReturn(encodedId);
        //when/then
        mockMvc.perform(post("/ur2lng")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/ur2lng/d"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Post /ur2lng with invalid URL responds with 400 BAD REQUEST")
    public void shortenInValidUrl() throws Exception {
        //given
        UrlDto dto = new UrlDto("abc123");
        //when/then
        mockMvc.perform(post("/ur2lng")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(content().string("Invalid Url!"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get /ur2lng with valid encodedUrlId redirects to longUrl")
    public void getUrlAndRedirect() throws Exception {
        //given
        String encodedId = "d";
        String longUrl = "https://github.com/keithcaff";
        Url persistedUrl = new Url(3L, longUrl);
        when(urlService.getUrl(encodedId)).thenReturn(persistedUrl);

        //when/then
        mockMvc.perform(get("/ur2lng/{id}", encodedId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", longUrl))
                .andExpect(status().isMovedPermanently());
    }

    @Test
    @DisplayName("Get /ur2lng with invalid encodedUrlId returns 400")
    public void getUrlAndRedirectWithInvalidId() throws Exception {
        //given
        String encodedId = "someInvalidId";
        when(urlService.getUrl(encodedId)).thenThrow(new UrlNotFoundException("URL not found"));

        //when/then
        mockMvc.perform(get("/ur2lng/{id}", encodedId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
