import org.json.simple.JSONObject;

import java.util.Comparator;

class MyJSONComparator implements Comparator<JSONObject> {

    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        // Сравниваем две аптеки по имени
        String v1 = (String) ((JSONObject) o1.get("attributes")).get("COMMERCIALNAME_E");
        String v3 = (String) ((JSONObject) o2.get("attributes")).get("COMMERCIALNAME_E");
        return v1.compareTo(v3);
    }

}