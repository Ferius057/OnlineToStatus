package com.ferius_057.onlineToStatus;

import configuration.file.FileConfiguration;
import configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public static String group_id = "111111111";
    public static String user_token = "token";

    public static String ip = "0.0.0.0";
    public static int port = 25565;

    public static long delay = 60;
    public static String statusText = "text";
    public static String time_format = "";

    public void get(File file) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        // получение данных вк
        group_id = config.getString("vk.group_id");
        user_token = config.getString("vk.user_token");

        // получение данных майнкрафта
        ip = config.getString("minecraft.ip");
        port = config.getInt("minecraft.port");

        // получение данные настроек задержки
        delay = config.getLong("delay") * 1000;

        // получение настроек текста
        statusText = config.getString("statusText");

        // получение настроек времени
        time_format = config.getString("time_format");
    }
}