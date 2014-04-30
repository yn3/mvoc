package processing.test.mountain_view_observation_center_revamp_w;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Stack; 
import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Mountain_View_Observation_Center_revamp_w extends PApplet {


/*


  |||||||                    |||||||
  ||| |||                    |||||||
  |||||||                    |||||||
                               
  |||||||      |             |||||||
  |af||||            ||      |||| ||
  |||||||                    |||||||  
  |||||||   MOUNTAIN VIEW    |||||||
  ||||||| OBSERVATION CENTER |||||||    
  |||||||                    |||||||    
  |||||||                    |||||||    

  |||||||          |         || ||||
  |||||||     |              |||||||
  |||||||                    |||||||    
  |||||||                    |||||||  


                                     */






int fg_color = color(PApplet.parseInt(random(5,55)),0,PApplet.parseInt(random(44,88)));
int bg_color = 0xffDCDCDC;
int black_color = 0xff000000;
int kill_color = fg_color;
int kill_t_color = fg_color;
int h1fill_color,h1fill_t_color,h2fill_color,h2fill_t_color,
      d1_color,d1_t_color,
      d2_color,d2_t_color,d3_color,d3_t_color,
      rnd_color,rnd_t_color;


final int FALSE = 0, TRUE = 1, MAYBE = -1;





int sw = 1, cnt = 0, speed = 2000,
    speedlimit = 4500, md = 0, cutoffh,cutoffw, rv1, 
    colrow=7, gridmin = 8, gridmax = 9;

float ran2 = random(1.6f,2.6f);
float theta = 0;
boolean fw = false, g_distro = false;



ControlP5 cp5;

Hori1Fill h1Fill;  
Hori2Fill h2Fill; 
Dia1Fill d1Fill;
Dia2Fill d2Fill;  
Dia3Fill d3Fill; 
KillFill kFill;
RndFill rFill;

public void setup() {
  
 
  cutoffh = PApplet.parseInt(displayHeight/10);
  cutoffw = PApplet.parseInt(displayWidth/5);
 
  reset();

  



  //* android setup *//
  orientation(LANDSCAPE);
  
  //* gui *//
  guimods();
  
  

}


public void draw() {

speeds();
colr();  
select();


}


public int get(int[] v) {
  return get(v[0], v[1]); 
}

public void set(int[] v, int c) {
  set(v[0], v[1], c);
}



public void colr(){

  
cnt++;
cnt=cnt%235;

  h1fill_color = color(PApplet.parseInt(random(20,cnt+20)),PApplet.parseInt(random(20,cnt+20)),cnt+20);
  h1fill_t_color = color(PApplet.parseInt(random(20,cnt+20)),PApplet.parseInt(random(20,cnt+20)),cnt+20);
  h2fill_color = color(30,PApplet.parseInt(random(30,cnt-130)),PApplet.parseInt(random(30,cnt+20)));
  h2fill_t_color = color(30,PApplet.parseInt(random(30,cnt-130)),PApplet.parseInt(random(30,cnt+20)));
  d1_color = color(PApplet.parseInt(random(20,cnt+20)),cnt+20,PApplet.parseInt(random(20,cnt+20)));
  d1_t_color = color(PApplet.parseInt(random(20,cnt+20)),cnt+20,PApplet.parseInt(random(20,cnt+20)));
  d2_color = color(cnt+20,PApplet.parseInt(random(20,cnt+20)),120);
  d2_t_color = color(PApplet.parseInt(random(20,cnt+20)),255,PApplet.parseInt(random(20,cnt+20)));
  
  d3_color = color(PApplet.parseInt(random(0,cnt/5+1)),cnt+20,PApplet.parseInt(random(120,cnt+20)));
  d3_t_color = color(PApplet.parseInt(random(0,cnt/5+1)),cnt/1.2f,cnt/1.2f);
  //d3_color = color(int(random(20,255)),cnt+20,int(random(20,cnt+20)));
  //d3_t_color = color(int(random(20,cnt+20)),cnt+20,int(random(20,cnt+20)));
  rnd_color = color(0,25,17);
  rnd_t_color = color(PApplet.parseInt(random(20,cnt+20)),cnt+20,PApplet.parseInt(random(20,cnt+20)));

}

/*
||||||||||||
 HORIZONTAL ONE

*/


class Hori1Fill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};

  public void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  public int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[4][];
    nbs[0] = new int[] { x, y + rv1 };
    nbs[1] = new int[] { x + 1, y };
    nbs[2] = new int[] { x - 1, y };
    nbs[3] = new int[] { x, y + 2 };
    return nbs;
  }
  

  public int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  public int hasNext() {
    
    int[][] nbs = getNeighbors();

    for (int i = 0; i < 4; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) ==  d1_t_color) {
        trace.push(nb);
        return TRUE;
      }
    }
    
  
    if(!trace.empty()) {
      pos = trace.pop();
      return MAYBE;
    }
    
 
    return FALSE;
    
  }
  

}


