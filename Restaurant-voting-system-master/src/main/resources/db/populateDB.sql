DELETE FROM VOTES;
DELETE FROM MENUS;
DELETE FROM MEALS;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD, PRIVILEGED) VALUES
  ('Petr_admin', 'petr@gmail.com', '{noop}petr_pass', TRUE),        -- 100000
  ('Ivan_user', 'ivan@mail.ru', '{noop}ivan_pass', FALSE),          -- 100001
  ('Dmitriy_user', 'dmitriy@mail.ru', '{noop}dmitriy_pass', FALSE), -- 100002
  ('Vasya_user', 'vasya@gmail.com', '{noop}vasya_pass', FALSE);     -- 100003

INSERT INTO MEALS (NAME) VALUES
  ('хлеб'),   -- 100004
  ('вода'),   -- 100005
  ('чай'),    -- 100006
  ('сок'),    -- 100007
  ('мясо'),   -- 100008
  ('масло');  -- 100009

INSERT INTO RESTAURANTS (NAME) VALUES
  ('Морской'),    -- 100010
  ('Китайский'),  -- 100011
  ('Французкий');  -- 100012

INSERT INTO MENUS (RESTAURANT_ID, DATE) VALUES
  (100010, '2018-01-01'), -- 100013
  (100010, '2018-01-02'), -- 100014
  (100011, '2018-01-01'), -- 100015
  (100011, '2018-01-02'), -- 100016
  (100012, '2018-01-02'); -- 100017

INSERT INTO MENU_MEALS(MENU_ID, MEAL_ID, PRICE) VALUES
  (100013, 100004, 100),
  (100013, 100005, 150),
  (100014, 100006, 50),
  (100014, 100009, 75),
  (100015, 100004, 250),
  (100015, 100008, 125),
  (100016, 100008, 130),
  (100016, 100009, 140);

INSERT INTO VOTES (MENU_ID, USER_ID) VALUES
  (100013, 100001),
  (100015, 100002),
  (100015, 100003),
  (100014, 100001),
  (100016, 100002),
  (100016, 100003);