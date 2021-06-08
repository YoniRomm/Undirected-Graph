public class DoublyHashLinkedList {

    private ListNode first = null;
    private ListNode last = null;
    private int length;


    public void insert_first(Graph.Node value){
        ListNode node = new ListNode(value,);
        node.next = this.first;
        this.first = node;
        if(node.next == null){
            this.last = node;
        }
        this.length++;
    }

    public boolean find(Graph.Node node){
        ListNode listNode = first;
        while(listNode != null){
            if(listNode.value.getId() == node.getId()) {
                return true;
            }
            listNode = listNode.next;
        }
        return false;
    }

    public void remove(Graph.Node node) {
        //TODO: remove
    }



    public class ListNode{

        private Graph.Node value; // graph node
        private ListNode next; // next
        private ListNode prev; // prev
        private MaxHeap.heapNode heapNode; // pointer to max heap
        private DoublyLinkedList neighbors; // neighbors

        public ListNode(Graph.Node value, MaxHeap.heapNode heapNode) {
            this.value = value;
            this.heapNode = heapNode;
            this.neighbors = new DoublyLinkedList();
        }
    }

    }

}
