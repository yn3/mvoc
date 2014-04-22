
/*
||||||||||||
 HORIZONTAL

*/


class HoriFill {
  
  Stack<int[]> trace = new Stack();

  int[] pos = {-1, -1};

  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[4][];
    nbs[0] = new int[] { x, y + rv1 };
    nbs[1] = new int[] { x + 1, y };
    nbs[2] = new int[] { x - 1, y };
    nbs[3] = new int[] { x, y + 2 };
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
  
  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[6][];
    nbs[0] = new int[] { x , y +1 };
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

  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
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
  int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  int hasNext() {
    
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
  
  void setStart(int x, int y) {
    pos = new int[] { x, y };
  }

  
  int[][] getNeighbors() {
    int x = pos[0];
    int y = pos[1];
    int[][] nbs = new int[12][];  
    nbs[0] = new int[] { x + int(random(4)), y - int(random(4)) };
    nbs[1] = new int[] { x - 1, y - 1 };
    nbs[2] = new int[] { x , y - 1 };
    nbs[3] = new int[] { x, y - 2 };
    nbs[4] = new int[] { x - 1, y - 2 };    
    nbs[5] = new int[] { x, y - 2 };
    nbs[6] = new int[] { x + 1, y - 2 };
    nbs[7] = new int[] { x -1, y +1 };
    nbs[8] = new int[] { x + int(random(4)), y - int(random(2)) };
    nbs[9] = new int[] { x - 1, y };
    nbs[10] = new int[] { x - 2 , y  };
    nbs[11] = new int[] { x + 3, y  };
 

    return nbs;
  }
  
  

  int[] getNext() {
    pos = trace.peek();
    return pos;
  }


  int hasNext() {
    
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










