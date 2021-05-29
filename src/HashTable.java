import java.util.*;

public class HashTable {
    public static void main(String[] args){
        HashTable h1 = new HashTable(5);
        h1.Insert(new Graph.Node(208295,5));
        h1.Insert(new Graph.Node(2128295,5));
        Graph.Node n1 =  new Graph.Node(25295,6);
        h1.Insert(n1);
        h1.Delete(new Graph.Node(208295,5));
        h1.Delete(n1);
        h1.Delete(n1);
        h1.Delete(n1);
        h1.Insert(n1);
        int i = 0;
        while (i < 10){
            if (!h1.Find(n1)){
                System.out.println("error");
            }
            i++;
        }




    }
    interface hashable{
        public int hashFunction(int x);
    }

   private LinkedList<Graph.Node> [] array;
   private hashable hashFunction;
   public static final int primeNumber = (10^9 + 9);

   public HashTable(int size){
       CreateHashFunction(size);
       this.array = new LinkedList[size];
   }
   private void CreateHashFunction(int size){
       Random random = new Random();
       int a = random.nextInt(primeNumber - 2) + 1;
       int b = random.nextInt(primeNumber - 1);
       this.hashFunction = (int x) -> {
           long l = 0;
           int i = 0;
            l = (long) a * x + b;
            i = (int) (l % primeNumber);
           return i % size;
       };
   }
   public Boolean Find(Graph.Node node){
       int IndexOfInsertion = hashFunction.hashFunction(node.getId());
       if (array[IndexOfInsertion] != null){
           return array[IndexOfInsertion].contains(node);
       }
       return null;
   }
   public void Insert(Graph.Node node){
       Boolean state = Find(node);
       int IndexOfInsertion = IndexOfHash(node);
       if (state == null){
           this.array[IndexOfInsertion] = new LinkedList<Graph.Node>();
           this.array[IndexOfInsertion].add(node);
           return;
       }
       if (!state){
           this.array[hashFunction.hashFunction(node.getId())].add(node);
       }
   }
   public void Delete(Graph.Node node){
       Boolean state = Find(node);
       int IndexOfDeletion = IndexOfHash(node);
       if (state != null && state){
           this.array[hashFunction.hashFunction(node.getId())].remove(node);
       }
   }
   private int IndexOfHash(Graph.Node node){
       return this.hashFunction.hashFunction(node.getId());
   }
}
