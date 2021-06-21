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



    /** O(N)
     * Initializes the graph on a given set of nodes. The created graph is empty, i.e. it has no edges.
     * You may assume that the ids of distinct nodes are distinct.
     *
     * @param nodes - an array of node objects
     */
    public Graph(Node [] nodes){
        this.maxHeap = new MaxHeap(nodes.length);
        hashTable = new HashTable(nodes.length);
        for(Node node : nodes){
            heapNode heapNode = new heapNode(node.weight, node.id);
            HashListNode hashListNode = new HashListNode(node,heapNode);
            hashTable.Insert(hashListNode);
            this.maxHeap.insert(heapNode);
            heapNode.hashListNode = hashListNode;
        }
    }

    /**
     * This method returns the node in the graph with the maximum neighborhood weight.
     * Note: nodes that have been removed from the graph using deleteNode are no longer in the graph.
     * @return a Node object representing the correct node. If there is no node in the graph, returns 'null'.
     */
    public Node maxNeighborhoodWeight(){
        heapNode heapNode =  this.maxHeap.Max();
        return heapNode.hashListNode.value;
    }

    /**
     * given a node id of a node in the graph, this method returns the neighborhood weight of that node.
     *
     * @param node_id - an id of a node.
     * @return the neighborhood weight of the node of id 'node_id' if such a node exists in the graph.
     * Otherwise, the function returns -1.
     */
    public int getNeighborhoodWeight(int node_id){
        HashListNode hashListNode = this.hashTable.Find(node_id);
        if(hashListNode == null){
            return -1;
        }
        return hashListNode.heapNode.key;
    }

    /**
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
        //TODO: implement this method.
        return false;
    }

    /**
     * Given the id of a node in the graph, deletes the node of that id from the graph, if it exists.
     *
     * @param node_id - the id of the node to delete.
     * @return returns 'true' if the function deleted a node, otherwise returns 'false'
     */
    public boolean deleteNode(int node_id){
        //TODO: implement this method.
        return false;
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

    public class heapNode {
        private int key; //sumWieghts
        private int value; // id of the node
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

    public class MaxHeap {

        private heapNode [] arr;
        private int lastIndex = 0;
        private int size;

        public MaxHeap(int size){
            this.arr = new heapNode[size+1];
            this.arr[0] = null;
            this.size = size;
        }

        public void insert(heapNode node){
            this.lastIndex++;
            arr[lastIndex] = node;
            heapify_up(lastIndex);
        }

        public void DeleteMax(){

        }
        public heapNode Max(){
            return arr[1];
        }

        public void Decrease_key(int i,int sum){
            arr[i].setKey(arr[i].getKey()+sum);
            if(sum < 0){
                heapify_down(i);
            } else{
                heapify_up(i);
            }
        }

        public void Delete(int i){
            switchNodes(i,lastIndex);
            lastIndex--;
            if(arr[i].key > arr[getParentIndex(i)].key){
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
            int smallest = i;
            if(left <= lastIndex){
                if(arr[left].key > arr[smallest].key){
                    smallest = left;
                }
            }
            if(right <= lastIndex){
                if(arr[right].key > arr[smallest].key){
                    smallest = right;
                }
            }
            if(smallest > i){
                switchNodes(i,smallest);
                heapify_down(smallest);
            }
        }

        private int getLeftIndex(int i) {
            return i*2;
        }
        private int getRightIndex(int i) {
            return i*2+1;
        }
        private int getParentIndex(int i) {
            return (int) Math.floor(i/2);
        }

        private void switchNodes(int i, int parent) {
            heapNode temp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = temp;
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

        public void remove(Graph.Node node) {
            //TODO: remove
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
            this.neighbors = new DoublyLinkedList();
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
            int IndexOfInsertion = IndexOfHash(node);
            if (Find(node.getId()) == null){
                this.array[IndexOfInsertion].insert_first(node,hash_node.getHeapNode());
            }
        }
        public void Delete(HashListNode hash_node){
            Graph.Node node = hash_node.getValue();
            HashListNode state = Find(node.getId());
            int IndexOfDeletion = IndexOfHash(node);
            if (state != null){
                this.array[hashFunction.hashFunction(node.getId())].remove(node);
            }
        }
        private int IndexOfHash(Graph.Node node){
            return this.hashFunction.hashFunction(node.getId());
        }
    }

    public class DoublyLinkedList {

        private ListNode first = null;
        private ListNode last = null;
        private int length;

        //TODO: find and remove functions


        public void insert_first(ListNode value){
            ListNode node = new ListNode(value,null,null);
            node.next = this.first;
            this.first = node;
            if(node.next == null){
                this.last = node;
            } else{
                this.first.next.prev = this.first;
            }
            this.length++;
        }

//    public boolean find(Graph.Node node){
//        ListNode listNode = first;
//        while(listNode != null){
//            if(listNode.value.getId() == node.getId()) {
//                return true;
//            }
//            listNode = listNode.next;
//        }
//        return false;
//    }


    }

    public class ListNode{

        private ListNode value;
        private ListNode next;
        private ListNode prev;

        public ListNode(ListNode value,ListNode next,ListNode prev){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

    }



}


