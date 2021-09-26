package com.ferius_057.onlineToStatus.data;

import configuration.file.FileConfiguration;
import configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public static String configVersion;

    public static String group_id = "111111111";
    public static String token = "token";

    public static String ip = "0.0.0.0";
    public static int port = 25565;

    public static long delay = 60;
    public static String statusText = "text";
    public static String time_format = "";

    static FileConfiguration records = YamlConfiguration.loadConfiguration(new File("records.yml"));
    public void get(File file) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        configVersion = config.getString("configVersion");

        // получение данных вк
        group_id = config.getString("vk.group_id");
        token = config.getString("vk.user_token");

        // получение данных майнкрафта
        ip = config.getString("minecraft.ip");
        port = config.getInt("minecraft.port");

        // получение данных настроек задержки
        delay = config.getLong("delay") * 1000;

        // получение настроек текста
        statusText = config.getString("statusText");

        // получение настроек времени
        time_format = config.getString("time_format");

        Data.record_all = records.getInt(Config.ip.replace(".",",") + "_" + Config.port + ".all");
    }

    public static void setRecord(int record) {
        records.set(Config.ip.replace(".",",") + "_" + Config.port + ".all", record);

        try {
            records.save(new File("records.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}