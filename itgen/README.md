**Запуск тестового набора:**

открываем консоль в папке модуля проекта и выполняем команду:

`gradlew -Pbrowser=chrome -Ptarget=remote -Pplatform=linux clean testFamilies`

`-Pbrowser` – желаемый браузер

`-Ptarget` – указание файла проперти (см ниже)

`-Pplatform` – ОС на которой будет запуск

`-PverifyUI=true` - запускает дополнительные отключаемые проверки ui

`-Petalon=true` - запускает тестовые наборы скриншотов в режиме получения эталонных снимков

`-Dfile.encoding=UTF-8` – для правильного отображения кириллицы из файла json

`testFamilies` – запускаемый тестовый набор

чтобы запустить по дефолту (Windows,Chrome,роль-суперадмин) команда выглядит так:
 
 `gradlew clean testFamilies`

Генерация отчета:

после выполнения теста выполнить команду:

`C:\Tools\allure-1.4.23\bin\allure generate build\allure-results`

если прописать переменную пути allure=C:\Tools\allure-1.4.23\bin\allure, то команда выглядит так:

`allure generate build\allure-results`

вывести отчет на экран 

`allure report open`

**Тестовые наборы** 
Запуск под суперадмином 
1.  testFamilies 
2.  testStudents 
3.  testParents 
4.  testLeads 
5.  testWorkers 
6.  testTrainers 
7.  testSchedule 
8.  testWindowSchedule 
9.  testRequests 
10. testMaterials
11. testPayments
12. testSmoke
13. testNostable

Запуск под дефолтным родителем  -Ptarget=localPar 
1. testSmokeParent
2. testNostableParent

Запуск под дефолтным тренером -Ptarget=localTrainer 
1. testSmokeTrainer

Запускаем автоматически ночью:
 1.  testFamilies 
 2.  testStudents 
 3.  testParents 
 4.  testLeads 
 5.  testWorkers 
 6.  testTrainers 
 7.  testSchedule 
 8.  testWindowSchedule 
 9.  testRequests 
 10. testMaterials
 11. testPayments
 12. testSmokeParent
 



**Значения -Ptarget** 

1. если вообще не указывать -Ptarget, то набор запуститься под суперадмином 
2. -Ptarget=localPar (для testSmokeParent) - набор запустится под дефолтным родителем. (пока в дефолтную базу
такой родитель не прописан)

