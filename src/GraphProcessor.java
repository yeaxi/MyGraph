import java.util.ArrayList;

/**
 * Created by RASTA on 29.02.2016.
 */
public class GraphProcessor {
    ArrayList<Vertex> list = new ArrayList<Vertex>();
    private int indexes = 1;
    private int name = 'A';

    public void addVertex() {
        list.add(new Vertex(indexes++, (char) name++));
    }


    public void addEdge(int indexFrom, int indexTo) {
        if (indexFrom - 1 < list.size() && indexTo - 1 < list.size()) {
            list.get(indexFrom - 1).edges.add(list.get(indexTo - 1));
        }
    }


}