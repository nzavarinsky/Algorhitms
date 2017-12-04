from collections import defaultdict
import operator

#graph class
class Graph:
    def __init__(self):
        self.nodes = set()
        self.edges = defaultdict(list)
        self.distances = {}

    #add node to graph
    def add_node(self, value):
        self.nodes.add(value)

    #add edge to graph
    def add_edge(self, from_node, to_node, distance):
        self.edges[from_node].append(to_node)
        self.distances[(from_node, to_node)] = distance

def find_max_path (graph, initial, start_weight):
    visited = {initial: start_weight} #list of visited nodes

    path = {} #max path from one nde to another
    nodes = set(graph.nodes)

    #find max path from one node to another
    while nodes:
        max_node = None
        for node in nodes:
            if node in visited:
                if max_node is None:
                    max_node = node
                elif visited[node]>visited[max_node]:
                    max_node = node

        if max_node is None:
            break

        nodes.remove(max_node) #remove node from list
        current_weight = visited[max_node] #current weight in node

        #find weight of node's edges
        for edge in graph.edges[max_node]:
            weight = current_weight + graph.distances[(max_node, edge)]
            if edge not in visited or weight>visited[edge]:
                visited[edge] = weight
                path[edge] = max_node

    return visited


if __name__ == '__main__':
    graph = Graph()
    #index of each node
    index = 0
    index_2 = 0

    #open file to read
    with open('career.txt') as read_file:
        n = int(read_file.readline()) #read count
        node_list = read_file.readline().split() #list of value on each stage
        start_weight = int(node_list[0])
        #set two edge to node
        for i in range(n):
            node_list_1 = read_file.readline().split() #list of value on stage+1
            index_2+=1
            for j in range(len(node_list_1) - 1):
                index += 1
                graph.add_node(index)
                #add two neighboor edge to node
                for k in range(j, j + 2):
                    index_2 += 1
                    graph.add_edge(index, index_2, int(node_list_1[k]))
                    if (k==j+1): #if k = last element in node_list_1
                        index_2-=1
            node_list = node_list_1 #values on stage = values on stage+1

    #find the max path
    result = find_max_path(graph, 1, start_weight)

    #print max value in result
    print (max(result.iteritems(), key=operator.itemgetter(1))[1])









