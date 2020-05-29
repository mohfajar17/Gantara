-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 23, 2019 at 03:57 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gantara`
--

-- --------------------------------------------------------

--
-- Table structure for table `atlet`
--

CREATE TABLE `atlet` (
  `id_atlet` int(12) NOT NULL,
  `id_psikolog` int(12) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `cabang_olahraga` varchar(255) NOT NULL,
  `jenis_kelamin` int(1) NOT NULL,
  `tempat_lahir` varchar(255) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `kota_asal` varchar(255) NOT NULL,
  `no_telefon` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token` text NOT NULL,
  `photo_profile` varchar(255) NOT NULL,
  `intensitas_target` double NOT NULL,
  `status_verifikasi` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atlet`
--

INSERT INTO `atlet` (`id_atlet`, `id_psikolog`, `nama`, `cabang_olahraga`, `jenis_kelamin`, `tempat_lahir`, `tanggal_lahir`, `alamat`, `kota_asal`, `no_telefon`, `user_name`, `password`, `token`, `photo_profile`, `intensitas_target`, `status_verifikasi`) VALUES
(2103141045, 1023151003, 'Della Dinarsari', 'Loncat Indah', 0, 'Surabaya', '2000-10-20', 'Sukolilo Surabaya', 'Surabaya', '085611300021', 'delladinar', 'delladella', '', 'delladinar.png', 60, 0),
(2103141043, 1023151003, 'Mochammad Khunaifi', 'Billiard', 1, 'Gresik', '1996-06-28', 'Manyar Gresik', 'Gresik', '08970011695', 'mkhunaifi', 'kayumanis', '', 'mkhunaifi.png', 60, 0),
(2103141040, 1023151003, 'Bey Aryo', 'Balap Sepeda', 1, 'Ngawi', '1996-04-12', 'Waru, Sidoarjo', 'Ngawi', '085730002103', 'beyaryo', 'passpass', 'cvXDGRXxDAw:APA91bGGtVfz5ah-T0mKsGcJapkNus4bCeYLWJOreZLDAdb8RthzEWPalNi9OUal6DjYGZhkIDrXg_ShIT0C3F6UFELRNgWPVUFPooLDSOZYMrH6VV28m-zaBaMaZG_zc74wiIleWFHu', 'beyaryo17.png', 60, 0),
(2103141041, 1023151003, 'Moh Fajar', 'Berkuda', 1, 'Lamongan', '1996-01-25', 'Turi Lamongan', 'Lamongan', '895807110011', 'mohfajar', 'passpass', '', 'mohfajar.png', 60, 0),
(2103141042, 1023151003, 'Ifan Anugrah Setiawan', 'Atletik', 1, 'Sidoarjo', '2000-09-15', 'Ds.Buduran Kec.Buduran Kab.Sidoarjo', 'Sidoarjo', '085144321167', 'ifanset1', 'ifanset1', 'fWDLBjnBbDk:APA91bG0uI79VFLmVHibQP9Xv6rnQfhDfl-1TijmK4kEdOPnVZvF_rvU4033iluIka-5E0DCmJ8HaNb-12_SL8bjMnkrgnmV8Fh8qojMjy_tzuYGstj1U9C1YhdZOP9HxZTSaPVOPuTb', 'ifanset1.png', 60, 0);

-- --------------------------------------------------------

--
-- Table structure for table `form`
--

CREATE TABLE `form` (
  `id_form` int(15) NOT NULL,
  `id_atlet` int(12) NOT NULL,
  `id_psikolog` int(12) NOT NULL,
  `waktu_input` date NOT NULL,
  `sesi_latihan` int(5) NOT NULL,
  `antusiasme_pre_latih` int(5) NOT NULL,
  `antusiasme_pos_latih` int(5) NOT NULL,
  `fisik` int(5) NOT NULL,
  `catatan_fisik` text NOT NULL,
  `stres` int(5) NOT NULL,
  `konsentrasi` int(5) NOT NULL,
  `keyakinan` int(5) NOT NULL,
  `target` int(5) NOT NULL,
  `lelah` int(5) NOT NULL,
  `komunikasi` int(5) NOT NULL,
  `intensitas` int(5) NOT NULL,
  `hidrasi` int(5) NOT NULL,
  `tidur` int(5) NOT NULL,
  `nutrisi` int(5) NOT NULL,
  `recovery` int(5) NOT NULL,
  `recovery_lain` text NOT NULL,
  `mental_skill` int(5) NOT NULL,
  `mental_skill_lain` text NOT NULL,
  `kendala_mental_skill` text NOT NULL,
  `saran_masalah` int(5) NOT NULL,
  `saran_masalah_lain` text NOT NULL,
  `hal_positif` text NOT NULL,
  `scoring_mental` double NOT NULL,
  `scoring_fisik` double NOT NULL,
  `intensitas_target` int(5) NOT NULL,
  `status` int(5) NOT NULL,
  `waktu_rm` date NOT NULL,
  `isi_rm` text,
  `status_rm` int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `form`
--

INSERT INTO `form` (`id_form`, `id_atlet`, `id_psikolog`, `waktu_input`, `sesi_latihan`, `antusiasme_pre_latih`, `antusiasme_pos_latih`, `fisik`, `catatan_fisik`, `stres`, `konsentrasi`, `keyakinan`, `target`, `lelah`, `komunikasi`, `intensitas`, `hidrasi`, `tidur`, `nutrisi`, `recovery`, `recovery_lain`, `mental_skill`, `mental_skill_lain`, `kendala_mental_skill`, `saran_masalah`, `saran_masalah_lain`, `hal_positif`, `scoring_mental`, `scoring_fisik`, `intensitas_target`, `status`, `waktu_rm`, `isi_rm`, `status_rm`) VALUES
(1, 2103141042, 1023151003, '2018-12-23', 3, 80, 90, 100, '', 70, 80, 80, 90, 80, 90, 70, 60, 60, 0, 22, '', 20, '', '', 2, '', '', 73.75, 47.058823, 60, 1, '0000-00-00', '', 0),
(2, 2103141042, 1023151003, '2018-12-24', 2, 80, 80, 100, '', 80, 100, 100, 90, 80, 90, 70, 80, 100, 20, 33, '', 40, '', '', 2, '', '', 83.75, 64.70589, 60, 1, '0000-00-00', '', 0),
(3, 2103141042, 1023151003, '2018-12-25', 1, 80, 90, 100, '', 90, 100, 100, 100, 80, 100, 90, 100, 100, 20, 56, '', 20, '', '', 2, '', '', 83.75, 73.52941, 60, 1, '0000-00-00', '', 0),
(5, 2103141042, 1023151003, '2018-12-26', 1, 80, 80, 80, '', 80, 90, 90, 90, 80, 90, 90, 60, 100, 20, 44, '', 20, '', '', 2, '', '', 78.75, 64.70589, 60, 1, '0000-00-00', '', 0),
(7, 2103141042, 1023151003, '2018-12-27', 2, 70, 90, 80, '', 80, 90, 80, 100, 90, 100, 90, 100, 80, 20, 56, '', 20, '', '', 2, '', '', 78.75, 69.117645, 60, 1, '0000-00-00', '', 0),
(9, 2103141042, 1023151003, '2018-12-28', 2, 80, 80, 100, '', 80, 90, 90, 100, 100, 90, 80, 80, 100, 0, 22, '', 40, '', '', 2, '', '', 81.25, 58.823532, 60, 0, '0000-00-00', '', 0),
(11, 2103141042, 1023151003, '2018-12-29', 1, 80, 80, 80, '', 70, 90, 90, 100, 80, 90, 70, 60, 100, 20, 22, '', 20, '', '', 1, '', '', 77.5, 58.823532, 60, 1, '0000-00-00', '', 0),
(13, 2103141042, 1023151003, '2018-12-30', 3, 70, 80, 100, '', 70, 80, 80, 90, 80, 90, 70, 60, 60, 0, 22, '', 20, '', '', 2, '', '', 73.75, 47.058823, 60, 1, '0000-00-00', '', 0),
(15, 2103141042, 1023151003, '2018-12-31', 2, 80, 90, 100, '', 70, 90, 100, 100, 80, 80, 90, 80, 80, 20, 56, '', 0, '', '', 2, '', '', 73.75, 67.64706, 60, 1, '0000-00-00', '', 0),
(17, 2103141042, 1023151003, '2019-01-01', 4, 80, 80, 80, '', 90, 90, 100, 90, 90, 80, 80, 80, 100, 0, 22, '', 20, '', '', 2, '', '', 80, 60.294117, 60, 1, '0000-00-00', '', 0),
(19, 2103141042, 1023151003, '2019-01-02', 3, 80, 90, 100, '', 80, 80, 90, 90, 80, 100, 80, 80, 100, 0, 22, '', 20, '', '', 2, '', '', 77.5, 55.88235, 60, 1, '0000-00-00', '', 0),
(21, 2103141042, 1023151003, '2019-01-03', 3, 70, 90, 80, '', 80, 100, 80, 100, 80, 90, 70, 80, 80, 20, 56, '', 40, '', '', 2, '', '', 82.5, 67.64706, 60, 1, '0000-00-00', '', 0),
(23, 2103141042, 1023151003, '2019-01-04', 1, 70, 80, 100, '', 70, 100, 80, 90, 90, 100, 80, 80, 100, 20, 56, '', 40, '', '', 1, '', '', 80, 69.117645, 60, 1, '0000-00-00', '', 0),
(25, 2103141042, 1023151003, '2019-01-05', 1, 80, 90, 100, '', 80, 100, 90, 100, 80, 90, 70, 80, 80, 20, 33, '', 20, '', '', 2, '', '', 78.75, 61.764706, 60, 1, '0000-00-00', '', 0),
(27, 2103141042, 1023151003, '2019-01-06', 3, 80, 80, 100, '', 80, 90, 80, 90, 70, 100, 70, 80, 80, 0, 33, '', 40, '', '', 2, '', '', 81.25, 55.88235, 60, 1, '0000-00-00', '', 0),
(29, 2103141042, 1023151003, '2019-01-07', 3, 80, 80, 80, '', 90, 90, 70, 90, 90, 100, 60, 60, 60, 20, 44, '', 20, '', '', 2, '', '', 77.5, 60.294117, 60, 1, '0000-00-00', '', 0),
(31, 2103141042, 1023151003, '2019-01-08', 4, 70, 70, 100, '', 80, 100, 100, 90, 90, 100, 70, 80, 80, 20, 33, '', 20, '', '', 2, '', '', 81.25, 60.294117, 60, 1, '0000-00-00', '', 0),
(33, 2103141042, 1023151003, '2019-01-09', 3, 80, 90, 80, '', 70, 80, 90, 100, 80, 100, 70, 80, 100, 20, 33, '', 20, '', '', 2, '', '', 75, 64.70589, 60, 1, '0000-00-00', '', 0),
(35, 2103141042, 1023151003, '2019-01-10', 3, 70, 90, 100, '', 90, 100, 90, 90, 80, 90, 80, 80, 100, 20, 11, '', 20, '', '', 2, '', '', 81.25, 55.88235, 60, 0, '0000-00-00', '', 0),
(37, 2103141042, 1023151003, '2019-01-11', 1, 70, 70, 100, '', 80, 90, 90, 100, 90, 90, 80, 80, 100, 20, 22, '', 40, '', '', 2, '', '', 81.25, 63.235294, 60, 0, '0000-00-00', '', 0),
(39, 2103141042, 1023151003, '2019-01-12', 1, 70, 80, 80, '', 70, 90, 90, 90, 80, 100, 80, 80, 100, 40, 0, '', 40, '', '', 2, '', '', 77.5, 58.823532, 60, 1, '0000-00-00', '', 0),
(41, 2103141042, 1023151003, '2019-01-13', 2, 70, 90, 100, '', 90, 90, 100, 100, 80, 90, 70, 100, 100, 20, 33, '', 20, '', '', 2, '', '', 80, 64.70589, 60, 1, '0000-00-00', '', 0),
(43, 2103141042, 1023151003, '2019-01-14', 1, 70, 90, 100, '', 70, 100, 90, 90, 90, 90, 80, 100, 100, 20, 44, '', 40, '', '', 2, '', '', 80, 72.05882, 60, 1, '0000-00-00', '', 0),
(45, 2103141042, 1023151003, '2019-01-15', 1, 100, 100, 60, '', 90, 80, 90, 100, 90, 100, 80, 100, 100, 0, 0, '', 20, '', '', 2, '', '', 80, 51.47059, 60, 1, '0000-00-00', '', 0),
(47, 2103141042, 1023151003, '2019-01-16', 3, 80, 90, 80, '', 70, 90, 100, 100, 80, 80, 90, 80, 80, 20, 44, '', 0, '', '', 2, '', '', 72.5, 64.70589, 60, 0, '0000-00-00', '', 0),
(4, 2103141045, 1023151003, '2018-12-26', 3, 80, 80, 80, '', 100, 90, 90, 70, 70, 80, 60, 60, 80, 40, 11, '', 40, '', '', 2, '', '', 78.75, 54.411762, 60, 1, '0000-00-00', '', 0),
(6, 2103141045, 1023151003, '2018-12-27', 3, 70, 70, 100, '', 80, 80, 90, 90, 90, 100, 80, 80, 100, 0, 22, '', 20, '', '', 2, '', '', 77.5, 60.294117, 60, 1, '0000-00-00', '', 0),
(8, 2103141045, 1023151003, '2018-12-28', 2, 70, 90, 100, '', 80, 90, 80, 90, 70, 100, 70, 80, 80, 0, 33, '', 40, '', '', 1, '', '', 81.25, 57.352943, 60, 1, '0000-00-00', '', 0),
(10, 2103141045, 1023151003, '2018-12-29', 1, 70, 90, 100, '', 90, 90, 100, 90, 90, 80, 80, 80, 100, 0, 22, '', 20, '', '', 2, '', '', 80, 57.352943, 60, 1, '0000-00-00', '', 0),
(12, 2103141045, 1023151003, '2018-12-30', 4, 80, 90, 100, '', 90, 90, 70, 90, 90, 100, 60, 60, 80, 20, 44, '', 20, '', '', 2, '', '', 76.25, 63.235294, 60, 1, '0000-00-00', '', 0),
(14, 2103141045, 1023151003, '2018-12-31', 4, 80, 90, 80, '', 90, 80, 90, 90, 90, 70, 80, 60, 80, 40, 11, '', 40, '', '', 2, '', '', 77.5, 57.352943, 60, 1, '0000-00-00', '', 0),
(16, 2103141045, 1023151003, '2019-01-01', 4, 70, 80, 100, '', 70, 90, 90, 90, 80, 100, 80, 80, 100, 40, 0, '', 20, '', '', 3, '', '', 77.5, 55.88235, 60, 1, '0000-00-00', '', 0),
(18, 2103141045, 1023151003, '2019-01-02', 3, 70, 90, 100, '', 80, 100, 90, 100, 80, 90, 70, 80, 80, 20, 33, '', 20, '', '', 2, '', '', 80, 58.823532, 60, 1, '0000-00-00', '', 0),
(20, 2103141045, 1023151003, '2019-01-03', 3, 80, 80, 80, '', 70, 80, 90, 100, 80, 100, 70, 80, 100, 20, 33, '', 20, '', '', 2, '', '', 77.5, 64.70589, 60, 1, '0000-00-00', '', 0),
(22, 2103141045, 1023151003, '2019-01-04', 2, 80, 80, 80, '', 70, 100, 80, 90, 90, 100, 80, 80, 100, 20, 56, '', 40, '', '', 1, '', '', 78.75, 69.117645, 60, 1, '0000-00-00', '', 0),
(24, 2103141045, 1023151003, '2019-01-05', 2, 80, 80, 100, '', 70, 100, 90, 90, 90, 100, 80, 100, 100, 20, 44, '', 40, '', '', 2, '', '', 81.25, 72.05882, 60, 1, '0000-00-00', '', 0),
(26, 2103141045, 1023151003, '2019-01-06', 1, 70, 80, 80, '', 80, 80, 100, 100, 80, 90, 70, 80, 80, 20, 56, '', 40, '', '', 2, '', '', 78.75, 67.64706, 60, 1, '0000-00-00', '', 0),
(28, 2103141045, 1023151003, '2019-01-07', 1, 80, 80, 100, '', 90, 100, 90, 90, 80, 90, 80, 80, 100, 20, 11, '', 20, '', '', 2, '', '', 80, 55.88235, 60, 1, '0000-00-00', '', 0),
(30, 2103141045, 1023151003, '2019-01-08', 3, 70, 70, 100, '', 80, 90, 80, 100, 90, 100, 90, 100, 80, 20, 56, '', 20, '', '', 1, '', '', 77.5, 72.05882, 60, 1, '0000-00-00', '', 0),
(32, 2103141045, 1023151003, '2019-01-09', 3, 80, 80, 80, '', 80, 100, 100, 90, 90, 100, 70, 80, 80, 20, 33, '', 20, '', '', 1, '', '', 82.5, 63.235294, 60, 1, '0000-00-00', '', 0),
(34, 2103141045, 1023151003, '2019-01-10', 1, 70, 80, 100, '', 80, 90, 90, 100, 90, 90, 80, 80, 100, 20, 22, '', 40, '', '', 2, '', '', 81.25, 60.294117, 60, 1, '0000-00-00', '', 0),
(36, 2103141045, 1023151003, '2019-01-11', 2, 80, 90, 100, '', 90, 90, 100, 100, 80, 90, 70, 100, 100, 20, 33, '', 20, '', '', 2, '', '', 82.5, 64.70589, 60, 1, '0000-00-00', '', 0),
(38, 2103141045, 1023151003, '2019-01-12', 2, 70, 90, 80, '', 80, 100, 80, 100, 80, 90, 70, 80, 80, 20, 44, '', 20, '', '', 2, '', '', 76.25, 64.70589, 60, 1, '0000-00-00', '', 0),
(40, 2103141045, 1023151003, '2019-01-13', 1, 80, 90, 80, '', 90, 80, 100, 80, 90, 100, 90, 100, 80, 20, 33, '', 20, '', '', 2, '', '', 80, 66.17647, 60, 1, '0000-00-00', '', 0),
(42, 2103141045, 1023151003, '2019-01-14', 1, 70, 70, 100, '', 90, 80, 90, 100, 90, 100, 80, 100, 100, 0, 44, '', 40, '', '', 1, '', '', 83.75, 69.117645, 60, 1, '0000-00-00', '', 0),
(44, 2103141045, 1023151003, '2019-01-15', 4, 80, 90, 100, '', 90, 90, 90, 100, 100, 100, 100, 60, 100, 20, 11, '', 20, '', '', 1, '', 'sehat', 86.25, 52.941177, 60, 0, '0000-00-00', '', 0),
(46, 2103141045, 1023151003, '2019-01-16', 4, 90, 80, 100, '', 90, 80, 90, 90, 70, 90, 100, 100, 80, 20, 22, '', 20, '', '', 2, '', '', 78.75, 57.352943, 60, 1, '0000-00-00', '', 0),
(48, 2103141042, 1023151003, '2019-01-16', 4, 70, 90, 60, ' ', 80, 100, 90, 80, 70, 90, 90, 80, 100, 20, 44, ' ', 40, ' ', ' ', 3, 'terasa nyeri pada lengan setelah tidur semalam ', ' ', 80, 60.294117, 70, 0, '2019-01-16', 'coba hubungi ke bagian kesehatan/klinik terdekat', 2);

-- --------------------------------------------------------

--
-- Table structure for table `info`
--

CREATE TABLE `info` (
  `id` int(15) NOT NULL,
  `id_psikolog` int(12) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `dari` varchar(255) NOT NULL,
  `untuk` varchar(255) NOT NULL,
  `waktu` date NOT NULL,
  `isi_info` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `info`
--

INSERT INTO `info` (`id`, `id_psikolog`, `judul`, `dari`, `untuk`, `waktu`, `isi_info`) VALUES
(75, 87654324, 'Jersey', 'Bag. Pengadaan', 'Atlet', '2019-01-13', 'Pengambilan jersey di masing-masing pelatih mulai besok pagi'),
(76, 87654324, 'Reminder Psikotes', 'Tim Psikolog', 'Semua Atlet', '2019-01-13', 'Psikotes diadakan tanggal 16 Januari 2019. Tempat di aula koni. Pukul 07.00-selesai'),
(77, 87654324, 'SALAM', 'Mental Coach', 'Semua', '2019-01-13', 'Semangat Pagiiiiii.. Kibarkan semangat merah putih di dada..'),
(79, 1023151003, 'Penilaian psikologi bulanan', 'Ardi Psikolog', 'Atlet Atletik', '2019-01-16', 'Diharapkan besok kumpul di basecamp jam 9 pagi\nTerimakasih');

-- --------------------------------------------------------

--
-- Table structure for table `psikolog`
--

CREATE TABLE `psikolog` (
  `id_psikolog` int(12) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` int(1) NOT NULL,
  `tempat_lahir` varchar(255) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `no_telefon` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token` text NOT NULL,
  `photo_profile` varchar(255) NOT NULL,
  `status_verifikasi` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `psikolog`
--

INSERT INTO `psikolog` (`id_psikolog`, `nama`, `jenis_kelamin`, `tempat_lahir`, `tanggal_lahir`, `alamat`, `no_telefon`, `user_name`, `password`, `token`, `photo_profile`, `status_verifikasi`) VALUES
(1023151004, 'Tridianto Rahmanda P', 1, 'Surabaya', '1995-05-19', 'Jl. Wisma Bungurasih I No.34 Waru Sidoarjo', '051314513300', 'tridianto', 'tridianto', '', 'tridianto.png', 0),
(1023151003, 'Rahardyanta Puruhita', 1, 'Surabaya', '1994-07-22', 'Simolangit XIII No.85 Surabaya', '085852044903', 'hardipuruhita', 'bambang', 'fMPCzLZghqo:APA91bH1_NrqZ3tNTdPs83O8LPMvyBLvpNsUCqGsJAHr1hUGcT1yiXNANC7KpzlQEat3y9a1a7w2JzkKzKwn8nMo8OtgLc0z8-Zq4KxNjo9vJPOPXS6h8d-chrc5L8c-p6jfSLVSmYMQ', 'hardipuruhita.png', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `atlet`
--
ALTER TABLE `atlet`
  ADD PRIMARY KEY (`id_atlet`),
  ADD KEY `id_psikolog` (`id_psikolog`);

--
-- Indexes for table `form`
--
ALTER TABLE `form`
  ADD PRIMARY KEY (`id_form`),
  ADD KEY `id_atlet` (`id_atlet`),
  ADD KEY `id_psikolog` (`id_psikolog`);

--
-- Indexes for table `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pengirim` (`id_psikolog`);

--
-- Indexes for table `psikolog`
--
ALTER TABLE `psikolog`
  ADD PRIMARY KEY (`id_psikolog`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `atlet`
--
ALTER TABLE `atlet`
  MODIFY `id_atlet` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2103141046;
--
-- AUTO_INCREMENT for table `form`
--
ALTER TABLE `form`
  MODIFY `id_form` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `info`
--
ALTER TABLE `info`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;
--
-- AUTO_INCREMENT for table `psikolog`
--
ALTER TABLE `psikolog`
  MODIFY `id_psikolog` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1023151005;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
