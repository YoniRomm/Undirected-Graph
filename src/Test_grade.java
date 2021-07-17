//Graph Tester

import java.util.ArrayList;
import java.util.Collections;

public class Test_grade {

    //static Heap heap;
    static Graph graph;
    static double grade;
    static double testScore;
	static int n;
	static int n2;
    public static void main(String[] args) {
		n = 10000;
		n2 = 300;
        grade = 40.0;
        testScore = grade / 20;
        try {test0();} catch (Exception e){bugFound("test0");}
        try {test1();} catch (Exception e){bugFound("test1");}
        try {test2();} catch (Exception e){bugFound("test2");}
        try {test3();} catch (Exception e){bugFound("test3");}
        try {test4();} catch (Exception e){bugFound("test4");}
        try {test5();} catch (Exception e){bugFound("test5");}
        try {test6();} catch (Exception e){bugFound("test6");}
        try {test7();} catch (Exception e){bugFound("test7");}
        try {test8();} catch (Exception e){bugFound("test8");}
        try {test9();} catch (Exception e){bugFound("test9");}
        try {test10();} catch (Exception e){bugFound("test10");}
        try {test11();} catch (Exception e){bugFound("test11");}
        try {test12();} catch (Exception e){bugFound("test12");}
        try {test13();} catch (Exception e){bugFound("test13");}
        try {test14();} catch (Exception e){bugFound("test14");}
        try {test15();} catch (Exception e){bugFound("test15");}
        try {test16();} catch (Exception e){bugFound("test16");}
        try {test17();} catch (Exception e){bugFound("test17");}
        try {test18();} catch (Exception e){bugFound("test18");}
        try {test19();} catch (Exception e){bugFound("test19");}
        System.out.println(grade);
    }

    static void test0() {
        String test = "test0";
		Graph.Node [] nodes = new Graph.Node[0];
        graph = new Graph(nodes);
		if(graph.maxNeighborhoodWeight()!= null){
			bugFound(test);
			return;
		}
        if(graph.getNumNodes()!= 0){
			bugFound(test);
		}
    }

    static void test1() {
        String test = "test1";
        addNodes(0,1,0,false);
		for(int i=0; i<n ;i++){
			if(graph.getNeighborhoodWeight(i)!=-1){
				bugFound(test);
				return;
			}
		}
    }

    static void test2() {
        String test = "test2";
        addNodes(n,1,0,false);
		for(int i=0; i<n ;i++){
			if(graph.getNeighborhoodWeight(i)!=1){
				bugFound(test);
				return;
			}
		}
    }

    static void test3() {
        String test = "test3";
        addNodes(n,1,1,false);
		for(int i=0; i<n ;i++){
			if(graph.getNeighborhoodWeight(i)!=1+i){
				bugFound(test);
				return;
			}
		}
    }

    static void test4() {
        String test = "test4";
        addNodes(n,1,0,true);
		for(int i=0; i<n ;i++){
			if(graph.getNeighborhoodWeight(i)!=1){
				bugFound(test);
				return;
			}
		}
    }

    static void test5() {
        String test = "test5";
        addNodes(n,1,1,true);
		for(int i=0; i<n ;i++){
			if(graph.getNeighborhoodWeight(i)!=1+i){
				bugFound(test);
				return;
			}
		}
    }

    static void test6() {
        String test = "test6";
		addNodes(n,1,0,true);
		if(graph.getNumNodes()!=n){
			bugFound(test);
			return;
		}
    }

