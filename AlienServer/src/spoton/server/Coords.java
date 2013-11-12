package spoton.server;

public class Coords {
        int x;
        int y;
        String s;
        
                public Coords(int x, int y){
                        this.x = x;
                        this.y = y;
                }
                
                public Coords(String s){
                        this.s = s;
                }
                
                public int getX(){
                        return x;
                }
                
                public int getY(){
                        return y;
                }
}