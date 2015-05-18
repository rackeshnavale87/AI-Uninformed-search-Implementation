# AI-Uninformed-search-Implementation
[Project] Uninformed Search using Greedy, Alpha-Beta and DFS-BFS algorithm
<pre>
# Input:
You are provided with a file input.txt that describes the network.
bfs = 1, dfs = 2, ucs = 3
source- the name of the first node, such as Andy
destination- the name of the target node, such as Alice
nodes- the total number of nodes
graph
The few lines in <nodes> will look something like this: Andy
Bill
Alice
This is a list of names representing the nodes of the graph.
The lines in <graph> of the file contains the matrix representation of the graph edges. It will look something like this:
0  5  10
5  0   2
10  2  0
The rows and columns correspond to the nodes in the same order. For example, the first row tells us that Andy is not connected to himself (0), connected to Bill with a value 5, and connected to Alice with a value 10. In this assignment, we will assume that all relationships are mutual, so the matrix is symmetric. A weight of 0 indicates that there is no edge between such two people, and any other value describes the distance of their relationship.
# Output:
The program should output in the format:
<Expansion> <Output> <PathCost>
*For example,
Stacy-Emma-Helen-Frank-Jennifer-John-Jenny-Gerald-Claire-Patrick Stacy-Emma-Frank-Gerald-Patrick
130
