package my.first.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        System.out.println("DFS");
        used = new boolean[vertexList.size()];
        for (Vertex start : vertexList) {
            dfs(start.getNumber());
        }


    }

    private Queue<Integer> queue = new LinkedList<Integer>();

    public void doBfs() {
        System.out.println("BFS");
        used = new boolean[vertexList.size()];
        bfs(0);

    }

    private void dfs(int pos) {
        if (used[pos - 1]) return;
        used[pos - 1] = true;
        System.out.println(pos);
        for (Vertex next : vertexList.get(pos - 1).edges)
            if (!used[next.getNumber() - 1])
                dfs(next.getNumber());
    }

    private void bfs(int v) {
        if (used[v])
            return;
        queue.add(v);
        used[v] = true;
        while (!queue.isEmpty()) {
            v = queue.poll();
            System.out.println(v + 1);
            for (Vertex vertex : vertexList.get(v).edges) {
                int w = vertex.getNumber() - 1;
                if (used[w])
                    continue;
                queue.add(w);
                used[w] = true;

            }
        }
    }


}