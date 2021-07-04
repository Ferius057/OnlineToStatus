package com.ferius_057.onlineToStatus;

import com.ferius_057.onlineToStatus.minecraft.Online;
import com.ferius_057.onlineToStatus.vkApi.Status;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("config.yml");

        createFileHelp();

        // проверка существует ли файл конфигрурации или нет
        if (!file.exists()) {
            new Config().create(file);
            System.out.println("Файл конфигурации был создан, перезапустите скрипт.");
            System.out.println("press [ENTER] key to continue....");
            new Scanner(System.in).nextLine();
            return;
        }
        else System.out.println("Файл конфигруции существует.");


        // получаение данные из конфига
        new Config().get(file);

        System.out.println("\n------------------------------------------" +
                        "\nid: " + Config.group_id +
                        "\ntoken: " + Config.user_token +
                        "\nip: " + Config.ip +
                        "\nport: " + Config.port +
                        "\ndelay: " + Config.delay / 1000 +
                        "\ntext: " + Config.statusText +
                        "\n------------------------------------------\n");
        run();
    }

    private static void run() throws InterruptedException {
        if (Config.ip == null) {
            System.err.println("X | Произошла неизвестная ошибка, проверьте конфиг.");
            System.exit(0);
        } else if (Config.port > 65535) {
            System.err.println("X | Произошла ошибка с 'port', вы указали слишком большой порт.");
            System.exit(0);
        } else if (Config.delay < 30000) {
            System.err.println("'delay' В КОНФИГЕ УСТАНОВЛЕН МЕНЬШЕ 30 СЕКУНД | НЕ РЕКОМЕНДУЕТСЯ СТАВИТЬ МЕНЬШЕ 30!");
        }

        SimpleDateFormat formatForDateNow = null;
        try {
            // Получения формат даты из конфига
            formatForDateNow = new SimpleDateFormat(Config.time_format, new Locale("ru"));
        } catch (IllegalArgumentException e) {
            System.err.println("X | Произошла ошибка с 'time_format', проверьте конфиг.");
            System.exit(0);
        }
        formatForDateNow.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        int last_online = 0;
        while (last_online != -1) {
            // Получение онлайна
            int[] online = new Online().get(Config.ip, Config.port);
            // Получить прирост
            int growth = online[0]-last_online;
            String growthStr;
            // Если этот онлайн больше прошлого значит произошел прирост [+]
            if (online[0] > last_online) growthStr = "+"+growth;
                // Если онлайн стал меньше значит growth уже < 0 ("-" не нужен)
            else growthStr = ""+growth;
            // Сработает только 1 раз при запуске что бы указать 0 в прирост
            if (online[0] == growth) growthStr = ""+0;
            // Установка статуса
            boolean status = new Status().setStatus(Config.statusText.replace("%online%", String.valueOf(online[0]))
                    .replace("%max_online%", String.valueOf(online[1]))
                    .replace("%growth%", growthStr)
                    .replace("%time%", formatForDateNow.format(new Date())));
            if (!status) {
                last_online = -1;
            } else {
                System.out.println("[" + new Date() + "] Обновил статус.");
                last_online = online[0];
                Thread.sleep(Config.delay);
            }
        }
    }

    private static void createFileHelp() throws IOException {
        InputStream in = Main.class.getResourceAsStream("/help.yml");

        StringBuilder result = new StringBuilder();
        assert in != null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            int i;
            while((i=reader.read())!=-1){

               result.append((char) i);
            }
        }

        File fileHelp = new File("help.yml");

        if (fileHelp.createNewFile()) {
            System.out.println("Создал файл инструкции: help.yml.");
        }

        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileHelp), StandardCharsets.UTF_8))) {
            out.write(result.toString());
        }
    }
}
