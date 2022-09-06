package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.*;
import my_project.model.Ball;
import my_project.model.GraphNode;
import my_project.model.Server;
import my_project.view.InputManager;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
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

        djikstra(g,"A","L");
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
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){

    }



    public void djikstra(Graph g, String target, String start){
        List<Vertex> gList = new List<>();
        boolean found = false;
        if(start != null && target != null){
            Vertex current = g.getVertex(start);
            current.setCost(0);
            List<Vertex> neighbours;
            while(!found){
                neighbours = g.getNeighbours(current);
                neighbours.toFirst();
                while(!neighbours.isEmpty()){
                    Vertex n = neighbours.getContent();
                    neighbours.remove();
                    double newWeight = g.getEdge(current,n).getWeight() + current.getCost();
                    if(newWeight < current.getCost()){
                        n.setPrev(current);
                        n.setCost(newWeight);
                    }
                    boolean exists = false;
                    gList.toFirst();
                    while(gList.hasAccess()){
                        if(gList.getContent() == n){
                            exists = true;
                        }
                        gList.next();
                    }
                    if(!exists){
                        gList.append(n);
                    }
                }
                gList.toFirst();
                current = gList.getContent();
                gList.next();
                while(gList.hasAccess()){
                    if(current.getCost() > gList.getContent().getCost()){
                        current = gList.getContent();
                    }
                    gList.next();
                }
                gList.toFirst();
                while(gList.getContent() != current){
                    gList.next();
                }
                gList.remove();
                if (current == g.getVertex(target)) {
                    found = true;
                }
                }
            Vertex prev = g.getVertex(target);
            String path = "Path: ";
            while(prev != null){
                path += prev.getID();
                prev = prev.getPrev();
            }
            System.out.println(path);
            }
        }
    }

