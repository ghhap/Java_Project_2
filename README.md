# Об'єднана лабораторна робота другого модуля Java
Предметна область: автомобілі. ЛР №8–11, Java Swing, JDBC, SQLite, Javadoc.

## Запуск у Windows / VS Code
1. Встановіть JDK 17 або новіший та Apache Maven (додайте bin до PATH). Перевірте `java -version` і `mvn -version` у терміналі.
2. Розпакуйте архів. У VS Code відкрийте папку Java_Module2 (з pom.xml), бажано з Extension Pack for Java.
3. У терміналі виконайте `mvn compile exec:java` або запустіть run.bat. Maven завантажить SQLite JDBC та інші залежності при першому запуску (потрібен інтернет).
4. База cars.db створюється автоматично в корені проєкту. У вкладці SQLite натисніть «Додати тестові дані».

## Демонстрація
- ЛР 8: покажіть 10 машин, сортування ціни в обох напрямках, фільтр >20000, додавання та редагування.
- ЛР 9: сортування марки в обох напрямках, Regex `^T` (Toyota, Tesla), мінімум і максимум ціни.
- ЛР 10: відкрийте cars.txt, збережіть cars.dat, відкрийте його і покажіть відсортовані записи. TXT: марка;модель;рік;ціна;об'єм двигуна. 0 означає невідомий об'єм або електромобіль.
- ЛР 11: додайте тестові дані, виконайте SELECT, WHERE, INSERT, UPDATE, DELETE. База і масив окремі, відповідно до завдань.

## API-документація
Відкрийте docs/index.html у браузері. Для повторної генерації запустіть generate-docs.bat або:
```
javadoc -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -d docs -sourcepath src/main/java -subpackages cars
```

## Перевірки
Вихідний код скомпільовано з параметром --release 17. SmokeTest перевіряє масиви, сортування, регулярні вирази та файли. Для перевірки також SQLite після завантаження залежностей у Maven:
```
mvn compile dependency:copy-dependencies
javac -encoding UTF-8 -cp "target/classes" -d target/test-classes tests/SmokeTest.java
java -cp "target/classes;target/test-classes;target/dependency/*" SmokeTest db
```
У Linux/macOS замініть крапки з комою в classpath на двокрапки. Графічний запуск і SQLite у середовищі підготовки архіву не перевірено.

## Висновок
Об'єднано функції чотирьох лабораторних у єдиному графічному застосунку. Реалізовано обробку масивів об'єктів, рядків, текстових і двійкових файлів та реляційної бази даних. Для класів і відкритих методів створено Javadoc-коментарі та HTML API-документацію.