/*
||||||||||||
 HORIZONTAL TWO

*/


class Hori2Fill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};

  public void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  public int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[5][];
    nbs[0] = new int[] { x, y + PApplet.parseInt(random(3,4)) };
    nbs[1] = new int[] { x , y +1 };
    nbs[2] = new int[] { x - 1, y };
    nbs[3] = new int[] { x + 2, y };
    nbs[4] = new int[] { x, y + 2 };
    return nbs;
  }
  

  public int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  public int hasNext() {
    
    int[][] nbs = getNeighbors();

    for (int i = 0; i < 5; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) ==  d1_t_color || get(nb) ==  d2_t_color || get(nb) ==  rnd_t_color || get(nb) ==  d3_t_color ) {
        trace.push(nb);
        return TRUE;
      }
    }
    
  
    if(!trace.empty()) {
      pos = trace.pop();
      return MAYBE;
    }
    
 
    return FALSE;
    
  }
  

}




/*  
      |   DIAGONAL ONE
    |
  |
|
*/



class Dia1Fill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};
  
  public void setStart(int x, int y) {
    pos = new int[] { x, y };
  }


  
  
   public int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[6][];
    nbs[0] = new int[] { x , y +1};
    nbs[1] = new int[] { x , y  };
    nbs[2] = new int[] { x - 1, y + PApplet.parseInt(random(4)) };
    nbs[3] = new int[] { x - 1, y + 1 };
    nbs[4] = new int[] { x + PApplet.parseInt(random(2)), y + 1 };
    nbs[5] = new int[] { x + 1, y + PApplet.parseInt(random(4)) };
    return nbs;
  }
  
  
  public int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  public int hasNext() {
    
    int[][] nbs = getNeighbors();
    
    for (int i = 0; i < 6; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) == kill_t_color || get(nb) == rnd_t_color || get(nb) == rnd_color || get(nb) ==  d3_color || get(nb) == h1fill_color
        || get(nb) == rnd_t_color || get(nb) == h2fill_color) {
        trace.push(nb);
        return TRUE;
      }
    }
    
    if(!trace.empty()) {
      pos = trace.pop();
      return MAYBE;
    }
    
    return FALSE;
    
  }

}


/*  
      |   DIAGONAL TWO
    |
  |
|
*/



class Dia2Fill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};

  public void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  public int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[4][];
    nbs[0] = new int[] { x - 1, y +  1 };
    nbs[1] = new int[] { x + 1, y + 1 };
    nbs[2] = new int[] { x - 1, y + 2 };
    nbs[3] = new int[] { x + 1, y + 2 };

    return nbs;
  }
  
  
  // move to the next pixel
  public int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  public int hasNext() {
    
    int[][] nbs = getNeighbors();
    
    for (int i = 0; i < 4; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) == kill_t_color
        || get(nb) == d1_color || get(nb) ==  d3_color || get(nb) == h1fill_color
        || get(nb) == rnd_t_color || get(nb) == h2fill_color) {
           trace.push(nb);
           return TRUE;
      }
    }
    
    if(!trace.empty()) {
      pos = trace.pop();
      return MAYBE;
    }
    
    return FALSE;
    
  }

}


