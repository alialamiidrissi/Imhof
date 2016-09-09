package ch.epfl.imhof.projection;
/**
 * Classe qui représente la « projection » CH1903 
 *
 * @author Ben Lalah Ali (251758)
 * @author Alami Idrissi Ali (251759)
 */

import ch.epfl.imhof.PointGeo;
import ch.epfl.imhof.geometry.Point;

final public class CH1903Projection implements Projection {
    
    /**
     * Projette sur le plan le point reçu en argument.
     * @param point: Point dans le syteme geodesique WGS84
     * @return Un point dans le system CH1903
     */
    @Override
    public Point project(PointGeo point) {
        double l1 = (1 / 10000.0)
                * (Math.toDegrees(point.longitude()) * 3600 - 26782.5);
        double p1 = (1 / 10000.0)
                * (Math.toDegrees(point.latitude()) * 3600 - 169028.66);
        double x = 600072.37 + 211455.93 * l1 - 10938.51 * l1 * p1 - 0.36 * l1
                * Math.pow(p1, 2) - 44.54 * Math.pow(l1, 3);
        double y = 200147.07 + 308807.95 * p1 + 3745.25 * Math.pow(l1, 2)
                + 76.63 * Math.pow(p1, 2) - 194.56 * Math.pow(l1, 2) * p1
                + 119.79 * Math.pow(p1, 3);
        Point p = new Point(x, y);
        return p;
    }
    
    /**
     * Dé-projette le point du plan reçu en argument.
     * @param point: Point dans le syteme geodesique CH1903
     * @return Un point dans le systeme WGS84
     */
    @Override
    public PointGeo inverse(Point point) {
        double x1 = (point.x() - 600000.0) / 1000000.0;
        double y1 = (point.y() - 200000.0) / 1000000.0;
        double l0 = 2.6779094 + 4.728982 * x1 + 0.791484 * x1 * y1 + 0.1306
                * x1 * Math.pow(y1, 2) - 0.0436 * Math.pow(x1, 3);
        double p0 = 16.9023892 + 3.238272 * y1 - 0.270978 * Math.pow(x1, 2)
                - 0.002528 * Math.pow(y1, 2) - 0.0447 * Math.pow(x1, 2) * y1
                - 0.0140 * Math.pow(y1, 3);
        double l = l0 * (100.0 / 36.0);
        double p1 = p0 * (100.0 / 36.0);
        PointGeo p = new PointGeo(Math.toRadians(l), Math.toRadians(p1));
        return p;
    }

}
