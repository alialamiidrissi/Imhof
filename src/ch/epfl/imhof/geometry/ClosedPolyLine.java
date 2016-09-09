package ch.epfl.imhof.geometry;

/**
 * Construit un PolyLine fermé.
 *
 * @author Ben Lalah Ali (251758)
 * @author Alami Idrissi Ali (251759)
 */

import java.util.List;

public final class ClosedPolyLine extends PolyLine {

    /**
     * Construit un PolyLine fermé
     * 
     * @param points
     *            : Liste des points sommets
     * @throws IllegalArgumentException
     *             : si la liste des points est vide
     */
    public ClosedPolyLine(List<Point> points){
        super(points);
    }

    /**
     * @return Vrai car le PolyLine est fermé
     */
    @Override
    public boolean isClosed() {
        return true;
    }

    /**
     * @return L'aire positive du PolyLine fermé
     */
    public double area() {
        List<Point> points = super.points();
        double aire = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            Point pp = points.get(Math.floorMod(i + 1, points.size()));
            Point pm = points.get(Math.floorMod(i - 1, points.size()));
            aire += p.x() * (pp.y() - pm.y());
        }
        return Math.abs(aire / 2);
    }

    /**
     * Verifie si le PolyLine contient le point rentré en paramètre;
     * 
     * @param p
     *            : point en question
     * @return Si le PolyLine contient ou pas le point rentré en paramètre
     */
    public boolean containsPoint(Point p) {
        List<Point> points = super.points();
        int indice = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % points.size());
            if (p1.y() <= p.y()) {
                if (p2.y() > p.y() && isLeftSide(p1, p2, p))
                    indice++;
            } else {
                if (p2.y() <= p.y() && isLeftSide(p2, p1, p))
                    indice--;
            }
        }
        return (indice != 0);
    }

    private boolean isLeftSide(Point p1, Point p2, Point p) {
        return (p1.x() - p.x()) * (p2.y() - p.y()) > (p2.x() - p.x())
                * (p1.y() - p.y());
    }

}
