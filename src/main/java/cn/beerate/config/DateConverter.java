package cn.beerate.config;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.DateUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

@Configuration
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if(StringUtils.isBlank(source)){
            return null;
        }

        return DateUtil.parse(source);
    }
}
