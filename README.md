## TourAgency
Web-застосунок для тур-агенції, що пропонує клієнтам список турів та можливість обчислити орієнтовну вартість.
Присутня інтеграція з Swagger по URL: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
Зображення зберігаються за допомогою хмарного сервісу Cloudinary.

Перевірити роботу endpoint'ів можливо за допомогою Swagger за посиланням вище, або HTTP-клієнтів(Postman).

Переглянути БД можливо за допомогою MySQL Workbench/phpMyAdmin за даними у файлі application.properties

Оскільки в БД небагато даних, для отримання результатів пошуку рекомендується вводити:

1.
- Куди: Єгипет або Шарм-ель-Шейх або Baron Resort
- Ночей від 6 до 8
- Виліт від 01.06.2021 до 30.07.2021
- Виліт з Львів

2.
- Куди: Туреччина або Бодрум або Bodrum Bay Resort
- Ночей від 6 до 8
- Виліт від 01.06.2021 до 30.07.2021
- Виліт з Київ

Кількість осіб повинна в сумі не перевищувати 4(1-3 дорослих, 0-3 дітей).

## Back-end
- Java 11
- Spring Boot 2.5.0
- Cloudinary API 1.29.0
- Springdoc-openapi-ui 1.5.2 (Swagger)

## Front-end
- HTML5
- CSS3 (Bootstrap 5.0.1)
- JS (JQuery 3.6.0)