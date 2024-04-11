package proc.avaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

public class Imagenes {

    // Array de tipografies
    PImage[] imagenes;

    // Constructor de les Fonts de l'App
    public Imagenes(PApplet p5){
        this.setImagenes(p5);
    }

    // Estableix les fonts de l'App
    public void setImagenes(PApplet p5){
        this.imagenes = new PImage[4];
        this.imagenes[0] = p5.loadImage("data/imagenInferior.png");
        this.imagenes[1] = p5.loadImage("data/imagenInicial.jpeg");
        this.imagenes[2] = p5.loadImage("data/imagenCocina.png");
        this.imagenes[3] = p5.loadImage("data/imagenLogo.png");
    }


    // Getter de la imatge i-èssima
    public PImage getImageAt(int i){
        return this.imagenes[i];
    }
}