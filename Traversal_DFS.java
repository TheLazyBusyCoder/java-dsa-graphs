// DFS with recursion 
public static void dfs(ArrayList<Edge> graph[] , boolean vis[] , int cur) {
	System.out.println(cur);
	vis[cur] = true;
	
	for(int i = 0; i < graph[cur].size(); i++) {
		Edge e = graph[cur].get(i);
		if(vis[e.dest] == false)
			dfs(graph , vis , e.dest);
	}
}

// DFS with stack
public static void dfs(ArrayList<Edge> graph[]) {
	Stack<Integer> stack = new Stack<>();
	boolean vis[] = new boolean[graph.length];
	stack.push(0);
	int cur = 0;
	while(!stack.isEmpty()) {
		if(!vis[cur]) {
			vis[cur] = true;
			System.out.print(cur + " ");
		} else {
			cur = stack.pop();
		}
		ArrayList<Edge> temp = graph[cur];
		for(Edge e: temp) {
			if(!vis[e.dest]) {
				stack.push(e.dest);
				cur = e.dest;
				break;
			}
		}
	}
}
