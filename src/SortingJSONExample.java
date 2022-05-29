// Пример сортировки JSON
//
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SortingJSONExample {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            // Считываем JSON-объект с датасета местных аптек
            JSONObject o = (JSONObject) parser.parse(new FileReader("pharm.json"));
            JSONArray array = (JSONArray) o.get("results");
            ArrayList<JSONObject> list = new ArrayList<>();

            // Проходимся по всем элементам массива
            for (int i = 0; i < array.size(); i++) {
                list.add((JSONObject) array.get(i));
            }
            // Сортируем по имени аптеки используя компаратор
            Collections.sort(list, new MyJSONComparator());

            // Выводим на экран айди объекта (должно быть 255, 1, 35)
            for (JSONObject obj : list) {
                System.out.println(((JSONObject) obj.get("attributes")).get("OBJECTID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
