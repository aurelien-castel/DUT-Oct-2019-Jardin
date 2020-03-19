-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 10, 2019 at 08:38 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `guillemo`
--

-- --------------------------------------------------------

--
-- Table structure for table `Parcelle`
--

CREATE TABLE `Parcelle` (
  `idparcelle` smallint(6) NOT NULL,
  `nomJardin` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `x0` int(12) NOT NULL,
  `x1` int(12) NOT NULL,
  `y0` int(12) NOT NULL,
  `y1` int(12) NOT NULL,
  `Orientation` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sousParcelle0` smallint(6) DEFAULT NULL,
  `sousParcelle1` smallint(6) DEFAULT NULL,
  `ParcelleRacine` smallint(6) DEFAULT NULL,
  `ParcelleMere` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `Parcelle`
--

INSERT INTO `Parcelle` (`idparcelle`, `nomJardin`, `x0`, `x1`, `y0`, `y1`, `Orientation`, `sousParcelle0`, `sousParcelle1`, `ParcelleRacine`, `ParcelleMere`) VALUES
(31, 'Arbe', 0, 100, 0, 300, 'HORIZONTAL', 32, 33, 31, 31),
(32, 'Arbe', 0, 0, 50, 300, 'VERTICAL', 34, 35, 31, 31),
(33, 'Arbe', 50, 0, 100, 300, NULL, NULL, NULL, 31, 31),
(34, 'Arbe', 0, 50, 0, 125, 'HORIZONTAL', 36, 37, 31, 32),
(35, 'Arbe', 0, 125, 0, 300, NULL, NULL, NULL, 31, 32),
(36, 'Arbe', 0, 0, 25, 125, 'VERTICAL', 38, 39, 31, 34),
(37, 'Arbe', 25, 0, 50, 125, NULL, NULL, NULL, 31, 34),
(38, 'Arbe', 0, 25, 0, 50, NULL, NULL, NULL, 31, 36),
(39, 'Arbe', 0, 50, 0, 125, NULL, NULL, NULL, 31, 36);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Parcelle`
--
ALTER TABLE `Parcelle`
  ADD PRIMARY KEY (`idparcelle`),
  ADD KEY `sousParcelle0` (`sousParcelle0`),
  ADD KEY `sousParcelle1` (`sousParcelle1`),
  ADD KEY `ParcelleRacine` (`ParcelleRacine`),
  ADD KEY `ParcelleMere` (`ParcelleMere`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Parcelle`
--
ALTER TABLE `Parcelle`
  MODIFY `idparcelle` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Parcelle`
--
ALTER TABLE `Parcelle`
  ADD CONSTRAINT `ParcelleMere` FOREIGN KEY (`ParcelleMere`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ParcelleRacine` FOREIGN KEY (`ParcelleRacine`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sousParcelle0` FOREIGN KEY (`sousParcelle0`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `sousParcelle1` FOREIGN KEY (`sousParcelle1`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
