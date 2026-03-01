package game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pos = request.getParameter("posicion");
        
        System.out.println("DEBUG: Entrando al Servlet. Posicion recibida: " + pos);

        if (request.getParameter("reiniciar") != null || session.getAttribute("tablero") == null) {
            System.out.println("DEBUG: Inicializando tablero nuevo");
            inicializarJuego(session);
        } else if (pos != null) {
            String[][] tablero = (String[][]) session.getAttribute("tablero");
            String[] coords = pos.split(",");
            int f = Integer.parseInt(coords[0]);
            int c = Integer.parseInt(coords[1]);

            if (tablero[f][c].equals("null")) {
                tablero[f][c] = "x";
                System.out.println("DEBUG: Usuario movió a " + f + "," + c);

                // TURNO DE LA MÁQUINA
                moverMaquina(tablero);
                System.out.println("DEBUG: La máquina ya debería haber movido");
                
                session.setAttribute("tablero", tablero);
            }
        }
        response.sendRedirect("game.jsp");
    }

    private void moverMaquina(String[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].equals("null")) {
                    tablero[i][j] = "o";
                    System.out.println("DEBUG: Máquina puso 'o' en: " + i + "," + j);
                    return; 
                }
            }
        }
    }

    private void inicializarJuego(HttpSession session) {
        String[][] tablero = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) tablero[i][j] = "null";
        session.setAttribute("tablero", tablero);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}