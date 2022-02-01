CREATE DATABASE IF NOT EXISTS "Interview"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
CREATE SCHEMA IF NOT EXISTS homework4;

SET search_path = homework4;

DROP TABLE IF EXISTS films CASCADE;
DROP TABLE IF EXISTS durations CASCADE;
DROP TABLE IF EXISTS prices CASCADE;
DROP TABLE IF EXISTS sold_tickets;
DROP TABLE IF EXISTS screening_times_prices CASCADE;
DROP TABLE IF EXISTS shedule CASCADE;
DROP TABLE IF EXISTS shedule_prices CASCADE;

CREATE TABLE durations (id SERIAL, duration SMALLINT NOT NULL, PRIMARY KEY(id));
CREATE TABLE films (id SERIAL, title VARCHAR(50) NOT NULL, duration_id bigint NOT NULL, PRIMARY KEY(id),
							CONSTRAINT fk_film_duration
							FOREIGN KEY(duration_id)
							REFERENCES durations(id));

CREATE TABLE prices (id SERIAL, price NUMERIC NOT NULL, PRIMARY KEY(id));

CREATE TABLE shedule (id SERIAL, film_id BIGINT, beginning TIME, PRIMARY KEY(id), UNIQUE(beginning),
							CONSTRAINT fk_film_id_screening_time_id 
							FOREIGN KEY(film_id) 
							REFERENCES films(id));
CREATE TABLE sold_tickets (id SERIAL, shedule_id INTEGER NOT NULL, PRIMARY KEY(id),
							CONSTRAINT fk_shedule_tickets 
							FOREIGN KEY(shedule_id) 
							REFERENCES shedule(id));
CREATE TABLE shedule_prices(shedule_id BIGINT, price_id BIGINT,
							CONSTRAINT fk_shudule_price_id 
							FOREIGN KEY(shedule_id) 
							REFERENCES shedule(id),
							CONSTRAINT fk_price_screening_time 
							FOREIGN KEY(price_id) 
							REFERENCES prices(id));


INSERT INTO durations (duration) VALUES (60), (90), (120);
INSERT INTO films (title, duration_id) VALUES ('Терминатор 2', 1),
('Властелин колец', 3),
('Дюна', 3),
('Бойцовский клуб', 2),
('На игле', 2);
INSERT INTO prices(price) VALUES (10), (20), (30), (40), (50), (50), (55);
INSERT INTO shedule (film_id, beginning) VALUES (1,'11:00:00'), (2,'13:00:00'),
(3,'16:00:00'), (5,'17:00:00'), (4,'18:30:00'), (1, '19:00:00'), (3, '21:30:00'); 
INSERT INTO shedule_prices (shedule_id, price_id) VALUES (1, 1),
							(2,2), (3,3), (4,4), (5,5), (6,6), (7,7);
INSERT INTO sold_tickets (shedule_id) VALUES (1), (2), (2), (3), (3), (4), (4), (4), (4), (5), (5),(5),
(5), (5),(5),(6),(6),(6),(6),(7),(7),(7),(7),(7),(7),(7),(7);

#----1----
SET search_path = homework4;
WITH intervals AS(
SELECT A.film_id, A.id AS shedule_id, B.title, A.beginning,
(A.beginning + make_interval(mins => C.duration)) AS end_time
		FROM films B INNER JOIN durations C ON B.duration_id = C.id
		INNER JOIN shedule A ON A.film_id = B.id)
SELECT I1.title, I1.beginning, I1.end_time,
I2.title, I2.beginning, I2.end_time
		FROM intervals I1
		INNER JOIN intervals I2 ON I1.beginning < I2.end_time
		AND I1.end_time > I2.beginning
		AND I1.shedule_id <> I2.shedule_id
		AND I1.shedule_id < I2.shedule_id
		ORDER BY I1.beginning;
		
