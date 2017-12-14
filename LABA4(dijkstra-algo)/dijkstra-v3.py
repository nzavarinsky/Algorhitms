import collections
import math
from collections import deque


class Graph:
    def __init__(self):
        self.vertices = set()
        # makes the default value for all vertices an empty list
        self.edges = collections.defaultdict(list)
        self.weights = {}

    def add_vertex(self, value):
        self.vertices.add(value)

    def add_edge(self, from_vertex, to_vertex, distance):
        if from_vertex == to_vertex: pass
        self.edges[from_vertex].append(to_vertex)
        self.weights[(from_vertex, to_vertex)] = distance

    def __str__(self):
        string = "Vertices: " + str(self.vertices) + "\n"
        string += "Edges: " + str(self.edges) + "\n"
        string += "Weights: " + str(self.weights)
        return string


def dijkstra(graph, start):
    # initializations
    S = set()

    # lenght_of_shortest_path represents the length shortest distance paths from start to edge, for edge in lenght_of_shortest_path.
    #initialize every vertex with path of infinity
    lenght_of_shortest_path = dict.fromkeys(list(graph.vertices), math.inf)
    previous = dict.fromkeys(list(graph.vertices), None)

    # then we set the path length of the start vertex to 0
    lenght_of_shortest_path[start] = 0

    # while there exists a vertex v not in S
    while S != graph.vertices:
        # let v be the closest vertex that has not been visited,it will be 'start'
        v = min((set(lenght_of_shortest_path.keys()) - S), key=lenght_of_shortest_path.get)

        # for each neighbor of v not in S
        for neighbor in set(graph.edges[v]) - S:
            new_path = lenght_of_shortest_path[v] + graph.weights[v, neighbor]

            # check new path length with current
            if new_path < lenght_of_shortest_path[neighbor]:
                # since it's optimal, update the shortest path for neighbor
                lenght_of_shortest_path[neighbor] = new_path

                # set the previous vertex of neighbor to v
                previous[neighbor] = v

        S.add(v)

    return lenght_of_shortest_path, previous


def shortest_path(graph, start, end):
    visited, paths = dijkstra(graph, start)
    full_path = deque()
    _destination = paths[end]

    #while end point is not start point append destination to path
    while _destination != start:
        full_path.appendleft(_destination)
        _destination = paths[_destination]

    full_path.appendleft(start)
    full_path.append(end)

    #return shortest way
    return visited[end], list(full_path)



if __name__ == '__main__':

    graph = Graph()
    with open('input.txt') as read_file:
        n = int(read_file.readline())
        for i in range(n):
            node_list = read_file.readline().split()
            graph.add_vertex(node_list[0])
            graph.add_vertex(node_list[1])
            graph.add_edge(node_list[0], node_list[1], int(node_list[2]))
        points = read_file.readline().split()
        start_point = points[0]
        end_point = points[1]
        print( shortest_path(graph, start_point, end_point))










