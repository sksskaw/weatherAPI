package com.kth.weatherAPI.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kth.weatherAPI.service.WeatherService;
import com.kth.weatherAPI.vo.Weather;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WeatherRestController {
	
	@Autowired WeatherService weatherService;
	
	@RequestMapping(value="/getWeatherList", method= RequestMethod.GET)
	public List<Map<String, Object>> getWeatherList(
			@RequestParam(value="weatherPoint", required = false) Integer weatherPoint,
			@RequestParam(value="startDateTime", required = false) String startDateTime,
			@RequestParam(value="endDateTime", required = false) String endDateTime){
		
		// 등록되지 않은 지점코드 입력 시
		if(weatherPoint != null && weatherService.getWeatherPoint(weatherPoint) == null) {
			List<Map<String,Object>> errorList = new ArrayList<>();
			Map<String,Object> error = new HashMap<>();
			error.put("errorCode", "01");
			error.put("errorMessage", "INVALID_REQUEST_PARAMETER_ERROR");
			errorList.add(error);
			
			return errorList;
		}
		
		Map<String,Object> paramMap = new HashMap<>();
		
		if(weatherPoint != null) {
			log.debug("[WeatherRestController] [getWeatherList] [Param] weatherPoint : " + weatherPoint);
			paramMap.put("weatherPoint", weatherPoint);
		}
		
		if(startDateTime != null) {
			log.debug("[WeatherRestController] [getWeatherList] [Param] startDateTime : " + startDateTime);
			paramMap.put("startDateTime", startDateTime);
		}
		
		if(startDateTime != null) {
			log.debug("[WeatherRestController] [getWeatherList] [Param] endDateTime : " + endDateTime);
			paramMap.put("endDateTime", endDateTime);
		}
		
		List<Map<String,Object>> weatherList = weatherService.getWeatherList(paramMap);
		log.debug("[WeatherRestController] [getWeatherList] weatherList : " + weatherList.toString());
		
		// 조회 데이터가 없을 시
		if(weatherList.size() == 0) {
			List<Map<String,Object>> errorList = new ArrayList<>();
			Map<String,Object> error = new HashMap<>();
			error.put("errorCode", "00");
			error.put("errorMessage", "NODATA_ERROR");
			errorList.add(error);
			
			return errorList;
		}
		
		return weatherList;
	}
	
	
	@RequestMapping(value="/addWeather", method= RequestMethod.POST)
	public Map<String, Object> addWeather(Weather weather){
		
		log.debug("[WeatherRestController] [addWeather] [Param] weather : " + weather.toString());
		
		String datetime = weather.getWeatherDate();
		datetime = datetime + "0000";
		weather.setWeatherDate(datetime);
		
		int resutl = weatherService.addWeather(weather);
		
		Map<String,Object> message = new HashMap<>();
		
		if(resutl == 1) {
			message.put("code", "1");
			message.put("message", "입력 완료");
			return message;
		} else {
			message.put("code", "2");
			message.put("message", "입력 실패");
			return message;
		}
		
	}
}
