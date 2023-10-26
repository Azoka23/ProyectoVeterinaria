-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 26-10-2023 a las 17:38:04
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

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idCliente`, `dni`, `apellido`, `nombre`, `direccion`, `telefono`, `contactoN`, `contactoTel`, `estado`, `correoElectronico`) VALUES
(1, 123, 'Lopez', 'Lucas', 'calle 24 123', '234-5643', 'juana', '1234-8765', 1, 'lucas@gmail.com'),
(2, 456, 'Gonzalez', 'Lucas', 'espora 345', '234-9876', 'Sole', '567-2341', 1, 'arg@ulp.com'),
(3, 789, 'Soler', 'Camila', 'Tomer 567', '234-9876', 'Lucio', '678-9876', 1, 'cor@ulp.com'),
(4, 111, 'Garcia', 'Luciana', 'Sanchez 123', '675-8765', 'Claudia', '123-3452', 1, 'sant@ulp.com'),
(5, 222, 'Soler', 'Julian', 'Avenida 789', '234-9753', 'Luciano', '146-6438', 1, 'mis@ulp.com'),
(6, 777, 'lopes', 'juan', '234', '234', 'sert', 'axz', 1, 'bgft'),
(7, 987, 'Mendes', 'Victoria', '', '', '', '', 1, ''),
(8, 555, 'Suarez', 'Noelia', 'los alamos 234', '234-9876', 'sarte', '235-7643', 1, 'noe@ulp.com');

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

--
-- Volcado de datos para la tabla `mascotas`
--

INSERT INTO `mascotas` (`idMascota`, `alias`, `sexo`, `especie`, `raza`, `colorDePelo`, `fechaNac`, `pesoM`, `pesoA`, `idCliente`, `estado`) VALUES
(1, 'Juancho', 'MACHO', 'perro', 'callejero', 'blanco', '2019-10-25', 0, 0, 1, 1),
(2, 'Kitty', 'HEMBRA', 'gato', 'callejero', 'gris', '2020-10-12', 0, 0, 3, 1),
(3, 'Khiara', 'HEMBRA', 'perro', 'golden', 'beige', '2018-07-10', 0, 0, 5, 1),
(4, 'Lola', 'HEMBRA', 'perro', 'golden', 'blanco', '2016-10-04', 5.3, 5.3, 4, 1),
(5, 'Luna', 'HEMBRA', 'perro', 'foxt', 'beige', '2016-10-04', 0, 0, 4, 1),
(6, 'Kitty', 'HEMBRA', 'gato', '', 'blanco', '2023-10-12', 2.5, 2.5, 3, 1),
(7, 'Lola', 'HEMBRA', '', '', 'beige', '2021-10-16', 0, 0, 6, 1),
(8, 'Pluto', 'MACHO', 'perro', 'fox', 'marron', '2020-10-08', 0, 4.5, 5, 1),
(9, 'tuti', 'MACHO', '', '', 'blanco', '2021-10-07', 3.94, 0, 1, 1),
(10, 'Tomy', 'MACHO', 'gato', '', 'blanco', '2020-10-06', 0, 4.5, 1, 1),
(11, 'Loli', 'HEMBRA', '', '', 'marron', '2023-10-11', 0, 0, 1, 0),
(12, 'Pipo', 'MACHO', 'perro', 'boxer', 'blanco', '2023-10-26', 0, 2.5, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `idReserva` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idMascota` int(11) NOT NULL,
  `horario` time NOT NULL,
  `estado` tinyint(4) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`idReserva`, `idCliente`, `idMascota`, `horario`, `estado`, `fecha`) VALUES
(7, 1, 9, '11:00:00', 1, '2023-10-20'),
(8, 1, 9, '10:00:00', 1, '2023-10-21'),
(9, 1, 9, '10:00:00', 1, '2023-10-20'),
(10, 1, 9, '12:00:00', 1, '2023-10-20'),
(12, 1, 9, '12:00:00', 1, '2023-10-21'),
(13, 1, 9, '10:00:00', 1, '2023-10-22'),
(15, 1, 9, '18:00:00', 1, '2023-10-24'),
(18, 1, 9, '15:00:00', 1, '2023-10-24'),
(20, 1, 1, '17:00:00', 1, '2023-10-25'),
(21, 1, 9, '10:00:00', 1, '2023-10-26'),
(22, 1, 1, '12:00:00', 1, '2023-10-26'),
(23, 1, 9, '14:00:00', 1, '2023-10-25'),
(24, 1, 1, '17:00:00', 1, '2023-10-24'),
(26, 1, 9, '12:00:00', 1, '2023-10-27'),
(27, 1, 1, '14:00:00', 1, '2023-10-27');

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

--
-- Volcado de datos para la tabla `tratamientos`
--

