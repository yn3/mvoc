void speeds(){
  
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
    speed=int(random(3,300));
  }
  
  if(fw){ 
    
  rv1 = int(random(1,3));
  
  }else{
    
  rv1=1;

  }
}
