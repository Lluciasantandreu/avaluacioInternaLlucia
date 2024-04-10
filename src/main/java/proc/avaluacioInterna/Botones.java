package proc.avaluacioInterna;

import processing.core.PApplet;
public class Botones {

    // Propiedades de un botón:
    float x, y, w, h;  // Posición (x, y) i dimensiones (w, h)
    int fillColor, strokeColor; // Colores del boton (fill / stroke).
    int fillColorSobre, fillColorFuera;  // Colores del boton (actiu / inactiu).

    int stroke = 2;
    String textBoto;  // Texto
    boolean activado;  // Estado del botón (activo / inactivo)

    // Constructor
    public Botones(PApplet p5, String text, float x, float y, float w, float h){
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
        this.fillColorFuera = cDesactivado;
    }

    public void setStroke(int m){
        this.stroke = m;
    }

    // Dibuja el botón
    public void display(PApplet p5){
        p5.pushStyle();
        if(!activado){
            p5.fill(fillColorFuera);  // Color desabilitado
        }
        else if(mouseSobreBoto(p5)){
            p5.fill(fillColorSobre);      // Color cuando mouse sobre
        }
        else{
            p5.fill(fillColor);          // Color activo pero mouse fuera
        }
        p5.stroke(strokeColor); p5.strokeWeight(stroke);        //Color i grosor del borde
        p5.rect(this.x, this.y, this.w, this.h, 10);    // Rectangulo del botón

        // Texto (color, alineación i medidas)
        p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(20);
        p5.text(textBoto, this.x + this.w/2, this.y + this.h/2 + 10);
        p5.popStyle();
    }

    // Indica si el cursor está sobre el botón
    public boolean mouseSobreBoto(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

    // Indica si cal posar el cursor a HAND
    public boolean actualizarCursor(PApplet p5){
        return mouseSobreBoto(p5) && activado;
    }
}
