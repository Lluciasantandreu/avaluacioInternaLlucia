package proc.avaluacioInterna;

import processing.core.PApplet;

public class LlistaCheck {
    // Propietats
    float x, y, w;

    // Colors
    int NSColor, bordesColor, checkColor;



    boolean checked;

    // Constructor
    public LlistaCheck(PApplet p5, float x, float y, float w){
        this.x = x;
        this.y = y;
        this.w = w;
        this.checked = false;
        this.NSColor = p5.color(0xFFDEE5E5);
        this.bordesColor = p5.color(30, 30, 30);
        this.checkColor = p5.color(0xFF9DC5BB);
    }

    // Dibuixa el CheckBox
    public void display(PApplet p5){

        p5.pushStyle();
        p5.stroke(bordesColor);
        p5.strokeWeight(2);

        if(this.checked){
            p5.fill(checkColor);
        }
        else{
            p5.fill(NSColor);
        }
        p5.rect(x, y, w, w);

        if(this.checked){
            p5.line(x, y, x + w, y + w);
            p5.line(x, y+w, x + w, y);
        }
        p5.popStyle();
    }

    public void setChecked(boolean b){
        this.checked = b;
    }

    // Canvia l'estat de selecció
    public void toggle(){
        this.checked = ! this.checked;
    }


    // Mira si el ratolí està sobre el checkbox
    public boolean onMouseOver(PApplet p5){
        return  p5.mouseX>= this.x &&
                p5.mouseX<= this.x + this.w &&
                p5.mouseY>= this.y &&
                p5.mouseY<= this.y + this.w;
    }
}
