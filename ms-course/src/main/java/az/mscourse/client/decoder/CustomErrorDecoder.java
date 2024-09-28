package az.mscourse.client.decoder;

import az.mscourse.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;

import static az.mscourse.client.decoder.JsonNodeFieldName.MESSAGE;
import static az.mscourse.enums.ErrorMessage.CLIENT_ERROR;
import static az.mscourse.util.MapperUtil.MAPPER_UTIL;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        var status = response.status();
        var errorMessage = CLIENT_ERROR.getMessage();
        var statusCode = response.status();

        JsonNode jsonNode;

        try (var body = response.body().asInputStream()) {
            jsonNode = MAPPER_UTIL.map(body, JsonNode.class);
        } catch (Exception exception) {
            throw new CustomFeignException(statusCode,CLIENT_ERROR.getMessage());

        }

        if (jsonNode.has(MESSAGE.getValue())) {
            errorMessage = jsonNode.get(MESSAGE.getValue()).asText();
        }
        return new CustomFeignException(statusCode,errorMessage);


    }
}
