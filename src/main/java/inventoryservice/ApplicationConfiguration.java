package inventoryservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import inventoryservice.domain.admin.JsonPatchHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR353Module());

        return objectMapper;
    }

//    @Bean
//    public HttpMessageConverters additionalConverters() {
//        return new HttpMessageConverters(new JsonPatchHttpMessageConverter());
//    }
//
//    @Bean
//    public HttpMessageConverter<?> messageConverter(){
//        return new JsonPatchHttpMessageConverter();
//    }
}
