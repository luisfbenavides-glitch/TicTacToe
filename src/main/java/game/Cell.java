package game;
import java.io.Serializable;

public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;
    private String state = null; // "x", "o" o null

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}