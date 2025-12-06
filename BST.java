import java.lang.reflect.Array;

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
            return search(key, node.left);
        } else {
            return search(key, node.right);
        }
        return node;
    }

    int remove(int key) {
        if (search(key, node)) {
            ArrayList<Node> nodes = getNodesFromNode(searchNode(key, node));
            for (Node n : nodes) {
                if (n.key != key) {
                    insert(n.key, root);
                }
            }
            return key;
        } else {
            return -1;
        }
    }

    public String toString(){
        String nodes = "";
        for (Node node : getNodesFromNode(root)) {
            nodes += node.key;
        }
        return nodes;
    }

    ArrayList<Node> getNodesFromNode(Node node){
        ArrayList<Node> nodes = new ArrayList<>();
        if (node != null) {
            nodes.add(node);
            nodes.addAll(getNodesFromNode(node.left));
            nodes.addAll(getNodesFromNode(node.right));
        }
        return nodes;
    }

}