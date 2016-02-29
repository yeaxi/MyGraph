import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by RASTA on 29.02.2016.
 */
public class GraphPrinter extends JFrame {
    //from jGraph library
    private mxGraph graph = new mxGraph();
    private Object parent = graph.getDefaultParent();

    //it is mine
    private int xCord = 200;
    private int yCord = 100;
    private Map<Vertex, Object> vertexes = new HashMap<Vertex, Object>();

    public GraphPrinter() {
        super("MyGraph");

        graph.getModel().beginUpdate();
        try {
            addVertexes();
            addEdges();
            fillingVertexesForPrinting();
            fillingEdgesForPrinting();
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    private GraphProcessor graphProcessor = new GraphProcessor();
    private Scanner scn = new Scanner(System.in);

    public void addVertexes() {
        System.out.print("Enter amount of vertexes: ");
        int amount = scn.nextInt();
        for (int i = 0; i < amount; i++) {
            graphProcessor.addVertex();
        }

    }

    public void addEdges() {
        for (Vertex vertex : graphProcessor.list) {
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

    private void fillingVertexesForPrinting() {
        for (Vertex vertex : graphProcessor.list) {
            vertexes.put(vertex, graph.insertVertex(parent, null, vertex.name, xCord, yCord, 30, 30));
            xCord = (int) (xCord + Math.random() * 200) - 50;
            yCord = (int) (yCord + Math.random() * 300) - 60;
        }
    }

    private void fillingEdgesForPrinting() {
        for (Vertex vertex : graphProcessor.list) {
            if (!vertex.edges.isEmpty()) {
                for (Vertex child : vertex.edges) {
                    graph.insertEdge(parent, null, "", vertexes.get(vertex), vertexes.get(child));
                }
            }
        }
    }


    public static void main(String[] args) {
        GraphPrinter frame = new GraphPrinter();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);


    }
}
