
void guimods(){
  
   cp5 = new ControlP5(this); 
   cp5.addToggle("pause")
     .setPosition(cutoffw,int(cutoffh/3))
     .setSize(190,int(cutoffh/1.8))
     .setMode(ControlP5.SWITCH)
     .setValue(false)
     .setLabel("                                   Start / Stop")
     .setColorCaptionLabel(234);
   cp5.addBang("gridbang")
       .setPosition(cutoffw*2, int(cutoffh/3))
       .setSize(100, int(cutoffh/1.8))
       .setLabel("             Switch Grid")
       .setColorCaptionLabel(234);
   cp5.addSlider("detail")
     .setPosition(cutoffw*2.7,int(cutoffh/3))
     .setSize(200,int(cutoffh/1.8)+2)
     .setRange(0,100)
     .setValue(50)
     .setLabel("        FILLSIZE")
     .setColorCaptionLabel(234);
   cp5.getController("detail").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
   cp5.addToggle("fancyWater")
     .setPosition(cutoffw*3.75,int(cutoffh/3))
     .setSize(100,int(cutoffh/1.8))
     .setValue(false)
     .setMode(ControlP5.SWITCH)
     .setLabel("             Fancy Water")
     .setColorCaptionLabel(234); 
   cp5.addRange("gridsize")
     .setPosition(cutoffw*4.4,int(cutoffh/3))
     .setSize(200,int(cutoffh/1.8))
     .setHandleSize(1)
     .setRange(1,40)
     .setRangeValues(1,8)
     .setColorLabel(fg_color-2)
     .setLabel("            # of Screens")
     ;
   cp5.getController("gridsize").getCaptionLabel().align(ControlP5.RIGHT, ControlP5.BOTTOM_OUTSIDE);
    cp5.addToggle("evenGrid")
     .setPosition(cutoffw*5.4,int(cutoffh/3))
     .setSize(100,int(cutoffh/1.8))
     .setValue(false)
     .setMode(ControlP5.SWITCH)
     .setLabel("               Even Grid")
     .setColorCaptionLabel(234); 
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
  
  speedlimit=int(levelOf*42+200);

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


void controlEvent(ControlEvent theControlEvent) {
  if(theControlEvent.isFrom("gridsize")) {
    gridmin = int(theControlEvent.getController().getArrayValue(0));
    gridmax = int(theControlEvent.getController().getArrayValue(1));
  }
  
}

