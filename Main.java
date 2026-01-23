import java.util.*;

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();

        for (int i = 0; i < 5; i++) {
            tree.insert(i);
            tree.printTree();
            System.out.println();
        }
        for (int i = -1; i > -5; i--) {
            tree.insert(i);
            tree.printTree();
            System.out.println();
        }
        

        System.out.println(tree.isBSTOrNot());
        tree.printTree();

        tree.remove(1);
        tree.printTree();
    }
}