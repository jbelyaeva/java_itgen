**Запуск тестового набора:**

открываем консоль в папке модуля проекта и выполняем команду:

`gradlew -Dfile.encoding=UTF-8 clean testFamilies` - запустится тестовый набор testFamilies по дефолту
 в хроме на локальной машине

`-Pbrowser` – желаемый браузер

`-Ptarget=local` – запуск на локальной машине

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
<li>testWindowsSchedule
<li>testSmoke
<li>testNostable - нестабильные тесты
