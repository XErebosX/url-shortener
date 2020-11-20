package io.twodigits.urlshortener.utils;

import io.twodigits.urlshortener.dto.RequestDto;
import io.twodigits.urlshortener.dto.StatisticDto;
import io.twodigits.urlshortener.model.Statistic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatisticsDtoAssembler {
  public StatisticDto assembleToStatisticDtoList(List<Statistic> statisticList) {
    StatisticDto statisticDto = new StatisticDto();
    statisticDto.setUrlId(statisticList.get(0).getUrlId());
    statisticDto.setCalls(statisticList.size());
    statisticDto.setRequest(assembleRequestDtoList(statisticList));
    return statisticDto;
  }

  public List<RequestDto> assembleRequestDtoList(List<Statistic> statisticList) {
    return statisticList.stream()
        .map(
            statistic -> {
              RequestDto requestDto = new RequestDto();
              requestDto.setDateTime(statistic.getDateTime());
              requestDto.setReferrer(statistic.getReferrer());
              requestDto.setUserAgent(statistic.getUserAgent());
              return requestDto;
            })
        .collect(Collectors.toList());
  }
}
