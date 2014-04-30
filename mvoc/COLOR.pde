void colr(){

  
cnt++;
cnt=cnt%235;

  h1fill_color = color(int(random(20,cnt+20)),int(random(20,cnt+20)),cnt+20);
  h1fill_t_color = color(int(random(20,cnt+20)),int(random(20,cnt+20)),cnt+20);
  
  h2fill_color = color(30,int(random(30,cnt-130)),int(random(30,cnt+20)));
   if(pl_mode==2){
    h2fill_t_color = color(255,int(random(200,cnt)),int(random(130,cnt+20)));
  }else{
  h2fill_t_color = color(30,int(random(30,cnt-130)),int(random(30,cnt+20)));
  }
  
  d1_color = color(int(random(20,cnt+20)),cnt+20,int(random(20,cnt+20)));
  if(pl_mode==2){
    d1_t_color = color(cnt+20,cnt,0);
  }else{
  d1_t_color = color(int(random(20,cnt+20)),cnt+20,int(random(20,cnt+20)));
  }
  d2_color = color(cnt+20,int(random(20,cnt+20)),120);
  d2_t_color = color(int(random(20,cnt+20)),255,int(random(20,cnt+20)));
  
  d3_color = color(int(random(0,cnt/2+1)),cnt+20,int(random(20,cnt+20)));
  d3_t_color = color(int(random(0,cnt/5+1)),cnt/1.2,cnt/1.2);
  
  d4_color = color(int(random(100,cnt+20)),33,0);
  d4_t_color = color(int(random(100,cnt+20)),33,0);
  
  rnd_color = color(0,25,17);
  rnd_t_color = color(int(random(20,cnt+20)),cnt+20,int(random(20,cnt+20)));

}
