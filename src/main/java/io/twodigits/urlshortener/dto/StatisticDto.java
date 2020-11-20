package io.twodigits.urlshortener.dto;

import java.util.List;
import java.util.Objects;

public class StatisticDto {

  private String urlId;

  private Integer calls;

  private List<RequestDto> request;

  public String getUrlId() {
    return urlId;
  }

  public void setUrlId(String urlId) {
    this.urlId = urlId;
  }

  public Integer getCalls() {
    return calls;
  }

  public void setCalls(Integer calls) {
    this.calls = calls;
  }

  public List<RequestDto> getRequest() {
    return request;
  }

  public void setRequest(List<RequestDto> request) {
    this.request = request;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StatisticDto that = (StatisticDto) o;
    return Objects.equals(urlId, that.urlId) &&
            Objects.equals(calls, that.calls) &&
            Objects.equals(request, that.request);
  }

  @Override
  public int hashCode() {
    return Objects.hash(urlId, calls, request);
  }
}
