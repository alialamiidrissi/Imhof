package ch.epfl.imhof;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Représente un graphe non orienté.
 *
 * @author Ben Lalah Ali (251758)
 * @author Alami Idrissi Ali (251759)
 */
public final class Graph<N> {
    private final Map<N, Set<N>> neighbors;

    /**
     * Construit un graphe non orienté avec la table d'adjacence donnée.
     * 
     * @param neighbors
     *            : Table d'adjacence du grpahe.
     */
    public Graph(Map<N, Set<N>> neighbors) {
        this.neighbors = Collections.unmodifiableMap(new HashMap<N, Set<N>>(
                neighbors));
    }

    /**
     * 
     * @return L'ensemble des nœuds du graphe.
     */
    public Set<N> nodes() {
        return this.neighbors.keySet();
    }

    /**
     * 
     * @param node
     *            : Noeud dont on veut le voisin.
     * @exception IllegalArgumentException
     *                :
     * @return L'ensemble des nœuds voisins du nœud donné.
     */
    public Set<N> neighborsOf(N node) {
        return neighbors.get(node);
    }

    /**
     * Batisseur d'un Graph.
     *
     * @author Ben Lalah Ali (251758)
     * @author Alami Idrissi Ali (251759)
     */
    public final static class Builder<N> {
        private Map<N, Set<N>> neighbors;

        public Builder() {
            this.neighbors = new HashMap<N, Set<N>>();
        }

        /**
         * Ajoute le nœud donné au graphe en cours de construction, s'il n'en
         * faisait pas déjà partie.
         * 
         * @param n
         *            : Noeud a ajouter.
         */
        public void addNode(N n) {
            neighbors.put(n, null);
        }

        /**
         * Ajoute une arête entre les deux nœuds donnés au graphe en cours de
         * construction.
         */
        public void addEdge(N n1, N n2) {

        }
    }
}
