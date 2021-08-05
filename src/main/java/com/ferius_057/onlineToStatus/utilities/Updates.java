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
        String new_version = test[0].get("tag_name").getAsString().replace("\"","");
        if (!Data.version.equals(new_version)) {
            System.err.println("Найдено новое обновление! - " + new_version);
            System.err.println("Информация:\n" + test[0].get("body").getAsString());
            System.out.println();
        } else {
            System.out.println("Обновлений не найдено.");
        }
    }
}