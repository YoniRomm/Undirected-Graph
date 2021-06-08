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
