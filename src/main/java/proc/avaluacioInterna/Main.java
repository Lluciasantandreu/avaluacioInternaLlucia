package proc.avaluacioInterna;

import processing.core.PApplet;

import java.io.File;


public class Main extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    GUI gui;
    DataBase bbdd;
    String idTipo;

    PopUp popUp;

    public static void main(String[] args) {
        PApplet.main("proc.avaluacioInterna.Main", args);

    }

    public void settings(){
        fullScreen();              // Pantalla completa
        //size(1442, 900);        // Pantalla HD
        smooth(10);
    }

    public void setup(){
        noStroke();                        // Sense bordes
        bbdd = new DataBase("admin", "12345", "recetas");
        bbdd.connect();
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI(this, bbdd);     // Constructor de la GUI
        idTipo = "";

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

           case SELECCIONRECETA:            gui.dibuixaPantallaHoy(this);
                                break;

           case INSERIR:    gui.dibuixaPantallaInserir(this);
                                break;

           case SEMANA:         gui.dibuixaPantallaSemana(this);
                                break;

           case MES:            gui.dibuixaPantallaMes(this);
                                break;

           case CONFIGURACION:  gui.dibuixaPantallaConfiguración(this);
                                break;

           case RECETAS:      gui.dibuixaPantallaRecetas(this);
                                break;

           case FAVORITOS:      gui.dibuixaPantallaFavoritos(this);
                                break;

           case CUENTA:         gui.dibuixaPantallaCuenta(this);
                                break;

           case INFORMACION:    gui.dibuixaPantallaInformacion(this);
                                break;

           case DETALLESRECETA: gui.dibuixaPantallaDetallesReceta(this);
                                break;

        }

        // Actualitza el cursor
        actualizarCursor();
    }

    public void fileSelected (File selection){
        if(selection ==null){
            System.out.println("No se ha seleccionado ningún documento");
        }
        else{
            String rutaImagen = selection.getAbsolutePath();

            gui.imagen = loadImage (rutaImagen);
            gui.titulo = selection.getName();
        }
    }
    public void actualizarCursor(){
        if(gui.BLogIn.actualizarCursor(this)||gui.BEntrar.actualizarCursor(this) || gui.MConfiguracion.actualizarCursor(this) || gui.MCrear.actualizarCursor(this) || gui.MMes.actualizarCursor(this) || gui.MSemana.actualizarCursor(this) || gui.MHome.actualizarCursor(this)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }


    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        if(key == '<'){
            gui.pantallaActual = GUI.PANTALLA.INSERIR;
        }
        gui.TUsuario.keyPressed(key, keyCode);
        gui.TContraseña.keyPressed(key, keyCode);
        gui.TNombre.keyPressed(key, keyCode);
        for (int i = 0; i < gui.Ingredients.length; i++){
            gui.TIngredients[i].keyPressed(key, keyCode);
        }
        for (int i = 0; i < gui.TCantidades.length; i++){
            gui.TCantidades[i].keyPressed(key, keyCode);
        }
        gui.CUsuario.keyPressed(key, keyCode);
        gui.CContraseña.keyPressed(key, keyCode);
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){

        if (gui.pantallaActual == GUI.PANTALLA.CONFIGURACION){
            if(gui.BRecetas.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.RECETAS;
            }
            if(gui.BCuenta.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.CUENTA;
            }
            if(gui.BInformación.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.INFORMACION;
            }
        }

        if(gui.pantallaActual != GUI.PANTALLA.CONFIGURACION & gui.pantallaActual != GUI.PANTALLA.RECETAS & gui.pantallaActual != GUI.PANTALLA.CUENTA & gui.pantallaActual != GUI.PANTALLA.INFORMACION & gui.pantallaActual != GUI.PANTALLA.LOGIN & gui.pantallaActual != GUI.PANTALLA.INICIAL){
            if(gui.MHome.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.SELECCIONAR;
            }
            if(gui.MCrear.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.SELECCIONRECETA;
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
            String userName = gui.TUsuario.getText();
            String password = gui.TContraseña.getText();

            if(bbdd.isValidUser(userName, password)){
                gui.BEntrar.activado = true;
            }

            if(!bbdd.isValidUser(userName, password)){
                gui.BEntrar.activado = false;
                if(gui.BEntrar.mouseSobreBoto(this)){
                    gui.p1.setVisible(true);
                }
            }

            if(gui.BEntrar.mouseSobreBoto(this) && gui.BEntrar.activado){
                println("HAS FET CLIC SOBRE EL BOTÓ BEntrar");
                gui.pantallaActual = GUI.PANTALLA.SELECCIONAR;
            }

            if(gui.p1.bAceptar.mouseSobreBoto(this)){
                gui.p1.setVisible(false);
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.SELECCIONRECETA) {
            if (gui.BCena.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BCena");
                gui.pantallaActual = GUI.PANTALLA.INSERIR;
                idTipo = "Cena";
            }

            if (gui.BComida.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BComida");
                gui.pantallaActual = GUI.PANTALLA.INSERIR;
                idTipo = "Comida";
            }

            if (gui.BPostre.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BPostre");
                gui.pantallaActual = GUI.PANTALLA.INSERIR;
                idTipo = "Postre";
            }

            if (gui.BDesayuno.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BDesayuno");
                gui.pantallaActual = GUI.PANTALLA.INSERIR;
                idTipo = "Desayuno";
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.INSERIR){

            if(gui.BImagen.mouseSobreBoto(this)){

            }
            if(gui.BImagen.mouseSobreBoto(this)){
                selectInput("Selecciona una imagen...", "fileSelected");
            }


            if(!gui.Unidades.isCollapsed()) {
                gui.Unidades.update(this);
            }

            else if(!gui.Unidades1.isCollapsed()) {
                gui.Unidades1.update(this);
            }

            else if(!gui.Unidades2.isCollapsed()) {
                gui.Unidades2.update(this);
            }

            else if(!gui.Unidades3.isCollapsed()) {
                gui.Unidades3.update(this);
            }

            else if(!gui.Unidades4.isCollapsed()) {
                gui.Unidades4.update(this);
            }

            else if(!gui.Unidades5.isCollapsed()) {
                gui.Unidades5.update(this);
            }


            if(gui.Unidades.mouseOverSelect(this)) {
                gui.Unidades.toggle();
            }

            else if(gui.Unidades1.mouseOverSelect(this)) {
                gui.Unidades1.toggle();
            }

            else if(gui.Unidades2.mouseOverSelect(this)) {
                gui.Unidades2.toggle();
            }

            else if(gui.Unidades3.mouseOverSelect(this)) {
                gui.Unidades3.toggle();
            }

            else if(gui.Unidades4.mouseOverSelect(this)) {
                gui.Unidades4.toggle();
            }

            else if(gui.Unidades5.mouseOverSelect(this)) {
                gui.Unidades5.toggle();
            }



           if(gui.BGuardar.mouseSobreBoto(this)){
               System.out.println("Has fet click sobre el botó Guardar");
               if(gui.c.dateSelected) {
                   if(gui.imagen != null){
                       bbdd.addRecetaImagen(gui.TNombre.getText(), idTipo, gui.titulo, gui.c.getSelectedDate2());
                   }
                   else {
                       bbdd.addReceta(gui.TNombre.getText(), idTipo, gui.c.getSelectedDate2());
                   }

                   for(int i=0; i<=gui.TIngredients.length-1; i++){
                       bbdd.addIngredientes(gui.TIngredients[i].getText());
                       String id = bbdd.getClaveFromTabla("RECETA", "idRECETA", "nombre", gui.TNombre.getText());
                       String u;
                       if(i==0){
                           u = bbdd.getClaveFromTabla("UNIDADES", "idUNIDADES", "nombre", gui.Unidades.getSelectedValue());
                       }
                       else if(i==1) {
                           u = bbdd.getClaveFromTabla("UNIDADES", "idUNIDADES", "nombre", gui.Unidades1.getSelectedValue());
                       }
                       else if(i==2) {
                           u = bbdd.getClaveFromTabla("UNIDADES", "idUNIDADES", "nombre", gui.Unidades2.getSelectedValue());
                       }
                       else if(i==3) {
                           u = bbdd.getClaveFromTabla("UNIDADES", "idUNIDADES", "nombre", gui.Unidades3.getSelectedValue());
                       }
                       else if(i==4) {
                           u = bbdd.getClaveFromTabla("UNIDADES", "idUNIDADES", "nombre", gui.Unidades4.getSelectedValue());
                       }
                       else{
                           u = bbdd.getClaveFromTabla("UNIDADES", "idUNIDADES", "nombre", gui.Unidades5.getSelectedValue());
                       }
                       bbdd.addIngredientesReceta(gui.TIngredients[i].getText(), id, gui.TCantidades[i].getText(), u);
                   }


               }
               if(!gui.c.isDateSelected()){
                   gui.p3.setVisible(true);
               }
               else {
                   gui.p2.setVisible(true);
               }
           }

            if(gui.p2.bAceptar.mouseSobreBoto(this)){
                gui.p2.setVisible(false);
            }
            if(gui.p3.bAceptar.mouseSobreBoto(this)){
                gui.p3.setVisible(false);
            }

           if(gui.BFavorita.mouseSobreBoto(this)){
               System.out.println("Has fet click sobre el botó Favorita");
               String id = bbdd.getClaveFromTabla("RECETA", "idRECETA", "nombre", gui.TNombre.getText());
               if(id != null){
                   gui.p4.setVisible(true);
                   bbdd.addRecetaFavorita(id, gui.TUsuario.getText());
               }
               else{
                   gui.p6.setVisible(true);
               }
           }
            if(gui.p4.bAceptar.mouseSobreBoto(this)){
                gui.p4.setVisible(false);
            }
            if(gui.p6.bAceptar.mouseSobreBoto(this)){
                gui.p6.setVisible(false);
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
            else if(gui.d.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.INSERIR;
            }
            else if(gui.i.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.SELECCIONRECETA;
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.SEMANA){
            gui.s.checkButtons(this);
            if(gui.ds.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.DETALLESRECETA;
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.SELECCIONAR) {
            if (gui.BHoy.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BHoy");
                gui.pantallaActual = GUI.PANTALLA.SELECCIONRECETA;
            }
            else if (gui.BSemana.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BSemana");
                gui.pantallaActual = GUI.PANTALLA.SEMANA;
            }
            else if (gui.BMes.mouseSobreBoto(this)) {
                println("HAS FET CLIC SOBRE EL BOTÓ BMes");
                gui.pantallaActual = GUI.PANTALLA.MES;
            }
        }


        if(gui.pantallaActual == GUI.PANTALLA.CONFIGURACION || gui.pantallaActual == GUI.PANTALLA.RECETAS || gui.pantallaActual == GUI.PANTALLA.CUENTA || gui.pantallaActual == GUI.PANTALLA.INFORMACION){
            if(gui.BInicio.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.SELECCIONAR;
            }
        }


        if(gui.pantallaActual == GUI.PANTALLA.CUENTA){
            if(gui.BConfirmar.mouseSobreBoto(this)){
                bbdd.updateInfoTablaUsuario(gui.TUsuario.getText(), gui.CUsuario.getText(), gui.CContraseña.getText());
            }
        }


        if(gui.pantallaActual == GUI.PANTALLA.RECETAS){
            if(gui.Bant.mouseSobreBoto(this)){
                gui.r.prevPage();
            }
            else if(gui.Bpost.mouseSobreBoto(this)) {
                gui.r.nextPage();
            }

            if(gui.BFavoritos.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.FAVORITOS;
            }
        }

        if(gui.pantallaActual == GUI.PANTALLA.FAVORITOS){
            if(gui.BAtras.mouseSobreBoto(this)){
                gui.pantallaActual = GUI.PANTALLA.RECETAS;
            }
            if(gui.Bant1.mouseSobreBoto(this)){
                gui.f.prevPage();
            }
            else if(gui.Bpost1.mouseSobreBoto(this)) {
                gui.f.nextPage();
            }

        }



        gui.TUsuario.isPressed(this);
        gui.TContraseña.isPressed(this);
        gui.TNombre.isPressed(this);
        gui.CUsuario.isPressed(this);
        gui.CContraseña.isPressed(this);
        for(int i=0; i<gui.TCantidades.length; i++){
            gui.TCantidades[i].isPressed(this);
        }


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