package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.*;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     *
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController) {
        this.viewController = viewController;
        Graph g = new Graph();
        g.addVertex(new Vertex("A"));
        g.addVertex(new Vertex("B"));
        g.addVertex(new Vertex("C"));
        g.addVertex(new Vertex("D"));
        g.addVertex(new Vertex("E"));
        g.addVertex(new Vertex("F"));
        g.addVertex(new Vertex("G"));
        g.addVertex(new Vertex("H"));
        g.addVertex(new Vertex("I"));
        g.addVertex(new Vertex("J"));
        g.addVertex(new Vertex("K"));
        g.addVertex(new Vertex("L"));
        g.addVertex(new Vertex("M"));
        g.addVertex(new Vertex("N"));
        g.addVertex(new Vertex("O"));

        g.addEdge(new Edge(g.getVertex("A"), g.getVertex("B"), 10));
        g.addEdge(new Edge(g.getVertex("E"), g.getVertex("D"), 5));
        g.addEdge(new Edge(g.getVertex("G"), g.getVertex("A"), 16));
        g.addEdge(new Edge(g.getVertex("N"), g.getVertex("B"), 7));
        g.addEdge(new Edge(g.getVertex("A"), g.getVertex("M"), 2));
        g.addEdge(new Edge(g.getVertex("M"), g.getVertex("N"), 3));
        g.addEdge(new Edge(g.getVertex("A"), g.getVertex("F"), 8));
        g.addEdge(new Edge(g.getVertex("I"), g.getVertex("F"), 9));
        g.addEdge(new Edge(g.getVertex("M"), g.getVertex("L"), 4));
        g.addEdge(new Edge(g.getVertex("G"), g.getVertex("H"), 2));
        g.addEdge(new Edge(g.getVertex("A"), g.getVertex("E"), 4));
        g.addEdge(new Edge(g.getVertex("F"), g.getVertex("G"), 5));
        g.addEdge(new Edge(g.getVertex("I"), g.getVertex("H"), 6));
        g.addEdge(new Edge(g.getVertex("I"), g.getVertex("D"), 12));
        g.addEdge(new Edge(g.getVertex("L"), g.getVertex("D"), 13));

        dijkstra(g, "N", "O");
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
        // Erstelle ein Objekt der Klasse Ball und lasse es zeichnen
    }

    /**
     * Aufruf mit jeder Frame
     *
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt) {

    }


    public void dijkstra(Graph g, String target, String start) {
        List<Vertex> allVerticles = g.getVertices();
        allVerticles.toFirst();
        while(allVerticles.hasAccess()){
            Vertex help = allVerticles.getContent();
            help.setCost(Double.MAX_VALUE);
            help.setPrev(null);
            allVerticles.next();
        }

        List<Vertex> gList = new List<>();
        boolean found = false;
        if (start != null && target != null) {
            Vertex current = g.getVertex(start);
            current.setCost(0);
            gList.append(current);
            while (!found && !gList.isEmpty()) {
                gList.toFirst();
                current = gList.getContent();
                gList.next();
                while (gList.hasAccess()) { //Find smallest
                    if (current.getCost() > gList.getContent().getCost()) {
                        current = gList.getContent();
                    }
                    gList.next();
                }
                if (current == g.getVertex(target)) {
                    found = true;
                } else {
                    gList.toFirst();
                    while (gList.getContent() != current) { //Get current out of the List
                        gList.next();
                    }
                    gList.remove();
                    List<Vertex> neighbours = g.getNeighbours(current);
                    neighbours.toFirst();
                    while (!neighbours.isEmpty()) { //Neigbour stuff
                        Vertex n = neighbours.getContent();
                        neighbours.remove();
                        if (n.getCost() == Double.MAX_VALUE) { //Add to pList if they are new
                            gList.append(n);
                        }
                        double newWeight = g.getEdge(current, n).getWeight() + current.getCost();
                        if (newWeight < n.getCost()) { //Update prev and cost if needed
                            n.setPrev(current);
                            n.setCost(newWeight);
                        }
                    }
                }
            }
            if(found) {
                Vertex prev = g.getVertex(target);
                String path = "Path: ";
                double cost = 0;
                while (prev != null) {
                    cost += prev.getCost();
                    path += prev.getID() + " <-- ";
                    prev = prev.getPrev();
                }
                path += "Start";
                System.out.println(path);
                System.out.println("Cost: " + cost);
            }else{
                System.out.println("No Path found");
            }
            }
        }
    }


