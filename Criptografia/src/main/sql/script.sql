create database criptografia;
use criptografia;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `PASSWORD_criptografada` blob DEFAULT NULL,
  `password_criptografada_rsa` blob DEFAULT NULL,
  `private_key` text,
  `public_key` text	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;