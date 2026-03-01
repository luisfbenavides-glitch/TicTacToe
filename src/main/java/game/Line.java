package game;
import java.util.ArrayList;
public class Line {
    private ArrayList<Cell> cells = new ArrayList<>();
    public void addCell(Cell c) { cells.add(c); }
    public ArrayList<Cell> getCells() { return cells; }
}