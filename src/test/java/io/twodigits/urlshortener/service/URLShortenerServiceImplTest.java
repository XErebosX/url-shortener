package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.repository.URLRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class URLShortenerServiceImplTest {
    @Mock
    private URLRepository urlRepository;

    @InjectMocks
    private URLShortenerServiceImpl urlShortenerService;

    private URL urlObject;

    @Before
    public void setup() {
        urlObject = new URL();
        urlObject.setId("1");
        urlObject.setUser("User");
        urlObject.setUrl("https:jhfjfjhfhjf");
    }

    @Test
    public void listURLs() {
        when(urlRepository.findByUser("User")).thenReturn(List.of(urlObject));
        Iterable<URL> expectedResult = List.of(urlObject);
        assertEquals(expectedResult, urlShortenerService.listURLs("User"));
    }

    @Test
    public void addURL() {
        when(urlRepository.save(any())).thenReturn(urlObject);
        when(urlRepository.findById(anyString())).thenReturn(Optional.of(urlObject)).thenReturn(Optional.empty());
        assertEquals(urlObject, urlShortenerService.addURL("User", "1"));
        Mockito.verify(urlRepository, times(2)).findById(anyString());
    }

    @Test
    public void getURLStringIdUrlId() {
        when(urlRepository.findByIdAndUser(anyString(), anyString())).thenReturn(Optional.of(urlObject));
        assertEquals(Optional.of(urlObject), urlShortenerService.getURL(anyString(), anyString()));
    }

    @Test
    public void getURLUrlId() {
        when(urlRepository.findById(anyString())).thenReturn(Optional.of(urlObject));
        assertEquals(Optional.of(urlObject), urlShortenerService.getURL(anyString()));
    }

    @Test
    public void updateURL() {
        when(urlRepository.save(any())).thenReturn(urlObject);
        assertEquals(urlObject, urlShortenerService.updateURL("User", "https:jhfjfjhfhjf", "1"));
    }

    @Test
    public void deleteURL() {
        when(urlRepository.findByIdAndUser(anyString(), anyString())).thenReturn(Optional.of(urlObject));
        urlShortenerService.deleteURL("User", "1");
        Mockito.verify(urlRepository, times(1)).findByIdAndUser(anyString(), anyString());
        Mockito.verify(urlRepository, times(1)).delete(any());
    }
}