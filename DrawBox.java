import java.awt.*;
/**DrawBox draws the box*/
public class DrawBox{
   private Box box;
   /**Constructor DrawBox signes the value of box to parameter
     *@param b is the box to be drawn */
   public DrawBox(Box b){
      box=b;
   }
   /**paint draws the box*/
   public void paint(Graphics g){
      int size=box.sizeOf();
      g.setColor(Color.black);
      g.fillRect(2,2,size,size);
      g.setColor(Color.black);
      g.drawRect(2,2,size,size);
   }
   
}
