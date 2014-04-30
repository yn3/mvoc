
void guimods(){
  
  

  
   cp5 = new ControlP5(this); 
   cp5.addToggle("pause")
     .setPosition(displayWidth-cutoffw*0.48,int(cutoffh/3))
     .setSize(int(cutoffw*0.4),int(cutoffh/1.5))
     .setValue(false)
     .setLabel("Start/Pause")
     .setColorCaptionLabel(234);
   cp5.getController("pause").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);      
   cp5.addBang("gridbang")
     .setPosition(displayWidth-cutoffw, int(cutoffh/3))
     .setSize(int(cutoffw*0.36), int(cutoffh/1.8))
     .setLabel("Switch Screens")
     .setColorCaptionLabel(234);
   cp5.getController("gridbang").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);    
    
   cp5.addSlider("detail")
     .setPosition(displayWidth-cutoffw*3.82,int(cutoffh/3))
     .setSize(int(cutoffw*0.5),int(cutoffh/1.8)+2)
     .setRange(0,100)
     .setValue(50)
     .setLabel("Zoomify")
     .setColorCaptionLabel(234);
   cp5.getController("detail").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
   
   cp5.addToggle("dynamic")
     .setPosition(displayWidth-cutoffw*3.26,int(cutoffh/3))
     .setSize(int(cutoffw*0.2),int(cutoffh/1.8))
     .setValue(false)
     .setLabel("EVOLUTION")
     .setColorCaptionLabel(234);
    cp5.getController("dynamic").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);   
     
   cp5.addSlider("gridsize")
     .setPosition(displayWidth-cutoffw*3.01,int(cutoffh/3))
     .setSize(int(cutoffw*0.4),int(cutoffh/1.8))
     .setRange(1,25)
     .setValue(8)
     .setColorLabel(fg_color-2)
     .setLabel("# of Screens");   
   cp5.getController("gridsize").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
     
   cp5.addToggle("evenGrid")
     .setPosition(displayWidth-cutoffw*1.27,int(cutoffh/3))
     .setSize(int(cutoffw*0.2),int(cutoffh/1.8))
     .setValue(false)
     .setLabel("Focus")
     .setColorCaptionLabel(234); 
   cp5.getController("evenGrid").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);  
     
     
   cp5.addToggle("fancyWater")
    .setPosition(displayWidth-cutoffw*2.55,int(cutoffh/3))
     .setSize(int(cutoffw*0.2),int(cutoffh/1.8))
     .setValue(false)
     .setLabel("Fancyness")
     .setColorCaptionLabel(234);
  cp5.getController("fancyWater").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);     
     
   cp5.addSlider("colorset")
    .setPosition(displayWidth-cutoffw*2.2,int(cutoffh/3))
     .setSize(int(cutoffw*0.6),int(cutoffh/1.8))
     .setValue(0)
     .setRange(0,2)
     .setNumberOfTickMarks(3)
     .setLabel("Switch Planet")
     .setColorCaptionLabel(234);   
   cp5.getController("colorset").getCaptionLabel().align(ControlP5.CENTER, ControlP5.BOTTOM_OUTSIDE);
 
   cp5.addTextlabel("planet1")   
     .show() 
     .setText("Retro")
     .setPosition(displayWidth-cutoffw*1.55,int(cutoffh/2.5))
     .setColorValue(fg_color); 
   cp5.addTextlabel("planet2")
     .hide() 
     .setText("Greenlands")
     .setPosition(displayWidth-cutoffw*1.55,int(cutoffh/1.65))
     .setColorValue(fg_color);
   cp5.addTextlabel("planet3")
     .hide() 
     .setText("Desert")
     .setPosition(displayWidth-cutoffw*1.55,int(cutoffh/1.25))
     .setColorValue(fg_color); 

  

}


void gridbang() {
    reset();
    ran2 = random(1.6,2.6);
}

void pause(boolean active) {
  if(active) {
    md=1;
  } else {
    md=0;
  }

}

void detail(float levelOf) {
  
  speedlimit=int(pow(levelOf,2)+200);

}


void fancyWater(boolean active) {
  if(active) {
    fw=true;
  } else {
    fw=false;
  }

}


void evenGrid(boolean active) {
  if(active) {
    g_distro=true;
  } else {
    g_distro=false;
  }

}


void gridsize(float screens){
  gridmin=int(screens);
  gridmax=int(screens*1.5);
  
}
void dynamic(boolean active){
dynamic=active;
  

}

void colorset(int levelOf){
  pl_mode=levelOf;
  
  switch(levelOf){
    case 0: 
    
    fill(bg_color);
    rect(displayWidth-cutoffw*1.55,int(cutoffh/2.5),cutoffw/5,int(cutoffh/2));
    cp5.controller("planet1").show();
    cp5.controller("planet2").hide(); 
    cp5.controller("planet3").hide();
    break;
    case 1:
     fill(bg_color);
     rect(displayWidth-cutoffw*1.55,int(cutoffh/2.5),cutoffw/5,int(cutoffh/2));
    cp5.controller("planet2").show();
    cp5.controller("planet1").hide(); 
    cp5.controller("planet3").hide();
    
    break;
    case 2:
    fill(bg_color);
    rect(displayWidth-cutoffw*1.55,int(cutoffh/2.5),cutoffw/5,int(cutoffh/2));
    cp5.controller("planet3").show();
    cp5.controller("planet1").hide(); 
    cp5.controller("planet2").hide();
    break;
  }
  

 
  
}

