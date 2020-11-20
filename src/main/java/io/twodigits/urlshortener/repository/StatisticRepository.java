package io.twodigits.urlshortener.repository;

import io.twodigits.urlshortener.model.Statistic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticRepository extends CrudRepository<Statistic, String> {

  List<Statistic> getStatisticByUrlId(String urlId);

  List<Statistic> getStatisticByUserId(String userId);

  List<Statistic> getStatisticByUserIdAndUrlId(String userId, String urlId);
}
