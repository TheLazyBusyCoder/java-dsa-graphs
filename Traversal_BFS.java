// BFS for joint graph
public static void bfs(ArrayList<Edge> graph[]) {
	ArrayDeque<Integer> queue = new ArrayDeque<>();
	int V = graph.length;
	boolean visited[] = new boolean[V];
	queue.add(0);
	while(!queue.isEmpty()) {
		int cur = queue.removeFirst();
		if(!visited[cur]) {
			System.out.print(cur + " ");
			visited[cur] = true;
			ArrayList<Edge> temp = graph[cur];
			for(Edge e: temp) {
				queue.add(e.dest);
			}
		}
	}
}


// BFS for disjoint graph

public static void main(String[] args) {
	int V = 7;
	ArrayList<Edge> graph[] = new ArrayList[V];
	createGraph(graph);
	boolean vis[] = new boolean[V];
	for(int i = 0; i < V; i++) {
		if(!vis[i]) {
			bfs(graph , vis , i);
		}
	}
}

public static void bfs(ArrayList<Edge> graph[] , boolean vis[] , int start) {
	Queue<Integer> queue = new ArrayDeque<>();
	queue.add(start);
	while(!queue.isEmpty()) {
		int cur = queue.poll();
		if(!vis[cur]) {
			System.out.print(cur + " ");
			vis[cur] = true;
			ArrayList<Edge> temp = graph[cur];
			for(Edge e: temp) {
				queue.add(e.dest);
			}
		}
	}
}
