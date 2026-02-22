-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 22, 2026 at 12:41 PM
-- Server version: 8.0.30
-- PHP Version: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentalps`
--

-- --------------------------------------------------------

--
-- Table structure for table `ps`
--

CREATE TABLE `ps` (
  `id_ps` int NOT NULL,
  `jenis_ps` varchar(20) NOT NULL,
  `harga_per_jam` int NOT NULL,
  `status` enum('tersedia','disewa') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `ps`
--

INSERT INTO `ps` (`id_ps`, `jenis_ps`, `harga_per_jam`, `status`) VALUES
(6, 'Playstation 1', 2000, 'tersedia'),
(7, 'PS 5', 9000, 'disewa'),
(8, 'Playstaton 4', 7000, 'disewa'),
(11, 'Playstation 2', 3000, 'disewa'),
(12, 'PS 3', 5000, 'tersedia'),
(13, 'PS  1', 2000, 'tersedia');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int NOT NULL,
  `id_ps` int NOT NULL,
  `durasi` int NOT NULL,
  `total` int NOT NULL,
  `nama_pelanggan` varchar(255) NOT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_ps`, `durasi`, `total`, `nama_pelanggan`, `tanggal`) VALUES
(7, 8, 4, 28000, 'rafly', '2025-12-06'),
(8, 6, 94, 188000, 'ripai', '2025-12-06'),
(9, 6, 2, 900000, 'raf', '2025-12-06'),
(10, 12, 10, 50000, 'olivia', '2025-12-24'),
(11, 11, 3, 9000, 'wis', '2025-12-08'),
(12, 11, 6, 18000, 'jaki', '2025-12-08'),
(13, 12, 12, 60000, 'kai', '2025-12-25'),
(14, 6, 2, 4000, 'riki', '2026-01-05'),
(15, 12, 9, 45000, 'chantika', '2025-11-21'),
(16, 13, 10, 20000, 'seto', '2025-12-11'),
(17, 6, 10, 20000, 'faqih', '2025-12-11');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_users` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` enum('admin','user') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_users`, `username`, `password`, `role`) VALUES
(3, 'admin', 'admin123', 'admin'),
(4, 'petugas', '12345', 'user'),
(7, 'kijang1', '111', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ps`
--
ALTER TABLE `ps`
  ADD PRIMARY KEY (`id_ps`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_ps` (`id_ps`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_users`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ps`
--
ALTER TABLE `ps`
  MODIFY `id_ps` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_users` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_ps`) REFERENCES `ps` (`id_ps`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
