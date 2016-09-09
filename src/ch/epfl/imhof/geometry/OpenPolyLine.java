package ch.epfl.imhof.geometry;
/**
 * Construit un PolyLine ouvert.
 *
 * @author Ben Lalah Ali (251758)
 * @author Alami Idrissi Ali (251759)
 */
import java.util.List;

public final class OpenPolyLine extends PolyLine {
    
    /**
     * Construit un PolyLine fermé
     * 
     * @param points
     *            : Liste des points sommets
     * @throws IllegalArgumentException
     *             : si la liste des points est vide
     */
    public OpenPolyLine(List<Point> points){
        super(points);
    }
    
    /**
     * @return Faux car le PolyLine est ouvert
     */
    @Override
    public boolean isClosed() {
        return false;
    }

}
