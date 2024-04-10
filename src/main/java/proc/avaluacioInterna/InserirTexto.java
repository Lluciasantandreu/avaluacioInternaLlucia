package proc.avaluacioInterna;

import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;

public class InserirTexto {
    // Propietats del camp de text
    float x, y, h, w;

    // Colors
    // No Seleccionado,
    int NSColor, fgColor, colorSeleccionado, colorBordes;
    int grossorBordes;

    Colores tablaColores;
    Fuentes fontsApp;

    // Text del camp
    String text = "";
    int textSize = 24;

    boolean seleccionado = false;
    // Constructor
    public InserirTexto(PApplet p5, float x, float y, float w, float h, String text) {
        tablaColores = new Colores(p5);
        fontsApp = new Fuentes(p5);
        this.text = text;
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.NSColor = p5.color(tablaColores.getColorDe(0));
        this.fgColor = p5.color(200, 10, 120);
        this.colorSeleccionado = p5.color(tablaColores.getColorDe(2));
        this.colorBordes = p5.color(30, 30, 30);
        this.grossorBordes = 1;
    }

    // Dibuixa el Camp de Text
    public void display(PApplet p5) {
        p5.pushStyle();
        if (seleccionado) {
            p5.fill(colorSeleccionado);
        } else {
            p5.fill(NSColor);
        }

        p5.strokeWeight(grossorBordes);
        p5.stroke(colorBordes);
        p5.rect(x, y, w, h, 5);

        p5.fill(fgColor);
        p5.textFont(fontsApp.getFontAt(0));
        p5.textSize(textSize); p5.textAlign(p5.LEFT, p5.CENTER);
        p5.text(text, x + 5, y + h - textSize);
        p5.popStyle();
    }
    // Afegeix, lleva el text que es tecleja
    public void keyPressed(char key, int keyCode) {
        if (seleccionado) {
            if (keyCode == (int)BACKSPACE) {
                borrarTexto();
            } else if (keyCode == 32) {
                añadeTexto(' '); // SPACE
            } else {

                boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                boolean isKeyNumber = (key >= '0' && key <= '9');

                if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                    añadeTexto(key);
                }
                else if(key == ',' || key == '\'' || key == 'ñ'){
                    añadeTexto(key);
                }
            }
        }
    }

    // Afegeix la lletra c al final del text
    public void añadeTexto(char c) {
        if (this.text.length() + 1 < w) {
            this.text += c;
        }
    }

    // Lleva la darrera lletra del text
    public void borrarTexto() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    // Retorna el text
    public String getText(){
        return this.text;
    }

    // Setter del text
    public void setText(String t){
        this.text= t;
    }

    // Indica si el ratolí està sobre el camp de text
    public boolean mouseOverTextField(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y + this.h);
    }
    // Selecciona el camp de text si pitjam a sobre
    // Deselecciona el camp de text si pitjam a fora
    public void isPressed(PApplet p5) {
        if (mouseOverTextField(p5)) {
            seleccionado = true;
        } else {
            seleccionado = false;
        }
    }
}
