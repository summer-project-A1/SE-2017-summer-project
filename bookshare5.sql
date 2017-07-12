/*
 * 2017/7/7
 *
 * 更新：Apply表改名为Reserve（图书预约）
 *       Borrow表和BorrowHistory表添加delay_count属性（判断是否延期过）
 *       orders、borrow、borrowhistory表添加province、city、district、address四个属性
 *       user表添加nickname（昵称）属性，province、city、district、address四个属性（用户地址，区别于收货地址）
 *       book表的category属性改为category1、category2，保存对应的属性id
 * 添加：category1表和category2表
 *
 */

 USE bookshare;
 
DROP TABLE IF EXISTS `Category2`;
DROP TABLE IF EXISTS `Category1`;
DROP TABLE IF EXISTS `Reserve`;
DROP TABLE IF EXISTS `Book`;
DROP TABLE IF EXISTS `BookRelease`;
DROP TABLE IF EXISTS `Borrow`;
DROP TABLE IF EXISTS `BorrowHistory`;
DROP TABLE IF EXISTS `Exchange`;
DROP TABLE IF EXISTS `ExchangeHistory`;
DROP TABLE IF EXISTS `Orders`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Comment`;

CREATE TABLE `Reserve` (
  `reserve_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `due` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
);

-- --------------------------------------------------------

--
-- 表的结构 `Book`
--

CREATE TABLE `Book` (
  `book_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `bookname` varchar(50) NOT NULL,
  `isbn` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `press` varchar(50) NOT NULL,
  `category1` varchar(30) NOT NULL,
  `category2` varchar(30) NOT NULL,
  `canexchange` int(11) NOT NULL,
  `canborrow` int(11) NOT NULL,
  `borrow_price` int(11) NOT NULL,
  `exchange_price` int(11) NOT NULL,
  `reserved` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `profile_id` varchar(50) NOT NULL,
  `image_id` varchar(50) NOT NULL
);

-- --------------------------------------------------------

--
-- 表的结构 `BookRelease`
--

CREATE TABLE `BookRelease` (
  `release_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `release_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
);

-- --------------------------------------------------------

--
-- 表的结构 `Borrow`
--

CREATE TABLE `Borrow` (
  `borrow_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id1` int(11) NOT NULL,
  `user_id2` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `yhdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `borrow_price` int(11) NOT NULL,
  `delay_count` int(4) NOT NULL,
  `status`   int(4) NOT NULL,
  `borrow_address` varchar(100) NOT NULL,
  `return_address` varchar(100) NOT NULL,
  `trackingNo1` varchar(30) NOT NULL,
  `trackingNo2` varchar(30) NOT NULL,
  `pay_date`   NULL NULL timestamp DEFAULT NULL,
  `fhdate`  NULL NULL timestamp DEFAULT NULL,
  `borrow_date`  NULL NULL timestamp DEFAULT NULL,
  `return_date`  NULL NULL timestamp DEFAULT NULL,
  `shdate`  NULL NULL timestamp DEFAULT NULL,
  `comment1`   int(4) DEFAULT NULL,
  `comment2`   int(4) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
);

-- --------------------------------------------------------

--
-- 表的结构 `BorrowHistory`
--

CREATE TABLE `BorrowHistory` (
  `bh_id` int(11) NOT NULL PRIMARY KEY ,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `yhdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `borrow_price` int(11) NOT NULL,
  `delay_count` int(4) NOT NULL,
  `borrow_address` varchar(100) NOT NULL,
  `return_address` varchar(100) NOT NULL,
  `trackingNo1` varchar(30) NOT NULL,
  `trackingNo2` varchar(30) NOT NULL,
  `pay_date`   NULL NULL timestamp DEFAULT NULL,
  `fhdate`  NULL NULL timestamp DEFAULT NULL,
  `borrow_date`  NULL NULL timestamp DEFAULT NULL,
  `return_date`  NULL NULL timestamp DEFAULT NULL,
  `shdate`  NULL NULL timestamp DEFAULT NULL,
  `comment1`   int(4) DEFAULT NULL,
  `comment2`   int(4) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
);

-- --------------------------------------------------------

--
-- 表的结构 `Exchange`
--

CREATE TABLE `Exchange` (
  `exchange_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `wanted_id` int(11) NOT NULL,
  `had_id` int(11) NOT NULL,
  `status` int(4) NOT NULL,
  `applydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fh_date1`  NULL NULL timestamp DEFAULT NULL,
  `fh_date2`  NULL NULL timestamp DEFAULT NULL,
  `sh_date1`  NULL NULL timestamp DEFAULT NULL,
  `sh_date2`  NULL NULL timestamp DEFAULT NULL,
  `comment1`   int(4) DEFAULT NULL,
  `comment2`   int(4) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- 表的结构 `ExchangeHistory`
--

CREATE TABLE `ExchangeHistory` (
  `eh_id` int(11) NOT NULL PRIMARY KEY,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `wanted_id` int(11) NOT NULL,
  `had_id` int(11) NOT NULL,
  `status` int(4) NOT NULL,
  `applydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fh_date1`  NULL NULL timestamp DEFAULT NULL,
  `fh_date2`  NULL NULL timestamp DEFAULT NULL,
  `sh_date1`  NULL NULL timestamp DEFAULT NULL,
  `sh_date2`  NULL NULL timestamp DEFAULT NULL,
  `comment1`   int(4) DEFAULT NULL,
  `comment2`   int(4) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- 表的结构 `Orders`
--

CREATE TABLE `Orders` (
  `order_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `bookid`  int(11) NOT NULL,
  `price`   int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `fh_date` NULL NULL timestamp DEFAULT NULL,
  `sh_date` NULL NULL timestamp DEFAULT NULL,
  `trackingNo` varchar(30),
  `buyer_comment`   int(4) DEFAULT NULL,
  `seller_comment`   int(4) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- 表的结构 `User`
--

CREATE TABLE `User` (
  `user_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nickname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL UNIQUE,
  `credit` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `honesty` int(11) NOT NULL,
  `profile_id` varchar(50) NOT NULL,
  `image_id` varchar(50) NOT NULL,
  `province` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `Comment` (
  `borrow_id` int(11) NOT NULL PRIMARY KEY,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL
);


CREATE TABLE `Category1` (
  `category1_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `category1_name` varchar(50) NOT NULL
);

CREATE TABLE `Category2` (
  `category2_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `category2_name` varchar(50) NOT NULL,
  `category1_id` int(11) NOT NULL
);
