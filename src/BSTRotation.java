public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree<T>{

  protected BSTNode<T> root;

  public BSTRotation(){
    root = super.root;
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree.
   * When the provided child is a left child of the provided parent, this
   * method will perform a right rotation. When the provided child is a right
   * child of the provided parent, this method will perform a left rotation.
   * When the provided nodes are not related in one of these ways, this
   * method will either throw a NullPointerException: when either reference is
   * null, or otherwise will throw an IllegalArgumentException.
   *
   * @param child is the node being rotated from child to parent position
   * @param parent is the node being rotated from parent to child position
   * @throws NullPointerException when either passed argument is null
   * @throws IllegalArgumentException when the provided child and parent
   *     nodes are not initially (pre-rotation) related that way
   */
  protected void rotate(BSTNode<T> child, BSTNode<T> parent)
      throws NullPointerException, IllegalArgumentException {
    // TODO: Implement this method.
    if (child == null || parent == null){
      throw new NullPointerException ("child or parent can't be null!");
    }
    if (!((parent.getRight().equals(child) || parent.getLeft().equals(child)))) {
      throw new IllegalArgumentException("nodes must have parent/child relation!");
    }


  }

  public static void main(String[] args) {
    BSTRotation<Integer> test = new BSTRotation<>();
    test.insert(10);
    test.insert(15);
    System.out.println(test.root.toInOrderString());
  }
}
