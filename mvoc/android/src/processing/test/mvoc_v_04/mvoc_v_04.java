package processing.test.mvoc_v_04;

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

public class mvoc_v_04 extends PApplet {


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





int fg_color = color(PApplet.parseInt(random(5,55)),0,PApplet.parseInt(random(44,88))),
      bg_color = 0xffDCDCDC, kill_color = fg_color, kill_t_color = fg_color,
      h1fill_color, h1fill_t_color, h2fill_color, h2fill_t_color,
      d1_color, d1_t_color, d2_color, d2_t_color, d3_color, d3_t_color,
      d4_color, d4_t_color, rnd_color, rnd_t_color;

final int FALSE = 0, TRUE = 1, MAYBE = -1;

int sw = 1, cnt = 0, speed = 2000,
    speedlimit = 4500, md = 0, cutoffh,cutoffw, rv1, 
    colrow=7, gridmin = 8, gridmax = 9, pl_mode = 2;

float ran2 = random(1.6f,2.6f),theta;

boolean fw = false, g_distro = false, dynamic = false;
String p_text;

ControlP5 cp5;
Textlabel myTextlabelA;
Hori1Fill h1Fill;  
Hori2Fill h2Fill; 
Dia1Fill d1Fill;
Dia2Fill d2Fill;  
Dia3Fill d3Fill; 
Dia4Fill d4Fill; 
KillFill kFill;
RndFill rFill;

//---

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
   if(pl_mode==2){
    h2fill_t_color = color(255,PApplet.parseInt(random(200,cnt)),PApplet.parseInt(random(130,cnt+20)));
  }else{
  h2fill_t_color = color(30,PApplet.parseInt(random(30,cnt-130)),PApplet.parseInt(random(30,cnt+20)));
  }
  
  d1_color = color(PApplet.parseInt(random(20,cnt+20)),cnt+20,PApplet.parseInt(random(20,cnt+20)));
  if(pl_mode==2){
    d1_t_color = color(cnt+20,cnt,0);
  }else{
  d1_t_color = color(PApplet.parseInt(random(20,cnt+20)),cnt+20,PApplet.parseInt(random(20,cnt+20)));
  }
  d2_color = color(cnt+20,PApplet.parseInt(random(20,cnt+20)),120);
  d2_t_color = color(PApplet.parseInt(random(20,cnt+20)),255,PApplet.parseInt(random(20,cnt+20)));
  
  d3_color = color(PApplet.parseInt(random(0,cnt/2+1)),cnt+20,PApplet.parseInt(random(20,cnt+20)));
  d3_t_color = color(PApplet.parseInt(random(0,cnt/5+1)),cnt/1.2f,cnt/1.2f);
  
