<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tela de Boas-Vindas</title>
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
        
        .welcome-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .welcome-container h1 {
            font-size: 36px;
            color: #007bff;
        }

        .welcome-container p {
            font-size: 18px;
            margin-top: 10px;
        }

        .welcome-container a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1>Bem-Vindo ao Nosso Site</h1>
        <p>Agradecemos por nos visitar. Esperamos que voc� aproveite a sua estadia em nosso site.</p>
        <p>Para come�ar, <a href="login.html">fa�a login</a> ou <a href="register.html">registre-se</a> se ainda n�o tiver uma conta.</p>
    </div>
</body>
</html>
