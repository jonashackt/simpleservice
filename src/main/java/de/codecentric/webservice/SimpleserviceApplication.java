package de.codecentric.webservice;

import de.codecentric.namespace.weatherservice.WeatherService;
import de.codecentric.webservice.endpoint.WeatherServiceEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class SimpleserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleserviceApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean dispatcherServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/soap-api/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public WeatherService weatherService() {
		return new WeatherServiceEndpoint();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), weatherService());
		endpoint.publish("/WeatherSoapService_1.0");
		return endpoint;
	}
}
