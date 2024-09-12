//////////////// BinarySearchTree.java  //////////////////////////
//
// Title:    BinarySearchTree.java
// Course:   CS 400 Fall 2024
//
// Author:   AARYA SAXENA
// Email:    asaxena26@wisc.edu
// Lecturer: Florian Heimerl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// N/A
//
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Canvas, Piazza
//
//
//
///////////////////////////////////////////////////////////////////////////////

import java.lang.Comparable;

/**
 * Binary Search Tree that implements the SortedComparison interface
 * Includes recursive helpers for insertion, lookup, and size.
 * @param <T> comparable/generic type
 */
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T>{

  protected BSTNode<T> root; //root node

  /**
   *  default constructor initializes a BST object with root node = null
   */
  public BinarySearchTree() {
    this.root = null;
  }

  /**
   * Check whether data is stored in the tree.
   *
   * @param data the value to check for in the collection
   * @return true if the collection contains data one or more times, and false otherwise
   */
  public boolean contains(Comparable<T> data) {
    return containsHelper(root, data);
  }

  /**
   * recursive helper that recursively combs through tree to look for value.
   * @param subTree subtree
   * @param data data to lookup
   * @return boolean if data is in structure
   */
  private boolean containsHelper(BSTNode<T> subTree, Comparable<T> data) {
    if (subTree == null){
      return false; //base case
    }
    int comparison = data.compareTo(subTree.getData());
    if (comparison == 0){
      return true;
    }
    else if (comparison < 0){
      return containsHelper(subTree.getLeft(), data);
    }
    else{
      return containsHelper(subTree.getRight(), data);
    }

  }

  /**
   * Inserts a new data value into the sorted collection.
   *
   * @param data the new value being inserted
   * @throws NullPointerException if data argument is null, we do not allow null values to be stored
   *                              within a SortedCollection
   */
  @Override
  public void insert(T data) throws NullPointerException {
    if (data == null) {
      throw new NullPointerException("we do not allow null values");
    }

    BSTNode<T> newNode = new BSTNode<>(data);

    if (root == null){
      root = newNode;
    }
    else {
      insertHelper(newNode, root);
    }
  }

  /**
   * Counts the number of values in the collection, with each duplicate value being counted
   * separately within the value returned.
   *
   * @return the number of values in the collection, including duplicates
   */
  public int size() {
    return sizeHelper(root);
  }

  /**
   * sizeHelper recursively finds the size of all subtrees then adds them up + 1 to account for
   * root node
   * @param subTree subtree
   * @return int size
   */
  private int sizeHelper(BSTNode<T> subTree){
    if (subTree == null) {
      return 0;
    }
    else{
      return 1 + sizeHelper(subTree.getLeft()) +  sizeHelper(subTree.getRight());
    }
  }

  /**
   * Checks if the collection is empty.
   *
   * @return true if the collection contains 0 values, false otherwise
   */
  public boolean isEmpty() {
   return root == null;
  }

  /**
   * Removes all values and duplicates from the collection.
   */
  public void clear() {
    root = null; // clear root reference therefore clearing all subtrees
  }


  /**
   * Performs the naive binary search tree insert algorithm to recursively insert the provided
   * newNode (which has already been initialized with a data value) into the provided tree/subtree.
   * When the provided subtree is null, this method does nothing.
   */
  protected void insertHelper(BSTNode<T> newNode, BSTNode<T> subtree) {

    if (subtree == null) {
      return; // base case
    }
    if (newNode.getData().compareTo(subtree.getData()) <= 0){
      if (subtree.getLeft() == null) {
        subtree.setLeft(newNode);
      }
      else {
        insertHelper(newNode, subtree.getLeft());
      }

    }
    if (newNode.getData().compareTo(subtree.getData()) > 0){
      if (subtree.getRight() == null) {
        subtree.setRight(newNode);
      }
      else {
        insertHelper(newNode, subtree.getRight());
      }

    }

  }

  /**
   * test with an Integer BST, tests that tree structure is correct, and that all methods behave
   * properly.
   * @return boolean if all tests pass
   */
  private static boolean testIntTree() {
    BinarySearchTree<Integer> integerTree = new BinarySearchTree<>();

    boolean isEmpty = integerTree.isEmpty();

    integerTree.insert(5);
    integerTree.insert(3);
    integerTree.insert(10);
    integerTree.insert(15);
    integerTree.insert(10);
    integerTree.insert(12);

    boolean contains = integerTree.contains(5);
    boolean containsFail = integerTree.contains(101);
    boolean size = (integerTree.size() == 6);
    String expectedLevel = "[ 5, 3, 10, 10, 15, 12 ]";
    String actualLevel = integerTree.root.toLevelOrderString();
    String expectedInOrder = "[ 3, 5, 10, 10, 12, 15 ]";
    String actualInOrder = integerTree.root.toInOrderString();
    boolean level = expectedLevel.equals(actualLevel);
    boolean inOrder = expectedInOrder.equals(actualInOrder);

    boolean rootLeftChild = integerTree.root.getLeft().getData().equals(3);
    boolean rootRightInterior = integerTree.root.getRight().getRight().getData().equals(15);
    boolean rootLeftInterior = integerTree.root.getRight().getLeft().getData().equals(10);
    boolean rootRightLeftInterior = integerTree.root.getRight().getRight().getLeft().getData().
        equals(12);

    boolean clear = false;
    integerTree.clear();
    if (integerTree.root == null){
      clear = true;
    }
    return (isEmpty && contains && !containsFail && size && level && inOrder && clear &&
        rootLeftChild && rootRightInterior && rootLeftInterior && rootRightLeftInterior);
  }
  /**
   * test with a String BST, tests that tree structure is correct, and that all methods behave
   * properly.
   * @return boolean if all tests pass
   */
  private static boolean testStringTree() {
    BinarySearchTree<String> stringTree = new BinarySearchTree<>();

    boolean isEmpty = stringTree.isEmpty();

    stringTree.insert("c");
    stringTree.insert("a");
    stringTree.insert("b");
    stringTree.insert("z");
    stringTree.insert("d");
    stringTree.insert("a");
    stringTree.insert("e");
    stringTree.insert("f");

    boolean contains = stringTree.contains("a");

    boolean containsFail = stringTree.contains("x");
    boolean size = (stringTree.size() == 8);
    String expectedLevel = "[ c, a, z, a, b, d, e, f ]";
    String actualLevel = stringTree.root.toLevelOrderString();

    String expectedInOrder = "[ a, a, b, c, d, e, f, z ]";
    String actualInOrder = stringTree.root.toInOrderString();
    boolean level = expectedLevel.equals(actualLevel);
    boolean inOrder = expectedInOrder.equals(actualInOrder);

    boolean rootLeftChild = stringTree.root.getLeft().getData().equals("a");
    boolean rootRightInterior = stringTree.root.getRight().getRight() == null;
    boolean rootLeftInterior = stringTree.root.getLeft().getLeft().getData().equals("a");

    boolean clear = false;
    stringTree.clear();
    if (stringTree.root == null){
      clear = true;
    }
    return (isEmpty && contains && !containsFail && size && level && inOrder && clear &&
        rootLeftChild && rootRightInterior && rootLeftInterior);
  }

  /**
   * test with an integer BST that only has right children, tests that tree structure is correct,
   * and that all methods behave properly.
   * @return boolean if all tests pass
   */
  private static boolean testWeirdIntTree() {
    BinarySearchTree<Integer> weirdIntegerTree = new BinarySearchTree<>();

    boolean isEmpty = weirdIntegerTree.isEmpty();

    weirdIntegerTree.insert(3);
    weirdIntegerTree.insert(5);
    weirdIntegerTree.insert(10);
    weirdIntegerTree.insert(15);
    weirdIntegerTree.insert(12);
    weirdIntegerTree.insert(100);
    weirdIntegerTree.insert(10);

    boolean root = weirdIntegerTree.root.getData().equals(3);


    boolean contains = weirdIntegerTree.contains(5);
    boolean containsFail = weirdIntegerTree.contains(101);
    boolean size = (weirdIntegerTree.size() == 7);
    String expectedLevel = "[ 3, 5, 10, 10, 15, 12, 100 ]";
    String actualLevel = weirdIntegerTree.root.toLevelOrderString();
    String expectedInOrder = "[ 3, 5, 10, 10, 12, 15, 100 ]";
    String actualInOrder = weirdIntegerTree.root.toInOrderString();
    boolean level = expectedLevel.equals(actualLevel);
    boolean inOrder = expectedInOrder.equals(actualInOrder);

    boolean rootLeftChild = weirdIntegerTree.root.getLeft() ==  null;
    boolean rootRightInterior = weirdIntegerTree.root.getRight().getRight().getData().equals(10);
    boolean rootLeftInterior = weirdIntegerTree.root.getLeft() == null;


    boolean clear = false;
    weirdIntegerTree.clear();
    if (weirdIntegerTree.root == null){
      clear = true;
    }
    return (isEmpty && contains && !containsFail && size && level && inOrder && clear &&
        rootLeftChild && rootRightInterior && rootLeftInterior && root);
  }

  /**
   * main method to test BST class.
   * @param args default argument
   */
  public static void main(String[] args) {
    System.out.println("test 1: " + testIntTree());
    System.out.println("test 2: " + testStringTree());
    System.out.println("test 3: " + testWeirdIntTree());
  }
}
