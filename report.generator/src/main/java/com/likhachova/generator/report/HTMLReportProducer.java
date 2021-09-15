package com.likhachova.generator.report;

import com.likhachova.generator.model.QueryResult;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HTMLReportProducer {
    private String reportPath;

    public HTMLReportProducer(String reportPath){
        this.reportPath = reportPath;
    }
    public void createReport(QueryResult result) {

    }

    /*

    db-client

read input from console and send to database

java -jar -Durl=... -Duser=root db-client.jar
Запускаем приложение.
При запуске указывает настройки подключения к базе через переменные окружения.
Если переменные не указаны, смотрим настройки в файле application.properties -> with all db configuration

Принимает на вход команды DDL, DML. Выполняет их на подключенной базе данных.

// ?
CREATE DATABASE users;
USE users;
CREATE TABLE ...
//

INSERT
SELECT
UPDATE
DELETE


На Update команды выводит количество измененных строк.
На Select команды
1. Выводит результат (таблицу) в консоль (можно заиспользовать сторонюю библиотеку)
2. Генерирует отчет о запросе в формате html и кладет его в папке /reports (или по пути указаному в конфигурации)

<table>
  <tr>
    <th>id</th>
    <th>name</th>
  </tr>
  <tr>
    <td>1</td>
    <td>Tolik</td>
  </tr>
  <tr>
    <td>2</td>
    <td>Sasha</td>
  </tr>
  <tr>
    <td>3</td>
    <td>Andrey</td>
  </tr>
</table>s
     */
}