/*  
      |   DIAGONAL THREE
    |
  |
|
*/


class Dia3Fill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};
  
  public void setStart(int x, int y) {
    pos = new int[] { x, y };
  }


  
   public int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[6][];
    nbs[0] = new int[] { x , y +3};
    nbs[1] = new int[] { x , y  };
    nbs[2] = new int[] { x - 1, y + PApplet.parseInt(random(4)) };
    nbs[3] = new int[] { x - 1, y + 1 };
    nbs[4] = new int[] { x + PApplet.parseInt(random(2)), y + 1 };
    nbs[5] = new int[] { x + 1, y + PApplet.parseInt(random(4)) };
    return nbs;
  }
  
  
  public int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  public int hasNext() {
    
    int[][] nbs = getNeighbors();
    
    for (int i = 0; i < 6; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) == kill_t_color || get(nb) == rnd_t_color) {
        trace.push(nb);
        return TRUE;
      }
    }
    
    if(!trace.empty()) {
      pos = trace.pop();
      return MAYBE;
    }
    
    return FALSE;
    
  }

}




/*
    
     KILL
     
*/


class KillFill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};
  
  public void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  public int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[12][];  
    nbs[0] = new int[] { x + PApplet.parseInt(random(4)), y - PApplet.parseInt(random(4)) };
    nbs[1] = new int[] { x - 1, y - 1 };
    nbs[2] = new int[] { x , y - 1 };
    nbs[3] = new int[] { x, y - 2 };
    nbs[4] = new int[] { x - 1, y - 2 };    
    nbs[5] = new int[] { x, y - 2 };
    nbs[6] = new int[] { x + 1, y - 2 };
    nbs[7] = new int[] { x -1, y +1 };
    nbs[8] = new int[] { x + PApplet.parseInt(random(4)), y - PApplet.parseInt(random(2)) };
    nbs[9] = new int[] { x - 1, y };
    nbs[10] = new int[] { x - 2 , y  };
    nbs[11] = new int[] { x + 3, y  };
 

    return nbs;
  }
  
  

  public int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  public int hasNext() {
    
    int[][] nbs = getNeighbors();
    
    
    for (int i = 0; i < 12; i++) {
      int[] nb = nbs[i];
      if ( get(nb) != fg_color && get(nb) != bg_color && get(nb) != fg_color/2) {
        trace.push(nb);
        return TRUE;
      }
    }
    
    if(!trace.empty()) {
      pos = trace.pop();
      return MAYBE;
    }
    
 
    return FALSE;
    
  }

}

/*
|   |       |
  RANDOM
 |     
*/

class RndFill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};
  

  public void setStart(int x, int y) {
    pos = new int[] { x, y };
    getStartX(x);
    getStartY(y);
  }
  
  public int getStartX(int x){
    return x;
    
  }
  
  public int getStartY(int y){
    return y;
    
  }
  
  public int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[4][];
    
    int rn1 = PApplet.parseInt(random(2));
    int rn2 = PApplet.parseInt(random(2));
    int rn3 = PApplet.parseInt(random(2));
    nbs[0] = new int[] { x + rn1, y + rn2};
    nbs[1] = new int[] { x + rn2, y + rn3 };
    nbs[2] = new int[] { x - rn3, y + rn1};
    nbs[3] = new int[] { x + rn2, y };
    return nbs;
  }
  
  

  public int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  public int hasNext() {
    
    int[][] nbs = getNeighbors();
    

    for (int i = 0; i < 4; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) == h1fill_t_color || get(nb) == d2_t_color || get(nb) == h2fill_t_color) {
        trace.push(nb);
        return TRUE;
      }
    }
    

    if(!trace.empty()) {
      pos = trace.pop();
      return MAYBE;
    }
    

    return FALSE;
    
  }

}











