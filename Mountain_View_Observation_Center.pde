
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


                                     */

import java.util.Stack;
import controlP5.*;

color fg_color = #00003f;
color bg_color = #ffffde;
color kill_color = #00003f;
color kill_t_color = #00003f;
color hfill_color,hfill_t_color,d1_color,d1_t_color,
      d2_color,d2_t_color,rnd_color,rnd_t_color;


final int FALSE = 0, TRUE = 1, MAYBE = -1;

int sw = 1, cnt = 0, speed = 7000,
    speedlimit = 4500, md = 0, cutoffh,cutoffw, rv1, gridmin = 1, gridmax = 8;

float ran2 = random(1.6,2.6);
boolean fw = false, g_distro = false;



ControlP5 cp5;

HoriFill hFill;  
Dia1Fill d1Fill;
Dia2Fill d2Fill;  
KillFill kFill;
RndFill rFill;

void setup() {
  
  size(displayWidth, displayHeight);
  cutoffh = int(displayHeight/10);
  cutoffw = int(displayWidth/6);
 
  reset();
  
  //* android setup *//
  orientation(LANDSCAPE);
  
  //* gui *//
  guimods();
  

}


void draw() {

speeds();
colr();  
select();


}


color get(int[] v) {
  return get(v[0], v[1]); 
}

void set(int[] v, color c) {
  set(v[0], v[1], c);
}



