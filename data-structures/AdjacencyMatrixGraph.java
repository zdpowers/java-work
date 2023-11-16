public class MyClass {
    public static void main(String[] args) {
        AdjMatrixGraph amg = new AdjMatrixGraph();
        amg.addVertex("A");
        amg.addVertex("B");
        amg.addVertex("C");
        amg.addVertex("D");
        amg.addVertex("E");
        
        amg.addEdge("A", "B");
        amg.addEdge("A", "C");
        amg.addEdge("B", "D");
        amg.addEdge("C", "E");
        
        System.out.println(amg);
        System.out.println();
        amg.printGraph();

    }
}

class AdjMatrixGraph{
    
    class Vertex {
        public String name;
        public Vertex(String n) {
            name = n;
        }
        public String toString() {
            return name;
        }
    }
    
    public Vertex[] vertexList = new Vertex[5];
    public int vertexCount = 0;
    public int[][] matrix = new int[5][5];
    
    public AdjMatrixGraph(){}
    
    public void addVertex(String vname) {
        if(vertexCount >= 5) {
            System.out.println("Graph is Full");
        } else {
            Vertex newVertex = new Vertex(vname);
            vertexList[vertexCount] = newVertex;
            vertexCount++;
        }
    }
    
    public int getVertexIndex(String n) {
        boolean found = false;
        int index = 0;
        while(!found && index < vertexList.length) {
            if(vertexList[index].name == n) {
                found = true;
            } else {
                index++;
            }
        }
        if(found)
            return index;
        else
            return -1;
    }
    public void addEdge(String v1, String v2) {
        int v1index = getVertexIndex(v1);
        int v2index = getVertexIndex(v2);
        if(v1index > -1 && v2index >-1) {
            matrix[v1index][v2index] = 1;
            matrix[v2index][v1index] = 1;
        } else {
            System.out.println("Invalid vertex error.");
        }
    }
    
    public void printGraph() {
        String output = "  ";
        for(Vertex v: vertexList) {
            output += v.name + " ";
        }
        output += "\n";
        for(int i=0; i < vertexList.length; i++) {
            output += vertexList[i].name + " ";
            for(int val : matrix[i]) {
                output += val + " ";
            }
            output += "\n";
        }
        
        System.out.println(output);
    }
    
    public String toString() {
        String output = "";
        int counter = 0;
        for(Vertex v : vertexList) {
            
            output += v.name + ":";
            
            for(int i=0; i<matrix[counter].length; i++) {
                
                if(matrix[counter][i]>0) {
                    output += "[" + v.name + "-" + vertexList[i].name + "], ";
                }
                
            }
            output+= "\n";
            counter++;
        }
        return output;
        
    }
    
    
}
