

void reset() {
  

  background(bg_color);

  
   fill(fg_color); noStroke();
   rect(20, 20+cutoffh, width-40, displayHeight-cutoffh-40);

   colrow = int(random(gridmin,gridmax));
   int distrox, distroy;
   float r,x,y,r2,x2,y2;
   for(int i = 0; i < colrow; i++) {
     
    distrox = (width/colrow)*i;
    distroy = (height/colrow)*i;
    r = random(10, 209);
    x = random(r, width - r);
    y = random(r+cutoffh, height - r);
    
    stroke(bg_color);
    strokeWeight(int(random(2,6)));
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
    rect(x2-(cutoffw*0.2), y2, r2, r2);
  

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
  textSize(int(cutoffw/3));
  
  text("MVOC", 18,int(cutoffh*1.1));
  fill(bg_color);
  text("MVOC", 20,int(cutoffh*1.1));
  fill(fg_color);
  text("MVOC", 22,int(cutoffh*1.1));
  fill(bg_color);
  text("MVOC", 24,int(cutoffh*1.1));
  fill(h1fill_color+100);
  text("MVOC", 26,int(cutoffh*1.1));


  
   strokeWeight(4); 
   stroke(h1fill_color+100);
   noFill();
   rect(cutoffw*1.11, cutoffh*0.2, displayWidth*0.665, cutoffh*0.94,10,10,10,10);
   stroke(fg_color);
   strokeWeight(6); 
   rect(cutoffw*0.02, 5, displayWidth*0.993, displayHeight*0.99,10,10,10,10);
   strokeWeight(2); 
   stroke(bg_color);
   rect(cutoffw*1.01, cutoffh*0.2, displayWidth*0.665, cutoffh*0.94,10,10,10,10);
   rect(cutoffw*0.3, cutoffh*0.2, cutoffw, cutoffh*0.94,10,10,10,10);
   rect(cutoffw*0.35, cutoffh*0.2, cutoffw*0.36, cutoffh*0.94,0,0,0,0); 
   
   
   
}




