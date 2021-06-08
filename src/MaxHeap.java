public class MaxHeap {

    //TODO: init maxheap from array

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


    public class heapNode {
        private int key; //sumWieghts
        private int value; // id of the node

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
}
