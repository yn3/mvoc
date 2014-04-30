void select(){
  
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
      hFill.setStart(int(random(width)), int(random(height))+cutoffh);
     
    case 1:
      d1Fill.setStart(int(random(width)), int(random(height))+cutoffh);
    
    case 2:
      d2Fill.setStart(int(random(width)), int(random(height))+cutoffh);
    
    case 3:
      kFill.setStart(int(random(width)), int(random(height))+cutoffh);
     
    case 4:
      rFill.setStart(int(random(width)), int(random(height))+cutoffh);
     
  }    
    
  
}