public void guimods(){
  
  

  
   cp5 = new ControlP5(this); 
   cp5.addToggle("pause")
     .setPosition(cutoffw*1.25f,PApplet.parseInt(cutoffh/3))
     .setSize(190,PApplet.parseInt(cutoffh/1.8f))
     .setMode(ControlP5.SWITCH)
     .setValue(false)
     .setLabel("                                   Start / Stop")
     .setColorCaptionLabel(234);
   cp5.addBang("gridbang")
       .setPosition(cutoffw*2.0f, PApplet.parseInt(cutoffh/3))
       .setSize(100, PApplet.parseInt(cutoffh/1.8f))
       .setLabel("         Switch Screens")
       .setColorCaptionLabel(234);
   cp5.addSlider("detail")
     .setPosition(cutoffw*2.5f,PApplet.parseInt(cutoffh/3))
     .setSize(200,PApplet.parseInt(cutoffh/1.8f)+2)
     .setRange(0,100)
     .setValue(50)
     .setLabel("        Zoomify")
     .setColorCaptionLabel(234);
   cp5.getController("detail").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
   cp5.addToggle("fancyWater")
     .setPosition(cutoffw*3.25f,PApplet.parseInt(cutoffh/3))
     .setSize(100,PApplet.parseInt(cutoffh/1.8f))
     .setValue(false)
     .setMode(ControlP5.SWITCH)
     .setLabel("             Fancy Water")
     .setColorCaptionLabel(234); 
   cp5.addRange("gridsize")
     .setPosition(cutoffw*3.7f,PApplet.parseInt(cutoffh/3))
     .setSize(180,PApplet.parseInt(cutoffh/1.8f))
     .setHandleSize(1)
     .setRange(1,25)
     .setRangeValues(5,15)
     .setColorLabel(fg_color-2)
     .setLabel("            # of Screens")
     ;
   cp5.getController("gridsize").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
    cp5.addToggle("evenGrid")
     .setPosition(cutoffw*4.4f,PApplet.parseInt(cutoffh/3))
     .setSize(80,PApplet.parseInt(cutoffh/1.8f))
     .setValue(false)
     .setMode(ControlP5.SWITCH)
     .setLabel("                Focus")
     .setColorCaptionLabel(234); 
     
     
 
}


public void gridbang() {
    reset();
    ran2 = random(1.6f,2.6f);
}

public void pause(boolean active) {
  if(active) {
    md=1;
  } else {
    md=0;
  }

}

public void detail(float levelOf) {
  
  speedlimit=PApplet.parseInt(levelOf*42+200);

}


public void fancyWater(boolean active) {
  if(active) {
    fw=true;
  } else {
    fw=false;
  }

}


public void evenGrid(boolean active) {
  if(active) {
    g_distro=true;
  } else {
    g_distro=false;
  }

}


public void controlEvent(ControlEvent theControlEvent) {
  if(theControlEvent.isFrom("gridsize")) {
    gridmin = PApplet.parseInt(theControlEvent.getController().getArrayValue(0));
    gridmax = PApplet.parseInt(theControlEvent.getController().getArrayValue(1));
  }
  
}









