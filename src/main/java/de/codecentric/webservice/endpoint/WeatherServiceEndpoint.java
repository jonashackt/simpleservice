package de.codecentric.webservice.endpoint;

import de.codecentric.namespace.weatherservice.WeatherException;
import de.codecentric.namespace.weatherservice.WeatherService;
import de.codecentric.namespace.weatherservice.general.ForecastRequest;
import de.codecentric.namespace.weatherservice.general.ForecastReturn;
import de.codecentric.namespace.weatherservice.general.WeatherInformationReturn;
import de.codecentric.namespace.weatherservice.general.WeatherReturn;

import javax.jws.WebParam;

/**
 * Created by jonashecht on 24.08.16.
 */
public class WeatherServiceEndpoint implements WeatherService {
    @Override
    public WeatherInformationReturn getWeatherInformation(@WebParam(name = "ZIP", targetNamespace = "http://www.codecentric.de/namespace/weatherservice/general") String zip) throws WeatherException {
        return null;
    }

    @Override
    public ForecastReturn getCityForecastByZIP(@WebParam(name = "ForecastRequest", targetNamespace = "http://www.codecentric.de/namespace/weatherservice/general") ForecastRequest forecastRequest) throws WeatherException {
        return null;
    }

    @Override
    public WeatherReturn getCityWeatherByZIP(@WebParam(name = "ForecastRequest", targetNamespace = "http://www.codecentric.de/namespace/weatherservice/general") ForecastRequest forecastRequest) throws WeatherException {
        return null;
    }
}
