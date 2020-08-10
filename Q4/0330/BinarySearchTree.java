import java.awt.*;
public class BinarySearchTree<E extends Comparable<E>> {
   private Node<E> root;
 
   public BinarySearchTree(){
       root = null;
   }
   public void add(E data){
       if(root == null){
           root = new Node<E>(data);
       }else{
           add(data,root);
       }
   }
   public void add(E data, Node<E> current){
       if(current.get().compareTo(data)>0){
           if(current.getLeft() == null){
               current.setLeft(new Node<E>(data));
           }else{
               add(data, current.getLeft());
           }
       }
       if(current.get().compareTo(data)<0){
           if(current.getRight() == null){
               current.setRight(new Node<E>(data));
           }else{
               add(data, current.getRight());
           }
       }
   }
 
   public String toString(){
       return inOrderString(root);
   }
 
   private String inOrderString(Node<E> current){
      
       if(current !=null){
           String out = "";
           out +=inOrderString(current.getLeft());
           out += current.get()+ ",";
           out += inOrderString(current.getRight());
           return out;
       }
       return "";
   }
   public String toStringPreOrder(){
       return printPreOrder(root);
   }
 
   private String printPreOrder(Node<E> current){
       if(current !=null){
           String out = "";
           out+=current.get()+",";
           out += printPreOrder(current.getLeft());
           out += printPreOrder(current.getRight());
          
          
           return out;
       }
       return "";
   }
   public boolean contains(E data){
       if(root != null){
           if(root.get().equals(data)){
               return true;
           }else{
               return contains(data, root);
           }
       }
       return false;
      
   }
   private boolean contains(E data, Node<E> current){
       if(current.get().compareTo(data)>0){
           if(current.getLeft() != null){
               if(current.getLeft().get() == data){
                   return true;
               }else{
                   return contains(data, current.getLeft());
               }
           }
       }
       if(current.get().compareTo(data)<0){
           if(current.getRight() != null){
               if(current.getRight().get() == data){
                   return true;
               }else{
                   return contains(data, current.getRight());
               }
           }
       }
       return false;
   }
   public void remove(E data){
       remove(data,root,null);
   }
   private void remove(E data, Node<E> node, Node<E> parent) {
       if (node == null) {
       } else if (data.compareTo(node.get()) > 0) {
           remove(data, node.getRight(), node);
       } else if (data.compareTo(node.get()) < 0) {
           remove(data, node.getLeft(), node);
       } else if (data.compareTo(node.get()) == 0) {
           if (node.getLeft() == null && node.getRight() == null) { // NO CHILDREN
               if (parent == null) { //root
                   node = null;
               } else {
                   if (parent.getRight() == node) {
                       parent.setRight(null);
                   } else if (parent.getLeft() == node ){
                       parent.setLeft(null);
                   }
               }
           } else if (node.getLeft() == null && node.getRight() != null) { // ONLY RIGHT CHILD
               if (parent == null) { //root
                   root = root.getRight();
                   node = null; //delete the current node;
               } else {
                   if (parent.getRight() == node) {
                       parent.setRight(node.getRight());
                   } else if (parent.getLeft() == node ){
                       parent.setLeft(node.getRight());
                   }
               }
           } else if (node.getLeft() != null && node.getRight() == null) { // ONLY LEFT CHILD
               if (parent == null) { //root
                   root = root.getLeft();
                   node = null; //delete the current node
               } else {
                   if (parent.getRight() == node) {
                       parent.setRight(node.getLeft());
                   } else if (parent.getLeft() == node ){
                       parent.setLeft(node.getLeft());
                   }
               }
           } else if (node.getLeft() != null && node.getRight() != null) { // BOTH CHILDREN EXIST
               if (parent == null) { //root
                   Node<E> lowest = lowest(node.getRight(), node); 
                   lowest.setRight(root.getRight());
                   lowest.setLeft(root.getLeft());
                   root = lowest;
                   node = null; //delete the current node;
               } else {
                   Node<E> lowest = lowest(node.getRight(), node); //get the lowest node to the right
                   lowest.setRight(node.getRight());
                   lowest.setLeft(node.getLeft());
                  
                   if (parent.getRight() == node) {
                       parent.setRight(lowest);
                   } else if (parent.getLeft() == node ){
                       parent.setLeft(lowest);
                   }
               }
           } else {
               System.out.println("");
           }
          
       }
   }
   private Node<E> lowest(Node<E> node, Node<E> parent) {
       if (node.getLeft() == null) {
          
           if (parent.getRight() == node) {
               parent.setRight(null);
           } else if (parent.getLeft() == node) {
               parent.setLeft(null);
           }
          
           return node;
       } else {
           return lowest(node.getLeft(), node);
       }
   }
   public void drawTree(Graphics g, Point location) {
    drawTree(g, location, root, 0);
   }   

    public void drawTree(Graphics g, Point location, Node<E> node, int level) {
        if (node == null) return;

        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        g.drawString(node.get().toString(), location.x - 10, location.y + 5);

        Point left = (Point) location.clone();
        left.translate(-(200 - level *100), 100);
        Point right = (Point) location.clone();
        right.translate(200 - level * 100, 100);
        drawTree(g, left, node.getLeft(), level + 1);
        drawTree(g, right, node.getRight(), level + 1);
    }
}
