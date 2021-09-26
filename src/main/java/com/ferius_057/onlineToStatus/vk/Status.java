package com.ferius_057.onlineToStatus.vk;

import com.ferius_057.onlineToStatus.data.Config;
import com.ferius_057.onlineToStatus.utilities.Request;

import java.io.IOException;
import java.net.URLEncoder;

public class Status {
    public boolean setStatus(String text) {
        try {
            String request = new Request().get("https://api.vk.com/method/status.set?text=" + URLEncoder.encode(text, "UTF-8") + "&group_id=" + Config.group_id + "&access_token=" + Config.token + "&v=5.130");

            if (request.contains("error")) {
                // Переведено специально для тех кто не шарит в английском
                if (request.contains("User authorization failed: invalid access_token")) {
                    System.err.println("ERROR: Ошибка авторизации пользователя, неверный token.");
                } else if (request.contains("Access denied: you should be a group administrator")) {
                    System.err.println("ERROR: Доступ запрещен: вы должны быть администратором/редактором группы, проверьте наброность id группы в конфиге.");
                } else if (request.contains("Captcha needed")) {
                    System.err.println("ERROR: Вы получили капчу, подождите несколько минут и сделайте больше задержку 'delay' в конфиге.");
                }
                System.out.println(request);
                return false;
            }
            return true;
        } catch (IOException e) {
            System.err.println("X | Не удалось сделать запрос. " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}