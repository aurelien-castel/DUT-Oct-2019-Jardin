-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  mer. 16 oct. 2019 à 17:21
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
-- Structure de la table `Legume`
--

CREATE TABLE `Legume` (
  `idLegume` smallint(6) NOT NULL,
  `nomLegume` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `familleLegume` tinyint(4) NOT NULL,
  `semis` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL,
  `recolte` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `Legume`
--

INSERT INTO `Legume` (`idLegume`, `nomLegume`, `familleLegume`, `semis`, `recolte`) VALUES
(47, 'Poireau de Carentan', 1, '100', '11111111000');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Legume`
--
ALTER TABLE `Legume`
  ADD PRIMARY KEY (`idLegume`),
  ADD UNIQUE KEY `nomLegume` (`nomLegume`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Legume`
--
ALTER TABLE `Legume`
  MODIFY `idLegume` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
