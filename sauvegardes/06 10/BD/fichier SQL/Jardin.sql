-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  mar. 01 oct. 2019 à 20:28
-- Version du serveur :  10.1.34-MariaDB
-- Version de PHP :  7.2.7


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";
 
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `guillemo`
--

-- --------------------------------------------------------

--
-- Structure de la table `Jardin`
--

USE `guillemo`;
CREATE TABLE `Jardin` (
  `idjardin` smallint(6) NOT NULL,
  `nomJardin` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dimx` int(12) NOT NULL,
  `dimy` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `Jardin`
--

--
-- Index pour la table `Jardin`
--
ALTER TABLE `Jardin`
  ADD PRIMARY KEY (`idjardin`),
  ADD UNIQUE KEY `nomJardin` (`nomJardin`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Jardin`
--
ALTER TABLE `Jardin`
  MODIFY `idjardin` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
