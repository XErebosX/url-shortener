package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.dto.StatisticDto;
import io.twodigits.urlshortener.model.Statistic;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.repository.StatisticRepository;
import io.twodigits.urlshortener.utils.StatisticsDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {
  @Autowired private StatisticRepository statisticRepository;

  @Autowired private StatisticsDtoAssembler statisticsDtoAssembler;

  @Override
  public Statistic saveStatistic(URL url, HttpServletRequest httpServletRequest) {
    Statistic statistic = new Statistic();
    statistic.setDateTime(LocalDateTime.now());
    statistic.setUrlId(url.getId());
    statistic.setUserId(url.getUser());
    statistic.setUserAgent(httpServletRequest.getHeader("user-agent"));
    statistic.setReferrer(httpServletRequest.getHeader("referrer"));
    return statisticRepository.save(statistic);
  }

  @Override
  public StatisticDto getStatisticByUrlId(String urlId) {
    return statisticsDtoAssembler.assembleToStatisticDtoList(
        statisticRepository.getStatisticByUrlId(urlId));
  }

  @Override
  public List<StatisticDto> getStatisticByUserId(String userId) {
    Map<String, List<Statistic>> groupedByUrlIdMap =
        statisticRepository.getStatisticByUserId(userId).stream()
            .collect(Collectors.groupingBy(Statistic::getUrlId));
    return groupedByUrlIdMap.values().stream()
        .map(statisticList -> statisticsDtoAssembler.assembleToStatisticDtoList(statisticList))
        .collect(Collectors.toList());
  }

  @Override
  public StatisticDto getStatisticByUserIdUrlId(String userId, String urlId) {
    return statisticsDtoAssembler.assembleToStatisticDtoList(
        statisticRepository.getStatisticByUserIdAndUrlId(userId, urlId));
  }
}
