-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Bulan Mei 2026 pada 20.26
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `matchcards_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `histori`
--

CREATE TABLE `histori` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `tanggal` varchar(20) NOT NULL,
  `error_count` int(11) NOT NULL,
  `durasi_detik` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `histori`
--

INSERT INTO `histori` (`id`, `nama`, `tanggal`, `error_count`, `durasi_detik`) VALUES
(3, 'asep', '18-05-2026 00:59', 11, 38),
(4, 'asep', '18-05-2026 01:02', 19, 60),
(5, 'AGUNG', '18-05-2026 01:05', 17, 58),
(6, 'AGUNG', '18-05-2026 01:06', 9, 35),
(7, 'admin', '18-05-2026 01:07', 5, 23);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `histori`
--
ALTER TABLE `histori`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `histori`
--
ALTER TABLE `histori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
