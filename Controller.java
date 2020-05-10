public class Controller{
/**Controller runs the game */
   public static void main(String[] args){
      int size=592;
      int radius=16;
      Box box=new Box(size);
      DrawBox draw_box=new DrawBox(box);
      DrawAnimation draw_animation=new DrawAnimation(box,draw_box,size,1,1,radius);
      draw_animation.runAnimation();
   }
}
