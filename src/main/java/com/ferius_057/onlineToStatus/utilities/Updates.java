package com.ferius_057.onlineToStatus.utilities;

import com.ferius_057.onlineToStatus.data.Data;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class Updates {
    String url = "https://api.github.com/repos/Ferius057/OnlineToStatus/releases";

    public void check() {
        String reqURl = "";
        try {
            reqURl = new Request().get(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject[] test = new Gson().fromJson(reqURl, JsonObject[].class);
        String newVersion = test[0].get("tag_name").getAsString().replace("\"","");

        if (compareVersion(newVersion)) {
            System.err.println("Найдено новое обновление! - " + newVersion);
            System.err.println("Информация:\n" + test[0].get("body").getAsString());
            System.out.println();
        } else {
            System.out.println("Обновлений не найдено.");
        }
    }

    private boolean compareVersion(String version) {
        String[] v1 = Data.version.split("\\.");
        String[] v2 = version.split("\\.");

        if (v1.length != v2.length) {
            return true;
        }

        for (int pos = 0; pos < v1.length; pos++) {
            if (Integer.parseInt(v1[pos]) > Integer.parseInt(v2[pos])) {
                return false;
            } else if (Integer.parseInt(v1[pos]) < Integer.parseInt(v2[pos])) {
                return true;
            }
        }
        return false;
    }
}