void reset() {

  background(bg_color);

    fill(fg_color); noStroke();
    rect(20, 20+cutoffh, width-40, displayHeight-cutoffh-40);

   int colrow = int(random(gridmin,gridmax));
   
   for(int i = 0; i < colrow; i++) {
     
    int distrox = (width/colrow)*i;
    int distroy = (height/colrow)*i;
    float r = random(10, 209);
    float x = random(r, width - r);
    float y = random(r+cutoffh, height - r);
    
 
    stroke(bg_color);
    strokeWeight(int(random(2,4)));
    if(g_distro){
    line(distrox,0,distrox,height); 
    line(0,distroy,width,distroy); 
    }else{
    line(x,0,x,height); 
    line(0,y,width,y);  
    }
    
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
  textSize(int(cutoffw/5));
  text("MVOC", 20,int(cutoffh/3)+52);


  hFill = new HoriFill();
  d1Fill = new Dia1Fill();
  d2Fill = new Dia2Fill();
  kFill = new KillFill();
  rFill = new RndFill(); 
}
