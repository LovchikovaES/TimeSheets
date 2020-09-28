package ru.catn.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        if (StringUtils.hasText(source)) {
            return LocalDate.parse(source);
        } else {
            return null;
        }
    }
}
