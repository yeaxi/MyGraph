package my.first.graph;

import java.util.ArrayList;

/**
 * Created by RASTA on 29.02.2016.
 * Class for all alg operations.
 */
class GraphProcessor {
    final ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
    private int indexes = 1;
    private int name = 'A';

    public void addVertex() {
        vertexList.add(new Vertex(indexes++, (char) name++));
    }


    public void addEdge(int indexFrom, int indexTo) {
        if (indexFrom - 1 < vertexList.size() && indexTo - 1 < vertexList.size()) {
            vertexList.get(indexFrom - 1).edges.add(vertexList.get(indexTo - 1));
        }
    }


    private boolean used[];

    public void doDfs() {
        used = new boolean[vertexList.size()];
        for (Vertex start : vertexList) {
            dfs(start.getNumber());
        }


    }

    private void dfs(int pos) {
        if (used[pos - 1]) return;
        used[pos - 1] = true;
        System.out.println(pos);
        for (Vertex next : vertexList.get(pos - 1).edges)
            if (!used[next.getNumber() - 1])
                dfs(next.getNumber());
    }
}