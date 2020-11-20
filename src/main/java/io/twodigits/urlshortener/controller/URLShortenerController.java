package io.twodigits.urlshortener.controller;

import io.twodigits.urlshortener.config.BadRequestException;
import io.twodigits.urlshortener.dto.URLDto;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.service.StatisticService;
import io.twodigits.urlshortener.service.URLShortenerService;
import io.twodigits.urlshortener.utils.URLValidator;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
public class URLShortenerController {

  @Autowired private URLShortenerService urlShortenerService;

  @Autowired private StatisticService statisticService;

  @Autowired private URLValidator urlValidator;

  @GetMapping("/{urlId}")
  public ResponseEntity<Void> getUrlByUrlId(
      @PathVariable String urlId, HttpServletRequest httpServletRequest) throws NotFoundException {
    URL url =
        urlShortenerService
            .getURL(urlId)
            .orElseThrow(() -> new NotFoundException("URL WITH ID:" + urlId + " NOT FOUND"));
    statisticService.saveStatistic(url, httpServletRequest);
    return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
        .location(URI.create(url.getUrl()))
        .build();
  }

  @GetMapping("/users/{userId}/urls")
  public Iterable<URL> getUserUrls(@PathVariable String userId) {
    return urlShortenerService.listURLs(userId);
  }

  @PostMapping("/users/{userId}/urls")
  public ResponseEntity<Void> addUrlByUserId(
      @RequestBody URLDto urlObject, @PathVariable String userId) {
    if (urlValidator.validateUrl(urlObject.getUrl())) {
      urlShortenerService.addURL(userId, urlObject.getUrl());
    } else {
      throw new BadRequestException("The URL is not validate!");
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/users/{userId}/urls/{urlId}")
  public URL getUrlByUrlIdAndUserId(@PathVariable String userId, @PathVariable String urlId)
      throws NotFoundException {
    return urlShortenerService
        .getURL(userId, urlId)
        .orElseThrow(() -> new NotFoundException("URL WITH ID:" + urlId + " NOT FOUND"));
  }

  @PutMapping("/users/{userId}/urls/{urlId}")
  public ResponseEntity<Void> editUrl(
      @RequestBody URLDto urlObject, @PathVariable String userId, @PathVariable String urlId) {
    if (urlValidator.validateUrl(urlObject.getUrl())) {
      urlShortenerService.updateURL(userId, urlObject.getUrl(), urlId);
    } else {
      throw new BadRequestException("The URL is not validate!");
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/users/{userId}/urls/{urlId}")
  public ResponseEntity<Void> deleteUrl(@PathVariable String userId, @PathVariable String urlId) {
    urlShortenerService.deleteURL(userId, urlId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
