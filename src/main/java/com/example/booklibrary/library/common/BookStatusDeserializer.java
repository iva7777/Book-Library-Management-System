package com.example.booklibrary.library.common;

import com.example.booklibrary.library.model.BookStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class BookStatusDeserializer extends JsonDeserializer<BookStatus> {
    @Override
    public BookStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.has("status") ? node.get("status").asText() : node.textValue();

        return BookStatus.valueOf(value.toLowerCase());
    }
}
