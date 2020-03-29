-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  mer. 16 oct. 2019 à 16:15
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
-- Structure de la table `Parcelle`
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
  `ParcelleMere` smallint(6) DEFAULT NULL,
  `idLegume` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `Parcelle`
--

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Parcelle`
--
ALTER TABLE `Parcelle`
  ADD PRIMARY KEY (`idparcelle`),
  ADD KEY `sousParcelle0` (`sousParcelle0`),
  ADD KEY `sousParcelle1` (`sousParcelle1`),
  ADD KEY `ParcelleRacine` (`ParcelleRacine`),
  ADD KEY `ParcelleMere` (`ParcelleMere`),
  ADD KEY `idLegume` (`idLegume`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Parcelle`
--
ALTER TABLE `Parcelle`
  MODIFY `idparcelle` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Parcelle`
--
ALTER TABLE `Parcelle`
  ADD CONSTRAINT `ParcelleMere` FOREIGN KEY (`ParcelleMere`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ParcelleRacine` FOREIGN KEY (`ParcelleRacine`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idLegume` FOREIGN KEY (`idLegume`) REFERENCES `Legumes` (`idLegume`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `sousParcelle0` FOREIGN KEY (`sousParcelle0`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `sousParcelle1` FOREIGN KEY (`sousParcelle1`) REFERENCES `Parcelle` (`idparcelle`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
