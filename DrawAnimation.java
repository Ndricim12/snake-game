import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**DrawAnimation draws the complete game and runs it */
public class DrawAnimation extends JPanel implements KeyListener, MouseListener{
   private DrawBox box;
   private Box kutia;
   private int key_code;
   private int x_velocity;
   private int y_velocity;
   private int radius;
   private int x_random;
   private int y_random;
   private Random rand;
   private int count;
   private int x;
   private int y;
   private int size;
   private String direction="";
   private int mouse_x;
   private int mouse_y;
   private int score;
   private int high_score;
   private boolean game_start=true;
   private boolean game_over=false;
   private boolean play_again=false;
   boolean loop;
   private ArrayList<Integer> x_positions=new ArrayList<>();
   private ArrayList<Integer> y_positions=new ArrayList<>();
   /**Constructor DrawAnimation draws the frame
     *@param n - the Box class
     *@param b - the DrawBox class
     *@param s - the size
     *@param x_init - the initial x position
     *@param y_init - the initial y position
     *@param r - the radius */
   public DrawAnimation(Box n,DrawBox b,int s,int x_init, int y_init,int r){
      x=x_init;
      y=y_init;
      size=s;
      count=1;
      score=0;
      
      x_positions.add(x);
      y_positions.add(y);
      box=b;
      radius=r;
      loop=true;
      kutia=n;
      rand=new Random();
      x_random=rand.nextInt(37);//generates a random number
      y_random=rand.nextInt(37);//between 0-37 not including 37
      JFrame korniza=new JFrame();
      korniza.getContentPane().add(this);//adds the panel to the frame
      korniza.setTitle("Snake");
      korniza.setSize(size+18,size+42);
      korniza.setVisible(true);
 korniza.setLocation(350,50);
      korniza.addKeyListener(this);//adds the KeyListener to the frame
      korniza.addMouseListener(this);//adds the MouseListener to the frame
   }
   /**paintComponent draws the panel */
   public void paintComponent(Graphics g){
        //if its the start of the game this menu is displayed
        if(game_start){
        g.setColor(Color.black);
        g.fillRect(2,2,size,size);
        g.setColor(Color.white);
        Font string_font = new Font( "Algerian", Font.PLAIN, 30);
        g.setFont(string_font);
        g.drawString("PLAY", 235,300);
        g.drawString("THE SNAKE GAME",155,200);
        g.drawString("QUIT",238,380);
        }
       //if the game isn't at the start or at the end this is displayed   
       if(!game_over && !game_start){ 
         box.paint(g);
         g.setColor(Color.green);
         g.fillRect(x_positions.get(0),y_positions.get(0),radius,radius);
         for(int i=1; i<x_positions.size(); i++){
            g.drawRect(x_positions.get(i),y_positions.get(i),radius,radius);
         }
         g.setColor(Color.red);
         g.fillRect(x_random*16,y_random*16,radius,radius);
       }
       //if the game is over and you didnt press play again this is displayed
       if(game_over && !play_again){
         Font string_font1 = new Font( "Algerian", Font.PLAIN, 30);
         g.setFont(string_font1);
         g.drawString("PLAY AGAIN", 195,260);
         g.drawString("GAME OVER",200,100);  
         g.drawString("QUIT",253,350);
         g.drawString("YOUR SCORE: "+score,10,480);
         g.drawString("HIGH SCORE: "+high_score,325,480);
       }
   }
   /**keyPressed is activated when keyboard keys are pressed */
   public void keyPressed(KeyEvent e){
         key_code=e.getKeyCode();//gets the integer that represents the key typed
         if(key_code==KeyEvent.VK_UP){
            if(!direction.equals("down")){
               direction = "up";
               x_velocity=0;
               y_velocity=-radius;
            }
         }
          else if(key_code==KeyEvent.VK_DOWN){
            if(!direction.equals("up")){
               direction="down";
               x_velocity=0;
               y_velocity=radius;
            }
          }
          else if(key_code==KeyEvent.VK_RIGHT){
            if(!direction.equals("left")){
               direction="right";
               x_velocity=radius;
               y_velocity=0;
            }
         }
          else if(key_code==KeyEvent.VK_LEFT){
            if(!direction.equals("right")){
               direction="left";
               x_velocity=-radius;
               y_velocity=0;
            }
         }
      }
   
   public void keyTyped(KeyEvent e){}
   public void keyReleased(KeyEvent e){}
   
