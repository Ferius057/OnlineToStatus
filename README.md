<h1 align="center">OnlineToStatus</h1>
<p align="center">
    <a href="https://github.com/Ferius057/OnlineToStatus/releases/tag/1.6.1">
    <img src="https://img.shields.io/badge/Release-1.6.1-blue.svg">
  </a>
    </a>
    <a href="https://opensource.org/licenses/Apache-2.0">
    <img src="https://img.shields.io/badge/Open%20Source-purple.svg">
  </a>
  <a href="https://www.java.com">
    <img src="https://img.shields.io/badge/java_version-1.8.0-red">
  </a>
  <a href="https://github.com/Ferius057/OnlineToStatus/blob/main/LICENSE">
    <img src="https://img.shields.io/badge/License-Apache%20License%202.0-yellow.svg">
  </a>
  <p align="center">
  <a href="https://github.com/Ferius057/OnlineToStatus/releases/download/1.6.1/OnlineToStatus.jar">
    <img src="https://img.shields.io/github/downloads/Ferius057/OnlineToStatus/total?color=6ff00">
  <a href="https://www.donationalerts.com/r/ferius_057">
    <img src="https://img.shields.io/badge/Donate-DonationAlerts-orange.svg">
  </a>
  </p>
</p>

<hr>

## Требования
- **Java 1.8.0**

<hr>

## Возможности
- Отображать онлайн любого сервера Minecraft JE
- Получить прирост за последнее обновление статуса
- Получить прирост за последний час
- Получить рекорд онлайна за последний час
- Показывать общий рекорд онлайна за все время
- Установить свою частоту обновления статуса
- Получить время и дату обновления статуса в любом для вас удобном формате
- Автовключение при перезагрузке сервера
- Удобные конфигурации и полная информация по настройке
- Легкая установка

<hr>

## Использование:
  <a href="https://github.com/Ferius057/OnlineToStatus/releases/download/1.6.1/OnlineToStatus.jar">
    <img src="https://img.shields.io/github/downloads/Ferius057/OnlineToStatus/total?color=6ff00">
  </a>

#### Linux:

```bash
# Для запуска скрипта
$ java -jar OnlineToStatus.jar

# После запуска рядом с OnlineToStatus.jar будет config.yml, его надо будет настроить.
# Так же создаться help.yml, инструкция по настройке конфига. 
```

#### Windows:

```bash

# После скачивания OnlineToStatus.jar:
# Рядом с OnlineToStatus.jar сделайте '.bat' файл и поместите туда команду:
java -jar OnlineToStatus.jar

# Для запуска скрипта запустите '.bat' файл.
# После запуска рядом с OnlineToStatus.jar будет config.yml, его надо будет настроить.
# Так же создаться help.yml, инструкция по настройке конфига. 
```

<hr>

## Безопасность
- Делайте все на свой страх и риск
#### Если вы поместите скрипт на том же дедике где и сервер, при взломе дедика могут украсть ваш token ключ, что делать?
1. Зарегистрировать новый аккаунт ВК<i>(желательно номер и зарегать акк, меняйте доменное имя и аву на аккаунте, что бы он в бан не улетел за покупку)</i>, дать ему редактора в группе через него получить User токен (с помощью этого токена можно управлять только группами), ну и запускать скрипт с помощью этого token ключа.
2. Купить отдельную дешевую вдс
3. Ну и самым безопасным вариантом будет зарегистрировать аккаунт ВК + Хостить на дешевом vds.
#### Как сбросить token ключ?
1. Зайти в -> Настройки ВК -> Безопасность -> Там будет кнопка "Завершить все сеансы", она сбрасывает все имеющиеся токены.
2. Сменить пароль от аккаунта.

<hr>

