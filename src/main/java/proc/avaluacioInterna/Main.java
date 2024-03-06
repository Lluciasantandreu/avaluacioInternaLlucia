package proc.avaluacioInterna;

import processing.core.PApplet;

public class Main extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    GUI gui;
    DataBase bbdd;

    public static void main(String[] args) {
        PApplet.main("proc.avaluacioInterna.Main", args);
    }

    public void settings(){
        fullScreen();              // Pantalla completa
        //size(1442, 900);        // Pantalla HD
        smooth(10);
    }

    public void setup(){
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI(this);          // Constructor de la GUI

        bbdd = new DataBase("admin", "12345", "recetas");
        bbdd.connect();

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

           case CONFIGURACION:  gui.dibuixaPantallaConfiguración(this);
                                break;

           case FAVORITOS:      gui.dibuixaPantallaFavoritos(this);
                                break;

           case CUENTA:         gui.dibuixaPantallaCuenta(this);
                                break;

           case INFORMACION:    gui.dibuixaPantallaInformacion(this);
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

        gui.TUsuario.keyPressed(key, keyCode);
        gui.TContraseña.keyPressed(key, keyCode);
        gui.TNombre.keyPressed(key, keyCode);
        gui.TReceta.keyPressed(key, keyCode);
        for (int i = 0; i < gui.Ingredients.length; i++){
            gui.TIngredients[i].keyPressed(key, keyCode);
        }
        gui.CUsuario.keyPressed(key, keyCode);
        gui.CContraseña.keyPressed(key, keyCode);
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){



        if (gui.pantallaActual == GUI.PANTALLA.CONFIGURACION){
            if(gui.BFavoritos.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.FAVORITOS;
            }
            if(gui.BCuenta.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.CUENTA;
            }
            if(gui.BInformación.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.INFORMACION;
            }
        }

        if(gui.pantallaActual != GUI.PANTALLA.CONFIGURACION & gui.pantallaActual != GUI.PANTALLA.FAVORITOS & gui.pantallaActual != GUI.PANTALLA.CUENTA & gui.pantallaActual != GUI.PANTALLA.INFORMACION & gui.pantallaActual != GUI.PANTALLA.LOGIN & gui.pantallaActual != GUI.PANTALLA.INICIAL){
            if(gui.MHome.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.SELECCIONAR;
            }
            if(gui.MHoy.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.HOY;
            }
            if(gui.MSemana.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.SEMANA;
            }
            if(gui.MMes.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.MES;
            }
            if(gui.MConfiguracion.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.CONFIGURACION;
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.INICIAL){
            if(gui.BLogIn.mouseSobreBoto(this)){
                println("HAS FET CLIC SOBRE EL BOTÓ BLogIn");
                gui.pantallaActual = GUI.PANTALLA.LOGIN;
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.LOGIN){
            if(gui.BEntrar.mouseSobreBoto(this)){
                println("HAS FET CLIC SOBRE EL BOTÓ BEntrar");
                gui.pantallaActual = GUI.PANTALLA.SELECCIONAR;
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.HOY) {
            if (gui.BCena.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BCena");
                gui.pantallaActual = GUI.PANTALLA.DETALLESHOY;
            }

            if (gui.BComida.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BComida");
                gui.pantallaActual = GUI.PANTALLA.DETALLESHOY;
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.DETALLESHOY){
            if(gui.BFavorito.mouseSobreBoto(this)){
                gui.BFavorito.toggle();
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.MES){
            gui.c.checkButtons(this);
            if(gui.ant.mouseSobreBoto(this)){
                gui.c.prevMonth();
            }
            else if(gui.post.mouseSobreBoto(this)){
                gui.c.nextMonth();
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.SELECCIONAR) {
            if (gui.BHoy.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BHoy");
                gui.pantallaActual = GUI.PANTALLA.HOY;
            }
            if (gui.BSemana.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BSemana");
                gui.pantallaActual = GUI.PANTALLA.SEMANA;
            }
            if (gui.BMes.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BMes");
                gui.pantallaActual = GUI.PANTALLA.MES;
            }
        }


        if(gui.pantallaActual == GUI.PANTALLA.CONFIGURACION || gui.pantallaActual == GUI.PANTALLA.FAVORITOS || gui.pantallaActual == GUI.PANTALLA.CUENTA || gui.pantallaActual == GUI.PANTALLA.INFORMACION){
            if(gui.BInicio.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.SELECCIONAR;
            }
        }




        gui.TUsuario.isPressed(this);
        gui.TContraseña.isPressed(this);
        gui.TNombre.isPressed(this);
        gui.TReceta.isPressed(this);
        gui.CUsuario.isPressed(this);
        gui.CContraseña.isPressed(this);


        for (int i = 0; i < gui.Ingredients.length; i++){
                gui.TIngredients[i].isPressed(this);
        }

        for (int i = 0; i < gui.Ingredients.length; i++){
            if(gui.Ingredients[i].onMouseOver(this)) {
                gui.Ingredients[i].toggle();
            }
        }

    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}