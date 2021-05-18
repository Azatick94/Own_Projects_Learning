### * Конфигурационный класс: <br>

#### "java/com/test_task/exchanges/config/GeneralConfigs.java"

---

### * Примеры запросов для получения результата
1) сравнить курс рубля <br>
GET http://localhost:8080/rest/feign/exchanges?coin=rub

2) сравнить курс канадского доллара <br>
GET http://localhost:8080/rest/feign/exchanges?coin=cad

---

### * Описание тестового задания:

Создать сервис, который обращается к сервису курсов валют, и отдает gif в ответ:

если курс по отношению к рублю за сегодня стал выше вчерашнего, то отдаем рандомную
отсюда https://giphy.com/search/rich  
если ниже - отсюда https://giphy.com/search/broke
Ссылки REST API курсов валют - https://docs.openexchangerates.org/
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide  


