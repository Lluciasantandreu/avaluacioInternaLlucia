package proc.avaluacioInterna;

import processing.core.PApplet;

public class Main extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    GUI gui;

    public static void main(String[] args) {
        PApplet.main("proc.avaluacioInterna.Main", args);
    }

    public void settings(){
        fullScreen();                       // Pantalla completa
        //size(1442, 900);        // Pantalla HD
        smooth(10);
    }

    public void setup(){
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI(this);                   // Constructor de la GUI


    }

    public void draw(){

       // Dibuixa la pantalla corresponent
       switch(gui.pantallaActual){
            case INICIAL:       gui.dibuixaPantallaInicial(this);
                                break;

            case LOGIN:         gui.dibuixaPantallaLogin(this);
                                break;

            case SELECCIONAR:   gui.dibuixaPantallaSeleccionar(this);
                                break;

           case HOY:            gui.dibuixaPantallaHoy(this);
                                break;

           case DETALLESHOY:    gui.dibuixaPantallaDetallesHoy(this);
                                break;

           case SEMANA:         gui.dibuixaPantallaSemana(this);
                                break;

           case MES:            gui.dibuixaPantallaMes(this);
                                break;
        }

        // Actualitza el cursor
        actualizarCursor();
    }
    public void actualizarCursor(){
        if(gui.BLogIn.actualizarCursor(this)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        else if(key=='1'){
            gui.pantallaActual = GUI.PANTALLA.LOGIN;
        }
        else if(key=='2'){
            gui.pantallaActual = GUI.PANTALLA.SELECCIONAR;
        }
        else if(key=='3'){
            gui.pantallaActual = GUI.PANTALLA.HOY;
        }
        else if(key=='4'){
            gui.pantallaActual = GUI.PANTALLA.DETALLESHOY;
        }
        else if(key=='5'){
            gui.pantallaActual = GUI.PANTALLA.SEMANA;
        }
        else if(key=='6'){
            gui.pantallaActual = GUI.PANTALLA.MES;
        }

        gui.TUsuario.keyPressed(key, (int)keyCode);
        gui.TContraseña.keyPressed(key, (int)keyCode);

    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        if(gui.BLogIn.mouseSobreBoto(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B1");
            gui.pantallaActual = GUI.PANTALLA.LOGIN;
        }
        gui.TUsuario.isPressed(this);
        gui.TContraseña.isPressed(this);
       /* else if(gui.b2.mouseSobreBoto(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B2");
        }
        else if(gui.b3.mouseSobreBoto(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B3");
        }
        else if(gui.b4.mouseSobreBoto(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B4");
            gui.b4.setActivado(false);
            gui.b4.setTextBoto("DISABLED");
        }*/
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}