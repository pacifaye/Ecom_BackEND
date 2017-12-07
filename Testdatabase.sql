-- phpMyAdmin SQL Dump
-- version 4.2.12deb2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 07, 2017 at 06:48 PM
-- Server version: 5.7.20
-- PHP Version: 5.6.7-1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Testdatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `ccard`
--

CREATE TABLE IF NOT EXISTS `ccard` (
  `IDCD` int(11) NOT NULL,
  `IDCM` int(11) NOT NULL,
  `NUMBERS` int(11) NOT NULL,
  `NAME` int(100) NOT NULL,
  `DATE` varchar(10) NOT NULL,
  `CODE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `Commande`
--

CREATE TABLE IF NOT EXISTS `Commande` (
`IDCM` int(11) NOT NULL,
  `IDUS` int(11) NOT NULL,
  `IDPR` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `PRICE` int(11) NOT NULL,
  `STATE` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Commande`
--

INSERT INTO `Commande` (`IDCM`, `IDUS`, `IDPR`, `DATE`, `PRICE`, `STATE`) VALUES
(2, 1995, 91, '2017-12-07', 35, 1),
(4, 1995, 23, '2017-12-07', 56, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Formation`
--

CREATE TABLE IF NOT EXISTS `Formation` (
  `id` int(11) NOT NULL,
  `theme` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `Produit`
--

CREATE TABLE IF NOT EXISTS `Produit` (
`IDPR` int(11) NOT NULL,
  `IDUS` int(11) NOT NULL,
  `TITLE` varchar(200) NOT NULL,
  `DESCRIPTION` text NOT NULL,
  `LINKPICTURE` varchar(100) NOT NULL,
  `PRICEMIN` int(11) NOT NULL,
  `PRICEMAX` int(11) NOT NULL,
  `ZIPCODE` int(11) NOT NULL,
  `EXPIRATION_DATE` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Produit`
--

INSERT INTO `Produit` (`IDPR`, `IDUS`, `TITLE`, `DESCRIPTION`, `LINKPICTURE`, `PRICEMIN`, `PRICEMAX`, `ZIPCODE`, `EXPIRATION_DATE`) VALUES
(23, 93, 'Iphone', 'Iphone X', 'No link', 0, 100, 38000, '2017-11-22'),
(88, 93, 'hello world', 'hello world hello world hello world', 'No one', 0, 100, 38000, '2017-11-14'),
(91, 1994, 'Merry', 'kjkjkj', 'none', 23, 50, 38000, '2017-12-07');

-- --------------------------------------------------------

--
-- Table structure for table `SuiviCommande`
--

CREATE TABLE IF NOT EXISTS `SuiviCommande` (
  `IDCM` int(11) NOT NULL,
  `IDUS` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `PRICE` int(11) NOT NULL,
  `STATE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Utilisateur`
--

CREATE TABLE IF NOT EXISTS `Utilisateur` (
`IDUS` int(11) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` int(11) DEFAULT NULL,
  `SUSCRIBDATE` date DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1996 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Utilisateur`
--

INSERT INTO `Utilisateur` (`IDUS`, `EMAIL`, `FNAME`, `NAME`, `PASSWORD`, `PHONE`, `SUSCRIBDATE`, `STATE`) VALUES
(1993, 'amine@gmail.com', 'Amine', 'der', 'admin', 83784, '2017-11-22', 0),
(1994, 'pacifaye@yahoo.fr', 'Pacy', 'Faye', 'azerty', 784657, '2017-12-07', 1),
(1995, 'golo@gala.com', 'Huge', 'Dick', 'azerty', 265656, '2017-12-07', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ccard`
--
ALTER TABLE `ccard`
 ADD PRIMARY KEY (`IDCD`,`IDCM`);

--
-- Indexes for table `Commande`
--
ALTER TABLE `Commande`
 ADD PRIMARY KEY (`IDCM`);

--
-- Indexes for table `Formation`
--
ALTER TABLE `Formation`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Produit`
--
ALTER TABLE `Produit`
 ADD PRIMARY KEY (`IDPR`);

--
-- Indexes for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
 ADD PRIMARY KEY (`IDUS`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Commande`
--
ALTER TABLE `Commande`
MODIFY `IDCM` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Produit`
--
ALTER TABLE `Produit`
MODIFY `IDPR` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=92;
--
-- AUTO_INCREMENT for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
MODIFY `IDUS` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1996;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
