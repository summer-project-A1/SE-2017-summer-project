DROP TABLE IF EXISTS `Apply`;
DROP TABLE IF EXISTS `Book`;
DROP TABLE IF EXISTS `BookRelease`;
DROP TABLE IF EXISTS `Borrow`;
DROP TABLE IF EXISTS `BorrowHistory`;
DROP TABLE IF EXISTS `Exchange`;
DROP TABLE IF EXISTS `ExchangeHistory`;
DROP TABLE IF EXISTS `OrderItem`;
DROP TABLE IF EXISTS `Orders`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Comment`;

CREATE TABLE `Apply` (
  `apply_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
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
  `category` varchar(50) NOT NULL,
  `canexchange` int(11) NOT NULL,
  `canborrow` int(11) NOT NULL,
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
  `borrow_price` int(11) NOT NULL,
  `exchange_price` int(11) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
);

-- --------------------------------------------------------

--
-- 表的结构 `Borrow`
--

CREATE TABLE `Borrow` (
  `borrow_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `borrowdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yhdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `borrow_price` int(11) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
);

-- --------------------------------------------------------

--
-- 表的结构 `BorrowHistory`
--

CREATE TABLE `BorrowHistory` (
  `bh_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `borrowdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yhdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `indate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `borrow_price` int(11) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
);

-- --------------------------------------------------------

--
-- 表的结构 `Exchange`
--

CREATE TABLE `Exchange` (
  `exchange_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `wanted_id` int(11) NOT NULL,
  `had_id` int(11) NOT NULL,
  `applydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------

--
-- 表的结构 `ExchangeHistory`
--

CREATE TABLE `ExchangeHistory` (
  `eh_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `book1_id` int(11) NOT NULL,
  `book2_id` int(11) NOT NULL,
  `exchangedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `applydate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
);

-- --------------------------------------------------------

--
-- 表的结构 `OrderItem`
--

CREATE TABLE `OrderItem` (
  `orderitem_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `price` int(11) NOT NULL
);

-- --------------------------------------------------------

--
-- 表的结构 `Orders`
--

CREATE TABLE `Orders` (
  `order_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `orderdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_price` int(11) NOT NULL,
  `status` int(11) NOT NULL
);

-- --------------------------------------------------------

--
-- 表的结构 `User`
--

CREATE TABLE `User` (
  `user_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL UNIQUE,
  `credit` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `profile_id` varchar(50) NOT NULL,
  `image_id` varchar(50) NOT NULL
);

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `Comment` (
  `comment_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL
);
