package cn.beerate.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.assertj.core.util.DateUtil;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Date;

@JsonComponent
public class JsonDateConverter extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return DateUtil.parse(jsonParser.getText());
    }
}
