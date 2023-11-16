import java.util.ArrayList;
import java.util.LinkedList;

public class MyClass { 
    public static void main(String args[]) {
		
		AdjListGraph ALSGraph = new AdjListGraph();
		// Insert Vertices
		ALSGraph.insertVertex(1);
		ALSGraph.insertVertex(2);
		ALSGraph.insertVertex(3);
		ALSGraph.insertVertex(4);
		
		// Add Edges
		ALSGraph.addEdge("A", 1, 2);
		ALSGraph.addEdge("B", 1, 3);
		ALSGraph.addEdge("C", 2, 3);
		ALSGraph.addEdge("D", 3, 4);
		
		System.out.println("ORIGINAL GRAPH:");
		System.out.println(ALSGraph);
		
		// REmvove Edge D
		System.out.println();
		System.out.println("REMOVE EDGE D:");
	    ALSGraph.removeEdge("D");
	    System.out.println(ALSGraph);
		
		
		// Add Edge E that connects vertices 2&4
		System.out.println();
		System.out.println("ADD EDGE E, CONNECTING 2 & 4:");
		ALSGraph.addEdge("E", 2, 4);
	    System.out.println(ALSGraph);
	    
	    // Remove edge that connects 1,3 (EDGE B)
	    System.out.println();
	    System.out.println("REMOVE EDGE CONNECTING 1 & 3 (EDGE B): ");
	    ALSGraph.removeEdge(1,3);
	    System.out.println(ALSGraph);
	    
	    // Add Edge Q that connects vertices 2&4
	    System.out.println();
	    System.out.println("ADD EDGE Q, CONNECTING 2 & 4:");
		ALSGraph.addEdge("Q", 1, 3);
	    System.out.println(ALSGraph);
	    
	    // Add Edge X connecting 3&4
	    System.out.println();
	    System.out.println("ADD EDGE X CONNECTING 3 & 4:");
	    ALSGraph.addEdge("X", 3, 4);
	    System.out.println(ALSGraph);
	    
	    // Remove vertex 2
	    System.out.println();
	    System.out.println("REMOVE VERTEX 2:");
	    ALSGraph.removeVertex(2);
	    System.out.println(ALSGraph);
	    
	}
}

class AdjListGraph {
    
    class Edge {
        public String name;
        public Vertex[] vertices = new Vertex[2];
        
        public Edge(String n, Vertex v1, Vertex v2) {
            name = n;
            vertices[0] = v1;
            vertices[1] = v2;
        }
        
        public String toString() {
            return name + ":[" + vertices[0] + ", " + vertices[1] + "]";
        }
    }
    class Vertex {
        private int val;
        public LinkedList<Edge> incidenceCollection;
        
        public Vertex(int value) {
            val = value;
            incidenceCollection = new LinkedList<>();
        }
        
        public int getVal() {
            return val;
        }
        
        public void addIncidentEdge(Edge newEdge) {
            incidenceCollection.add(newEdge);
        }
        
        public LinkedList getIncidentEdges() {
            return incidenceCollection;
        }
        
        public String incidenceCollectionList() {
            String retval = "";
            for(Edge e : incidenceCollection) {
                retval += " --> " + e.toString();
            }
            return retval;
        }
        
        public int getOutDegree() {
            return incidenceCollection.size();
        }
        
        public String toString() {
            return Integer.toString(val);
        }
    }
    
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    
    public AdjListGraph() {}
    
    public int numVertices() {
        return vertices.size();
    }
    public int numEdges() {
        return edges.size();
    }
    
    public ArrayList getVertices() {
        return vertices;
    }
    public ArrayList getEdges() {
        return edges;
    }
    
    
    // GET THE EDGE USING THE VERTEX VALUES AS OPPOSED TO VERTEX OBJECTS
    public Edge getEdge(int v1, int v2) {
        boolean v1Found = false; int v1Index = 0; Vertex v1Object = null;
        int numVertices = vertices.size();
        // FIND OBJECT INSTANCE OF VERTEX WITH VALUE V1
        while(!v1Found && v1Index < numVertices) {
            v1Object = vertices.get(v1Index);
            if(v1Object.getVal() == v1)
                v1Found = true;
            v1Index++;    
        }
        
        if(!v1Found) {
            // IF VERTEX OBJECT WITH VALUE V1 NOT FOUND, RETURN NULL
            System.out.println("Vertex 1 not found.");
            return null;
        } else {
            
            // SEARCH THE EDGES ASSOCIATED WITH V1 TO FIND THE EDGE THAT CONNECTS TO V2
            boolean edgeFound = false; int edgeIndex = 0; Edge edgeObject = null; int numEdges = v1Object.incidenceCollection.size();
            // FIND THE EDGE OBJECT THAT CONNECTS TO V2
            while(!edgeFound && edgeIndex < numEdges) {
                edgeObject = v1Object.incidenceCollection.get(edgeIndex);
                if(edgeObject.vertices[0].getVal() == v2 || edgeObject.vertices[1].getVal() == v2)
                    edgeFound = true;
                edgeIndex++;
            }
            
            if(!edgeFound) {
                // IF NO EDGE CONNECTING TO V2 FOUND, RETURN NULL
                System.out.println("No edge found connecting Vertex 1 to Vertex 1.");
                return null;
            } else {
                //System.out.println(edgeObject);
                return edgeObject;
            }
        }
        
    }
    
