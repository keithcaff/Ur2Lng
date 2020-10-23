package com.keithcaff.Ur2Lng.unit;

import com.keithcaff.Ur2Lng.dto.UrlDto;
import com.keithcaff.Ur2Lng.entity.Url;
import com.keithcaff.Ur2Lng.repository.UrlRepository;
import com.keithcaff.Ur2Lng.service.UrlKeyGenerator;
import com.keithcaff.Ur2Lng.service.UrlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    private UrlServiceImpl urlService;

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private UrlKeyGenerator urlKeyGenerator;

    @BeforeEach
    void init() {
        urlService = new UrlServiceImpl(urlRepository, urlKeyGenerator);
    }

    @Test
    @DisplayName("Should save Url and encode when persisted")
    public void saveUrlTest() {
        //given
        String longUrl = "https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener";
        UrlDto dto = new UrlDto(longUrl);
        Url persistedUrl = new Url(1L, longUrl);
        when(urlRepository.save(any(Url.class))).thenReturn(persistedUrl);

        //when
        urlService.shortenUrl(dto);

        //then
        verify(urlRepository, times(1)).save(new Url(longUrl));
        verify(urlKeyGenerator, times(1)).encode(persistedUrl);
    }

    @Test
    @DisplayName("Should NOT save Url when already persisted")
    public void saveExistingUrlTest() {
        //given
        String longUrl = "https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener";
        UrlDto dto = new UrlDto(longUrl);
        Url persistedUrl = new Url(1L, longUrl);
        when(urlRepository.findByLongUrl(longUrl)).thenReturn(Optional.of(persistedUrl));

        //when
        urlService.shortenUrl(dto);

        //then
        verify(urlRepository, times(0)).save(new Url(longUrl));
        verify(urlKeyGenerator, times(1)).encode(persistedUrl);
    }

    @Test
    @DisplayName("Should retrieve long url from DB when valid encodedId is passed")
    public void getLongUrlFromEncodedId() {

        //given
        String longUrl = "https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener";
        String encodeId = "b";
        Url persistedUrl = new Url(1L, longUrl);
        when(urlRepository.findById(1L)).thenReturn(Optional.of(persistedUrl));
        when(urlKeyGenerator.decode(encodeId)).thenReturn(1L);

        //when
        Url url = urlService.getUrl("b");

        //then
        verify(urlKeyGenerator, times(1)).decode(encodeId);
        verify(urlRepository, times(1)).findById(1L);
        assertEquals(url, persistedUrl);
    }
}
