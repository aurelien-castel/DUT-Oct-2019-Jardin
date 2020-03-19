-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  mer. 16 oct. 2019 à 16:51
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
-- Structure de la table `Action`
--

CREATE TABLE `Action` (
  `idAction` smallint(6) NOT NULL,
  `Date` date NOT NULL,
  `Parcelle` smallint(6) NOT NULL,
  `ParcelleRacine` smallint(6) NOT NULL,
  `Type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Legume` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Action`
--
ALTER TABLE `Action`
  ADD PRIMARY KEY (`idAction`),
  ADD KEY `Parcelle` (`Parcelle`),
  ADD KEY `ParcelleRacine` (`ParcelleRacine`),
  ADD KEY `Legume` (`Legume`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Action`
--
ALTER TABLE `Action`
  MODIFY `idAction` smallint(6) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Action`
--
ALTER TABLE `Action`
  ADD CONSTRAINT `Legume` FOREIGN KEY (`Legume`) REFERENCES `Legumes` (`idLegume`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Parcelle` FOREIGN KEY (`Parcelle`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ParcelleRacine2` FOREIGN KEY (`ParcelleRacine`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