#----2----
SET search_path = homework4;
WITH intervals AS(
SELECT A.film_id, A.id AS shedule_id, B.title, A.beginning, C.duration,
(A.beginning + make_interval(mins => C.duration)) AS end_time
		FROM films B INNER JOIN durations C ON B.duration_id = C.id
		INNER JOIN shedule A ON A.film_id = B.id)
SELECT I1.title, I1.beginning, I1.duration,
I2.title, I2.beginning, I2.duration,
-(I1.end_time - I2.beginning) as gap
		FROM intervals I1
		INNER JOIN intervals I2 ON I1.end_time < I2.end_time
		AND (I1.end_time - I2.beginning) < '00:30:00'
		AND (I1.end_time - I2.beginning) <>'00:00:00'
		WHERE (I2.shedule_id - I1.shedule_id = 1)
		ORDER BY gap;
		
#----3----
SET search_path = homework4;
WITH films_income AS(
SELECT B.id, A.id AS shedule_id, B.title, C.id AS ticket_id, E.price 
FROM films B INNER JOIN shedule A ON B.id = A.film_id INNER JOIN sold_tickets C ON A.id = C.shedule_id 
INNER JOIN shedule_prices D ON D.shedule_id = A.id INNER JOIN prices E ON D.price_id = E.id)

SELECT part1.* FROM(
SELECT FI.title, COUNT(FI.title) as tickets_sold, COUNT(FI.title)/COUNT(DISTINCT FI.shedule_id) AS avg_visitors,
SUM(FI.price) AS total_income 
FROM films_income FI
GROUP BY FI.title, FI.id
ORDER BY total_income DESC) as part1
UNION ALL
SELECT part2.* FROM (
SELECT 'Итого', COUNT(FI.title),COUNT(FI.title)/COUNT( DISTINCT FI.shedule_id), SUM(FI.price)
FROM films_income as FI) as part2;

#----4----
SET search_path = homework4;
WITH films_income AS(
SELECT  B.title, E.price, A.beginning 
FROM films B INNER JOIN shedule A ON B.id = A.film_id INNER JOIN sold_tickets C ON A.id = C.shedule_id 
INNER JOIN shedule_prices D ON D.shedule_id = A.id INNER JOIN prices E ON D.price_id = E.id)

SELECT list.beginning_interval, list.visitors, list.income FROM(
SELECT '9:00 - 15:00' as beginning_interval, SUM(visitors) as visitors, SUM(income) as income, 1 as sortby 
FROM(
SELECT FI.beginning, COUNT(FI.title) as visitors, sum(FI.price) as income 
FROM films_income FI
WHERE FI.beginning >= '9:00:00' AND FI.beginning < '15:00:00'
GROUP BY FI.beginning) as part1
UNION
SELECT '15:00 - 18:00' as beginning_interval, SUM(visitors) as visitors, SUM(income) as income, 2 as sortby
FROM(
SELECT FI.beginning, COUNT(FI.title) as visitors, sum(FI.price) as income 
FROM films_income FI
WHERE FI.beginning >= '15:00:00' AND FI.beginning < '18:00:00'
GROUP BY FI.beginning) as part2
UNION
SELECT '18:00 - 21:00' as beginning_interval, SUM(visitors) as visitors, SUM(income) as income, 3 as sortby 
FROM(
SELECT FI.beginning, COUNT(FI.title) as visitors, sum(FI.price) as income 
FROM films_income FI
WHERE FI.beginning >= '18:00:00' AND FI.beginning < '21:00:00'
GROUP BY FI.beginning) as part3
UNION
SELECT '21:00 - 00:00' as beginning_interval, SUM(visitors) as visitors, SUM(income) as income, 4 as sortby 
FROM(
SELECT FI.beginning, COUNT(FI.title) as visitors, sum(FI.price) as income 
FROM films_income FI
WHERE FI.beginning >= '21:00:00' AND FI.beginning < '23:59:59'
GROUP BY FI.beginning) as part4) as list
ORDER BY sortby;
		


			












