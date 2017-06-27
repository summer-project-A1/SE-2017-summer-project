-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2017-06-27 03:16:43
-- 服务器版本： 5.7.17
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `summer_project`
--

-- --------------------------------------------------------

--
-- 表的结构 `Apply`
--

CREATE TABLE `Apply` (
  `apply_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `due` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `Book`
--

CREATE TABLE `Book` (
  `book_id` int(11) NOT NULL,
  `bookname` varchar(50) NOT NULL,
  `isbn` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `press` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `BookRelease`
--

CREATE TABLE `BookRelease` (
  `release_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `borrow_price` int(11) NOT NULL,
  `exchange_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `Borrow`
--

CREATE TABLE `Borrow` (
  `borrow_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `borrowdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yhdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `borrow_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `BorrowHistory`
--

CREATE TABLE `BorrowHistory` (
  `bh_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `borrowdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yhdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `indate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `borrow_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `Exchange`
--

CREATE TABLE `Exchange` (
  `exchange_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `wanted_id` int(11) NOT NULL,
  `had_id` int(11) NOT NULL,
  `applydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `ExchangeHistory`
--

CREATE TABLE `ExchangeHistory` (
  `eh_id` int(11) NOT NULL,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `book1_id` int(11) NOT NULL,
  `book2_id` int(11) NOT NULL,
  `exchangedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `applydate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `OrderItem`
--

CREATE TABLE `OrderItem` (
  `orderitem_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `Orders`
--

CREATE TABLE `Orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `orderdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `User`
--

CREATE TABLE `User` (
  `user_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `credit` int(11) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Apply`
--
ALTER TABLE `Apply`
  ADD PRIMARY KEY (`apply_id`);

--
-- Indexes for table `Book`
--
ALTER TABLE `Book`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `BookRelease`
--
ALTER TABLE `BookRelease`
  ADD PRIMARY KEY (`release_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `Borrow`
--
ALTER TABLE `Borrow`
  ADD PRIMARY KEY (`borrow_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `BorrowHistory`
--
ALTER TABLE `BorrowHistory`
  ADD PRIMARY KEY (`bh_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `Exchange`
--
ALTER TABLE `Exchange`
  ADD PRIMARY KEY (`exchange_id`);

--
-- Indexes for table `ExchangeHistory`
--
ALTER TABLE `ExchangeHistory`
  ADD PRIMARY KEY (`eh_id`);

--
-- Indexes for table `OrderItem`
--
ALTER TABLE `OrderItem`
  ADD PRIMARY KEY (`orderitem_id`);

--
-- Indexes for table `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`user_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `Apply`
--
ALTER TABLE `Apply`
  MODIFY `apply_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `Book`
--
ALTER TABLE `Book`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `BookRelease`
--
ALTER TABLE `BookRelease`
  MODIFY `release_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `Borrow`
--
ALTER TABLE `Borrow`
  MODIFY `borrow_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `BorrowHistory`
--
ALTER TABLE `BorrowHistory`
  MODIFY `bh_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `Exchange`
--
ALTER TABLE `Exchange`
  MODIFY `exchange_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `ExchangeHistory`
--
ALTER TABLE `ExchangeHistory`
  MODIFY `eh_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `OrderItem`
--
ALTER TABLE `OrderItem`
  MODIFY `orderitem_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `Orders`
--
ALTER TABLE `Orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `User`
--
ALTER TABLE `User`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
