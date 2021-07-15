package br.com.teste.sicredi.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class ConvertersConfiguration {

	@Bean
	public MongoCustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new ZonedDateTimeReadConverter());
		converters.add(new ZonedDateTimeWriteConverter());
		return new MongoCustomConversions(converters);
	}

}
