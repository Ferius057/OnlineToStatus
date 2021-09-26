package com.ferius_057.onlineToStatus;

import com.ferius_057.onlineToStatus.data.ClearRecord;
import com.ferius_057.onlineToStatus.data.Config;
import com.ferius_057.onlineToStatus.data.Data;
import com.ferius_057.onlineToStatus.minecraft.Online;
import com.ferius_057.onlineToStatus.utilities.Updates;
import com.ferius_057.onlineToStatus.vk.Status;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Проверка обновления...\n");
        new Updates().check();

        File file = new File("config.yml");
        File records = new File("records.yml");

        createFileHelp("help.yml");

        // проверка существует ли файл конфигрурации или нет
        if (!file.exists() || !records.exists()) {
            if (!file.exists()) {
                createFileHelp("config.yml");
                System.out.println("Файл конфигурации был создан, перезапустите скрипт.");
            }
            if (!records.exists()) {
                createFileHelp("records.yml");
                System.out.println("Файл рекордов был создан, перезапустите скрипт.");
            }
            System.out.println("press [ENTER] key to continue....");
            new Scanner(System.in).nextLine();
            return;
        }
        else System.out.println("\nФайл конфигруции существует.");

        // получаение данные из конфига
        new Config().get(file);

        if (Config.configVersion == null) {
            System.err.println("Неизвестная версия скрипта, пожалуйста обновите скрипт 'https://github.com/Ferius057/OnlineToStatus/tags' и удалите config.yml\nпопробуйте удалить config.yml.");
            return;
        } else if (Float.parseFloat(Data.configVersion) > Float.parseFloat(Config.configVersion)) {
            System.err.println("У вас старая версия конфига, удалите config.yml, не забудьте сохранить данные.");
            return;
        }

        System.out.println("\n------------------------------------------" +
                        "\n\uD835\uDCDE\uD835\uDCF7\uD835\uDCF5\uD835\uDCF2\uD835\uDCF7\uD835\uDCEE\uD835\uDCE3\uD835\uDCF8\uD835\uDCE2\uD835\uDCFD\uD835\uDCEA\uD835\uDCFD\uD835\uDCFE\uD835\uDCFC" +
                        "\nVersion: " + Data.version +
                        "\nConfig version: " + Data.configVersion +
                        "\n" +
                        "\ntoken: " + Config.token +
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
            return;
        } else if (Config.port > 65535) {
            System.err.println("X | Произошла ошибка с 'port', вы указали слишком большой порт.");
            return;
        } else if (Config.delay < 30000) {
            System.err.println("'delay' В КОНФИГЕ УСТАНОВЛЕН МЕНЬШЕ 30 СЕКУНД | НЕ РЕКОМЕНДУЕТСЯ СТАВИТЬ МЕНЬШЕ 30!");
        }

        new ClearRecord().start();

        SimpleDateFormat formatForDateNow;
        try {
            // Получения формат даты из конфига
            formatForDateNow = new SimpleDateFormat(Config.time_format, new Locale("ru"));
        } catch (IllegalArgumentException e) {
            System.err.println("X | Произошла ошибка с 'time_format', проверьте конфиг.");
            return;
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

            // Проверка рекорда/прироста за час
            if (Data.record_hour < online[0]) Data.record_hour = online[0];
            if (Data.growth_hour == 0) Data.growth_hour = online[0];
            int growthHour = online[0] - Data.growth_hour;
            String growthHourStr;
            if (online[0] > Data.growth_hour) growthHourStr = "+"+growthHour;
            else growthHourStr = ""+growthHour;
            if (online[0] == growthHour) {
                growthHourStr = ""+0;
            }

            // Проверка общего рекорда
            if (Data.record_all < online[0]) {
                Data.record_all = online[0];
                Config.setRecord(online[0]);
            }

            // Установка статуса
            boolean status = new Status().setStatus(Config.statusText.replace("%online%", String.valueOf(online[0]))
                    .replace("%max_online%", String.valueOf(online[1]))
                    .replace("%growth%", growthStr)
                    .replace("%growth_hour%", growthHourStr)
                    .replace("%record_hour%", String.valueOf(Data.record_hour))
                    .replace("%record_all%", String.valueOf(Data.record_all))
                    .replace("%time%", formatForDateNow.format(new Date())));
            if (!status) {
                last_online = -1;
            } else {
                System.out.println("[" + new Date() + "] Обновил статус.");
                last_online = online[0];
                Data.work = true;
                Thread.sleep(Config.delay);
            }
        }
    }

    private static void createFileHelp(String fileName) throws IOException {
        InputStream in = Main.class.getResourceAsStream("/" + fileName);

        StringBuilder result = new StringBuilder();
        assert in != null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            int i;
            while((i=reader.read())!=-1){

               result.append((char) i);
            }
        }

        File fileHelp = new File(fileName);

        if (fileHelp.createNewFile()) {
            System.out.println("Создал файл: " + fileName + ".");
        }

        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileHelp), StandardCharsets.UTF_8))) {
            out.write(result.toString());
        }
    }
}