    static void test7() {
        String test = "test7";
		addNodes(n,1,0,true);
		if(graph.getNumNodes()!=n){
			bugFound(test);
			return;
		}
		for(int i=0; i<n ;i++){
			if(graph.getNeighborhoodWeight(i)!=1){
				bugFound(test);
				return;
			}
		}
		for(int i=0; i<n ;i++){
			graph.deleteNode(i);
			if(graph.getNeighborhoodWeight(i)!=-1 || graph.getNumNodes()!=n-i-1){
				bugFound(test);
				return;
			}
		}
		addNodes(n,1,1,false);
		if(graph.getNumNodes()!=n){
			bugFound(test);
			return;
		}
		for(int i=0; i<n ;i++){
			if(graph.getNeighborhoodWeight(i)!=1+i){
				bugFound(test);
				return;
			}
		}
		for(int i=0; i<n ;i++){
			graph.deleteNode(i);
			if(graph.getNeighborhoodWeight(i)!=-1 || graph.getNumNodes()!=n-i-1){
				bugFound(test);
				return;
			}
		}
    }
	
	static void test8() {
        String test = "test8";
		addNodes(n,1,1,true);
		for(int i=n-1; i>=0 ;i--){
			Graph.Node node = graph.maxNeighborhoodWeight();
			if(node.getId()!=i || node.getWeight()!=i+1){
				bugFound(test);
				return;
			}
			if(!graph.deleteNode(i)){
				bugFound(test);
				return;
			}
		}
		if(graph.maxNeighborhoodWeight()!= null){
			bugFound(test);
			return;
		}
		if(graph.deleteNode(n/2)||graph.deleteNode(2*n)){
			bugFound(test);
			return;
		}
    }
	
	static void test9() {
        String test = "test9";
		addNodes(n,n,-1,true);
		for(int i=0; i<n ;i++){
			Graph.Node node = graph.maxNeighborhoodWeight();
			if(node.getId()!=i || node.getWeight()!=-i+n){
				bugFound(test);
				return;
			}
			if(!graph.deleteNode(i)){
				bugFound(test);
				return;
			}
		}
		if(graph.maxNeighborhoodWeight()!= null){
			bugFound(test);
			return;
		}
		if(graph.deleteNode(n/2)||graph.deleteNode(2*n)){
			bugFound(test);
			return;
		}
    }
	
	static void test10() {
        String test = "test10";
		addNodes(0,1,0,false);
		if(graph.addEdge(0,1)){
			bugFound(test);
			return;
		}
		addNodes(2,1,0,true);
		if(!graph.addEdge(0,1)){
			bugFound(test);
			return;
		}
		if(graph.getNeighborhoodWeight(0)!=2){
			bugFound(test);
			return;
		}
		addNodes(3,3,1,false);
		if(graph.maxNeighborhoodWeight().getId()!=2){
			bugFound(test);
			return;
		}
		if(!graph.addEdge(0,1)){
			bugFound(test);
			return;
		}
		if(graph.maxNeighborhoodWeight().getId()==2 || graph.getNeighborhoodWeight(1)!=7){
			bugFound(test);
			return;
		}
		if(!graph.deleteNode(0)){
			bugFound(test);
			return;
		}
		if(graph.maxNeighborhoodWeight().getId()!=2 || graph.getNeighborhoodWeight(1)!=4){
			bugFound(test);
			return;
		}
    }
	static void test11() {
        String test = "test11";
		addNodes(n,1,1,true);
		if(graph.getNeighborhoodWeight(0)!=1){
			bugFound(test);
			return;
		}
		int sum=1;
		for(int i=1;i<n;i++){
			sum += i+1;
			if(graph.getNeighborhoodWeight(i)!=1+i || !graph.addEdge(0,i)){
				bugFound(test);
				return;
			}
		}
		if(graph.maxNeighborhoodWeight().getId()!=0 || graph.getNeighborhoodWeight(0)!=sum){
			bugFound(test);
			return;
		}
    }
	static void test12() {
        String test = "test12";
		runner(n,1,0,new IsConnected(){
			public boolean con(int i,int j){
				/*if(i>j){
					return con(j,i);
				}
				return (i%2 == 0) && (i+1 == j);*/
				return false;
			}
		},test);
    }
	static void test13() {
        String test = "test13";
		runner(n,1,0,new IsConnected(){
			public boolean con(int i,int j){
				if(i>j){
					return con(j,i);
				}
				return ((i+1)%n == j);
			}
		},test);
    }
	static void test14() {
        String test = "test14";
		runner(n,n,1,new IsConnected(){
			public boolean con(int i,int j){
				if(i>j){
					return con(j,i);
				}
				return (i%2 == 0) && (i+1 == j);
			}
		},test);
    }
	static void test15() {
        String test = "test15";
		runner(n,n,1,new IsConnected(){
			public boolean con(int i,int j){
				if(i>j){
					return con(j,i);
				}
				return ((i+1)%n == j);
			}
		},test);
    }
	static void test16() {
        String test = "test16";
		runner(n2,n2,1,new IsConnected(){
			public boolean con(int i,int j){
				if(i>j){
					return con(j,i);
				}
				return ((i+j+(i&j))%3==0) ;
			}
		},test);
    }
	static void test17() {
        String test = "test17";
		runner(n2,n2+1000,-1,new IsConnected(){
			public boolean con(int i,int j){
				if(i>j){
					return con(j,i);
				}
				return ((i+j+(i^j))%3==1) ;
			}
		},test);
    }
	static void test18() {
        String test = "test18";
		runner(n2,34,2,new IsConnected(){
			public boolean con(int i,int j){
				if(i==0){
					return j==1;
				}
				return con(j%i,i);
			}
		},test);
    }
	static void test19() {
        String test = "test19";
		runner(n2,235,1,new IsConnected(){
			public boolean con(int i,int j){
				if(i==0){
					return j!=1;
				}
				return con(j%i,i);
			}
		},test);
    }
	interface IsConnected {
		boolean con(int i,int j);
	}
    static void bugFound (String test) {
        System.out.println("Bug found in " + test);
        grade -= testScore;
    }

