
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



||||||  based on code by   |||||||
||||||    Martin Schneider |||||||    

*/


import java.util.Stack;

color fg_color = #00003f;
color bg_color = #ffffde;
color fill_color4 = #00003f;
color track_color4 = #00003f;
color fill_color,track_color,fill_color2,track_color2,fill_color3,track_color3,fill_color5,track_color5;

final int FALSE = 0, TRUE = 1, MAYBE = -1;
int sw = 1, cnt = 0, speed = 100;
float ran2 = random(1.6,2.6);



NeumannFill fl;  
MooreFill fl2;
DiagonalFill fl3;  
FaupelFill fl4;
DunnoFill fl5;


void setup() {
  size(displayWidth, displayHeight);
  reset();
  orientation(LANDSCAPE);
 
}



void draw() {
speed=int(random(3,4500)); 
colr();  
select();
  
}

color get(int[] v) {
  return get(v[0], v[1]); 
}

void set(int[] v, color c) {
  set(v[0], v[1], c);
}

void mousePressed() {
reset();
ran2 = random(1.6,2.6);
}


void colr(){

cnt++;
cnt=cnt%235;

fill_color = color(int(random(20,cnt+20)),int(random(20,cnt+20)),cnt+20);
track_color = color(int(random(20,cnt+20)),int(random(20,cnt+20)),cnt+20);
fill_color2 = color(int(random(20,255)),cnt+20,int(random(20,cnt+20)));
track_color2 = color(int(random(20,cnt+20)),cnt+20,int(random(20,cnt+20)));
fill_color3 = color(cnt+20,int(random(20,cnt+20)),120);
track_color3 = color(int(random(20,cnt+20)),255,int(random(20,cnt+20)));
fill_color5 = color(0,25,17);
track_color5 = color(int(random(20,cnt+20)),cnt+20,int(random(20,cnt+20)));

}

class DiagonalFill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};

  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[4][];
    nbs[2] = new int[] { x - 1, y + 2 };
    nbs[0] = new int[] { x - 1, y +  1 };
    nbs[1] = new int[] { x + 1, y + 1 };
    nbs[3] = new int[] { x + 1, y + 2 };
    return nbs;
  }
  
  
  // move to the next pixel
  int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  int hasNext() {
    
    int[][] nbs = getNeighbors();
    
    for (int i = 0; i < 4; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) == track_color4 
        || get(nb) == track_color2 || get(nb) == track_color 
        || get(nb) == track_color5) {
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


class DunnoFill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};
  

  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[4][];
    
    int rn1 = int(random(2));
    int rn2 = int(random(2));
    int rn3 = int(random(2));
    nbs[0] = new int[] { x + rn1, y + rn2};
    nbs[1] = new int[] { x + rn2, y + rn3 };
    nbs[2] = new int[] { x - rn3, y + rn1};
    nbs[3] = new int[] { x + rn2, y };
    return nbs;
  }
  
  

  int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  int hasNext() {
    
    int[][] nbs = getNeighbors();
    

    for (int i = 0; i < 4; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) == track_color || get(nb) == track_color3) {
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

class FaupelFill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};
  
  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[8][];
    nbs[7] = new int[] { x -1, y +1 };
    nbs[1] = new int[] { x - 1, y - 1 };
    nbs[2] = new int[] { x - 1, y - 1 };
    nbs[3] = new int[] { x - 1, y - 2 };
    nbs[4] = new int[] { x - 1, y - 2 };    
    nbs[5] = new int[] { x, y - 2 };
    nbs[6] = new int[] { x + 1, y - 2 };
    nbs[0] = new int[] { x + int(random(4)), y - int(random(4)) };
    return nbs;
  }
  
  

  int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  int hasNext() {
    
    int[][] nbs = getNeighbors();
    
    
    for (int i = 0; i < 8; i++) {
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
    
    // give up
    return FALSE;
    
  }

}

class MooreFill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};
  
  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[6][];
    nbs[0] = new int[] { x , y +1};
    nbs[1] = new int[] { x , y  };
    nbs[2] = new int[] { x - 1, y + int(random(4)) };
    nbs[3] = new int[] { x - 1, y + 1 };
    nbs[4] = new int[] { x + int(random(2)), y + 1 };
    nbs[5] = new int[] { x + 1, y + int(random(4)) };
    return nbs;
  }
  
  
  int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  int hasNext() {
    
    int[][] nbs = getNeighbors();
    
    for (int i = 0; i < 6; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) == track_color4 || get(nb) == track_color5) {
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


class NeumannFill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};

  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[4][];
    nbs[2] = new int[] { x - 1, y };
    nbs[0] = new int[] { x, y + 1 };
    nbs[1] = new int[] { x + 1, y };
    nbs[3] = new int[] { x, y + 2 };
    return nbs;
  }
  

  int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  int hasNext() {
    
    int[][] nbs = getNeighbors();
    
    // if we find a free pixel push it to the stack
    for (int i = 0; i < 4; i++) {
      int[] nb = nbs[i];
      if ( get(nb) == fg_color || get(nb) == track_color2 || get(nb) == track_color2 ) {
        trace.push(nb);
        return TRUE;
      }
    }
    
    // otherwise pop from the stack and try again (later)
    if(!trace.empty()) {
      pos = trace.pop();
      return MAYBE;
    }
    
    // give up
    return FALSE;
    
  }
  

}


