-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 29, 2016 at 04:19 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `beacathon`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `itemID` int(11) NOT NULL,
  `itemName` varchar(255) NOT NULL,
  `itemPrice` float NOT NULL,
  `itemQuantity` int(11) NOT NULL,
  `itemCheck` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`itemID`, `itemName`, `itemPrice`, `itemQuantity`, `itemCheck`) VALUES
(1, 'Dominos Pizza', 50, 10, 0),
(2, 'Five Star Chocolate', 10, 1, 0),
(3, 'Motorola G4', 100, 2, 0),
(4, 'One plus 3', 200, 1, 0),
(5, 'Sony Vaio Laptop', 90, 4, 0),
(6, 'Dell Inspiron', 150, 1, 0),
(7, 'Lenskart Glasses', 50, 1, 0),
(8, 'Nescafe Coffee Bags', 80, 20, 0),
(9, 'Green tea bags', 10, 1, 0),
(10, 'Bourborn Biscuits', 10, 1, 0),
(11, 'Honeytus', 5, 1, 0),
(12, 'Strepsils', 10, 1, 0),
(13, 'Whirlpool Washing Machine', 120, 1, 0),
(14, 'Samsung LED TV', 200, 1, 0),
(15, 'HP Pavilion Laptop', 250, 1, 0),
(16, 'WD Ultra Hard Drive', 160, 1, 0),
(17, 'Kinley Water Bottle', 20, 1, 0),
(18, 'Hero Karizma', 300, 1, 0),
(19, 'Whiteboard', 50, 1, 0),
(20, 'Tata Salt', 20, 1, 0),
(21, 'Cauli Flower', 10, 1, 0),
(22, 'Samsung mobile charger', 10, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gcm_registration_id` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `name`, `email`, `gcm_registration_id`, `created_at`) VALUES
(32, 'Lovepreet', 'lovepreet@gmail.com', '', '2016-12-29 09:26:24'),
(33, 'chetan', 'chetan@gmail.com', '', '2016-12-29 09:26:39'),
(34, 'AndroidHive', 'admin@androidhive.info', '', '2016-12-29 11:32:02');

-- --------------------------------------------------------

--
-- Table structure for table `usertransaction`
--

CREATE TABLE `usertransaction` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `list` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usertransaction`
--

INSERT INTO `usertransaction` (`id`, `name`, `mobile`, `list`) VALUES
(35, 'tushar', '8130533512', '1|10-2|1-3|2-4|1-5|4-6|1-7|1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`itemID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `usertransaction`
--
ALTER TABLE `usertransaction`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `itemID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `usertransaction`
--
ALTER TABLE `usertransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