    // HELPER FUNCTION TO GET VERTEX OBJECT REFERENCE
    private Vertex getVertexReference(int vertexvalue) {
        boolean v1Found = false; int v1Index = 0; Vertex v1Object = null; int numVertices = vertices.size();
        
        // FIND OBJECT INSTANCE OF VERTEX WITH VALUE V1
        while(!v1Found && v1Index < numVertices) {
            v1Object = vertices.get(v1Index);
            if(v1Object.getVal() == vertexvalue)
                v1Found = true;
            v1Index++;    
        }
        
        // RETURN OBJECT IF IT EXISTS
        if(v1Found) {
            return v1Object;
        } else {
            System.out.println("Vertex not found!");
            return null;
        }
    }
    
    // FIND THE VERTEX OUTDEGREE
    public int outDegree(int vertexvalue) {
        Vertex v1 = getVertexReference(vertexvalue);
        if(v1 == null) {
            System.out.println("Vertex does not exist!");
            return 0;
        } else {
            return v1.getOutDegree();
        }
    }
    
    // ADD VERTEX
    public void insertVertex(int vertexvalue) {
        Vertex newVertex = new Vertex(vertexvalue);
        vertices.add(newVertex);
    }
    
    // ADD EDGE
    public void addEdge(String edgeName, int v1, int v2) {
        Vertex vertex1 = getVertexReference(v1);
        Vertex vertex2 = getVertexReference(v2);
        if(vertex1 != null && vertex2 !=null) {
            Edge newEdge = new Edge(edgeName, vertex1, vertex2);
            vertex1.addIncidentEdge(newEdge);
            vertex2.addIncidentEdge(newEdge);
            edges.add(newEdge);
        } else {
            if(vertex1 == null)
                System.out.println("Vertex 1 does not exist!");
            else
                System.out.println("Vertex 2 does not exist!");
        }
    }
    
    // REMOVE VERTEX
    public Vertex removeVertex(int vertexvalue) {
        Vertex v1 = getVertexReference(vertexvalue);
        if(v1 !=null) {
            Vertex otherVertex;
            for(Edge incidentEdge : v1.incidenceCollection) {
                // For each incident edge associated with the given vertex
                // remove it from the incidentedge list of the other vertex
                if(incidentEdge.vertices[0].getVal() != vertexvalue) {
                    otherVertex = incidentEdge.vertices[0];
                } else {
                    otherVertex = incidentEdge.vertices[1];
                }
                otherVertex.incidenceCollection.remove(incidentEdge);
            }
            vertices.remove(v1);
            return v1;
        } else {
            // If vertex isnt found return null
            System.out.println("Vertex not found!");
            return null;
        }
    }
    
    // REMOVE EDGE IF PASSED EDGE OBJECT
    public Edge removeEdge(Edge e) {
        //remove edge references from vertex incidence collections
        for(Vertex v : e.vertices) {
            v.incidenceCollection.remove(e);
        }
        //remove from edge list
        edges.remove(e);
        return e;
    }
    
    // Remove Edge IF PASSED ITS NAME
    public Edge removeEdge(String edgeName) {
        boolean edgeFound = false; int index = 0; Edge edgeObject = null;
        while(!edgeFound && index < edges.size()) {
            edgeObject = edges.get(index);
            if (edgeObject.name == edgeName)
                edgeFound = true;
            index++;
        }
        if(edgeFound) {
            return removeEdge(edgeObject);
        } else {
            System.out.println("Edge Not Found!");
            return null;
        }
    }
    
    // Remove Edge IF PASSED THE VERTEX VALUES
    public Edge removeEdge(int v1, int v2) {
        Edge edgeObject = getEdge(v1, v2);
        if(edgeObject != null) {
            return removeEdge(edgeObject);
        } else {
            return null;
        }
    }
    
    public String toString() {
        String retval = "";
        for(Vertex v : vertices) {
            retval += "Vertex " + v.getVal() + v.incidenceCollectionList() + "\n";
        }
        return retval;
    }
    
}
