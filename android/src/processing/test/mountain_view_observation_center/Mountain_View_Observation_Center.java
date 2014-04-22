package processing.test.mountain_view_observation_center;

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

public class Mountain_View_Observation_Center extends PApplet {


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




int fg_color = 0xff00003f;
int bg_color = 0xffffffde;
int kill_color = 0xff00003f;
int kill_t_color = 0xff00003f;
int hfill_color,hfill_t_color,d1_color,d1_t_color,
      d2_color,d2_t_color,rnd_color,rnd_t_color;


final int FALSE = 0, TRUE = 1, MAYBE = -1;

int sw = 1, cnt = 0, speed = 7000,
    speedlimit = 4500, md = 0, cutoffh,cutoffw, rv1;

float ran2 = random(1.6f,2.6f);
boolean fw = false;



ControlP5 cp5;

HoriFill hFill;  
Dia1Fill d1Fill;
Dia2Fill d2Fill;  
KillFill kFill;
RndFill rFill;

public void setup() {
  
 
  cutoffh = PApplet.parseInt(displayHeight/10);
  cutoffw = PApplet.parseInt(displayWidth/6);
 
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

  hfill_color = color(PApplet.parseInt(random(20,cnt+20)),PApplet.parseInt(random(20,cnt+20)),cnt+20);
  hfill_t_color = color(PApplet.parseInt(random(20,cnt+20)),PApplet.parseInt(random(20,cnt+20)),cnt+20);
  d1_color = color(PApplet.parseInt(random(20,255)),cnt+20,PApplet.parseInt(random(20,cnt+20)));
  d1_t_color = color(PApplet.parseInt(random(20,cnt+20)),cnt+20,PApplet.parseInt(random(20,cnt+20)));
  d2_color = color(cnt+20,PApplet.parseInt(random(20,cnt+20)),120);
  d2_t_color = color(PApplet.parseInt(random(20,cnt+20)),255,PApplet.parseInt(random(20,cnt+20)));
  rnd_color = color(0,25,17);
  rnd_t_color = color(PApplet.parseInt(random(20,cnt+20)),cnt+20,PApplet.parseInt(random(20,cnt+20)));

}

/*
||||||||||||
 HORIZONTAL

*/


class HoriFill {
  
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
    nbs[0] = new int[] { x , y +1 };
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
        || get(nb) == d1_t_color || get(nb) == hfill_t_color
        || get(nb) == rnd_t_color) {
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
      if ( get(nb) != fg_color && get(nb) != bg_color) {
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
      if ( get(nb) == fg_color || get(nb) == hfill_t_color || get(nb) == d2_t_color) {
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
     .setPosition(cutoffw,PApplet.parseInt(cutoffh/3))
     .setSize(190,PApplet.parseInt(cutoffh/1.8f))
     .setMode(ControlP5.SWITCH)
     .setValue(false)
     .setLabel("                                   Start / Stop")
     .setColorCaptionLabel(234);
   cp5.addBang("gridbang")
       .setPosition(cutoffw*2, PApplet.parseInt(cutoffh/3))
       .setSize(100, PApplet.parseInt(cutoffh/1.8f))
       .setLabel("             Switch Grid")
       .setColorCaptionLabel(234);
   cp5.addSlider("detail")
     .setPosition(cutoffw*2.7f,PApplet.parseInt(cutoffh/3))
     .setSize(200,PApplet.parseInt(cutoffh/1.8f)+2)
     .setRange(0,100)
     .setValue(100)
     .setLabel("        DETAIL")
     .setColorCaptionLabel(234);
   cp5.getController("detail").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
   cp5.addToggle("fancyWater")
     .setPosition(cutoffw*3.75f,PApplet.parseInt(cutoffh/3))
     .setSize(100,PApplet.parseInt(cutoffh/1.8f))
     .setValue(false)
     .setMode(ControlP5.SWITCH)
     .setLabel("             Fancy Water")
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
public void reset() {

  background(bg_color);

    fill(fg_color); noStroke();
    rect(20, 20+cutoffh, width-40, displayHeight-cutoffh-40);

   int colrow = PApplet.parseInt(random(1,8));

   for(int i = 0; i < colrow; i++) {
    
    float r = random(10, 209);
    float x = random(r, width - r);
    float y = random(r+cutoffh, height - r);
    
 
    stroke(bg_color);
    strokeWeight(random(2,10));
    line(x,0,x,height); 
    line(0,y,width,y); 

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
  
  
  fill(fg_color);
  textSize(PApplet.parseInt(cutoffw/5));
  text("MVOC", 20,PApplet.parseInt(cutoffh/3)+52);


  hFill = new HoriFill();
  d1Fill = new Dia1Fill();
  d2Fill = new Dia2Fill();
  kFill = new KillFill();
  rFill = new RndFill(); 
}
public void select(){
  
 switch(sw) {
   
   
       // hFill
      case 0:
  
       for(int i = 0; i < speed; i++) {
    
        int next = hFill.hasNext();
    
        switch(next) {
      
          case TRUE:
          
            int[] v = hFill.getNext();
            set(v, hfill_color);
          break;
      
          case MAYBE:
           
            set(hFill.pos, hfill_t_color);
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
  
   }
   
   
   //cycle//
   sw++;
   sw=sw%5;
   //       //
   
   switch(sw) {
     
    case 0:
      hFill.setStart(PApplet.parseInt(random(width)), PApplet.parseInt(random(height))+cutoffh);
     
    case 1:
      d1Fill.setStart(PApplet.parseInt(random(width)), PApplet.parseInt(random(height))+cutoffh);
    
    case 2:
      d2Fill.setStart(PApplet.parseInt(random(width)), PApplet.parseInt(random(height))+cutoffh);
    
    case 3:
      kFill.setStart(PApplet.parseInt(random(width)), PApplet.parseInt(random(height))+cutoffh);
     
    case 4:
      rFill.setStart(PApplet.parseInt(random(width)), PApplet.parseInt(random(height))+cutoffh);
     
  }    
    
  
}
public void speeds(){
  
  switch(md) {
    case 0:
    speed=0;
    break;
   
   
    case 1:
    speed=speed-10;
    if(speed<200){
      speed=speedlimit;
      break;
    }
    
    
    case 2:
    speed=PApplet.parseInt(random(3,300));
  }
  
  if(fw){ 
    
  rv1 = PApplet.parseInt(random(1,3));
  
  }else{
    
  rv1=1;

  }
}

  public int sketchWidth() { return displayWidth; }
  public int sketchHeight() { return displayHeight; }
}
