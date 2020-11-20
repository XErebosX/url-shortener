package io.twodigits.urlshortener.controller;

import io.twodigits.urlshortener.dto.StatisticDto;
import io.twodigits.urlshortener.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {

  @Autowired private StatisticService statisticService;

  @GetMapping("/statistics/users/{userId}")
  public List<StatisticDto> getListByUser(@PathVariable String userId) {
    return statisticService.getStatisticByUserId(userId);
  }

  @GetMapping("/statistics/urls/{urlId}")
  public StatisticDto getListByUrlId(@PathVariable String urlId) {
    return statisticService.getStatisticByUrlId(urlId);
  }

  @GetMapping("/statistics/users/{userId}/urls/{urlId}")
  public StatisticDto getListByUserIdUrlId(
      @PathVariable String userId, @PathVariable String urlId) {
    return statisticService.getStatisticByUserIdUrlId(userId, urlId);
  }
}
