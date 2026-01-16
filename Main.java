import java.util.*;

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();

        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        for (int i = -1; i > -10; i--) {
            tree.insert(i);
        }
        

        System.out.println(tree.isBSTOrNot());
        tree.printTree();
        System.out.println(tree.getHeight());

        tree.remove(1);
        tree.printTree();
        System.out.println(tree.getHeight());

        tree.balance();
        //System.out.println(tree.isBSTOrNot());
        //tree.printTree();
    }
}