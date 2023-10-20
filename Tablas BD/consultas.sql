-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-10-2023 a las 16:06:31
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clinicavete`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consultas`
--

CREATE TABLE `consultas` (
  `idConsulta` int(11) NOT NULL,
  `consulta` varchar(70) NOT NULL,
  `descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `consultas`
--

INSERT INTO `consultas` (`idConsulta`, `consulta`, `descripcion`) VALUES
(1, 'Lista de Visitas por Mascota', 'Obtener todas las visitas asociadas a una mascota específica.'),
(2, 'Lista de Visitas por Cliente', 'Obtener todas las visitas asociadas a un cliente específico.'),
(3, 'Lista de Clientes con Mascotas', 'Encontrar clientes que tengan al menos una mascota registrada.'),
(4, 'Lista de Cliente sin Mascotas', 'Encontrar clientes que no tengan mascotas registradas.'),
(5, 'Lista de Mascotas Inactivas', 'Obtener una lista de mascotas inactivas en el sistema.'),
(6, 'Lista de Mascotas Activas', 'Obtener una lista de mascotas activas en el sistema.'),
(7, 'Lista de Tratamientos Activos', 'Obtener una lista de tratamientos activos en el sistema.'),
(8, 'Lista de Tratamientos Inactivos', 'Obtener una lista de tratamientos inactivos en el sistema.'),
(9, 'Lista de Clientes Activos', 'Obtener una lista de clientes activos en el sistema.'),
(10, 'Lista de Clientes Inactivos', 'Obtener una lista de clientes inactivos en el sistema.'),
(15, 'Listar los Tratamientos Realizados por Mascota', 'Obtener todos los tratamientos realizados en una mascota específica.'),
(16, 'Listar las Mascotas que Realizaron un Tipo de Tratamiento', 'Encontrar tratamientos realizados de un tipo específico y a que Mascotas se realizaron.'),
(17, 'Listar los Tratamientos Realizados a una Mascota', 'Encontrar todos los tratamientos realizados a una Mascotas.');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `consultas`
--
ALTER TABLE `consultas`
  ADD PRIMARY KEY (`idConsulta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `consultas`
--
ALTER TABLE `consultas`
  MODIFY `idConsulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