  d4_color = color(PApplet.parseInt(random(100,cnt+20)),33,0);
  d4_t_color = color(PApplet.parseInt(random(100,cnt+20)),33,0);
  
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
    nbs[0] = new int[] { x, y + rv1 };
    nbs[1] = new int[] { x + 1, y  };
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
    nbs[0] = new int[] { x , y + 1 };
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
        || get(nb) == d1_t_color || get(nb) ==  d3_t_color || get(nb) == h1fill_t_color
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
    nbs[0] = new int[] { x -1 , y +1};
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
      |   DIAGONAL FOUR
    |
  |
|
*/


class Dia4Fill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};
  
  public void setStart(int x, int y) {
    pos = new int[] { x, y };
  }


  
   public int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[4][];
    nbs[0] = new int[] { x + 1 , y +1};
    nbs[1] = new int[] { x -rv1 , y  + PApplet.parseInt(random(3))  };
    nbs[2] = new int[] { x - 1, y + PApplet.parseInt(random(3)) };
    nbs[3] = new int[] { x - 1, y + 1 };

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
      if ( get(nb) == fg_color || get(nb) == h1fill_t_color || get(nb) == d2_t_color ) {
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
     .setPosition(displayWidth-cutoffw*0.48f,PApplet.parseInt(cutoffh/3))
     .setSize(PApplet.parseInt(cutoffw*0.4f),PApplet.parseInt(cutoffh/1.5f))
     .setValue(false)
     .setLabel("Start/Pause")
     .setColorCaptionLabel(234);
   cp5.getController("pause").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);      
   cp5.addBang("gridbang")
     .setPosition(displayWidth-cutoffw, PApplet.parseInt(cutoffh/3))
     .setSize(PApplet.parseInt(cutoffw*0.36f), PApplet.parseInt(cutoffh/1.8f))
     .setLabel("Switch Screens")
     .setColorCaptionLabel(234);
   cp5.getController("gridbang").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);    
    
   cp5.addSlider("detail")
     .setPosition(displayWidth-cutoffw*3.82f,PApplet.parseInt(cutoffh/3))
     .setSize(PApplet.parseInt(cutoffw*0.5f),PApplet.parseInt(cutoffh/1.8f)+2)
     .setRange(0,100)
     .setValue(50)
     .setLabel("Zoomify")
     .setColorCaptionLabel(234);
   cp5.getController("detail").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
   
   cp5.addToggle("dynamic")
     .setPosition(displayWidth-cutoffw*3.26f,PApplet.parseInt(cutoffh/3))
     .setSize(PApplet.parseInt(cutoffw*0.2f),PApplet.parseInt(cutoffh/1.8f))
     .setValue(false)
     .setLabel("EVOLUTION")
     .setColorCaptionLabel(234);
    cp5.getController("dynamic").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);   
     
   cp5.addSlider("gridsize")
     .setPosition(displayWidth-cutoffw*3.01f,PApplet.parseInt(cutoffh/3))
     .setSize(PApplet.parseInt(cutoffw*0.4f),PApplet.parseInt(cutoffh/1.8f))
     .setRange(1,25)
     .setValue(8)
     .setColorLabel(fg_color-2)
     .setLabel("# of Screens");   
   cp5.getController("gridsize").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
     
   cp5.addToggle("evenGrid")
     .setPosition(displayWidth-cutoffw*1.27f,PApplet.parseInt(cutoffh/3))
     .setSize(PApplet.parseInt(cutoffw*0.2f),PApplet.parseInt(cutoffh/1.8f))
     .setValue(false)
     .setLabel("Focus")
     .setColorCaptionLabel(234); 
   cp5.getController("evenGrid").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);  
     
     
   cp5.addToggle("fancyWater")
    .setPosition(displayWidth-cutoffw*2.55f,PApplet.parseInt(cutoffh/3))
     .setSize(PApplet.parseInt(cutoffw*0.2f),PApplet.parseInt(cutoffh/1.8f))
     .setValue(false)
     .setLabel("Fancyness")
     .setColorCaptionLabel(234);
  cp5.getController("fancyWater").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);     
     
   cp5.addSlider("colorset")
    .setPosition(displayWidth-cutoffw*2.2f,PApplet.parseInt(cutoffh/3))
     .setSize(PApplet.parseInt(cutoffw*0.6f),PApplet.parseInt(cutoffh/1.8f))
     .setValue(0)
     .setRange(0,2)
     .setNumberOfTickMarks(3)
     .setLabel("Switch Planet")
     .setColorCaptionLabel(234);   
   cp5.getController("colorset").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);
 
   cp5.addTextlabel("planet1")   
     .show() 
     .setText("Retro")
     .setPosition(displayWidth-cutoffw*1.55f,PApplet.parseInt(cutoffh/2.5f))
     .setColorValue(fg_color); 
   cp5.addTextlabel("planet2")
     .hide() 
     .setText("Greenlands")
     .setPosition(displayWidth-cutoffw*1.55f,PApplet.parseInt(cutoffh/1.65f))
     .setColorValue(fg_color);
   cp5.addTextlabel("planet3")
     .hide() 
     .setText("Desert")
     .setPosition(displayWidth-cutoffw*1.55f,PApplet.parseInt(cutoffh/1.25f))
     .setColorValue(fg_color); 

  

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
  
  speedlimit=PApplet.parseInt(pow(levelOf,2)+200);

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


