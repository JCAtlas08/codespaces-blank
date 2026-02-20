public class main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(100);
        hashTable.put("a", 1);
        hashTable.put("b", 2);
        hashTable.put("c", 3);
        hashTable.put("d", 4);
        hashTable.put("e", 5);
        hashTable.put("f", 6);
        hashTable.put("g", 7);
        hashTable.put("h", 8);
        hashTable.put("i", 9);
        hashTable.put("j", 10);
        hashTable.put("k", 11);
        hashTable.put("l", 12);
        hashTable.put("m", 13);
        hashTable.put("n", 14);
        hashTable.put("o", 15);
        hashTable.put("p", 16);
        hashTable.put("q", 17);
        hashTable.put("r", 18);
        hashTable.put("s", 19);
        hashTable.put("t", 20);
        hashTable.put("u", 21);
        hashTable.put("v", 22);
        hashTable.put("w", 23);
        hashTable.put("x", 24);
        hashTable.put("y", 25);
        hashTable.put("z", 26);

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (char letter : alphabet.toCharArray()) {
            System.out.println("Value for '" + letter + "': " + hashTable.get(letter + ""));
        }
        //System.out.println("Value for 'apple': " + hashTable.get("apple"));

        System.out.println("\nAll keys in the HashTable:");
        hashTable.print();

        System.out.println("\nRemoving 'b'...");
        hashTable.remove("b");

        System.out.println("\nAll keys in the HashTable after removal:");
        hashTable.print();
    }
}