package ch.epfl.imhof.projection;
/**
 * Classe de projection avec la projection Equirectangulaire
 *
 * @author Ben Lalah Ali (251758)
 * @author Alami Idrissi Ali (251759)
 */

import ch.epfl.imhof.PointGeo;
import ch.epfl.imhof.geometry.Point;

final public class EquirectangularProjection implements Projection {
    
    /**
     * Projette sur le plan le point reçu en argument.
     * @param point: point dans le systeme de coordonnées cartesien
     * @return Le point dans un systeme de coordonnées geodesique
     */
    @Override
    public Point project(PointGeo point) {
        Point p = new Point(point.longitude(), point.latitude());
        return p;
    }
    
    /**
     * Dé-projette le point du plan reçu en argument.
     * @param point: point dans le systeme de coordonnées geodesique
     * @return Le point dans un systeme de coordonnées cartesien
     */
    @Override
    public PointGeo inverse(Point point) {
       PointGeo p = new PointGeo(point.x(), point.y());
        return p;
    }

}
