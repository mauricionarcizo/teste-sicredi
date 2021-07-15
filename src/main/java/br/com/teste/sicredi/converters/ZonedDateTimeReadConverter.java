package br.com.teste.sicredi.converters;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {
	@Override
	public ZonedDateTime convert(Date date) {
		return date.toInstant().atZone(ZoneId.of("America/Sao_Paulo"));
	}
}