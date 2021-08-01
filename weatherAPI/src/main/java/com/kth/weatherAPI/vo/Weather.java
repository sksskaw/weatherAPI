package com.kth.weatherAPI.vo;

import lombok.Data;

@Data
public class Weather {
	private int weatherPoint;
	private String weatherPlace;
	private String weatherDate;
	private int weatherTemperatures;
	private String weatherWindSpeed;
	private int weatherHumidity;
	private String weatherAtmosphericPressure;
}