public void gridsize(float screens){
  gridmin=PApplet.parseInt(screens);
  gridmax=PApplet.parseInt(screens*1.5f);
  
}
public void dynamic(boolean active){
dynamic=active;
  

}

public void colorset(int levelOf){
  pl_mode=levelOf;
  
  switch(levelOf){
    case 0: 
    
    fill(bg_color);
    rect(displayWidth-cutoffw*1.55f,PApplet.parseInt(cutoffh/2.5f),cutoffw/5,PApplet.parseInt(cutoffh/2));
    cp5.controller("planet1").show();
    cp5.controller("planet2").hide(); 
    cp5.controller("planet3").hide();
    break;
    case 1:
     fill(bg_color);
     rect(displayWidth-cutoffw*1.55f,PApplet.parseInt(cutoffh/2.5f),cutoffw/5,PApplet.parseInt(cutoffh/2));
    cp5.controller("planet2").show();
    cp5.controller("planet1").hide(); 
    cp5.controller("planet3").hide();
    
    break;
    case 2:
    fill(bg_color);
    rect(displayWidth-cutoffw*1.55f,PApplet.parseInt(cutoffh/2.5f),cutoffw/5,PApplet.parseInt(cutoffh/2));
    cp5.controller("planet3").show();
    cp5.controller("planet1").hide(); 
    cp5.controller("planet2").hide();
    break;
  }
  

 
  
}



