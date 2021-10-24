-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2021. Okt 17. 13:09
-- Kiszolgáló verziója: 10.4.21-MariaDB
-- PHP verzió: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `etr`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `belepesi_adat`
--

CREATE TABLE `belepesi_adat` (
  `login_nev` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `jelszo` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `belepesi_adat`
--

INSERT INTO `belepesi_adat` (`login_nev`, `jelszo`, `neptun_kod`) VALUES
('DGVE45', '6eIP5gHqKq', 'DGVE45'),
('FBRM92', 'EHWdxUYM1a', 'FBRM92'),
('FNDU56', 'TzVz3Jm1hG', 'FNDU56'),
('GCLO63', 'cYTpqrW4Pk', 'GCLO63'),
('YKME96', 'a7pkQjELdZ', 'YKME96'),
('YNQM27', 'XkMuT2vjEI', 'YNQM27');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felev`
--

CREATE TABLE `felev` (
  `evfolyam` int(4) NOT NULL,
  `szemeszter` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `felev`
--

INSERT INTO `felev` (`evfolyam`, `szemeszter`) VALUES
(2020, 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felevet_elvegez`
--

CREATE TABLE `felevet_elvegez` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `evfolyam` int(4) NOT NULL,
  `szemeszter` int(1) NOT NULL DEFAULT 1,
  `felvett_kredit` int(2) NOT NULL,
  `teljesitett_kredit` int(2) DEFAULT NULL,
  `tanulmanyi_atlag` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `felevet_elvegez`
--

INSERT INTO `felevet_elvegez` (`neptun_kod`, `evfolyam`, `szemeszter`, `felvett_kredit`, `teljesitett_kredit`, `tanulmanyi_atlag`) VALUES
('YNQM27', 2020, 1, 25, 25, 4.71);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felhasznalo`
--

CREATE TABLE `felhasznalo` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `vezeteknev` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `keresztnev` varchar(20) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `felhasznalo`
--

INSERT INTO `felhasznalo` (`neptun_kod`, `vezeteknev`, `keresztnev`) VALUES
('DGVE45', 'Nagy', 'Antal'),
('FBRM92', 'Gergely', 'Tamás'),
('FNDU56', 'Németh', 'Zoltán'),
('GCLO63', 'Kátai', 'Kamilla'),
('YKME96', 'Bilicki', 'Vilmos'),
('YNQM27', 'Csinos', 'Richárd');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felvetelek`
--

CREATE TABLE `felvetelek` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `evfolyam` int(4) NOT NULL,
  `szemeszter` int(1) NOT NULL DEFAULT 1,
  `targy_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `kurzus_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `felvetelek`
--

INSERT INTO `felvetelek` (`neptun_kod`, `evfolyam`, `szemeszter`, `targy_kod`, `kurzus_kod`) VALUES
('YNQM27', 2020, 1, 'MBNXK311E', 'MBNX311E-1'),
('YNQM27', 2020, 1, 'MBNXK311G', 'MBNXK311G-13');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hallgato`
--

CREATE TABLE `hallgato` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `hallgato`
--

INSERT INTO `hallgato` (`neptun_kod`) VALUES
('YNQM27');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kepzes`
--

CREATE TABLE `kepzes` (
  `torzsszam` varchar(25) COLLATE utf8_hungarian_ci NOT NULL,
  `nev` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `leiras` varchar(128) COLLATE utf8_hungarian_ci NOT NULL,
  `tagozat` varchar(10) COLLATE utf8_hungarian_ci NOT NULL,
  `szint` char(3) COLLATE utf8_hungarian_ci NOT NULL,
  `statusz` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `felevek_szama` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `kepzes`
--

INSERT INTO `kepzes` (`torzsszam`, `nev`, `leiras`, `tagozat`, `szint`, `statusz`, `felevek_szama`) VALUES
('T023554/FI62198/S', 'programtervező informatikus', '[aktuális] 2017-től induló képzés', 'nappali', 'BSc', 'állami ösztöndíjas', 6);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kepzest_felvesz`
--

CREATE TABLE `kepzest_felvesz` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `torzsszam` varchar(25) COLLATE utf8_hungarian_ci NOT NULL,
  `felvetel_datum` date NOT NULL,
  `befejezes_datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `kepzest_felvesz`
--

INSERT INTO `kepzest_felvesz` (`neptun_kod`, `torzsszam`, `felvetel_datum`, `befejezes_datum`) VALUES
('YNQM27', 'T023554/FI62198/S', '2020-07-23', '2024-01-31');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kurzus`
--

CREATE TABLE `kurzus` (
  `kurzus_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `targy_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `teremszam` int(3) NOT NULL,
  `tipus` varchar(10) COLLATE utf8_hungarian_ci NOT NULL DEFAULT 'gyakorlat',
  `ferohely` int(3) NOT NULL DEFAULT 999,
  `kurzus_limit` int(3) NOT NULL DEFAULT 999
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `kurzus`
--

INSERT INTO `kurzus` (`kurzus_kod`, `targy_kod`, `neptun_kod`, `teremszam`, `tipus`, `ferohely`, `kurzus_limit`) VALUES
('MBNXK311G-13', 'MBNXK311G', 'FNDU56', 215, 'gyakorlat', 50, 50);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kurzust_felvesz`
--

CREATE TABLE `kurzust_felvesz` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `kurzus_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `felvetelek_szama` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `nev_elotag`
--

CREATE TABLE `nev_elotag` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `elotag` varchar(5) COLLATE utf8_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `okmany`
--

CREATE TABLE `okmany` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `azonosito` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `megnevezes` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `kiallitas` date NOT NULL,
  `lejarat` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `okmany`
--

INSERT INTO `okmany` (`neptun_kod`, `azonosito`, `megnevezes`, `kiallitas`, `lejarat`) VALUES
('YNQM27', '3942815932', 'adóigazolvány', '2002-03-24', NULL);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `oktato`
--

CREATE TABLE `oktato` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `oktato`
--

INSERT INTO `oktato` (`neptun_kod`) VALUES
('DGVE45'),
('FBRM92'),
('FNDU56'),
('GCLO63'),
('YKME96');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `szemelyes_adat`
--

CREATE TABLE `szemelyes_adat` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `szuletesi_hely` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `szuletesi_nev` varchar(40) COLLATE utf8_hungarian_ci NOT NULL,
  `szuletesi_datum` date NOT NULL,
  `szuletesi_megye` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `szuletesi_orszag` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `nem` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `szemelyes_adat`
--

INSERT INTO `szemelyes_adat` (`neptun_kod`, `szuletesi_hely`, `szuletesi_nev`, `szuletesi_datum`, `szuletesi_megye`, `szuletesi_orszag`, `nem`) VALUES
('YNQM27', 'Orosháza', 'Csinos Richárd', '2001-03-06', 'Békés', 'Magyarország', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `targy`
--

CREATE TABLE `targy` (
  `targy_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `elokovetelmeny_targy_kod` varchar(20) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `nev` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `kreditertek` int(11) NOT NULL DEFAULT 3,
  `heti_oraszam` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `targy`
--

INSERT INTO `targy` (`targy_kod`, `elokovetelmeny_targy_kod`, `neptun_kod`, `nev`, `kreditertek`, `heti_oraszam`) VALUES
('MBNXK311E', NULL, 'FNDU56', 'Kalkulus I.', 2, 2),
('MBNXK311G', NULL, 'FNDU56', 'Kalkulus I.', 3, 2);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `targyat_felvesz`
--

CREATE TABLE `targyat_felvesz` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `targy_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `felvetelek_szama` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `terem`
--

CREATE TABLE `terem` (
  `teremszam` int(3) NOT NULL,
  `megnevezes` varchar(20) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `terem`
--

INSERT INTO `terem` (`teremszam`, `megnevezes`) VALUES
(215, 'Haar Alfréd terem');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `vizsga`
--

CREATE TABLE `vizsga` (
  `kurzus_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `idopont` datetime NOT NULL,
  `teremszam` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `vizsgazik`
--

CREATE TABLE `vizsgazik` (
  `neptun_kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `kurzus_kod` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `idopont` datetime NOT NULL,
  `eredmeny` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `belepesi_adat`
--
ALTER TABLE `belepesi_adat`
  ADD PRIMARY KEY (`login_nev`),
  ADD KEY `neptun_kod` (`neptun_kod`);

--
-- A tábla indexei `felev`
--
ALTER TABLE `felev`
  ADD PRIMARY KEY (`evfolyam`,`szemeszter`),
  ADD KEY `szemeszter` (`szemeszter`);

--
-- A tábla indexei `felevet_elvegez`
--
ALTER TABLE `felevet_elvegez`
  ADD PRIMARY KEY (`neptun_kod`,`evfolyam`,`szemeszter`),
  ADD KEY `evfolyam` (`evfolyam`,`szemeszter`),
  ADD KEY `szemeszter` (`szemeszter`);

--
-- A tábla indexei `felhasznalo`
--
ALTER TABLE `felhasznalo`
  ADD PRIMARY KEY (`neptun_kod`);

--
-- A tábla indexei `felvetelek`
--
ALTER TABLE `felvetelek`
  ADD PRIMARY KEY (`neptun_kod`,`evfolyam`,`szemeszter`,`targy_kod`),
  ADD KEY `evfolyam` (`evfolyam`),
  ADD KEY `szemeszter` (`szemeszter`);

--
-- A tábla indexei `hallgato`
--
ALTER TABLE `hallgato`
  ADD PRIMARY KEY (`neptun_kod`);

--
-- A tábla indexei `kepzes`
--
ALTER TABLE `kepzes`
  ADD PRIMARY KEY (`torzsszam`);

--
-- A tábla indexei `kepzest_felvesz`
--
ALTER TABLE `kepzest_felvesz`
  ADD PRIMARY KEY (`neptun_kod`,`torzsszam`,`felvetel_datum`),
  ADD KEY `torzsszam` (`torzsszam`);

--
-- A tábla indexei `kurzus`
--
ALTER TABLE `kurzus`
  ADD PRIMARY KEY (`kurzus_kod`),
  ADD KEY `teremszam` (`teremszam`),
  ADD KEY `neptun_kod` (`neptun_kod`),
  ADD KEY `targy_kod` (`targy_kod`);

--
-- A tábla indexei `kurzust_felvesz`
--
ALTER TABLE `kurzust_felvesz`
  ADD PRIMARY KEY (`neptun_kod`,`kurzus_kod`),
  ADD KEY `kurzus_kod` (`kurzus_kod`);

--
-- A tábla indexei `nev_elotag`
--
ALTER TABLE `nev_elotag`
  ADD PRIMARY KEY (`neptun_kod`);

--
-- A tábla indexei `okmany`
--
ALTER TABLE `okmany`
  ADD PRIMARY KEY (`neptun_kod`,`azonosito`),
  ADD UNIQUE KEY `neptun_kod` (`neptun_kod`);

--
-- A tábla indexei `oktato`
--
ALTER TABLE `oktato`
  ADD PRIMARY KEY (`neptun_kod`);

--
-- A tábla indexei `szemelyes_adat`
--
ALTER TABLE `szemelyes_adat`
  ADD PRIMARY KEY (`neptun_kod`);

--
-- A tábla indexei `targy`
--
ALTER TABLE `targy`
  ADD PRIMARY KEY (`targy_kod`),
  ADD KEY `neptun_kod` (`neptun_kod`);

--
-- A tábla indexei `targyat_felvesz`
--
ALTER TABLE `targyat_felvesz`
  ADD PRIMARY KEY (`neptun_kod`,`targy_kod`),
  ADD KEY `targy_kod` (`targy_kod`);

--
-- A tábla indexei `terem`
--
ALTER TABLE `terem`
  ADD PRIMARY KEY (`teremszam`);

--
-- A tábla indexei `vizsga`
--
ALTER TABLE `vizsga`
  ADD PRIMARY KEY (`kurzus_kod`,`idopont`),
  ADD KEY `teremszam` (`teremszam`);

--
-- A tábla indexei `vizsgazik`
--
ALTER TABLE `vizsgazik`
  ADD PRIMARY KEY (`neptun_kod`,`kurzus_kod`,`idopont`),
  ADD KEY `kurzus_kod` (`kurzus_kod`);

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `belepesi_adat`
--
ALTER TABLE `belepesi_adat`
  ADD CONSTRAINT `belepesi_adat_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `felhasznalo` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `felevet_elvegez`
--
ALTER TABLE `felevet_elvegez`
  ADD CONSTRAINT `felevet_elvegez_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `hallgato` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `felevet_elvegez_ibfk_2` FOREIGN KEY (`evfolyam`) REFERENCES `felev` (`evfolyam`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `felevet_elvegez_ibfk_3` FOREIGN KEY (`szemeszter`) REFERENCES `felev` (`szemeszter`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `felvetelek`
--
ALTER TABLE `felvetelek`
  ADD CONSTRAINT `felvetelek_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `felevet_elvegez` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `felvetelek_ibfk_2` FOREIGN KEY (`evfolyam`) REFERENCES `felevet_elvegez` (`evfolyam`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `felvetelek_ibfk_3` FOREIGN KEY (`szemeszter`) REFERENCES `felevet_elvegez` (`szemeszter`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `hallgato`
--
ALTER TABLE `hallgato`
  ADD CONSTRAINT `hallgato_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `felhasznalo` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `kepzest_felvesz`
--
ALTER TABLE `kepzest_felvesz`
  ADD CONSTRAINT `kepzest_felvesz_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `hallgato` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kepzest_felvesz_ibfk_2` FOREIGN KEY (`torzsszam`) REFERENCES `kepzes` (`torzsszam`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `kurzus`
--
ALTER TABLE `kurzus`
  ADD CONSTRAINT `kurzus_ibfk_2` FOREIGN KEY (`teremszam`) REFERENCES `terem` (`teremszam`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kurzus_ibfk_3` FOREIGN KEY (`neptun_kod`) REFERENCES `oktato` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kurzus_ibfk_4` FOREIGN KEY (`targy_kod`) REFERENCES `targy` (`targy_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `kurzust_felvesz`
--
ALTER TABLE `kurzust_felvesz`
  ADD CONSTRAINT `kurzust_felvesz_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `hallgato` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kurzust_felvesz_ibfk_2` FOREIGN KEY (`kurzus_kod`) REFERENCES `kurzus` (`kurzus_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `nev_elotag`
--
ALTER TABLE `nev_elotag`
  ADD CONSTRAINT `nev_elotag_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `szemelyes_adat` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `okmany`
--
ALTER TABLE `okmany`
  ADD CONSTRAINT `okmany_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `felhasznalo` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `oktato`
--
ALTER TABLE `oktato`
  ADD CONSTRAINT `oktato_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `felhasznalo` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `szemelyes_adat`
--
ALTER TABLE `szemelyes_adat`
  ADD CONSTRAINT `szemelyes_adat_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `felhasznalo` (`neptun_kod`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Megkötések a táblához `targy`
--
ALTER TABLE `targy`
  ADD CONSTRAINT `targy_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `oktato` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `targyat_felvesz`
--
ALTER TABLE `targyat_felvesz`
  ADD CONSTRAINT `targyat_felvesz_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `hallgato` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `targyat_felvesz_ibfk_2` FOREIGN KEY (`targy_kod`) REFERENCES `targy` (`targy_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `vizsga`
--
ALTER TABLE `vizsga`
  ADD CONSTRAINT `vizsga_ibfk_2` FOREIGN KEY (`teremszam`) REFERENCES `terem` (`teremszam`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vizsga_ibfk_3` FOREIGN KEY (`kurzus_kod`) REFERENCES `kurzus` (`kurzus_kod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `vizsgazik`
--
ALTER TABLE `vizsgazik`
  ADD CONSTRAINT `vizsgazik_ibfk_1` FOREIGN KEY (`neptun_kod`) REFERENCES `hallgato` (`neptun_kod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vizsgazik_ibfk_2` FOREIGN KEY (`kurzus_kod`) REFERENCES `vizsga` (`kurzus_kod`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
