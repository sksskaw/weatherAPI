package com.kth.weatherAPI.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kth.weatherAPI.vo.Weather;

@Mapper
public interface WeatherMapper {
	List<Map<String,Object>> selectWeatherList(Map<String,Object> map);
	Integer selectWeatherPoint(Integer weatherPoint);
	int insertWeather(Weather weather);
}
