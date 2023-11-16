public class FamilyTree {
    public static void main(String args[]) {
        LinkedBinaryFamilyTree ft = new LinkedBinaryFamilyTree();
        ft.addRoot("Abraham");
        
        // Abraham's Children
        String[] AbrahamsChildren = {
            "Ishmael", "Isaac", "Zimran", "Jokshan", "Medan", "Midian", "Ishbak", "Shuah"
        };
        for(String child : AbrahamsChildren) {
            ft.addChildOf("Abraham", child);
        }
        
        // Ishmael's Children
        String[] IshmaelsChildren = {
            "Nevaioth", "Kedar", "Adbeel", "Mibsam", "Mishma", "Dumah",
            "Massa", "Hadad", "Tema", "Jetur", "Naphish", "Kedemah"
        };
        for(String child : IshmaelsChildren) {
            ft.addChildOf("Ishmael", child);
        }
        
        // Isaac's Children
        ft.addChildOf("Isaac", "Esau");
        ft.addChildOf("Isaac", "Jacob");
        
        // Esau's Children
        String[] EsausChildren = {"Eliphaz", "Reuel", "Jeush", "Jalam", "Korah"};
        for(String child : EsausChildren) {
            ft.addChildOf("Esau", child);
        }
        
        // Jacob's Children
        String[] JacobsChildren = {
            "Reuben", "Simeon", "Levi", "Judah", "Dan", "Naphtali", "Gad", 
            "Asher", "Issachar", "Zebulun", "Dinah", "Joseph", "Benjamin"
        };
        for(String child : JacobsChildren) {
            ft.addChildOf("Jacob", child);
        }
        
        // Jokshan's Children
        ft.addChildOf("Jokshan", "Sheba");
        ft.addChildOf("Jokshan", "Dedan");
        
        // Midian's Children
        String[] MidiansChildren = {"Ephah", "Epher", "Hanoch", "Abida", "Eldaah"};
        for(String child : MidiansChildren) {
            ft.addChildOf("Midian", child);
        }
        
        
        
        ft.printTree();

        
    }
}

class LinkedBinaryFamilyTree {
    
    class Node {
        private Node parent, left, right;
        private String name;
        
        public Node(String n, Node p, Node l, Node r) {
            name = n;
            parent = p;
            left = l;
            right = r;
        }
        
        public String getName() {
            return name;
        }
        public Node getParent() {
            return parent;
        }
        public Node getLeft() {
            return left;
        }
        public Node getRight() {
            return right;
        }
        
        public void setName(String n) {
            name = n;
        }
        public void setParent(Node p) {
            parent = p;
        }
        public void setLeft(Node l) {
            left = l;
        }
        public void setRight(Node r) {
            right = r;
        }
        
        public String toString() {
            return name;
        }
    }
    
    private Node root = null;
    private int size = 0;
    
    public LinkedBinaryFamilyTree() {}
    
    public Node getRoot() {
        return root;
    }
    public int getSize() {
        return size;
    }
    public Boolean isEmpty() {
        return size == 0;
    }
    
    public void addRoot(String n) {
        if(isEmpty()) {
            root = new Node(n, null, null, null);
            size++;
        } else {
            System.out.println("Tree is not empty");
        }
    }
    
    public void addLeft(Node p, String n) {
        if(p.getLeft() == null) {
            Node newnode = new Node(n, p, null, null);
            p.setLeft(newnode);
            size++;
        } else {
            System.out.println("Node already has left child.");
        }
    }
    
    public void addRight(Node p, String n) {
        if(p.getRight()==null) {
            Node newnode = new Node(n, p, null, null);
            p.setRight(newnode);
            size++;
        } else {
            System.out.println("Node already has right child.");
        }
    }
    
    public void addChild(Node p, String n) {
        if(p.getLeft()==null) {
            addLeft(p, n);
        } else {
            Node current = p.getLeft();
            while(current.getRight() != null) {
                current = current.getRight();
            }
            addRight(current, n);
        }
    }
    
    public Node findNode(Node node, String name) {
        if(node==null)
            return null;
        if(node.getName() == name)
            return node;
        Node leftResult = findNode(node.getLeft(), name);
        if(leftResult != null)
            return leftResult;
        Node rightResult = findNode(node.getRight(), name);
        if(rightResult != null)
            return rightResult;
        return null;
    }
    
    public void addChildOf(String parentName, String childName) {
        Node parentNode = findNode(root, parentName);
        if(parentNode != null) {
            addChild(parentNode, childName);
        } else {
            System.out.println("Parent not found.");
        }
    }
    
    private String constructIndent(int numIndents) {
        String indentation = "";
        while(numIndents>0) {
            indentation += "|\t";
            numIndents--;
        }
        return indentation;
    }
    
    private void makeTree(Node start, int treeLevel) {
        String indentation = constructIndent(treeLevel);
        System.out.println(indentation + start.getName());
        Node child = start.getLeft();
        if(child != null) {
            makeTree(child, treeLevel+1);
            while(child.getRight() != null) {
                child = child.getRight();
                makeTree(child, treeLevel+1);
            }
        }
    }
    
    public void printTree() {
        makeTree(root, 0);
    }
    
}