public void reset() {
  

  background(bg_color);


  
  
    fill(fg_color); noStroke();
    rect(20, 20+cutoffh, width-40, displayHeight-cutoffh-40);

   colrow = PApplet.parseInt(random(gridmin,gridmax));
   
   for(int i = 0; i < colrow; i++) {
     
    int distrox = (width/colrow)*i;
    int distroy = (height/colrow)*i;
    float r = random(10, 209);
    float x = random(r, width - r);
    float y = random(r+cutoffh, height - r);
    
  
 
    stroke(bg_color);
    strokeWeight(PApplet.parseInt(random(2,6)));
    if(g_distro){
    line(distrox,0,distrox,height); 
    line(0,distroy,width,distroy);   
    }else{
    line(x,0,x,height); 
    line(0,y,width,y);    
    }
    strokeWeight(PApplet.parseInt(random(2,6)));
    fill(fg_color);
    ellipse(x, y, r, r);

  }
  
  for(int i = 0; i < random(0,2); i++) {
    
    float r = random(10, 209);
    float x = random(r, width - r);
    float y = random(r+cutoffh, height - r);
    
    noStroke();
    fill(fg_color);
    rect(x, y, r, r, r/4,r/4,r/5,r/5);
  

  }


  h1Fill = new Hori1Fill();
  h2Fill = new Hori2Fill();
  d1Fill = new Dia1Fill();
  d2Fill = new Dia2Fill();
  d3Fill = new Dia3Fill();
  kFill = new KillFill();
  rFill = new RndFill(); 
  
  
    fill(fg_color/2);
  textSize(PApplet.parseInt(cutoffw/3));
  
  text("MVOC", 20,PApplet.parseInt(cutoffh));
  fill(bg_color);
  text("MVOC", 22,PApplet.parseInt(cutoffh));
  fill(fg_color);
  text("MVOC", 24,PApplet.parseInt(cutoffh));
  fill(bg_color);
  text("MVOC", 26,PApplet.parseInt(cutoffh));
  fill(fg_color);
  text("MVOC", 28,PApplet.parseInt(cutoffh));
  
  
     strokeWeight(4); 
   stroke(fg_color);
   noFill();
   rect(cutoffw*1.18f, 20, displayWidth*0.708f, cutoffh-10,10,10,10,10);
   strokeWeight(6); 
   rect(cutoffw*0.02f, 5, displayWidth*0.993f, displayHeight*0.99f,10,10,10,10);
   strokeWeight(2); 
   stroke(bg_color);
   rect(cutoffw*1.08f, 20, displayWidth*0.708f, cutoffh-10,10,10,10,10);
   

}



class Screen{
  
  int x,y;
  
  
  Screen(int _x,int _y){
   x=_x;
   y=_y;
    
  }
  
  
  
