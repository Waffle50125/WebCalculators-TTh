package edu.kirkwood.dao;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeParseException;

public class JsonTypeAdapter {
    /**
     * Used by Gson when date is formatted like "1967-07-23"
     */
    public static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            try {
                return LocalDate.parse(json.getAsString());
            } catch (DateTimeParseException e) {
                return null;
            }
        }
    }

    /**
     * Used by Gson when date is formatted like "1967-07-23T09:18:33"
     */
    public static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            try {
                return LocalDateTime.parse(json.getAsString());
            } catch (DateTimeParseException e) {
                return null;
            }
        }
    }

    /**
     * Used by Gson when date is formatted like "1967-07-23T09:18:33.666Z"
     */
    public static class InstantDeserializer implements JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            try {
                return Instant.parse(json.getAsString());
            } catch (DateTimeParseException e) {
                return null;
            }
        }
    }
}
