-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-05-2023 a las 20:22:49
-- Versión del servidor: 10.1.28-MariaDB
-- Versión de PHP: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `familycontrol`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agenda`
--

CREATE TABLE `agenda` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `cumpleaños` date DEFAULT NULL,
  `direccion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `agenda`
--

INSERT INTO `agenda` (`id`, `nombre`, `apellido`, `telefono`, `mail`, `cumpleaños`, `direccion`) VALUES
(3, 'Fatima', 'Osorio', '676160725', 'fatimaosorioaramos82@gmail.com', '1982-01-06', 'Av. Monasterio del escorial 43'),
(4, 'Alfonso', 'Santamaria', '678000046', 'alfonso.santamaria@gmail.com', '1982-08-25', 'Av. Monasterio del escorial 43');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id` int(100) NOT NULL,
  `saldoInicial` double DEFAULT NULL,
  `ingresoAdicional` double DEFAULT NULL,
  `gastoFijo` double DEFAULT NULL,
  `conceptoFijo` varchar(50) DEFAULT NULL,
  `gastoVariable` double DEFAULT NULL,
  `concepto` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `saldoInicial`, `ingresoAdicional`, `gastoFijo`, `conceptoFijo`, `gastoVariable`, `concepto`) VALUES
(15, 1000, 345, 576, 'asas', 123, 'sss'),
(16, 333, 12, 23, 'asda', 12, 'sad'),
(17, 8000, 234, 444, 'sdx', 34, 'qwe'),
(18, 0, 0, 0, '0', 200, 'compra'),
(19, 0, 0, 0, '', 200, 'compra'),
(20, 0, 0, 0, '', 200, 'compra'),
(21, 0, 0, 0, '', 200, 'compra'),
(22, 0, 0, 0, '', 200, 'compra'),
(23, 0, 0, 0, '', 200, 'compra'),
(24, 0, 0, 0, '', 200, 'compra'),
(25, 8000, 0, 333, 'compra', 0, '0'),
(26, 8000, 200, 500, 'compra', 230, 'compra'),
(27, 8000, 400, 500, 'compra', 23323, 'gasto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `id` int(11) NOT NULL,
  `tarea` varchar(500) NOT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `comentarios` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`id`, `tarea`, `fechaInicio`, `fechaFin`, `comentarios`) VALUES
(7, 'cita médica Alex', '2023-04-28', '2023-04-28', '20:30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `password`, `date`) VALUES
(8, 'admin', 'admin1234', '2023-04-02'),
(9, 'prueba', '12345678', '2023-04-02'),
(10, 'test', '12345678', '2023-04-02'),
(11, 'Alfonso', '46894666n', '2023-04-08'),
(12, 'fatimaosorio', 'Aalex9890', '2023-04-08');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
