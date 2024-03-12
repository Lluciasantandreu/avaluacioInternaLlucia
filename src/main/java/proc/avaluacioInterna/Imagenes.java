package proc.avaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

import static proc.avaluacioInterna.Mides.*;
public class Imagenes {

    // Array de tipografies
    PImage[] imagenes;

    // Constructor de les Fonts de l'App
    public Imagenes(PApplet p5){
        this.setImagenes(p5);
    }

    // Estableix les fonts de l'App
    public void setImagenes(PApplet p5){
        this.imagenes = new PImage[3];
        this.imagenes[0] = p5.loadImage("data/imagenInferior.png");
        //this.imagenes[1] = p5.loadImage("data/Tipografia1.2.otf");
        //this.imagenes[2] = p5.loadImage("data/Tipografia2.ttf");
    }


    // Getter de la imatge i-èssima
    public PImage getImageAt(int i){
        return this.imagenes[i];
    }
}