package my.first.graph;

import java.util.*;

/**
 * Created by RASTA on 29.02.2016.
 * Class for all alg operations.
 */
class GraphProcessor {
    private static int INF = Integer.MAX_VALUE / 2;
    final ArrayList<Vertex> vertexList = new ArrayList<Vertex>();

    int start; //стартовая вершина, от которой ищется расстояние до всех других
    private int indexes = 1;
    private int name = 'A';
    private Queue<Integer> queue = new LinkedList<Integer>();
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах
    private int dist[]; //массив для хранения расстояния от стартовой вершины
    private int pred[];

    public void addVertex() {
        vertexList.add(new Vertex(indexes++, (char) name++));
    }

    public void addEdge(int indexFrom, int indexTo, int weight) {
        if (indexFrom - 1 < vertexList.size() && indexTo - 1 < vertexList.size()) {
            Vertex vertex = vertexList.get(indexFrom - 1);
            vertex.edges.put(vertexList.get(indexTo - 1), weight);
        }
    }

    public void doDfs() {
        System.out.println("DFS");
        used = new boolean[vertexList.size()];
        for (Vertex start : vertexList) {
            dfs(start.getNumber());
        }


    }

    public void doBfs() {
        System.out.println("BFS");
        used = new boolean[vertexList.size()];
        bfs(0);

    }

    private void dfs(int pos) {
       /* if (used[pos - 1]) return;
        used[pos - 1] = true;
        System.out.println(pos);
        for (Vertex next : vertexList.get(pos - 1).edges)
            if (!used[next.getNumber() - 1])
                dfs(next.getNumber());*/
    }

    private void bfs(int v) {
       /* if (used[v])
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
        }*/
    }


    public void doDejkstra() {
        initDejkstra();
        dejkstra(start);
        printData();

    }

    private void dejkstra(int s) {
        dist[s] = 0; //кратчайшее расстояние до стартовой вершины равно 0
        for (int iter = 0; iter < vertexList.size(); ++iter) {
            int v = -1;
            int distV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int i = 0; i < vertexList.size(); ++i) {
                if (used[i]) {
                    continue;
                }
                if (distV < dist[i]) {
                    continue;
                }
                v = i;
                distV = dist[i];
            }
            //рассматриваем все дуги, исходящие из найденной вершины

            for (Map.Entry<Vertex, Integer> entry : vertexList.get(v).edges.entrySet()) {
                int u = entry.getKey().getNumber() - 1;
                int weightU = entry.getValue();
                //релаксация вершины
                if (dist[v] + weightU < dist[u]) {
                    dist[u] = dist[v] + weightU;
                    pred[u] = v;
                }
            }
            //помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            used[v] = true;
        }
    }

    private void initDejkstra() {
        System.out.print("Enter number of start vertex :");
        int start = new Scanner(System.in).nextInt();
        int n = vertexList.size();
        used = new boolean[n];
        Arrays.fill(used, false);
        pred = new int[n];
        Arrays.fill(pred, -1);
        dist = new int[n];
        Arrays.fill(dist, INF);
    }

    private void printData() {
        for (int v = 0; v < vertexList.size(); ++v) {
            if (dist[v] != INF) {
                System.out.print(dist[v] + " ");
            } else {
                System.out.print("-1 ");
            }
        }
        System.out.println();
        for (int v = 0; v < vertexList.size(); ++v) {
            System.out.print((v + 1) + ": ");
            if (dist[v] != INF) {
                printWay(v);
            }
            System.out.println();
        }
    }

    private void printWay(int v) {
        if (v == -1) {
            return;
        }
        printWay(pred[v]);
        System.out.print((v + 1) + " ");

    }


}