void reset() {
  // draw something to be filled
  background(bg_color);
 
    for(int i = 0; i < random(10,250); i++) {
    
    float r = random(40, 420);
    float x = random(r, width - r);
    float y = random(r, height - r);
    
 
    fill(fg_color); noStroke();
    rect(20, 20, width-40, height-40);
   
  }

  
    
  
  for(int i = 0; i < random(0,2); i++) {
    float ran = random(1.01,1.4);
    float r = random(40, 620);
    float x = random(r, width - r);
    float y = random(r, height - r);
    
    
    
    noStroke();
    fill(bg_color); 
    ellipse(displayWidth/ran2, displayHeight/ran2, r/2, r/2);
    fill(fg_color);
    ellipse(displayWidth/ran2, displayHeight/ran2, r/3*ran, r/3*ran);

  }
  
   for(int i = 0; i < random(1,2); i++) {
    
    float r = random(10, 209);
    float x = random(r, width - r);
    float y = random(r, height - r);
    
 
    noStroke();
    fill(bg_color);
    
    ellipse(x*1.5, y, r*122, r/6);
    ellipse(y*1.5, x, r/12, r*122);
     ellipse(displayWidth/ran2,displayHeight/ran2, r*122, r/6);
    ellipse(displayWidth/ran2, displayHeight/ran2, r/12, r*122);
    fill(fg_color);
    ellipse(x, y, r, r);
  
   

  }
  

  fl = new NeumannFill();
  fl2 = new MooreFill();
  fl3 = new DiagonalFill();
  fl4 = new FaupelFill();
  fl5 = new DunnoFill(); 
}

void select(){
  
 switch(sw) {
     
     case 1:
      for(int i = 0; i < speed; i++) {
    
        int next = fl2.hasNext();
    
        switch(next) {
      
          case TRUE:
           
            int[] v = fl2.getNext();
            set(v, fill_color2);
          break;
      
          case MAYBE:
           
            set(fl2.pos, track_color2);
            break;
        
          case FALSE:
            
            break;
      
        }
      }
        break;
      
    case 0:
  
       for(int i = 0; i < speed; i++) {
    
        int next = fl.hasNext();
    
        switch(next) {
      
          case TRUE:
          
            int[] v = fl.getNext();
            set(v, fill_color);
          break;
      
          case MAYBE:
           
            set(fl.pos, track_color);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
  
     break;
      
   case 2:
  
       for(int i = 0; i < speed; i++) {
    
        int next = fl3.hasNext();
    
        switch(next) {
      
          case TRUE:
         
            int[] v = fl3.getNext();
            set(v, fill_color3);
          break;
      
          case MAYBE:
            
            set(fl3.pos, track_color3);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
  
     break;
      
   case 3:
  
       for(int i = 0; i < speed; i++) {
    
        int next = fl4.hasNext();
    
        switch(next) {
      
          case TRUE:
         
            int[] v = fl4.getNext();
            set(v, fill_color4);
          break;
      
          case MAYBE:
            
            set(fl4.pos, track_color4);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
      
     break;
  
    case 4:
  
       for(int i = 0; i < speed; i++) {
    
        int next = fl5.hasNext();
    
        switch(next) {
      
          case TRUE:
          
            int[] v = fl5.getNext();
            set(v, fill_color5);
          break;
      
          case MAYBE:
           
            set(fl5.pos, track_color5);
            break;
        
          case FALSE:
           
            break;
      
        }
      }
     break; 
  
   }
   
   sw = sw+1;
   sw=sw%5;
   
   switch(sw) {
    case 1:
      fl2.setStart(int(random(width)), int(random(height)));
  
    case 0:
      fl.setStart(int(random(width)), int(random(height)));
   
    case 2:
      fl3.setStart(int(random(width)), int(random(height)));
 
    case 3:
      fl4.setStart(int(random(width)), int(random(height)));
      
    case 4:
      fl5.setStart(int(random(width)), int(random(height)));
  }    
    
  
}