  public void display(){
    fill(fg_color); noStroke();
    rect(width*x,y,3,3);

  }
  
  
  
}
public void select(){
  
 switch(sw) {
   
   
       // h1Fill
      case 0:
  
       for(int i = 0; i < speed; i++) {
    
        int next = h1Fill.hasNext();
    
        switch(next) {
      
          case TRUE:
          
            int[] v = h1Fill.getNext();
            set(v, h1fill_color);
          break;
      
          case MAYBE:
           
            set(h1Fill.pos, h1fill_t_color);
            break;
        
          case FALSE:
           
            break;
      
          }
        }
  
       break;
     
      // d1Fill
     case 1:
      for(int i = 0; i < speed; i++) {
    
        int next = d1Fill.hasNext();
    
        switch(next) {
      
          case TRUE:
           
            int[] v = d1Fill.getNext();
            set(v, d1_color);
          break;
      
          case MAYBE:
           
            set(d1Fill.pos, d1_t_color);
            break;
        
          case FALSE:
            
            break;
      
        }
      }
        break;
      
      // d2Fill
   case 2:
  
       for(int i = 0; i < speed; i++) {
    
        int next = d2Fill.hasNext();
    
        switch(next) {
      
          case TRUE:
         
            int[] v = d2Fill.getNext();
            set(v, d2_color);
          break;
      
          case MAYBE:
            
            set(d2Fill.pos, d2_t_color);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
  
     break;
      
      
      //kFill
   case 3:
  
       for(int i = 0; i < speed; i++) {
    
        int next = kFill.hasNext();
    
        switch(next) {
      
          case TRUE:
         
            int[] v = kFill.getNext();
            set(v, kill_color);
          break;
      
          case MAYBE:
            
            set(kFill.pos, kill_t_color);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
      
     break;
     
     
     //rFill
    case 4:
  
       for(int i = 0; i < speed; i++) {
    
        int next = rFill.hasNext();
    
        switch(next) {
      
          case TRUE:
          
            int[] v = rFill.getNext();
            set(v, rnd_color);
          break;
      
          case MAYBE:
           
            set(rFill.pos, rnd_t_color);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
     break; 
     
      //d3Fill
    case 5:
  
       for(int i = 0; i < speed; i++) {
    
        int next = d3Fill.hasNext();
    
        switch(next) {
      
          case TRUE:
          
            int[] v = d3Fill.getNext();
            set(v, d3_color);
          break;
      
          case MAYBE:
           
            set(d3Fill.pos, d3_t_color);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
     break; 
    
    
    // h2Fill
     case 6:
  
       for(int i = 0; i < speed; i++) {
    
        int next = h2Fill.hasNext();
    
        switch(next) {
      
          case TRUE:
          
            int[] v = h2Fill.getNext();
            set(v, h2fill_color);
          break;
      
          case MAYBE:
           
            set(h2Fill.pos, h2fill_t_color);
            break;
        
          case FALSE:
           
            break;
      
          }
        }
  
       break;
     
  
   }
   
   
   //cycle//
   //sw=6;
   //sw++;
   //sw=sw%8;
   sw=PApplet.parseInt(random(0,7));
   //       //


   
   
   switch(sw) {
     
    case 1:
      int xh1 = PApplet.parseInt(random(20,width-40));
      int yh1 = PApplet.parseInt(random(cutoffh,height));
      h1Fill.setStart(xh1, yh1);
     
      
    case 2:
      int xd1 = PApplet.parseInt(random(20,width-40));
      int yd1 = PApplet.parseInt(random(cutoffh,height));
      d1Fill.setStart(xd1, yd1);
  
    case 3:
      int xd2 = PApplet.parseInt(random(20,width-40));
      int yd2 = PApplet.parseInt(random(cutoffh,height));
      d2Fill.setStart(xd2, yd2);
     break;
     
    case 4:
      int xk = PApplet.parseInt(random(20,width-40));
      int yk = PApplet.parseInt(random(cutoffh,height));
      kFill.setStart(xk, yk);
     break;  
    case 5:
      int xr = PApplet.parseInt(random(20,width-40));
      int yr = PApplet.parseInt(random(cutoffh,height));
      rFill.setStart(xr,yr);
     break;
    case 6:
      int xd3 = PApplet.parseInt(random(20,width-40));
      int yd3 = PApplet.parseInt(random(cutoffh,height));
      d3Fill.setStart(xd3, yd3);
     break;
     
    case 7: 
      int xh2 = PApplet.parseInt(random(20,width-40));
      int yh2 = PApplet.parseInt(random(cutoffh,height));
      h2Fill.setStart(xh2,yh2);
      break;
     case 8:
      xk = PApplet.parseInt(random(20,width-40));
      yk = PApplet.parseInt(random(cutoffh,height));
      kFill.setStart(xk, yk);
      break;
  }    

  if(fw){ 
    
    rv1 = PApplet.parseInt(random(1,3));
  
    }else{
    
    rv1=1;

  }
}

public void speeds(){

  switch(md) {
    
    case 0:
      speed=0;
      break;
    
    
    case 2:
    
    float dia = speedlimit +20;

    speed = 40+PApplet.parseInt((sin(theta) * dia/2)+dia/2);
    if(speed > 500){
    theta+= 0.01f;
    }else{
     theta+=0.001f; 
    }
    break;
   
   case 1:
      speed=speed-10;
      if(speed<150){
      speed=speedlimit;
      break;
      }
   
   
   }
  
}

  public int sketchWidth() { return displayWidth; }
  public int sketchHeight() { return displayHeight; }
}
