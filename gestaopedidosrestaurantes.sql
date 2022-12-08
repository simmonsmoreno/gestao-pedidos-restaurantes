-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 08-Dez-2022 às 11:56
-- Versão do servidor: 10.4.27-MariaDB
-- versão do PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `gestaopedidosrestaurantes`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

CREATE TABLE `funcionarios` (
  `idFuncionarios` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `dataNasc` varchar(30) DEFAULT NULL,
  `sexo` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefone` int(11) DEFAULT NULL,
  `morada` varchar(45) DEFAULT NULL,
  `dataEntrada` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `numeroPedido` int(11) NOT NULL,
  `numeroMesa` int(11) DEFAULT NULL,
  `preçoTotal` int(11) DEFAULT NULL,
  `data` varchar(45) DEFAULT NULL,
  `pronto` tinyint(4) DEFAULT NULL,
  `entregue` tinyint(4) DEFAULT NULL,
  `idGarcom` int(11) DEFAULT NULL,
  `idCozinha` int(11) DEFAULT NULL,
  `Funcionarios_idFuncionarios` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pratos`
--

CREATE TABLE `pratos` (
  `idPrato` int(11) NOT NULL,
  `descrição` varchar(100) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `preço` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `numeroPedido` int(11) NOT NULL,
  `idPrato` int(11) NOT NULL,
  `qtdade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`idFuncionarios`);

--
-- Índices para tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`numeroPedido`,`Funcionarios_idFuncionarios`),
  ADD KEY `fk_Pedidos_Funcionarios1_idx` (`Funcionarios_idFuncionarios`);

--
-- Índices para tabela `pratos`
--
ALTER TABLE `pratos`
  ADD PRIMARY KEY (`idPrato`);

--
-- Índices para tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`numeroPedido`,`idPrato`),
  ADD KEY `fk_Pedidos_has_Pratos_Pratos1_idx` (`idPrato`),
  ADD KEY `fk_Pedidos_has_Pratos_Pedidos1_idx` (`numeroPedido`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `idFuncionarios` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `numeroPedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pratos`
--
ALTER TABLE `pratos`
  MODIFY `idPrato` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_Pedidos_Funcionarios1` FOREIGN KEY (`Funcionarios_idFuncionarios`) REFERENCES `funcionarios` (`idFuncionarios`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `produtos`
--
ALTER TABLE `produtos`
  ADD CONSTRAINT `fk_Pedidos_has_Pratos_Pedidos1` FOREIGN KEY (`numeroPedido`) REFERENCES `pedidos` (`numeroPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Pedidos_has_Pratos_Pratos1` FOREIGN KEY (`idPrato`) REFERENCES `pratos` (`idPrato`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
