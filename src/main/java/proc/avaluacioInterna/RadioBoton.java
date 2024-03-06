package proc.avaluacioInterna;

import processing.core.PApplet;
import processing.core.PConstants;

public class RadioBoton {



    // Propietats
    int x, y, r;

    // Colors
    int bgColor, borderColor, checkedColor;

    boolean checked;

    // Constructor
    public RadioBoton(PApplet p5, int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
        this.checked = false;
        this.bgColor = p5.color(255);
        this.borderColor = p5.color(0);
        this.checkedColor = p5.color(180);
    }

    // Getter
    public  boolean isChecked(){
        return  this.checked;
    }

    public void setColors(int bgColor, int borderColor, int checkedColor){
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.checkedColor = checkedColor;
    }


    // Dibuixa el CheckBox
    public void display(PApplet p5){

        p5.pushStyle();
        p5.ellipseMode(PConstants.CENTER);
        p5.stroke(borderColor);
        p5.strokeWeight(2);
        p5.fill(bgColor);
        p5.ellipse(x, y, 2f*r, 2f*r);

        if(this.checked){
            p5.ellipseMode(PConstants.CENTER);
            p5.fill(checkedColor); p5.noStroke();
            p5.ellipse(x, y, 1.5f*r, 1.5f*r);
        }
    }

    public void setChecked(boolean b){
        this.checked = b;
    }

    // Canvia l'estat de selecció
    public void toggle(){
        this.checked = ! this.checked;
    }


    // Mira si el ratolí està sobre el checkbox
    public boolean mouseSobreBoto(PApplet p5){
        return  p5.dist(p5.mouseX, p5.mouseY, this.x, this.y) < this.r;
    }
}