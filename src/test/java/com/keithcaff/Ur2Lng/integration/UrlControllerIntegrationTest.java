package com.keithcaff.Ur2Lng.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;
import com.keithcaff.Ur2Lng.repository.UrlRepository;
import com.keithcaff.Ur2Lng.service.UrlKeyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class UrlControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UrlKeyGenerator keyGenerator;

    @Test
    void shortenUrlWorksThroughAllLayers() throws Exception {
        UrlDto dto = new UrlDto("https://github.com/keithcaff");

        MvcResult result = mockMvc.perform(post("/ur2lng")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated()).andReturn();

        String uri = result.getResponse().getHeader(HttpHeaders.LOCATION);
        String encodedId = uri.substring(uri.lastIndexOf("/")+1);
        long decodedId = keyGenerator.decode(encodedId);

        Optional<Url> url = urlRepository.findById(decodedId);
        assertEquals(dto.getLongUrl(),url.get().getLongUrl());
    }

}
