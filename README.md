# Лабораторная работа №2 — вариант 7 «Отель»

Проект продолжает ЛР №1: `Application` создает бронирования через `BookingService`, который получает `BookingRepository` через конструктор. Сервис запрещает пересечение дат для одного номера. Maven компилирует код, запускает JUnit-тесты и собирает JAR.

## Структура

- `pom.xml` — координаты `com.hotel:hotel-booking-system:1.0-SNAPSHOT`, JUnit 5, плагины Surefire и JAR, Java 11.
- `src/main/java` — приложение, модели, сервис и репозиторий.
- `src/test/java` — тесты бронирования и загрузки ресурса.
- `src/main/resources/hotel.properties` — конфигурация варианта 7.
- `target` — создаваемые Maven классы, отчеты тестов и JAR.

`target` формируется заново при сборке, поэтому файлы внутри него не редактируют. `mvn clean` удаляет этот каталог, а `mvn test` и `mvn package` создают нужные файлы повторно.

## Команды для защиты

Выполнять из каталога `hotel-booking-system` при установленном JDK 11 или новее и Maven:

```bash
mvn clean
mvn compile
mvn test
mvn package
jar tf target/hotel-booking-system-1.0-SNAPSHOT.jar
unzip -p target/hotel-booking-system-1.0-SNAPSHOT.jar hotel.properties
java -jar target/hotel-booking-system-1.0-SNAPSHOT.jar
```

`mvn test` выполняет 4 теста: 3 проверки логики бронирования из ЛР №1 и новую проверку доступности конфигурации. `mvn package` создает `target/hotel-booking-system-1.0-SNAPSHOT.jar`. Команда `jar tf` показывает `hotel.properties` внутри JAR, `unzip -p` показывает его значение `hotel.name=Hotel Booking System`. При запуске JAR приложение печатает название отеля и результат попыток бронирования.

## Индивидуальное задание

Конфигурация находится в `src/main/resources/hotel.properties`. Стандартный `maven-resources-plugin` копирует файл в `target/classes` на фазе обработки ресурсов, а `maven-jar-plugin` помещает его в корень JAR на фазе `package`. `Application` читает файл через classpath и выводит `hotel.name`. Это показывает, что настройка доступна приложению после сборки, а не только из исходного каталога.

## Демонстрация ошибочного теста

Для проверки реакции Maven в `HotelResourceTest` временно заменили ожидаемое имя на `Wrong Hotel Name` и запустили `mvn test`. Результат: `Tests run: 4, Failures: 1`, `BUILD FAILURE`, сообщение `expected: <Wrong Hotel Name> but was: <Hotel Booking System>`. Затем ожидание вернули к правильному значению и повторили сборку.
