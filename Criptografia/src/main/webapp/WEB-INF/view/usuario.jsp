<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tela de Registro</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        
        .register-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            width: 300px;
            text-align: center;
        }

        .register-container h2 {
            font-size: 24px;
            color: #007bff;
        }

        .register-container form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .register-container input[type="text"],
        .register-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .register-container input[type="submit"] {
            width: 100%;
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px;
            border-radius: 3px;
            cursor: pointer;
        }

        .register-container input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .register-container p {
            margin-top: 10px;
        }

        .register-container a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Registro</h2>
        <form action="<%=request.getContextPath()%>/UserServlet" method="post">
            <input type="text" placeholder="Nome Completo" name="nome">
            <input type="text" placeholder="Endereço de E-mail" name="email">
            <input type="password" placeholder="Senha" name="senha">
            <input type="password" placeholder="Confirme a Senha" name="confirma_senha">
            <input type="submit" value="Registrar">
        </form>
        <p>Já tem uma conta? <a href="login.html">Faça login</a></p>
    </div>
</body>
</html>
