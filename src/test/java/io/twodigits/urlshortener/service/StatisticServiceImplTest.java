package io.twodigits.urlshortener.service;


import io.twodigits.urlshortener.dto.RequestDto;
import io.twodigits.urlshortener.dto.StatisticDto;
import io.twodigits.urlshortener.model.Statistic;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.repository.StatisticRepository;
import io.twodigits.urlshortener.repository.URLRepository;
import io.twodigits.urlshortener.utils.StatisticsDtoAssembler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceImplTest {

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Mock
    private StatisticRepository statisticRepository;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Spy
    private StatisticsDtoAssembler statisticsDtoAssembler;

    private URL url;

    private Statistic statistic;

    private StatisticDto statisticDto;

    private List<StatisticDto> statisticDtoList;

    private RequestDto requestDto;

    private List<RequestDto> requestDtoList;

    private List<Statistic> statisticList;

    @Before
    public void setup() {
        statistic = new Statistic();
        statistic.setDateTime(null);
        statistic.setUrlId("1");
        statistic.setUserId("007");
        statistic.setUserAgent("Firefox");
        statistic.setReferrer("Null");
        statisticList = new ArrayList<Statistic>();
        statisticList.add(statistic);

        requestDto = new RequestDto();
        requestDto.setUserAgent("Firefox");
        requestDto.setReferrer("Null");
        requestDto.setDateTime(null);
        requestDtoList = new ArrayList<RequestDto>();
        requestDtoList.add(requestDto);

        statisticDto = new StatisticDto();
        statisticDto.setRequest(requestDtoList);
        statisticDto.setCalls(1);
        statisticDto.setUrlId("1");

        statisticDtoList = new ArrayList<StatisticDto>();
        statisticDtoList.add(statisticDto);

        url = new URL();
        url.setUrl("https...");
    }

    @Test
    public void saveStatistic() {
        when(statisticRepository.save(any())).thenReturn(statistic);
        assertEquals(statistic, statisticService.saveStatistic(url, httpServletRequest));
        Mockito.verify(statisticRepository, times(1)).save(any());
    }

    @Test
    public void getStatisticByUrlId() {
        when(statisticRepository.getStatisticByUrlId(any())).thenReturn(statisticList);
        assertEquals(statisticDto, statisticService.getStatisticByUrlId("1"));
    }

    @Test
    public void getStatisticByUserId() {
        when(statisticRepository.getStatisticByUserId(any())).thenReturn(statisticList);
        assertEquals(statisticDtoList, statisticService.getStatisticByUserId("007"));
    }

    @Test
    public void getStatisticByUserIdUrlId() {
        when(statisticRepository.getStatisticByUserIdAndUrlId(any(), any())).thenReturn(statisticList);
        assertEquals(statisticDto, statisticService.getStatisticByUserIdUrlId("007", "1"));
    }
}