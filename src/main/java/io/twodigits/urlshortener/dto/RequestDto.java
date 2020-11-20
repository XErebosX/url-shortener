package io.twodigits.urlshortener.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class RequestDto {

  private String userAgent;

  private String referrer;
  private LocalDateTime dateTime;

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
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
    RequestDto that = (RequestDto) o;
    return Objects.equals(userAgent, that.userAgent) &&
            Objects.equals(referrer, that.referrer) &&
            Objects.equals(dateTime, that.dateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userAgent, referrer, dateTime);
  }
}
