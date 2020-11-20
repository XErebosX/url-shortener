package io.twodigits.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Statistic {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String urlId;

  private String userId;

  private String userAgent;

  private LocalDateTime dateTime;

  private String referrer;

  public String getUrlId() {
    return urlId;
  }

  public void setUrlId(String urlId) {
    this.urlId = urlId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public String getReferrer() {
    return referrer;
  }

  public void setReferrer(String referrer) {
    this.referrer = referrer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Statistic statistic = (Statistic) o;
    return Objects.equals(id, statistic.id) &&
            Objects.equals(urlId, statistic.urlId) &&
            Objects.equals(userId, statistic.userId) &&
            Objects.equals(userAgent, statistic.userAgent) &&
            Objects.equals(dateTime, statistic.dateTime) &&
            Objects.equals(referrer, statistic.referrer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, urlId, userId, userAgent, dateTime, referrer);
  }
}
