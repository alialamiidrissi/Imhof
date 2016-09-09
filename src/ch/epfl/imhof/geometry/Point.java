package ch.epfl.imhof.geometry;

/**
 * Construit un Point avec des coordonnées cartsiennes.
 *
 * @author Ben Lalah Ali (251758)
 * @author Alami Idrissi Ali (251759)
 */

final public class Point {
    
    private final double x, y;

    /**
     * @param x
     *            : coordonnée x du point
     * @param y
     *            : coordonnée y du point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return  La coordonée x du point
     */
    public double x() {
        return x;
    }

    /**
     * @return La coordonée y du point
     */
    public double y() {
        return y;
    }
}
