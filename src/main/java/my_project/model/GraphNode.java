package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Vertex;

public class GraphNode extends Vertex {

    private GraphNode prev;
    private double cost;
    /**
     * Ein neues Objekt vom Typ Vertex wird erstellt. Seine Markierung hat den Wert false.
     *
     * @param pID
     */
    public GraphNode(String pID) {
        super(pID);
        cost = Double.MAX_VALUE;
    }

    public GraphNode getPrev() {
        return prev;
    }

    public void setPrev(GraphNode prev) {
        this.prev = prev;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
