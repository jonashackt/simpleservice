package de.codecentric.webservice;

import de.codecentric.cxf.common.BootStarterCxfException;
import de.codecentric.cxf.common.XmlUtils;
import de.codecentric.namespace.weatherservice.WeatherException;
import de.codecentric.namespace.weatherservice.WeatherService;
import de.codecentric.namespace.weatherservice.general.ForecastReturn;
import de.codecentric.namespace.weatherservice.general.GetCityForecastByZIP;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		properties = {"server.port=8080"}
)
public class SimpleserviceApplicationTests {

	@Value("classpath:GetCityForecastByZIPTest.xml")
	private Resource getCityForecastByZipTestXml;

	@Test
	public void should_call_Weatherservice_and_get_Response() throws IOException, BootStarterCxfException, WeatherException {
		// Given
		WeatherService weatherService = initClient();

		GetCityForecastByZIP getCityForecastByZIP = XmlUtils.readSoapMessageFromStreamAndUnmarshallBody2Object(getCityForecastByZipTestXml.getInputStream(), GetCityForecastByZIP.class);

		//When
		ForecastReturn cityForecastByZIP = weatherService.getCityForecastByZIP(getCityForecastByZIP.getForecastRequest());

		// Then
		assertNotNull(cityForecastByZIP);
		assertThat(cityForecastByZIP.getCity(), is(equalTo("Weimar")));
	}

	private WeatherService initClient() {
		JaxWsProxyFactoryBean jaxWsFactory = new JaxWsProxyFactoryBean();
		jaxWsFactory.setServiceClass(WeatherService.class);
		jaxWsFactory.setAddress("http://localhost:8080/soap-api/WeatherSoapService_1.0");
		return (WeatherService) jaxWsFactory.create();
	}

}
