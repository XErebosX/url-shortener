package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.repository.URLRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class URLShortenerServiceImpl implements URLShortenerService {
  @Autowired private URLRepository repository;

  @Override
  public Iterable<URL> listURLs(String user) {
    return repository.findByUser(user);
  }

  @Override
  public URL addURL(String user, String url) {
    String urlId;
    do {
      urlId = generateUrlId();
    } while (repository.findById(urlId).isPresent());

    URL urlObject = new URL();
    urlObject.setId(urlId);
    urlObject.setUrl(url);
    urlObject.setUser(user);
    return repository.save(urlObject);
  }

  @Override
  public Optional<URL> getURL(String user, String id) {
    return repository.findByIdAndUser(id, user);
  }

  @Override
  public Optional<URL> getURL(String id) {
    return repository.findById(id);
  }

  @Override
  public URL updateURL(String user, String url, String urlId) {
    URL urlObject = new URL();
    urlObject.setId(urlId);
    urlObject.setUrl(url);
    urlObject.setUser(user);
    return repository.save(urlObject);
  }

  @Override
  public void deleteURL(String user, String id) {
    repository.findByIdAndUser(id, user).ifPresent(url -> repository.delete(url));
  }

  private String generateUrlId() {
    return RandomStringUtils.randomAlphanumeric(5);
  }
}
