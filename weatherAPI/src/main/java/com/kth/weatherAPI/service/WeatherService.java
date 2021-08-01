package com.kth.weatherAPI.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kth.weatherAPI.mapper.WeatherMapper;
import com.kth.weatherAPI.vo.Weather;

@Service
public class WeatherService {
	
	@Autowired WeatherMapper weatherMapper;
	
	public List<Map<String,Object>> getWeatherList(Map<String,Object> map) {
		return weatherMapper.selectWeatherList(map);
	}
	
	public Integer getWeatherPoint(Integer weatherPoint) {
		return weatherMapper.selectWeatherPoint(weatherPoint);
	}
	
	public int addWeather(Weather weather) {
		return weatherMapper.insertWeather(weather);
	}
}
