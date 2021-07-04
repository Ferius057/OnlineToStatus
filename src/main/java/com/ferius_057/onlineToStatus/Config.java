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

    public static long delay = 7000;
    public static String statusText = "text";
    public static String time_format = "";

    public void create(File file) throws IOException {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        System.out.println("Создание файла конфигруции...");
        // создание vk настроек
        config.set("vk.group_id", "111111111");
        config.set("vk.user_token", "token");

        // создание minecraft настроек
        config.set("minecraft.ip", "0.0.0.0");
        config.set("minecraft.port", 25565);

        // создание настроек задержки
        config.set("delay", 7000);

        // создание настроек текста в статусе
        config.set("statusText", "text");

        // создание настроек времени в статусе
        config.set("time_format", "dd.MM.yyyy HH:mm:ss");

        //сохранение
        config.save(file);

     }

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