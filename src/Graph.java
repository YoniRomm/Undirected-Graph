/*
You must NOT change the signatures of classes/methods in this skeleton file.
You are required to implement the methods of this skeleton file according to the requirements.
You are allowed to add classes, methods, and members as required.
 */

import java.util.Random;

/**
 * This class represents a graph that efficiently maintains the heaviest neighborhood over edge addition and
 * vertex deletion.
 *
 */
public class Graph {

    private MaxHeap maxHeap;
    private HashTable hashTable;
    private int num_Nodes;
    private int num_Edges;



    /** O(N)
     * Initializes the graph on a given set of nodes. The created graph is empty, i.e. it has no edges.
     * You may assume that the ids of distinct nodes are distinct.
     *
     * @param nodes - an array of node objects
     */
    public Graph(Node [] nodes){
        hashTable = new HashTable(nodes.length);
        heapNode [] arr = new heapNode[nodes.length+1];
        int i=1;
        for(Node node : nodes){
            heapNode heapNode = new heapNode(node.weight, node.id);
            heapNode.index = i;
            HashListNode hashListNode = new HashListNode(node,heapNode);
            hashTable.Insert(hashListNode);
            arr[i++] = heapNode;
            heapNode.hashListNode = hashListNode;
        }
        this.maxHeap = new MaxHeap(arr);
        num_Nodes = nodes.length;
    }

    /** O(1)
     * This method returns the node in the graph with the maximum neighborhood weight.
     * Note: nodes that have been removed from the graph using deleteNode are no longer in the graph.
     * @return a Node object representing the correct node. If there is no node in the graph, returns 'null'.
     */
    public Node maxNeighborhoodWeight(){
        heapNode heapNode =  this.maxHeap.Max();
        return heapNode.hashListNode.value;
    }

    /** O(1)
     * given a node id of a node in the graph, this method returns the neighborhood weight of that node.
     *
     * @param node_id - an id of a node.
     * @return the neighborhood weight of the node of id 'node_id' if such a node exists in the graph.
     * Otherwise, the function returns -1.
     */
    public int getNeighborhoodWeight(int node_id){
        //TODO: check this function in tester
        HashListNode hashListNode = this.hashTable.Find(node_id);
        if(hashListNode == null){
            return -1;
        }
        return hashListNode.heapNode.key;
    }

    /** O(log n)
     * This function adds an edge between the two nodes whose ids are specified.
     * If one of these nodes is not in the graph, the function does nothing.
     * The two nodes must be distinct; otherwise, the function does nothing.
     * You may assume that if the two nodes are in the graph, there exists no edge between them prior to the call.
     *
     * @param node1_id - the id of the first node.
     * @param node2_id - the id of the second node.
     * @return returns 'true' if the function added an edge, otherwise returns 'false'.
     */
    public boolean addEdge(int node1_id, int node2_id){
        HashListNode hashListNode1 = this.hashTable.Find(node1_id);
        HashListNode hashListNode2 = this.hashTable.Find(node2_id);

        if (hashListNode1 == null || hashListNode2 == null){
            return false;
        }
        ListNode node1 = new ListNode(null, hashListNode2);
        ListNode node2 = new ListNode(node1, hashListNode1);
        node1.value = node2;
        hashListNode1.neighbors.insert_first(node2);
        int index = hashListNode1.heapNode.index;
        this.maxHeap.decrease_key(index,hashListNode2.value.weight); // O(log n)
        hashListNode2.neighbors.insert_first(node1);
        int index2 = hashListNode2.heapNode.index;
        this.maxHeap.decrease_key(index2,hashListNode1.value.weight); // O(log n)

        this.num_Edges++;
        //TODO: check numedges and nodes

        return true;
    }

