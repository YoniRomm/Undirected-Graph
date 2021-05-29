public class DoublyLinkedList {

    private listNode first = null;
    private listNode last = null;
    private int length;


    public void insert_first(listNode value){
        listNode node = new listNode(value,null,null);
        node.next = this.first;
        this.first = node;
        if(node.next == null){
            this.last = node;
        }
        this.length++;
    }

    public class listNode{

        private listNode value;
        private listNode next;
        private listNode prev;

        public listNode(listNode value,listNode next,listNode prev){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

    }

}
