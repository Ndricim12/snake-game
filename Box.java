/**Box sets the boundaries of the box where the snake moves*/
public class Box{
   private int box_size;
   /**Constructor Box signes the value of the box_size to the parameter
     *@param size is the boxes size */
   public Box(int size){
      box_size=size;
   }
   /**inHorizontalContact() returns true if the snake touches
     *the left or right box side, returns false otherwise */
   public boolean inHorizontalContact(int x_position){
      return (x_position<=0 || x_position>=box_size);
   }
   /**inVerticalContact() returns true if the snake touches
     *the up or down box side, returns false otherwise */
   public boolean inVerticalContact(int y_position){
      return (y_position<=0 || y_position>=box_size);
   }
   /**sizeOf() returns the size of the box*/
   public int sizeOf(){
      return box_size;
   }
}
