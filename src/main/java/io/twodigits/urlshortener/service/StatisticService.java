package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.dto.StatisticDto;
import io.twodigits.urlshortener.model.Statistic;
import io.twodigits.urlshortener.model.URL;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StatisticService {

  Statistic saveStatistic(URL url, HttpServletRequest httpServletRequest);

  StatisticDto getStatisticByUrlId(String urlId);

  List<StatisticDto> getStatisticByUserId(String userId);

  StatisticDto getStatisticByUserIdUrlId(String userId, String urlId);
}
