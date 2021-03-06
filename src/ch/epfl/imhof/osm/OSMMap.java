package ch.epfl.imhof.osm;

/**
 * Représente une carte OpenStreetMap.
 *
 * @author Ben Lalah Ali (251758)
 * @author Alami Idrissi Ali (251759)
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class OSMMap {

    private final List<OSMWay> ways;
    private final List<OSMRelation> relations;

    /**
     * Construit une carte OSM avec les chemins et les relations donnés.
     * 
     * @param ways
     *            : Chemins de la carte.
     * @param relations
     *            : Relations de la carte.
     */
    public OSMMap(Collection<OSMWay> ways, Collection<OSMRelation> relations) {
        this.ways = Collections.unmodifiableList(new ArrayList<OSMWay>(ways));
        this.relations = Collections
                .unmodifiableList(new ArrayList<OSMRelation>(relations));
    }

    /**
     * 
     * @return la liste des relations de la carte
     */
    public List<OSMRelation> relations() {
        return this.relations;
    }

    /**
     * 
     * @return La liste des chemins de la carte
     */
    public List<OSMWay> ways() {
        return this.ways;
    }

    /**
     * Construit un noeud OSM.
     *
     * @author Ben Lalah Ali (251758)
     * @author Alami Idrissi Ali (251759)
     */
    public static final class Builder {

        private List<OSMNode> nodes;
        private List<OSMWay> ways;
        private List<OSMRelation> relations;

        /**
         * Construit un nouveau batisseur de OSMMAp
         */
        public Builder() {
            nodes = new ArrayList<>();
            ways = new ArrayList<>();
            relations = new ArrayList<>();
        }

        /**
         * Ajoute le nœud donné au bâtisseur:
         * 
         * @param newNode
         *            : Noeud a ajouter.
         */
        public void addNode(OSMNode newNode) {
            nodes.add(newNode);
        }

        /**
         * 
         * @param id
         * @return Le nœud dont l'identifiant unique est égal à celui donné, ou
         *         null si ce nœud n'a pas été ajouté précédemment au bâtisseur.
         */
        public OSMNode nodeForId(long id) {
            for (OSMNode n : nodes)
                if (n.id() == id)
                    return n;
            return null;
        }

        /**
         * Ajoute le chemin donné à la carte en cours de construction.
         * 
         * @param newWay
         *            : Chemin à ajouter a la liste des chemins.
         */
        public void addWay(OSMWay newWay) {
            ways.add(newWay);
        }

        /**
         * 
         * @param id
         *            : Identifiant de l'element a retouner
         * @return Le chemin dont l'identifiant unique est égal à celui donné,
         *         ou null si ce chemin n'a pas été ajouté précédemment au
         *         bâtisseur.
         */
        public OSMWay wayForId(long id) {
            for (OSMWay n : ways)
                if (n.id() == id)
                    return n;
            return null;
        }

        /**
         * Ajoute la relation donnée à la carte en cours de construction.
         * 
         * @param newRelation
         *            : Relation a ajouter a la liste.
         */
        public void addRelation(OSMRelation newRelation) {
            relations.add(newRelation);
        }

        /**
         * 
         * @param id: Identifiant de la relation a retourner.
         * @return La relation dont l'identifiant unique est égal à celui
         *         donné, ou null si cette relation n'a pas été ajoutée
         *         précédemment au bâtisseur.
         */
        public OSMRelation relationForId(long id) {
            for (OSMRelation n : relations)
                if (n.id() == id)
                    return n;
            return null;
        }
        
        /**
         * 
         * @return  Une carte OSM avec les chemins et les relations ajoutés jusqu'à présent.
         */
        public OSMMap build(){
            return new OSMMap(ways, relations);
        }
    }
}