    /** O((#neigbors+1)logn)
     * Given the id of a node in the graph, deletes the node of that id from the graph, if it exists.
     *
     * @param node_id - the id of the node to delete.
     * @return returns 'true' if the function deleted a node, otherwise returns 'false'
     */
    public boolean deleteNode(int node_id){
        HashListNode hashListNode1 = this.hashTable.Find(node_id);
        if (hashListNode1 == null){
            return false;
        }
        this.hashTable.Delete(hashListNode1);
        maxHeap.Delete(hashListNode1.heapNode.index); // O(log n)
        ListNode node = hashListNode1.neighbors.first;
        while (node != null){
            ListNode listNodeNeighbor = node.value;
            listNodeNeighbor.hashListNode.neighbors.delete(listNodeNeighbor); // O(1)
            int index = listNodeNeighbor.hashListNode.heapNode.index;
            int weight = hashListNode1.value.weight;
            this.maxHeap.decrease_key(index,-weight); // O(log n)
            node = node.next;
            this.num_Edges--;
        }
        this.num_Nodes--;

        return true;
    }

    /** O(1)
     * Returns the number of nodes currently in the graph.
     * @return the number of nodes in the graph.
     */
    public int getNumNodes(){
        return num_Nodes;
    }

    /** O(1)
     * Returns the number of edges currently in the graph.
     * @return the number of edges currently in the graph.
     */
    public int getNumEdges(){
        return num_Edges;
    }


    /**
     * This class represents a node in the graph.
     */
    public static class Node{

        private int id;
        private int weight;


        /** O(1)
         * Creates a new node object, given its id and its weight.
         * @param id - the id of the node.
         * @param weight - the weight of the node.
         */
        public Node(int id, int weight){
            this.id = id;
            this.weight = weight;
        }

        /** O(1)
         * Returns the id of the node.
         * @return the id of the node.
         */
        public int getId(){
            return this.id;
        }

        /** O(1)
         * Returns the weight of the node.
         * @return the weight of the node.
         */
        public int getWeight(){
            return this.weight;
        }
    }

    public class MaxHeap {

        private heapNode [] arr;
        private int lastIndex = 0;
        private int size;

        public MaxHeap(heapNode [] arr){
            this.size = arr.length;
            this.arr = arr;
            lastIndex = arr.length-1;
            array_to_MaxHeap();
        }

        public void insert(heapNode node){
            this.lastIndex++;
            arr[lastIndex] = node;
            node.index = lastIndex;
            heapify_up(lastIndex);
        }

        public void DeleteMax(){
            switchNodes(1,lastIndex);
            lastIndex--;
            heapify_down(1);
        }

        public heapNode Max(){
            return arr[1];
        }

        public void decrease_key(int i, int sum){
            arr[i].setKey(arr[i].getKey()+sum);
            if(sum < 0){
                heapify_down(i);
            } else{
                heapify_up(i);
            }
        }

        public void Delete(int i){
            if(i==1){
                DeleteMax();
                return;
            }
            switchNodes(i,lastIndex);
            lastIndex--;
            int parent_index = getParentIndex(i);
            if(arr[i].key > arr[parent_index].key){
                heapify_up(i);
            }
            else{
                heapify_down(i);
            }
        }

        public void heapify_up(int i){
            int parent = getParentIndex(i);
            while (i > 1 && arr[i].key > arr[parent].key){
                switchNodes(i,parent);
                i = parent;
            }
        }

        public void heapify_down(int i){
            int left = getLeftIndex(i);
            int right = getRightIndex(i);
            int largest = i;
            if(left <= lastIndex){
                if(arr[left].key > arr[largest].key){
                    largest = left;
                }
            }
            if(right <= lastIndex){
                if(arr[right].key > arr[largest].key){
                    largest = right;
                }
            }
            if(largest > i){
                switchNodes(i,largest);
                heapify_down(largest);
            }
        }

        private int getLeftIndex(int i) {
            return i*2;
        }
        private int getRightIndex(int i) {
            return i*2+1;
        }
        private int getParentIndex(int i) {
            if(i==1){
                return 1;
            }
            return (int) Math.floor(i/2);
        }

        private void switchNodes(int i, int parent) {
            heapNode temp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = temp;
            arr[i].index = i;
            arr[parent].index = parent;
        }

        /** O(N)
         * turn an array to maxHeap
         *
         */

        public void array_to_MaxHeap() {
            int index = (int) Math.floor(lastIndex/2);
            for(int i=index;i>0;i--){
                heapify_down(i);
            }
        }
    }

    public class heapNode {
        private int key; //sumWieghts
        private int value; // id of the node
        private int index; // index in maxHeap
        private HashListNode hashListNode; //pointer to the node in hashtable

