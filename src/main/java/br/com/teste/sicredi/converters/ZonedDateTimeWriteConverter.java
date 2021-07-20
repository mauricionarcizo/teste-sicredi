package br.com.teste.sicredi.converters;

import java.util.Date;
import java.time.ZonedDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date> {
	@Override
	public Date convert(ZonedDateTime zonedDateTime) {
		return Date.from(zonedDateTime.toInstant());
	}
}
