// Получение и работа с данными по апи
// Поиск количества вакцинированных на примере датасета заболеваний ковидом в Канаде
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Scanner;
import java.util.*;
// Подключаем библиотеку json.simple (внутри /lib)
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.*;

public class Main {

    public static void main(String[] args) {
        try {

            // Задаем ссылку
            // Датасет: количество случаев ковида в Канаде
            URL url = new URL("https://api.covid19tracker.ca/summary");

            // Подключаемся по протоколу GET
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Получаем код ответа, если 200 OK -- продолжаем
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                // Если код отличный от 200, кидаем исключение
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                // Записываем все полученные данные в строку
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                // Закрываем сканер
                scanner.close();

                // Создаем объект парсера
                JSONParser parse = new JSONParser();
                //Используя библиотеку json.simple парсим строку в json объект
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                // Получаем объект данных с созданного выше объекта (который содержит дату обновления, и данные)
                JSONArray data = (JSONArray) data_obj.get("data");
                // Идем вглубь массива
                JSONObject internalData = (JSONObject) data.get(0);
                // Получаем необходимые данные по ключу
                // В данном случае количество всех вакцинированных людей
                String total_hospitalizations = (String) internalData.get("total_vaccinated");
                // Выводим полученные данные на экран
                System.out.println("Количество вакцинированных в Канаде: " + total_hospitalizations);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}