<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tic Tac Toe - En Juego</title>
    <style>
        .celda {
            width: 80px;
            height: 80px;
            padding: 0;
            cursor: pointer;
        }
        .celda img {
            width: 100%;
            height: 100%;
            display: block;
        }
        .ganador {
            color: #2c3e50;
            background-color: #ecf0f1;
            padding: 10px;
            border-radius: 5px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <h1>Tres en Raya</h1>

    <%-- 1. Mostrar el mensaje de ganador si existe en la sesión --%>
    <% if (session.getAttribute("ganador") != null) { %>
        <div class="ganador">
            <h2>¡Fin de la partida!</h2>
            <p>Resultado: <strong><%= session.getAttribute("ganador") %></strong></p>
        </div>
    <% } %>

    <%-- 2. Formulario principal que conecta con el Servlet --%>
    <form action="GameServlet" method="post">
        <table border="1" style="border-collapse: collapse;">
            <%
                // Recuperamos el tablero de la sesión
                String[][] tablero = (String[][]) session.getAttribute("tablero");
                
                if (tablero != null) {
                    for (int i = 0; i < 3; i++) {
            %>
                <tr>
                <% for (int j = 0; j < 3; j++) { 
                    String estado = tablero[i][j]; // Puede ser "x", "o" o "null"
                    
                    // Bloqueamos el botón si la celda ya tiene dueño o si ya hay un ganador
                    boolean desactivado = !estado.equals("null") || session.getAttribute("ganador") != null;
                %>
                    <td>
                        <button type="submit" name="posicion" value="<%=i%>,<%=j%>" 
                                class="celda" <%= desactivado ? "disabled" : "" %>>
                            <%-- Importante: La ruta de la imagen debe coincidir con tu carpeta /img --%>
                            <img src="img/state_<%=estado%>.png" alt="<%=estado%>">
                        </button>
                    </td>
                <% } %>
                </tr>
            <% 
                    }
                } else {
            %>
                <p>Error: No se encontró el tablero. Por favor, <a href="index.jsp">regresa al inicio</a>.</p>
            <% } %>
        </table>

        <br>
        
        <%-- 3. Botones de control --%>
        <button type="submit" name="reiniciar" value="true" style="padding: 10px 20px;">
            Reiniciar Partida
        </button>
    </form>

    <br>
    <a href="index.jsp">Configuración inicial (Elegir quién empieza)</a>

</body>
</html>