**Запуск тестового набора:**

открываем консоль в папке модуля проекта и выполняем команду:

`gradlew -Pbrowser=chrome -Ptarget=remote -Pplatform=linux clean testFamilies`

`-Pbrowser` – желаемый браузер

`-Ptarget` – указание файла проперти (см ниже)

`-Pplatform` – ОС на которой будет запуск

`-PverifyUI=true` - запускает дополнительные отключаемые проверки ui

`-Petalon=true` - запускает тестовый набор testScreenShot в режиме получения эталонных снимков

`-Dfile.encoding=UTF-8`    – чтобы правильно отображалась кириллица из файла json

`testFamilies` – запускаемый тестовый набор

**Генерация отчета:**

после выполнения теста :

`C:\Tools\allure-1.4.23\bin\allure generate build\allure-results`

если прописать переменную пути allure=C:\Tools\allure-1.4.23\bin\allure, то команда выглядит так:

`allure generate build\allure-results `

вывести отчет на экран


**Тестовые наборы**
<li>testFamilies
<li>testStudents
<li>testParents
<li>testLeads
<li>testWorkers
<li>testTrainers
<li>testSchedule
<li>testNostable - нестабильные тесты
