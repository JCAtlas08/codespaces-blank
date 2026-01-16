import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

class BST {
    Node root;

    public BST()
    {
        root = null;
    }

    void insert(int key) {
        if (root == null) {
            root = new Node(key);
            return;
        }
        insert(new Node(key), root);
    }

    private void insert(Node newNode, Node node) {
        if (newNode.key < node.key) {
            if (node.left == null) {
                node.left = newNode;
            } else {
                insert(newNode, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = newNode;
            } else {
                insert(newNode, node.right);
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

    public boolean remove(int key){
        if(remove(key, root, null)== null){
            return false;
        }
        else{
            return true;
        }
    }

   private  Node remove(int key, Node node, Node above) {
        if (node == null) {
            return node;
        }

        if (node.key != key) {
            if (key < node.key) {
                remove(key, node.left, node);
            } else {
                remove(key, node.right, node);
            }

        } else {
            //no children
            if (node.left == null && node.right == null) {
                if(above == null){
                    root = null;
                } else if (above.right == node){
                    above.right = null;
                } else {
                    above.left = null;
                }
                return node;
            }

            // one child
            else if (node.left != null && node.right == null) {
                if (above == null) {
                    root = node.left;
                } else if (above.right == node) {
                    above.right = node.left;
                } else {
                    above.left = node.left;
                }
                return node;
            } else if (node.left == null && node.right != null) {
                if (above == null) {
                    root = node.right;
                } else if (above.right == node) {
                    above.right = node.right;
                } else {
                    above.left = node.right;
                }
                return node;
            }

            //two children 
            else {
                if (above == null) {
                    root = null; //to be implemented
                } else if (above.right == node) {
                    above.right = null; //to be implemented
                } else {
                    above.left = null; //to be implemented
                }
                return node;
            }
        }
        return node;
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
        System.out.println("Loop");
        if (nodes.size() <= depth) {
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

 

   // please use the following pieces of code to di lay your tree in a more easy to follow style (Note* you'll need to place the Trunk class in it's own file)
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
    private void rotateLeft(Node node, Node prev) {
        Node oldRoot = node;

        node = node.right;
        oldRoot.right = node.left;
        node.right = oldRoot;

        if (prev == null) {
            root = node;
        } else {
            if(prev.right == oldRoot){
                prev.right = node;
            } else {
                prev.left = node;
            }
        }
    }

    // rotates the tree such that the subRoot is replaced with it's left child with subRoot becoming the right child of the new subRoot. prev now points to the new subRoot.
    private void rotateRight(Node node, Node prev) {
        Node oldRoot = node;

        node = node.left;
        oldRoot.left = node.right;
        node.left = oldRoot;

        if (prev == null) {
            root = node;
        } else {
            if(prev.right == oldRoot){
                prev.right = node;
            } else {
                prev.left = node;
            }
        }
    }

    int getHeight() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        //return ((1 + height(node.left)) - (1 + height(node.right)));
        return 1 + (height(node.left) - height(node.right));
    }

    void balance() {
        balance(root);
    }

    private void balance(Node node) {
        if (node == null) {
            return;
        }
        if (height(node) != 0) {
            if (height(node) > 0) {
                balance(node.left);
                if (node.left != null) {
                    rotateLeft(node.left, node);
                }
            }
            if (height(node) < 0) {
                balance(node.right);
                if (node.right != null) {
                    rotateRight(node.right, node);
                }
            }
        }
    }
}