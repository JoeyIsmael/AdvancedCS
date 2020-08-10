import java.io.Serializable;
public class BinarySearchTree<E extends Comparable<E>> implements Serializable{
   private Node<E> root;
   private E currentData;
   int count;
 
   public BinarySearchTree(){
       root = null;
       count = 0;
   }
   public void add(E data){
      count = 1;
       if(root == null){
           root = new Node<E>(data);
       }else{
           add(data,root);
       }
   }
   public void add(E data, Node<E> current){
       if(contains(data)){
        return;
       }if(current.get().compareTo(data)>0){
           if(current.getLeft() == null){
              Node<E> newNode = new Node<E>(data);
              current.setLeft(newNode);
              newNode.setParent(current);
           }else{
               add(data, current.getLeft());
               count++;
           }
       } else if(current.get().compareTo(data)<0){
           if(current.getRight() == null){
              Node<E> newNode = new Node<E>(data);
              current.setRight(newNode);
              newNode.setParent(current);
           }else{
               add(data, current.getRight());
               count++;
           }
       }
   }

   public int getCount(){
    return count;
   }
 
   public String toString(){
       return inOrderString(root);
   }
 
   private String inOrderString(Node<E> current){
      
       if(current !=null){
           String out = "";
           out += inOrderString(current.getLeft());
           out += current.get()+ "\n";
           out += inOrderString(current.getRight());
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

   public E get(E data){
      count = 1;
      if(contains2(data, root)){
        E got = get2(data, root);
        return got;
      } else {
        return null;
      }
  
      
   }

   public E get2(E data, Node<E> current){
      if(data.compareTo(current.get()) == 0){
        return current.get();
      } else if(data.compareTo(current.get()) < 0){
        count++;
        return get2(data, current.getLeft());
      } else if(data.compareTo(current.get()) > 0){
        count++;
        return get2(data, current.getRight());
      } else{
        return null;
      }
   }
   private boolean contains(E data, Node<E> current){
       if(current.get().equals(data)){
        return true;
       } else if(current.get().compareTo(data)>0){
           if(current.getLeft() != null){
               if(current.getLeft().get() == data){
                   return true;
               }else{
                   return contains(data, current.getLeft());
               }
           }
       } else if(current.get().compareTo(data)<0){
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
   private boolean contains2(E data, Node<E> current){
       if(current.get().compareTo(data) == 0){
        return true;
       } else if(current.get().compareTo(data)>0){
           if(current.getLeft() != null){
               if(current.getLeft().get() == data){
                   return true;
               }else{
                   return contains2(data, current.getLeft());
               }
           }
       } else if(current.get().compareTo(data)<0){
           if(current.getRight() != null){
               if(current.getRight().get() == data){
                   return true;
               }else{
                   return contains2(data, current.getRight());
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
 
 
 
  
}