INSERT INTO `tratamientos` (`idTratamiento`, `tipo`, `descripcion`, `importe`, `estado`) VALUES
(1, 'vacunacion', 'vacunas', 1500, 1),
(2, 'Baño', 'mantenimiento', 2000, 1),
(3, 'Desparasitar', 'desparacitar ', 3500, 1),
(4, 'peluqueria', 'mantenimiento', 1560.5, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamientosRealizados`
--

CREATE TABLE `tratamientosRealizados` (
  `idTratamientoRealizados` int(11) NOT NULL,
  `idVisita` int(11) NOT NULL,
  `idMascota` int(11) NOT NULL,
  `idTratamiento` int(11) NOT NULL,
  `importe` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tratamientosRealizados`
--

INSERT INTO `tratamientosRealizados` (`idTratamientoRealizados`, `idVisita`, `idMascota`, `idTratamiento`, `importe`) VALUES
(1, 1, 9, 1, 1500),
(2, 1, 9, 3, 3500),
(3, 2, 9, 2, 2000),
(4, 2, 9, 3, 3500),
(5, 3, 3, 1, 1500),
(6, 3, 3, 3, 3500),
(7, 4, 9, 1, 1500),
(8, 4, 9, 3, 3500),
(9, 5, 8, 2, 2000),
(10, 5, 8, 3, 3500),
(11, 6, 10, 1, 1500),
(12, 6, 10, 3, 3500),
(13, 7, 1, 2, 2000),
(14, 8, 9, 1, 1500),
(15, 9, 9, 1, 1500),
(16, 10, 9, 1, 1500),
(17, 10, 9, 2, 2000),
(18, 11, 12, 3, 3500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `rol` int(3) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `nombre`, `password`, `rol`, `estado`) VALUES
(1, 'ulp', 'hola', 1, 1),
(2, 'nicolas', 'hola', 0, 1),
(3, 'tuti', 'hola', 2, 1),
(5, 'marce', 'hola', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas`
--

CREATE TABLE `visitas` (
  `idVisita` int(11) NOT NULL,
  `fechaV` date NOT NULL,
  `detallesSintomas` varchar(50) NOT NULL,
  `pesoActual` double NOT NULL,
  `Importe` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `visitas`
--

INSERT INTO `visitas` (`idVisita`, `fechaV`, `detallesSintomas`, `pesoActual`, `Importe`) VALUES
(1, '2023-10-12', 'visita mensual', 3.5, 5000),
(2, '2023-10-17', 'visita control', 3.5, 5500),
(3, '2023-10-17', 'control gratuito', 4.8, 5000),
(4, '2023-10-20', 'control mensual', 3.5, 5000),
(5, '2023-10-20', 'cliente nuevo', 4.5, 5500),
(6, '2023-10-24', 'control mensual', 4.5, 5000),
(7, '2023-10-25', 'control', 0, 2000),
(8, '2023-10-25', 'control', 5, 1500),
(9, '2023-10-25', 'control', 5.5, 1500),
(10, '2023-10-25', 'control', 0, 3500),
(11, '2023-10-26', 'control', 2.5, 3500);

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
-- Indices de la tabla `consultas`
--
ALTER TABLE `consultas`
  ADD PRIMARY KEY (`idConsulta`);

--
-- Indices de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD PRIMARY KEY (`idMascota`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `reservas_ibfk_1` (`idCliente`),
  ADD KEY `idMascota` (`idMascota`);

--
-- Indices de la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  ADD PRIMARY KEY (`idTratamiento`);

--
-- Indices de la tabla `tratamientosRealizados`
--
ALTER TABLE `tratamientosRealizados`
  ADD PRIMARY KEY (`idTratamientoRealizados`),
  ADD KEY `idVisita` (`idVisita`),
  ADD KEY `idMascota` (`idMascota`),
  ADD KEY `idTratamiento` (`idTratamiento`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- Indices de la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD PRIMARY KEY (`idVisita`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `consultas`
--
ALTER TABLE `consultas`
  MODIFY `idConsulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  MODIFY `idMascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  MODIFY `idTratamiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tratamientosRealizados`
--
ALTER TABLE `tratamientosRealizados`
  MODIFY `idTratamientoRealizados` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `visitas`
--
ALTER TABLE `visitas`
  MODIFY `idVisita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD CONSTRAINT `mascotas_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`idMascota`) REFERENCES `mascotas` (`idMascota`);

--
-- Filtros para la tabla `tratamientosRealizados`
--
ALTER TABLE `tratamientosRealizados`
  ADD CONSTRAINT `tratamientosrealizados_ibfk_1` FOREIGN KEY (`idVisita`) REFERENCES `visitas` (`idVisita`),
  ADD CONSTRAINT `tratamientosrealizados_ibfk_2` FOREIGN KEY (`idMascota`) REFERENCES `mascotas` (`idMascota`),
  ADD CONSTRAINT `tratamientosrealizados_ibfk_3` FOREIGN KEY (`idTratamiento`) REFERENCES `tratamientos` (`idTratamiento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
