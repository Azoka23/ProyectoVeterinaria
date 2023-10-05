-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 03-10-2023 a las 15:41:07
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
-- Base de datos: `clinicaVete`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `idCliente` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `contactoN` varchar(100) NOT NULL,
  `contactoTel` varchar(50) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `correoElectronico` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascotas`
--

CREATE TABLE `mascotas` (
  `idMascota` int(11) NOT NULL,
  `alias` varchar(50) NOT NULL,
  `sexo` varchar(50) NOT NULL,
  `especie` varchar(100) NOT NULL,
  `raza` varchar(50) NOT NULL,
  `colorDePelo` varchar(50) NOT NULL,
  `fechaNac` date NOT NULL,
  `pesoM` double NOT NULL,
  `pesoA` double NOT NULL,
  `idCliente` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamientos`
--

CREATE TABLE `tratamientos` (
  `idTratamiento` int(11) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `importe` double NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas`
--

CREATE TABLE `visitas` (
  `idVisita` int(11) NOT NULL,
  `idMascota` int(11) NOT NULL,
  `idTratamiento` int(11) NOT NULL,
  `fechaV` date NOT NULL,
  `detallesSintomas` varchar(50) NOT NULL,
  `pesoActual` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD PRIMARY KEY (`idMascota`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Indices de la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  ADD PRIMARY KEY (`idTratamiento`);

--
-- Indices de la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD PRIMARY KEY (`idVisita`),
  ADD KEY `idMascota` (`idMascota`),
  ADD KEY `idTratamiento` (`idTratamiento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  MODIFY `idMascota` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  MODIFY `idTratamiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `visitas`
--
ALTER TABLE `visitas`
  MODIFY `idVisita` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD CONSTRAINT `mascotas_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`);

--
-- Filtros para la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD CONSTRAINT `visitas_ibfk_2` FOREIGN KEY (`idMascota`) REFERENCES `mascotas` (`idMascota`),
  ADD CONSTRAINT `visitas_ibfk_3` FOREIGN KEY (`idTratamiento`) REFERENCES `tratamientos` (`idTratamiento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
