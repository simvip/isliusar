package my.jpa;

import com.google.gson.Gson;

/**
 * Created by Ivan Sliusar on 22.04.2018.
 * Red Line Soft corp.
 */
public class TestClass {
    public static void main(String[] args) {
        String txt = "{\"command\"}";
        String json = new Gson().toJson(txt);
        json.replace("\\","");
        System.out.println(json);
    }
}
