-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:8889
-- Généré le :  Jeu 30 Novembre 2017 à 12:45
-- Version du serveur :  5.6.35
-- Version de PHP :  7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `Testdatabase`
--

-- --------------------------------------------------------
USE Testdatabase;
--
-- Structure de la table `ccard`
--

CREATE TABLE `ccard` (
  `IDCD` int(11) NOT NULL,
  `IDCM` int(11) NOT NULL,
  `NUMBERS` int(11) NOT NULL,
  `NAME` int(100) NOT NULL,
  `DATE` varchar(10) NOT NULL,
  `CODE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Structure de la table `Commande`
--

CREATE TABLE `Commande` (
  `IDCM` int(11) NOT NULL,
  `IDPR` int(11) NOT NULL,
  `IDUS` int(11) NOT NULL,
  `PRICE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Formation`
--

CREATE TABLE `Formation` (
  `id` int(11) NOT NULL,
  `theme` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `Produit`
--

CREATE TABLE `Produit` (
  `IDPR` int(11) NOT NULL,
  `IDUS` int(11) NOT NULL,
  `TITLE` varchar(200) NOT NULL,
  `DESCRIPTION` text NOT NULL,
  `LINKPICTURE` varchar(100) NOT NULL,
  `PRICEMIN` int(11) NOT NULL,
  `PRICEMAX` int(11) NOT NULL,
  `ZIPCODE` int(11) NOT NULL,
  `EXPIRATION_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Produit`
--

INSERT INTO `Produit` (`IDPR`, `IDUS`, `TITLE`, `DESCRIPTION`, `LINKPICTURE`, `PRICEMIN`, `PRICEMAX`, `ZIPCODE`, `EXPIRATION_DATE`) VALUES
(23, 93, 'Iphone', 'Iphone X', 'No link', 0, 100, 38000, '2017-11-22'),
(88, 93, 'hello world', 'hello world hello world hello world', 'No one', 0, 100, 38000, '2017-11-14');

-- --------------------------------------------------------

--
-- Structure de la table `SuiviCommande`
--

CREATE TABLE `SuiviCommande` (
  `IDCM` int(11) NOT NULL,
  `IDUS` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `PRICE` int(11) NOT NULL,
  `STATE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `IDUS` int(11) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` int(11) DEFAULT NULL,
  `SUSCRIBDATE` date DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`IDUS`, `EMAIL`, `FNAME`, `NAME`, `PASSWORD`, `PHONE`, `SUSCRIBDATE`, `STATE`) VALUES
(1993, 'amine@gmail.com', 'Amine', 'der', 'admin', 83784, '2017-11-22', 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ccard`
--
ALTER TABLE `ccard`
  ADD PRIMARY KEY (`IDCD`,`IDCM`);

--
-- Index pour la table `Commande`
--
ALTER TABLE `Commande`
  ADD PRIMARY KEY (`IDCM`,`IDPR`,`IDUS`);

--
-- Index pour la table `Formation`
--
ALTER TABLE `Formation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Produit`
--
ALTER TABLE `Produit`
  ADD PRIMARY KEY (`IDPR`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`IDUS`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `IDUS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1994;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
