package proc.avaluacioInterna;

import processing.core.PApplet;
public class Botons {
    // Propietats d'un botó:

    float x, y, w, h;  // Posició (x, y) i dimensions (w, h)
    int fillColor, strokeColor; // Colors del boto (fill / stroke).
    int fillColorSobre, fillColorFora;  // Colors del boto (actiu / inactiu).
    String textBoto;  // Text
    boolean activado;  // Estat del botó (actiu / inactiu).

    // Constructor
    public Botons(PApplet p5, String text, float x, float y, float w, float h){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.activado = true;
    }

    // Setters

    public void setActivado(boolean b){
        this.activado = b;
    }

    public void setTextBoto(String t){ this.textBoto = t; }

    public void setColors(int cFill, int cStroke, int cSobre, int cDesactivado){
        this.fillColor = cFill;
        this.strokeColor = cStroke;
        this.fillColorSobre = cSobre;
        this.fillColorFora = cDesactivado;
    }

    // Dibuixa el botó
    public void display(PApplet p5){
        p5.pushStyle();
        if(!activado){
            p5.fill(fillColorFora);  // Color desabilitat
        }
        else if(mouseSobreBoto(p5)){
            p5.fill(fillColorSobre);      // Color quan ratolí a sobre
        }
        else{
            p5.fill(fillColor);          // Color actiu però ratolí fora
        }
        p5.stroke(strokeColor); p5.strokeWeight(2);        //Color i gruixa del contorn
        p5.rect(this.x, this.y, this.w, this.h, 10);    // Rectangle del botó

        // Text (color, alineació i mida)
        p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(20);
        p5.text(textBoto, this.x + this.w/2, this.y + this.h/2 + 10);
        p5.popStyle();
    }

    // Indica si el cursor està sobre el botó
    public boolean mouseSobreBoto(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

    // Indica si cal posar el cursor a HAND
    public boolean actualizarCursor(PApplet p5){
        return mouseSobreBoto(p5) && activado;
    }
}
