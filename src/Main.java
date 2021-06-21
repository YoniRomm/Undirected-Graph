import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int id1 = 314649286;
        int id2 = 208295691;
        int id3 = 209424555;


        Graph.Node node1 = new Graph.Node(id1,10);
        Graph.Node node2 = new Graph.Node(id2,50);
        Graph.Node node3 = new Graph.Node(id3,37);

        Graph.Node [] array = new Graph.Node[3];
        array[0] = node1;
        array[1] = node2;
        array[2] = node3;

        Graph graph = new Graph(array);

    }
}