        public heapNode(int sumWeights,int idOfTheNode){
            this.key = sumWeights;
            this.value = idOfTheNode;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public class DoublyHashLinkedList {

        private HashListNode first = null;
        private HashListNode last = null;
        private int length;


        public void insert_first(Graph.Node value, Graph.heapNode heapNode){
            HashListNode node = new HashListNode(value,heapNode);
            node.next = this.first;
            this.first = node;
            if(node.next == null){
                this.last = node;
            } else{
                this.first.next.prev = this.first;
            }
            this.length++;
        }

        public HashListNode find(int node_id){
            HashListNode listNode = first;
            while(listNode != null){
                if(listNode.value.getId() == node_id) {
                    return listNode;
                }
                listNode = listNode.next;
            }
            return null;
        }

        public void remove(HashListNode listNode){
            if(listNode == this.first && listNode == this.last){
                this.first = null;
                this.last = null;
                return;
            }
            if(listNode == this.first){
                this.first = listNode.next;
                this.first.prev = null;
                return;
            }
            if(listNode == this.last){
                this.last = listNode.prev;
                this.last.next = null;
                return;
            }
            listNode.prev.next = listNode.next;
            listNode.next.prev = listNode.prev;
        }
    }

    public class HashListNode {

        private Graph.Node value; // graph node
        private HashListNode next; // next
        private HashListNode prev; // prev
        private Graph.heapNode heapNode; // pointer to max heap
        private DoublyLinkedList neighbors; // neighbors

        public HashListNode(Graph.Node value, Graph.heapNode heapNode) {
            this.value = value;
            this.heapNode = heapNode;
            this.neighbors = new DoublyLinkedList(this);
        }

        public Graph.Node getValue() {
            return value;
        }

        public Graph.heapNode getHeapNode() {
            return heapNode;
        }
    }

    interface hashable{
        public int hashFunction(int x);
    }

    public class HashTable {

        private hashable hashFunction;
        private DoublyHashLinkedList[] array;
        public static final int primeNumber = (10^9 + 9);

        public HashTable(int size){
            CreateHashFunction(size);
            this.array = new DoublyHashLinkedList[size];
            for(int i=0;i<array.length;i++){
                array[i] = new DoublyHashLinkedList();
            }
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

        public HashListNode Find(int node_id){
            int IndexOfInsertion = hashFunction.hashFunction(node_id);
            return array[IndexOfInsertion].find(node_id);
        }

        public void Insert(HashListNode hash_node){
            Graph.Node node = hash_node.getValue();
            int IndexOfInsertion = IndexOfHash(node.getId());
            if (Find(node.getId()) == null){
                this.array[IndexOfInsertion].insert_first(node,hash_node.getHeapNode());
            }
        }
        public void Delete(HashListNode hash_node){
            int IndexOfDeletion = IndexOfHash(hash_node.value.id);
            this.array[IndexOfDeletion].remove(hash_node);
        }
        private int IndexOfHash(int node_id){
            return this.hashFunction.hashFunction(node_id);
        }
    }

    public class DoublyLinkedList {

        private ListNode first;
        private ListNode last;
        private int length;
        private HashListNode hashListNode;


        public DoublyLinkedList(HashListNode hashListNode){
            this.hashListNode = hashListNode;
        }

        public void insert_first(ListNode node){
            node.next = this.first;
            this.first = node;
            if(node.next == null){
                this.last = node;
            } else{
                this.first.next.prev = this.first;
            }
            this.length++;
        }

        public void delete(ListNode listNode){
            if(listNode == this.first && listNode == this.last){
                this.first = null;
                this.last = null;
                return;
            }
            if(listNode == this.first){
                this.first = listNode.next;
                this.first.prev = null;
                return;
            }
            if(listNode == this.last){
                this.last = listNode.prev;
                this.last.next = null;
                return;
            }
            listNode.prev.next = listNode.next;
            listNode.next.prev = listNode.prev;
        }
    }

    public class ListNode{

        private ListNode value;
        private ListNode next;
        private ListNode prev;
        private HashListNode hashListNode;

        public ListNode(ListNode value, HashListNode hashListNode){
            this.value = value;
            this.hashListNode = hashListNode;
        }

    }
}


