package inventoryservice.domain.admin;



import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonPatch;
import javax.json.JsonWriter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class JsonPatchHttpMessageConverter extends AbstractHttpMessageConverter<JsonPatch> {

    public JsonPatchHttpMessageConverter() {
        super(MediaType.valueOf("application/json-patch+json"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return JsonPatch.class.isAssignableFrom(aClass);
    }

    @Override
    protected JsonPatch readInternal(Class<? extends JsonPatch> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        try (JsonReader reader = Json.createReader(httpInputMessage.getBody())) {
            return Json.createPatch(reader.readArray());
        } catch (Exception e) {
            throw new HttpMessageNotReadableException(e.getMessage(), httpInputMessage);
        }
    }

    @Override
    protected void writeInternal(JsonPatch jsonPatch, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException{

        try (JsonWriter writer = Json.createWriter(httpOutputMessage.getBody())) {
            writer.write(jsonPatch.toJsonArray());
        } catch (Exception e) {
            throw new HttpMessageNotWritableException(e.getMessage(), e);
        }
    }
}
