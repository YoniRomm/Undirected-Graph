import java.util.*;

public class Main {

    public static void main(String[] args) {
        //myTester();
        measurements();





    }
    public static void myTester(){
        int id1 = 314649286;
        int id2 = 208295691;
        int id3 = 209424555;


        Graph.Node node1 = new Graph.Node(id1,100);
        Graph.Node node2 = new Graph.Node(id2,200);
        Graph.Node node3 = new Graph.Node(id3,300);
        Graph.Node node4 = new Graph.Node(123,400);
        Graph.Node node5 = new Graph.Node(126,500);


        Graph.Node [] array = new Graph.Node[5];
        array[0] = node1;
        array[1] = node2;
        array[2] = node3;
        array[3] = node4;
        array[4] = node5;

        Graph graph = new Graph(array);
        graph.addEdge(node1.getId(),node2.getId());
        graph.addEdge(node1.getId(), node3.getId());
        graph.addEdge(node2.getId(), node3.getId());
        graph.addEdge(123,126);


        System.out.format("getNeighborhoodWeight is %s \n", graph.getNeighborhoodWeight(id2));

        System.out.format("num Edges is %s \n", graph.getNumEdges());
        System.out.format("num Nodes is %s \n", graph.getNumNodes());

        System.out.format("max in max Heap is %s \n", graph.maxHeap.Max().getKey());

    }
    public static void measurements(){
        int max_rank = 0;
        for (int i = 6; i < 22; i++){
            Graph.Node [] array_of_nodes = new Graph.Node [(int)Math.pow(2,i)];
            for (int j = 0; j < (int)Math.pow(2,i); j++){
                array_of_nodes[j] = new Graph.Node(j,1);
            }
            Graph m_graph = new Graph(array_of_nodes);
            Random random = new Random();
            Map<Integer,Set<Integer>> map = new HashMap<>();
            while (m_graph.getNumEdges() < i){
                int n1  = random.nextInt((int)Math.pow(2,i));
                int n2  = random.nextInt((int)Math.pow(2,i));
                if (map.containsKey(n1) && map.containsKey(n2)){
                    if (n1 != n2 && !map.get(n1).contains(n2) && !map.get(n2).contains(n1)){
                        map.get(n1).add(n2);
                        map.get(n2).add(n1);
                        m_graph.addEdge(n1,n2);
                    }
                }
                else {
                    map.put(n1, new HashSet<>(n2));
                    map.put(n2, new HashSet<>(n1));
                    m_graph.addEdge(n1,n2);
                }
            }
            System.out.println("Maximum rank in graph number " + i +" is " + max_rank);
        }
    }
}
