package persistence;

import org.json.JSONObject;

public interface Writable {
    // This interface is adapted from the JsonSerializationDemo project
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
