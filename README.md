# Процедура запуска автотестов.

### *Необходимые предустановленные программы:*
* Windows 10 и выше
* Intellige IDEA
* Java: OpenJDK 11
* Docker Desktop

 ### Шаги для запуска
 1. Клонировать репозиторий https://github.com/KravetsElena/Diplom  командой ```git clone```
 2. Запустить Docker Desktop
 3. Открыть проект в Intellige IDEA
 4. Запусить Docker-контейнеры командой в терминале ```docker-compose up```
    
    5.1. *Для СУБД MySQL*
    * Открыть второй локальный терминал, запустить сервис командой ```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar```
    * Открыть третий локальный терминал, запустить тесты командой ```.\gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" allureReport```
    
    5.2. *Для СУБД PostgreSQL*
    * Открыть второй локальный терминал, запустить сервис командой ```java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar ```
    * Открыть третий локальный терминал, запустить тесты командой ```./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app" allureReport```
  
  6. Сформировать отчет командой ```.\gradlew allureServe```   в том же третьем терминале
  7. Завершить выполнение пакетного файла allureServe командой ```ctrl+c``` в том же третьем терминале
  8. Остановить запущенный сервис командой ```ctrl+c``` во втором терминалов
  9. закрыть контейнеры командой ```docker-compose down``` в любом из локальных терминалов
      
