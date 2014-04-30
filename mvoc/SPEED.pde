void speeds(){

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

        speed = 40+int((sin(theta) * dia/2)+dia/2);
    
          if(speed > 500){
      
            theta+= 0.01;
    
          }else{
      
            theta+=0.001; 
      
          }
    
        break;
      }
   }
  
}
