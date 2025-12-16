import java.lang.reflect.Array;
import java.util.ArrayList;

class BST {
    Node root;

    public BST()
    {
        root = null;
    }

    void insert(int key, Node node) {
        Node newLeaf = new Node(key);
        if (root == null) {
            root = newLeaf;

        } else if (key < root.key) {
            if (root.left == null) {
                root.left = newLeaf;
            } else {
                insert(key, root.left);
            }
        } else {
            if (root.right == null) {
                root.right = newLeaf;
            } else {
                insert(key, root.right);
            }
        }
    }

    boolean search(int key, Node node) {
        if (node == null) {
            return false;
        } else if (node.key == key) {
            return true;
        } else if (key < node.key) {
            return search(key, node.left);
        } else {
            return search(key, node.right);
        }
    }

    Node searchNode(int key, Node node) {
        if (node.key == key) {
            return node;
        } else if (key < node.key) {
            return searchNode(key, node.left);
        } else {
            return searchNode(key, node.right);
        }
    }

    int remove(int key) {
        if (search(key, root)) {
            for (ArrayList<Node> nodeList : getNodesFromNode(root, 0, new ArrayList<ArrayList<Node>>())) {
                for (Node node : nodeList) {
                    if (node.key != key) {
                        insert(node.key, root);
                    }
                }
            }
            return key;
        } else {
            return -1;
        }
    }

    public String toString() {
        String nodes = "";

        for (ArrayList<Node> nodeList : getNodesFromNode(root, 0, new ArrayList<ArrayList<Node>>())) {
            for(Node node : nodeList)
                nodes += node.key;
            nodes += "\n";
        }       
        return nodes;
    }

    ArrayList<ArrayList<Node>> getNodesFromNode(Node node, int depth, ArrayList<ArrayList<Node>> nodes) { // preorder traversal
        if (nodes.size() < depth) {
            nodes.add(new ArrayList<Node>());
        }
        if (node != null) {
            nodes.get(depth).add(node);
            nodes.addAll(getNodesFromNode(node.left, depth++, nodes));
            nodes.addAll(getNodesFromNode(node.right, depth++, nodes));
        }
        return nodes;
    }

//Add the following functions to your BST
//Please use this code to verify your tree integrity
    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTOrNot(Node root, int minValue, int maxValue) {
        // check for root is not null or not
        if (root == null) {
            return true;
        }
        // check for current node value with left node value and right node value and recursively check for left sub tree and right sub tree
        if(root.key >= minValue && root.key <= maxValue && isBSTOrNot(root.left, minValue, root.key) && isBSTOrNot(root.right, root.key, maxValue)){
            return true;
        }
        return false;
    }

 

   // please use the following pieces of code to display your tree in a more easy to follow style (Note* you'll need to place the Trunk class in it's own file)
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public void printTree(){
        printTree(root, null, false);
    }

    private void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.key);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.left, trunk, false);
    }

    // rotates the tree such that the subRoot is replaced with it's right child with subRoot becoming the left child of the new subRoot. prev now points to the new subRoot.
    private void rotateLeft(Node subRoot, Node prev) {
        Node oldRoot;
        if (prev == null) {
            oldRoot = root;
            root = subRoot.right;
        } else if (prev.left == subRoot) {
            oldRoot = prev.left;
            prev.left = subRoot.right;
        } else {
            oldRoot = prev.right;
            prev.right = subRoot.right;
        }
        oldRoot.right = subRoot.right.left;
        subRoot.right.left = oldRoot;
    }

    // rotates the tree such that the subRoot is replaced with it's left child with subRoot becoming the right child of the new subRoot. prev now points to the new subRoot.
    private void rotateRight(Node subRoot, Node prev) {
        Node oldRoot;
        if (prev == null) {
            oldRoot = root;
            root = subRoot.left;
        } else if (prev.left == subRoot) {
            oldRoot = prev.left;
            prev.left = subRoot.left;
        } else {
            oldRoot = prev.right;
            prev.right = subRoot.left;
        }
        oldRoot.left = subRoot.left.right;
        subRoot.left.right = oldRoot;
    }

    private int height(Node node) {
        if (node == null) {
            return 1;
        }
        return (1 - height(node.left)) - (1 - height(node.right));
    }

    private void balance(Node node) {
        if (node == null) {
            return;
        }
        if (Math.abs(height(node)) <= 1) {
            balance(node.left);
            balance(node.right);
        }
        if (height(node) > 0) {
            rotateLeft(node.right, node);
            balance(node);
        }
        if (height(node) < 0) {
            rotateRight(node.left, node);
            balance(node);
        }
    }
}                                                                                                                                                                                                                               