    static void addNodes(int n,int start,int linear, boolean randomize) {
		Graph.Node [] nodes = new Graph.Node[n];
		ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
		//System.out.println("ok");
		if(randomize){
			Collections.shuffle(numbers);
		}
		//System.out.println("ok"+nodes.length);
        for (int i = 0; i < n; i++) {
            nodes[numbers.get(i)]= new Graph.Node(i,start+linear*i);
        }
		//System.out.println("ok3");
		graph = new Graph(nodes);
		//System.out.println("ok4");
    }
	static void runner(int n,int start,int linear, IsConnected isconnected, String test) {
		addNodes(n,start,linear,true);
		int [] weights = new int[n];
		int [] neighborhoodWeight = new int[n];
		for(int i=0;i<n;i++){
			weights[i]=start+i*linear;
			neighborhoodWeight[i]=start+i*linear;
			
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<i;j++){
				if(isconnected.con(i,j)){
					if(!graph.addEdge(i,j)){
						bugFound(test);
						return;
						
					}
					neighborhoodWeight[i]+=weights[j];
					neighborhoodWeight[j]+=weights[i];
				}
			}
		}
		for(int i=0;i<n;i++){
			int cur=graph.maxNeighborhoodWeight().getId();
			if(graph.getNeighborhoodWeight(cur)!=neighborhoodWeight[cur] || graph.getNumNodes()!=n-i){
				bugFound(test);
				return;
			}
			int old=neighborhoodWeight[cur];
			for(int j=0;j<n;j++){
				if(old<neighborhoodWeight[j]){
					bugFound(test);
					return;
				}
				if(isconnected.con(cur,j)&&j!=cur){
					neighborhoodWeight[cur]-=weights[j];
					neighborhoodWeight[j]-=weights[cur];
				}
			}
			if(neighborhoodWeight[cur]!=weights[cur] || !graph.deleteNode(cur)){
				bugFound(test);
				return;
			}
			neighborhoodWeight[cur]=0;
			weights[cur]=0;
		}
    }
}

