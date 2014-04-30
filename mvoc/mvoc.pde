
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


color fg_color = color(int(random(5,55)),0,int(random(44,88))),
      bg_color = #DCDCDC, kill_color = fg_color, kill_t_color = fg_color,
      h1fill_color, h1fill_t_color, h2fill_color, h2fill_t_color,
      d1_color, d1_t_color, d2_color, d2_t_color, d3_color, d3_t_color,
      d4_color, d4_t_color, rnd_color, rnd_t_color;

final int FALSE = 0, TRUE = 1, MAYBE = -1;

int sw = 1, cnt = 0, speed = 2000,
    speedlimit = 4500, md = 0, cutoffh,cutoffw, rv1, 
    colrow=7, gridmin = 8, gridmax = 9, pl_mode = 2;

float ran2 = random(1.6,2.6),theta;

boolean fw = false, g_distro = false, dynamic = false;
String p_text;

ControlP5 cp5;
Textlabel myTextlabelA;
Hori1Fill h1Fill;  
Hori2Fill h2Fill; 
Dia1Fill d1Fill;
Dia2Fill d2Fill;  
Dia3Fill d3Fill; 
Dia4Fill d4Fill; 
KillFill kFill;
RndFill rFill;

//---

void setup() {
  
  
  
  
  size(displayWidth, displayHeight);
  cutoffh = int(displayHeight/10);
  cutoffw = int(displayWidth/5);
 

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
