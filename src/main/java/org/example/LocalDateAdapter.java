//package org.example;
//
//import com.google.gson.*;
//import java.lang.reflect.Type;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
//    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//    @Override
//    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
//        return new JsonPrimitive(date.format(formatter));
//    }
//
//    @Override
//    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
//        return LocalDate.parse(json.getAsString(), formatter);
//    }
//}

// Hade LocalDate för i Transaction för att logga datum men stötte på massor error och kunde inte lista ut hur
// jag skulle gå till väga detta var ett försök från StackOverflow men fick det inte att fungera korrekt
//Bytte till String istället så man får skriva in datum varje gång man gör en ny transaktion


