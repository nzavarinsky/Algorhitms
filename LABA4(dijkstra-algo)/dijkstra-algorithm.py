from collections import defaultdict, deque


class Graph(object):
    # makes the default value for all vertices an empty list
    def __init__(self):
        self.nodes = set()
        self.edges = defaultdict(list)
        self.distances = {}

    def add_node(self, value):
        self.nodes.add(value)

    def add_edge(self, from_node, to_node, distance):
        self.edges[from_node].append(to_node)
        self.edges[to_node].append(from_node)
        self.distances[(from_node, to_node)] = distance


def dijkstra(graph, initial):
    # initializations
    visited = {initial: 0}
    path = {}

    nodes = set(graph.nodes)

    while nodes:
        #while min node is none
        min_node = None
        #go through nodes in nodes set
        for node in nodes:
            if node in visited:
                if min_node is None:
                    #set node that is visiterd first as minimal
                    min_node = node
                    #if visited node is < than visited min node set it as min node
                elif visited[node] < visited[min_node]:
                    min_node = node
        #if min node is not found break
        if min_node is None:
            break

        nodes.remove(min_node)
        #set curreng weight of node as min
        current_weight = visited[min_node]

        for edge in graph.edges[min_node]:
            try:
                # node weigth + vertex to next node
                weight = current_weight + graph.distances[(min_node, edge)]
            except:
                continue
                # if its not visited set them weight an min node
            if edge not in visited or weight < visited[edge]:
                visited[edge] = weight
                path[edge] = min_node

    return visited, path


def shortest_path(graph, origin, destination):
    #set all path tha was found
    visited, paths = dijkstra(graph, origin)
    full_path = deque()
    _destination = paths[destination]

    #while end point is not start point append destination to path
    while _destination != origin:
        full_path.appendleft(_destination)
        _destination = paths[_destination]

    full_path.appendleft(origin)
    full_path.append(destination)

    #return shortest way
    return visited[destination], list(full_path)

if __name__ == '__main__':

    graph = Graph()
    with open('input.txt') as read_file:
        n = int(read_file.readline())
        for i in range(n):
            node_list = read_file.readline().split()
            graph.add_node(node_list[0])
            graph.add_node(node_list[1])
            graph.add_edge(node_list[0], node_list[1], int(node_list[2]))
        points = read_file.readline().split()
        start_point = points[0]
        end_point = points[1]

        print( shortest_path(graph, start_point, end_point))
