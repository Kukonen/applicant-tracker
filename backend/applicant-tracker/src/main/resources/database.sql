-- Поступающий
CREATE TABLE Applicant (

                    id bigserial PRIMARY KEY,
                    snils varchar(32) UNIQUE NOT NULL, -- СНИЛС поступающего
                    name varchar(128) -- ФИО поступающего (если есть информация)

);

-- Кандидат - конкретный поступающий на конкретное направление конкретного вуза
CREATE TABLE Candidate (

                    id bigserial PRIMARY KEY,
                    applicant_id bigint NOT NULL, -- id поступающего
                    program_id bigint NOT NULL, -- id направления
                    score integer, -- баллы поступающего на конкретное направление конкретного вуза
                    priority integer, -- приоритет поступающего (не обязателен)
                    is_certificate_submitted boolean, -- подал ли абитуриент оригинал аттестата (если Null, то false)

                    FOREIGN KEY (applicant_id) REFERENCES Applicant(id),
                    FOREIGN KEY (program_id) REFERENCES Program(id),
                    UNIQUE (applicant_id, program_id) -- В каждом направлении человек может встречаться всего один раз

);


-- Направление / Программа обучения
CREATE TABLE Program (

                    id bigserial PRIMARY KEY,
                    university varchar(64) NOT NULL, -- Название университета
                    places integer, -- Количество мест
                    name varchar(32) NOT NULL, -- Название специальности / код
                    form varchar(32) NOT NULL, -- Форма обучения (Очная / Заочная / Очно-заочная)
                    type varchar(32) NOT NULL, -- Тип оплаты обучения (Контракт / Бюджет / Целевое / ...)

                    UNIQUE (university, name, form, type)
);