## Config
> Подробную информацию по конфигу можно получить [ТУТ](https://github.com/Ferius057/OnlineToStatus/blob/main/src/main/resources/help.yml)<br>
> Подробную информацию про формат даты можно получить [ТУТ](https://github.com/Ferius057/OnlineToStatus/blob/main/timeFormat.md)
```yml
# 𝓞𝓷𝓵𝓲𝓷𝓮𝓣𝓸𝓢𝓽𝓪𝓽𝓾𝓼


vk:
  # id группы где будет отображаться онлайн (id вводить только цифрами)
  # !!! Менять статус может только Администратор или Редактор группы.
  # {Где получить id группы? (если вы админ группы) - Нажмите на кнопку Управление, там же есть пункт Адрес сообщества - > Номер сообщества.}
  # {Где получить id группы? (если вы редактор группы) - Откройте аву группы и скопируйте часть адресной строки от "=photo-" до "_" [между =photo- и _ цифры это и есть id группы]}
  group_id: "200934694"
  # Токен user'а от вк.
  # {Почему user токен? - Потому что так устроен вк, иначе никак.}
  # {Где получить user токен? - https://vk.cc/c4F4lp}
  #   1. После перехода по ссылке, Kate Mobile запросит доступ к группам -> "Разрешить"
  #   2. Скопируйте часть адресной строки от "access_token=" до "&expires_in" -> И этот токен вставляете в user_token
  user_token: "4bbi4xjlowwx9ic1fmyi3wijodrixm8lbc2104kw0zwt8skziybdfujfov7ewxjzy9q1j5azfv5rhles2qtz"
minecraft:
  # ip сервера с которого нужно получать онлайн
  ip: "mc.hypixel.net"
  # Порт этого сервера
  port: 25565
# Как часто нужно обновлять статус(получать онлайн), ВРЕМЯ В СЕКУНДАХ
# !!! Не рекомендую ставить ниже 45, а лучше 60+, иначе словите лимит от вк
delay: 45
# Текст который нужно отображать в статусе
# %online% - нынешний онлайн
# %max_online% - максимальный онлайн который возможен на сервере
# %growth% - оно покажет прирост за последнее обновление статуса:
#    - то есть, если на сервере сейчас 60 онлайна, а через 'delay' секунд 61 онлайна
#    - значит +1, ну а если стало 59 онлайна то -1, если онлайн остался таким же то просто 0
# %time% - нынешнее время (не забудьте указать формат в 'time_format')
# %growth_hour% - прирост онлайна за последний час (отсчёт начинается от запуска скрипта) (такой же как и %growth%, но этот показывает за последний час)
# %record_hour% - рекорд онлайна за последний час (отсчёт начинается от запуска скрипта)
# %record_all% - общий рекорд онлайна (можно самому указать в файле 'records.yml')
statusText: "Online: %online% / %max_online% {%growth%} Hour: {%growth_hour%}, Рекорд онлайна за последний час: {%record_hour%} | %record_all% | %time%"
# Формат даты для %time%
# Подробную информацию можно получить тут: https://github.com/Ferius057/OnlineToStatus/blob/main/timeFormat.md
time_format: "dd.MM.yyyy HH:mm:ss"
```

<hr>

## FAQ
> #### Где запускать этот скрипт?
> Для постоянной основы лучше на vds/дедиках.
> #### Чем скрипт лучше плагина?
> - Нет нагрузки на сервер, скрипт использует сокеты и получает онлайн сервера.
> - Вы можете получать онлайн любого сервера просто указав его ip в конфиге.
> - Просто лучше 🙃
> #### Можно ли его запустить на дедике там где и запущен сервер?
> Да можно, в другом окне, в конфиге ip можете указать 'localhost' если скрипт запущен там где и сервер.
>#### Как в тексте статуса перейти на следующую строку?
> Увы никак, вк такого не позволяет.
> #### Когда обнова?
> Скоро :D

<hr>

## Version
> *Скрипта: 1.6.1*<br>
> *VK Api: 5.130*

<hr>

## Links:
 - [Java 8](https://www.java.com)
 > По всем вопросам/предложениям:
 - Author: [Charles_Grozny](https://vk.me/ferius_057)

<hr>

## Screenshot:
<a href='' title=''><img style="width:100%" src='https://i.ibb.co/dcG1mNB/2021-07-05-01-21.png' alt=''  /></a>
<a href='' title=''><img style="width:100%" src='https://i.ibb.co/WcLt0cN/image.png' alt=''  /></a>
<a href='' title=''><img style="width:100%" src='https://i.ibb.co/0c32zMz/IMG-20210705-013505.jpg' alt=''  /></a>
