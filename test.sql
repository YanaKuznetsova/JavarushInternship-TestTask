-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Сен 10 2018 г., 11:19
-- Версия сервера: 5.7.17-log
-- Версия PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `test`
--

-- --------------------------------------------------------

--
-- Структура таблицы `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `necessity` tinyint(4) NOT NULL DEFAULT '0',
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `items`
--

INSERT INTO `items` (`id`, `name`, `necessity`, `amount`) VALUES
(1, 'материнская плата', 1, 5),
(2, 'звуковая карта', 0, 3),
(3, 'процессор', 0, 7),
(4, 'подсветка корпуса', 0, 4),
(5, 'hdd диск', 0, 17),
(6, 'корпус', 1, 20),
(7, 'оперативная память', 1, 30),
(8, 'SSD диск', 1, 17),
(9, 'видеокарта', 0, 14),
(10, 'принтер', 0, 5),
(11, 'мфу', 0, 6),
(12, 'клавиатура', 1, 13),
(13, 'мышь', 1, 16),
(14, 'блок питания', 1, 30),
(15, 'кабель для БП', 1, 50),
(16, 'сетевая карта', 0, 15),
(17, 'CD привод', 0, 5),
(18, 'DVD привод', 0, 5),
(19, 'картридер', 0, 1),
(20, 'охлаждение процессора', 0, 5),
(21, 'колонки', 0, 12),
(22, 'наушники', 0, 15),
(23, 'гарнитура', 0, 13),
(24, 'ИБП', 0, 15),
(25, 'веб-камера', 0, 10),
(26, 'монитор', 1, 15);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