   /**mousePressed is activated when you click the frame */
   public void mousePressed(MouseEvent e){
      mouse_x=e.getX();//the x position where you pressed on the frame
      mouse_y=e.getY();//the y position where you pressed on the frame
      if(game_start){
         //if the PLAY button is pressed at the start of the game
         if((mouse_x>=235 && mouse_x<=315) && (mouse_y>=300 && mouse_y<=330)){
            game_start=false;
         }
         //if the QUIT button is pressed
         else if((mouse_x>=238 && mouse_x<=310) && (mouse_y>=380 && mouse_y<=410)){
            System.exit(0);
         }
  
      }
      if(game_over){
         //if the PLAY AGAIN button is pressed when the game is over
         if((mouse_x>=195 && mouse_x<=385) && (mouse_y>=260 && mouse_y<=290)){
            play_again=true;
            this.repaint();
            play_again=false;
            x_positions.clear();
            y_positions.clear();
            x_positions.add(x);
            y_positions.add(y);
            count=1;
            score=0;
            game_over=false;
         }
         //if the QUIT button is pressed
         else if((mouse_x>=253 && mouse_x<=325) && (mouse_y>=350 && mouse_y<=380)){
            System.exit(0);
         }
      }
      
   }
   
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
   
   
    /** delay adds a delay to the execution of the program*/   
    private void delay(int how_long){
      try {
         Thread.sleep(how_long);
      }
      catch (InterruptedException e) { }
   }
   /**runAnimation() makes the snake move */
   public void runAnimation(){
      int painting_delay=100;
      while(loop){
         delay(painting_delay);
         //each x position of the elements of the tail follows the x position before it
         for(int i=x_positions.size()-1; i>0; i--){
            x_positions.set(i,x_positions.get(i-1));
         }
         //the x position at the head of the snake
         x_positions.set(0,x_positions.get(0)+x_velocity);
         //if the snake touches the box the game is over and the
         //snake stops moving
         if(kutia.inHorizontalContact(x_positions.get(0))){
            x_velocity=0;
            y_velocity=0;
            game_over=true;
         }

         //each y position of the elements of the tail follows the y position before it
         for(int i=y_positions.size()-1; i>0; i--){
            y_positions.set(i,y_positions.get(i-1));
         }
         //the y position at the head of the snake
         y_positions.set(0,y_positions.get(0)+y_velocity);
         //if the snake touches the box the game is over and the
         //snake stops moving
         if(kutia.inVerticalContact(y_positions.get(0))){
            y_velocity=0;
            x_velocity=0;
            game_over=true;
         }
         //if the snake head touches the reward, the reward appears in a different place randomly
         if(((x_positions.get(0)>=x_random*16) && (x_positions.get(0)<=x_random*16+radius))  && ((y_positions.get(0)>=y_random*16) && (y_positions.get(0)<=y_random*16+radius))){
            x_random=rand.nextInt(37);
            y_random=rand.nextInt(37);
            detectReward();
               x_positions.add(x-(count*radius));//adds new element
               y_positions.add(y);               //to the list
            count=count+1;
            score++;
            //keeps track of the high score
            if(high_score<score){
               high_score=score;
            }  
         }
         //checks if the snake touches its tail and ends the game if so
         for(int i=1; i<x_positions.size(); i++){
            if(((x_positions.get(0)>=x_positions.get(i)) && (x_positions.get(0)<=x_positions.get(i)+radius-1)) && ((y_positions.get(0)>=y_positions.get(i)) && (y_positions.get(0)<=y_positions.get(i)+radius-1))){
               y_velocity=0;
               x_velocity=0;
               game_over=true;

            }
         }

         System.out.print(x_positions.get(0)+" , "+y_positions.get(0)+"  ");
          System.out.println(x_random*16+" , "+y_random*16);

         this.repaint();//repaints the panel

         
      }
   }
   /**detectReward makes the x and y positions of the reward appear on a non-occupied space */
   private void detectReward(){
      for(int i=0; i<x_positions.size(); i++){
             if(((x_random*16+1>=x_positions.get(i)) && (x_random*16+1<=x_positions.get(i)+radius-1)) && ((y_random*16+1>=y_positions.get(i)) && (y_random*16+1<=y_positions.get(i)+radius-1))){
                  x_random=rand.nextInt(37);
                  y_random=rand.nextInt(37);
                  detectReward();
               }
            }
   }
}
