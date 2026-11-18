
import java.util.Scanner;

class StudentRecordBST {

    // Node class
    static class Node {
        int rollNo;
        String name;
        String course;
        Node left, right;

        Node(int rollNo, String name, String course) {
            this.rollNo = rollNo;
            this.name = name;
            this.course = course;
            left = right = null;
        }
    }

    static Node root = null;

    // Insert student record
    static Node insert(Node root, int rollNo, String name, String course) {

        if (root == null) {
            return new Node(rollNo, name, course);
        }

        if (rollNo < root.rollNo) {
            root.left = insert(root.left, rollNo, name, course);
        }
        else if (rollNo > root.rollNo) {
            root.right = insert(root.right, rollNo, name, course);
        }
        else {
            System.out.println("Roll number already exists!");
        }

        return root;
    }

    // Search student record
    static void search(Node root, int rollNo) {

        if (root == null) {
            System.out.println("Student record not found!");
            return;
        }

        if (rollNo == root.rollNo) {
            System.out.println("\nStudent Found!");
            System.out.println("Roll Number: " + root.rollNo);
            System.out.println("Name: " + root.name);
            System.out.println("Course: " + root.course);
        }
        else if (rollNo < root.rollNo) {
            search(root.left, rollNo);
        }
        else {
            search(root.right, rollNo);
        }
    }

    // Display student records in sorted order
    static void display(Node root) {

        if (root != null) {
            display(root.left);

            System.out.println("Roll No: " + root.rollNo
                    + " | Name: " + root.name
                    + " | Course: " + root.course);

            display(root.right);
        }
    }

    
// Display BST in tree structure
static void displayTree(Node root) {

    if (root == null) {
        System.out.println("Tree is empty!");
        return;
    }

    System.out.println(root.rollNo + " - " + root.name);

    displayTree(root.left, "", true);
    displayTree(root.right, "", false);
}

//  method to print tree
static void displayTree(Node root, String prefix, boolean isLeft) {

    if (root == null) {
        return;
    }

    System.out.println(prefix +
            (isLeft ? "|-- L: " : "\\-- R: ") +
            root.rollNo + " - " + root.name);

    String newPrefix = prefix + (isLeft ? "|   " : "    ");

    displayTree(root.left, newPrefix, true);
    displayTree(root.right, newPrefix, false);
}


    // Find minimum value node
    static Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Delete student record
    static Node delete(Node root, int rollNo) {

        if (root == null) {
            return null;
        }

        if (rollNo < root.rollNo) {
            root.left = delete(root.left, rollNo);
        }
        else if (rollNo > root.rollNo) {
            root.right = delete(root.right, rollNo);
        }
        else {

            // Node has no left child
            if (root.left == null) {
                return root.right;
            }

            // Node has no right child
            else if (root.right == null) {
                return root.left;
            }

            // Node has two children
            Node temp = findMin(root.right);

            root.rollNo = temp.rollNo;
            root.name = temp.name;
            root.course = temp.course;

            root.right = delete(root.right, temp.rollNo);
        }

        return root;
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice, rollNo;
        String name, course;

        do {
              System.out.println("1. Insert Student Record");
             System.out.println("2. Search Student Record");
             System.out.println("3. Display All Records (Sorted)");
             System.out.println("4. Delete Student Record");
             System.out.println("5. Display BST Structure");
             System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Roll Number: ");
                    rollNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    name = sc.nextLine();

                    System.out.print("Enter Course: ");
                    course = sc.nextLine();

                    root = insert(root, rollNo, name, course);
                    System.out.println("Student record inserted!");
                    break;

                case 2:
                    System.out.print("Enter Roll Number to search: ");
                    rollNo = sc.nextInt();
                    search(root, rollNo);
                    break;

                case 3:
                    if (root == null) {
                        System.out.println("No student records available!");
                    } else {
                        System.out.println("\nStudent Records:");
                        display(root);
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll Number to delete: ");
                    rollNo = sc.nextInt();

                    if (root == null) {
                        System.out.println("No records available!");
                    } else {
                        root = delete(root, rollNo);
                        System.out.println("Delete operation completed.");
                    }
                    break;

                case 5:
                     System.out.println("\nBinary Search Tree:");
                     displayTree(root);
                    break;

                case 6:
                    System.out.println("Exiting Student Record System...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