public void reset() {
  

  background(bg_color);

  
   fill(fg_color); noStroke();
   rect(20, 20+cutoffh, width-40, displayHeight-cutoffh-40);

   colrow = PApplet.parseInt(random(gridmin,gridmax));
   int distrox, distroy;
   float r,x,y,r2,x2,y2;
   for(int i = 0; i < colrow; i++) {
     
    distrox = (width/colrow)*i;
    distroy = (height/colrow)*i;
    r = random(10, 209);
    x = random(r, width - r);
    y = random(r+cutoffh, height - r);
    
    stroke(bg_color);
    strokeWeight(PApplet.parseInt(random(2,6)));
      if(g_distro){
        line(distrox,0,distrox,height); 
        line(0,distroy,width,distroy);   
      }else{
        line(x,0,x,height); 
        line(0,y,width,y);    
      }

      if(i%2==1)
        ellipse(x, y, r, r);

  }
  
  for(int i = 0; i < random(0,2); i++) {
    
    r2 = random(10, 209);
    x2 = random(r2, width - r2);
    y2 = random(r2+cutoffh, height - r2);
    
    noStroke();
    fill(fg_color);
    rect(x2-(cutoffw*0.2f), y2, r2, r2);
  

  }


  h1Fill = new Hori1Fill();
  h2Fill = new Hori2Fill();
  d1Fill = new Dia1Fill();
  d2Fill = new Dia2Fill();
  d3Fill = new Dia3Fill();
  d4Fill = new Dia4Fill();
  kFill = new KillFill();
  rFill = new RndFill(); 
  
  
  fill(fg_color/2);
  textSize(PApplet.parseInt(cutoffw/3));
  
  text("MVOC", 18,PApplet.parseInt(cutoffh*1.1f));
  fill(bg_color);
  text("MVOC", 20,PApplet.parseInt(cutoffh*1.1f));
  fill(fg_color);
  text("MVOC", 22,PApplet.parseInt(cutoffh*1.1f));
  fill(bg_color);
  text("MVOC", 24,PApplet.parseInt(cutoffh*1.1f));
  fill(h1fill_color+100);
  text("MVOC", 26,PApplet.parseInt(cutoffh*1.1f));


  
   strokeWeight(4); 
   stroke(h1fill_color+100);
   noFill();
   rect(cutoffw*1.11f, cutoffh*0.2f, displayWidth*0.665f, cutoffh*0.94f,10,10,10,10);
   stroke(fg_color);
   strokeWeight(6); 
   rect(cutoffw*0.02f, 5, displayWidth*0.993f, displayHeight*0.99f,10,10,10,10);
   strokeWeight(2); 
   stroke(bg_color);
   rect(cutoffw*1.01f, cutoffh*0.2f, displayWidth*0.665f, cutoffh*0.94f,10,10,10,10);
   rect(cutoffw*0.3f, cutoffh*0.2f, cutoffw, cutoffh*0.94f,10,10,10,10);
   rect(cutoffw*0.35f, cutoffh*0.2f, cutoffw*0.36f, cutoffh*0.94f,0,0,0,0); 
   
   
   
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
     
   //d4Fill
    case 7:
  
       for(int i = 0; i < speed; i++) {
    
        int next = d4Fill.hasNext();
    
        switch(next) {
      
          case TRUE:
          
            int[] v = d4Fill.getNext();
            set(v, d4_color);
          break;
      
          case MAYBE:
           
            set(d4Fill.pos, d4_t_color);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
     break; 
  
  
  
  
  
  
   }
   
   
   
   //random cycle//
  
   sw=PApplet.parseInt(random(0,8));



   
   
   switch(sw) {
     
    case 0:
      int xh1 = PApplet.parseInt(random(20,width-40));
      int yh1 = PApplet.parseInt(random(cutoffh*0.2f,height));
      if(pl_mode==0)
       h1Fill.setStart(xh1, yh1);
      break;
      
    case 1:
      int xd1 = PApplet.parseInt(random(20,width-40));
      int yd1 = PApplet.parseInt(random(cutoffh*0.2f,height));
      d1Fill.setStart(xd1, yd1);
  
    case 2:
      int xd2 = PApplet.parseInt(random(20,width-40));
      int yd2 = PApplet.parseInt(random(cutoffh*0.2f,height));
      if(pl_mode==0){
        d2Fill.setStart(xd2, yd2);
       }else if(pl_mode==2){
        d4Fill.setStart(xd2,yd2);
       }
     break;
     
    case 3:
      int xk = PApplet.parseInt(random(20,width-40));
      int yk = PApplet.parseInt(random(cutoffh*0.2f,height));
      kFill.setStart(xk, yk);
     break;  
    case 4:
      int xr = PApplet.parseInt(random(20,width-40));
      int yr = PApplet.parseInt(random(cutoffh*0.2f,height));
       if(pl_mode==0 || pl_mode==1)
        rFill.setStart(xr,yr);
     break;
    case 5:
      int xd3 = PApplet.parseInt(random(20,width-40));
      int yd3 = PApplet.parseInt(random(cutoffh*0.2f,height));
      
      if(pl_mode==1)
        d3Fill.setStart(xd3, yd3);
     break;
     
    case 6: 
      int xh2 = PApplet.parseInt(random(20,width-40));
      int yh2 = PApplet.parseInt(random(cutoffh*0.2f,height));
      if(pl_mode==1 || pl_mode==2){
       h2Fill.setStart(xh2,yh2);
      }
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
      
    case 1:
      if(dynamic==false){

        speed=speed-10;
      
          if(speed<150){
        
          speed=speedlimit;
      
        break;
      
        }
        
      }else{
   
 
    
        float dia = speedlimit +20;

        speed = 40+PApplet.parseInt((sin(theta) * dia/2)+dia/2);
    
          if(speed > 500){
      
            theta+= 0.01f;
    
          }else{
      
            theta+=0.001f; 
      
          }
    
        break;
      }
   }
  
}

  public int sketchWidth() { return displayWidth; }
  public int sketchHeight() { return displayHeight; }
}
