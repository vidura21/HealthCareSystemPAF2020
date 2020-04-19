-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2020 at 05:13 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--
CREATE DATABASE IF NOT EXISTS `hospital` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `hospital`;

-- --------------------------------------------------------

--
-- Table structure for table `hospitals`
--

DROP TABLE IF EXISTS `hospitals`;
CREATE TABLE `hospitals` (
  `HospitalID` int(10) NOT NULL,
  `HospitalCode` varchar(20) NOT NULL,
  `HospitalName` varchar(20) NOT NULL,
  `HospitalAddress` varchar(30) NOT NULL,
  `HospitalPhoNo` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `hospitals`
--

TRUNCATE TABLE `hospitals`;
--
-- Dumping data for table `hospitals`
--

INSERT DELAYED IGNORE INTO `hospitals` (`HospitalID`, `HospitalCode`, `HospitalName`, `HospitalAddress`, `HospitalPhoNo`) VALUES
(7, 'Host10', 'Lanka', 'Kiribathgoda', 112568342),
(8, 'Host3', 'Co-op', 'Kurunegala', 372050348),
(9, 'Host4', 'Durdans', 'Kurunegala', 372037521),
(11, 'Host8', 'Peradeniya', 'Kandy', 375565958);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hospitals`
--
ALTER TABLE `hospitals`
  ADD PRIMARY KEY (`HospitalID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hospitals`
--
ALTER TABLE `hospitals`
  MODIFY `HospitalID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
