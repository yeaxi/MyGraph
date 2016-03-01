package my.first.graph;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by RASTA on 29.02.2016.
 * Class for visual part.
 */
public class GraphPrinter extends JFrame {
    private final mxGraph mxGraph = new mxGraph();
    private final Object parent = mxGraph.getDefaultParent();

    private int startXCord = 200;
    private int startYCord = 100;
    private Map<Vertex, Object> vertexes = new HashMap<Vertex, Object>();
    private GraphProcessor graphProcessor = new GraphProcessor();
    private Scanner scn = new Scanner(System.in);

    private void fillingVertexesForPrinting() {
        for (Vertex vertex : graphProcessor.vertexList) {
            vertexes.put(vertex, mxGraph.insertVertex(parent, null, vertex.getName() + " " + vertex.getNumber(), startXCord, startYCord, 30, 30));
            startXCord = (int) (startXCord + Math.random() * 200) - 50;
            startYCord = (int) (startYCord + Math.random() * 300) - 60;
        }
    }

    private void fillingEdgesForPrinting() {
        for (Vertex vertex : graphProcessor.vertexList) {
            if (!vertex.edges.isEmpty()) {
                for (Vertex child : vertex.edges) {
                    mxGraph.insertEdge(parent, null, "", vertexes.get(vertex), vertexes.get(child));
                }
            }
        }
    }

    public GraphPrinter() {
        super("MyGraph");

        mxGraph.getModel().beginUpdate();
        try {
            addVertexes();
            addEdges();
            fillingVertexesForPrinting();
            fillingEdgesForPrinting();
        } finally {
            mxGraph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        getContentPane().add(graphComponent);
    }

    public void addVertexes() {
        System.out.print("Enter amount of vertexes: ");
        int amount = scn.nextInt();
        for (int i = 0; i < amount; i++) {
            graphProcessor.addVertex();
        }

    }

    public void addEdges() {
        for (Vertex vertex : graphProcessor.vertexList) {
            System.out.println(vertex);
        }
        while (true) {
            System.out.println("Enter index of start vertex & end vertex or write '-1' to exit: ");
            int first = scn.nextInt();
            if (first == -1) break;
            int second = scn.nextInt();
            graphProcessor.addEdge(first, second);
        }
    }


    public static void main(String[] args) {
        GraphPrinter frame = new GraphPrinter();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.graphProcessor.doDfs();

    }
}
