package proc.avaluacioInterna;

import processing.core.PApplet;

public class Colors {

    private int[] colors;

    public Colors(PApplet p5){
        this.setColores(p5);
    }

    // Estableix colors de l'App
    void setColores(PApplet p5){
        this.colors = new int[6];
        this.colors[0] = p5.color(0xFFDEE5E5);
        this.colors[1] = p5.color(0xFFBED5D0);
        this.colors[2] = p5.color(0xFF9DC5BB);
        this.colors[3] = p5.color(0xFF53938D);
        this.colors[4] = p5.color(0xFF08605F);
        this.colors[5] = p5.color(0xFF0D0908);
    }

    // Getter del número de colors
    public int getNumColores(){
        return this.colors.length;
    }


    // Getter del color i-èssim
    public int getColorDe(int i){
        return this.colors[i];
    }


}