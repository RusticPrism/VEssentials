package de.rusticprism.vessentials.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.velocitypowered.api.util.UuidUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UUIdUtils {
    public static String getUUId(String name) {
        JsonObject object;
        try {
            HttpsURLConnection con = (HttpsURLConnection)(new URL("https://api.mojang.com/users/profiles/minecraft/" + name)).openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());

            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder result = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            object = new Gson().fromJson(String.valueOf(result),JsonObject.class);
        } catch (Exception var4) {
            var4.printStackTrace();
            return UuidUtils.generateOfflinePlayerUuid(name).toString();
        }
        return UuidUtils.fromUndashed(object.get("id").getAsString()).toString();
    }
    public static String getName(String uuid) {
        JsonObject object;
        try {
            HttpsURLConnection con = (HttpsURLConnection)(new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid)).openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());

            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder result = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            object = new Gson().fromJson(String.valueOf(result),JsonObject.class);
        } catch (Exception var4) {
            var4.printStackTrace();
            return "NULL";
        }
        return object.get("name").getAsString();
    }
}
