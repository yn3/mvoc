void select(){
  
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
  
   sw=int(random(0,8));



   
   
   switch(sw) {
     
    case 0:
      int xh1 = int(random(20,width-40));
      int yh1 = int(random(cutoffh*0.2,height));
      if(pl_mode==0)
       h1Fill.setStart(xh1, yh1);
      break;
      
    case 1:
      int xd1 = int(random(20,width-40));
      int yd1 = int(random(cutoffh*0.2,height));
      d1Fill.setStart(xd1, yd1);
  
    case 2:
      int xd2 = int(random(20,width-40));
      int yd2 = int(random(cutoffh*0.2,height));
      if(pl_mode==0){
        d2Fill.setStart(xd2, yd2);
       }else if(pl_mode==2){
        d4Fill.setStart(xd2,yd2);
       }
     break;
     
    case 3:
      int xk = int(random(20,width-40));
      int yk = int(random(cutoffh*0.2,height));
      kFill.setStart(xk, yk);
     break;  
    case 4:
      int xr = int(random(20,width-40));
      int yr = int(random(cutoffh*0.2,height));
       if(pl_mode==0 || pl_mode==1)
        rFill.setStart(xr,yr);
     break;
    case 5:
      int xd3 = int(random(20,width-40));
      int yd3 = int(random(cutoffh*0.2,height));
      
      if(pl_mode==1)
        d3Fill.setStart(xd3, yd3);
     break;
     
    case 6: 
      int xh2 = int(random(20,width-40));
      int yh2 = int(random(cutoffh*0.2,height));
      if(pl_mode==1 || pl_mode==2){
       h2Fill.setStart(xh2,yh2);
      }
      break;

  }    

  if(fw){ 
    
    rv1 = int(random(1,3));
  
    }else{
    
    rv1=1;

  }
}
