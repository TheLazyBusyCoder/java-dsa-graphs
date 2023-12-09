// BFS
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
