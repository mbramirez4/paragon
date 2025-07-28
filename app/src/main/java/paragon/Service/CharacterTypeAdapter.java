package paragon.Service;

import com.google.gson.*;
import paragon.Model.Character.Character;
import paragon.Model.Character.Warrior;
import paragon.Model.Character.Wizard;
import paragon.Model.Character.Rogue;

import java.lang.reflect.Type;

public class CharacterTypeAdapter implements JsonSerializer<Character>, JsonDeserializer<Character> {
    
    private static final String TYPE_FIELD = "characterType";
    
    @Override
    public JsonElement serialize(Character character, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement jsonElement = context.serialize(character);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        
        // Only add the type information
        jsonObject.addProperty(TYPE_FIELD, character.getClass().getSimpleName());
        
        return jsonObject;
    }
    
    @Override
    public Character deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException {
        
        JsonObject jsonObject = json.getAsJsonObject();
        
        if (!jsonObject.has(TYPE_FIELD)) {
            throw new JsonParseException("Missing character type field");
        }
        
        String characterType = jsonObject.get(TYPE_FIELD).getAsString();
        
        switch (characterType) {
            case "Warrior":
                return context.deserialize(json, Warrior.class);
            case "Wizard":
                return context.deserialize(json, Wizard.class);
            case "Rogue":
                return context.deserialize(json, Rogue.class);
            default:
                throw new JsonParseException("Unknown or disallowed character type: " + characterType);
        }
    }
} 