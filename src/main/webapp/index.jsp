<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Configuración de Partida</title>
</head>
<body>
    <h1>Bienvenido al 4 en Raya</h1>
    <form action="GameServlet" method="post">
    <p>¿Quién empieza la partida?</p>
    <input type="radio" name="start" value="user" checked> Usuario <br>
    <input type="radio" name="start" value="machine"> Máquina <br><br>
    <input type="submit" value="Empezar juego">
</form>
</body>
</html>