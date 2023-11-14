package proc.avaluacioInterna;

import processing.core.PApplet;

import static proc.avaluacioInterna.Mides.*;
import static proc.avaluacioInterna.Layout.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, LOGIN, SELECCIONAR, HOY, DETALLESHOY, SEMANA, MES};
    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Colors i Fonts de l'APP
    Colors tablaColores;
    Fonts fontsApp;

    Botons BLogIn;
    InserirText TUsuario;
    InserirText TContraseña;

    // Constructor de la GUI
    public GUI(PApplet p5){
        pantallaActual = PANTALLA.INICIAL;
        tablaColores = new Colors(p5);   // Constructor dels colors de l'App
        fontsApp = new Fonts(p5);     // Constructor de les fonts de l'App

        BLogIn = new Botons (p5, "LogIn", logInX, margeV, logInH, logInV);
        BLogIn.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));

        TUsuario = new InserirText (p5, 200, 350, textX, textY);
        TContraseña = new InserirText (p5, 200, 450, textX, textY);
    }


    // PANTALLES DE LA GUI

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaImatgeInicial(p5);

        BLogIn.display(p5);
    }

    public void dibuixaPantallaLogin(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaUsuari(p5);
        dibuixaImatgeLogIn(p5);

        TUsuario.display(p5);
        TContraseña.display(p5);
    }

    public void dibuixaPantallaSeleccionar(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);
        dibuixaColumnes123(p5);
    }

    public void dibuixaPantallaHoy(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);
        dibuixaColumnes12(p5);
    }

    public void dibuixaPantallaDetallesHoy(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);
        dibuixaColumnes123(p5);
    }

    public void dibuixaPantallaSemana(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);
        dibuixaColumna1Mitja(p5);
    }

    public void dibuixaPantallaMes(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);
        dibuixaColumna1(p5);
    }


    // ZONES DE LA GUI

    public void dibuixaLogo(PApplet p5){
        p5.fill(tablaColores.getColorDe(4));
        p5.rect(margeH, margeV, logoX, logoY);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(0)); p5.textSize(midaParagraf);
        p5.text("LOGO", margeH + logoX/2, margeV + logoY/2);
    }

    public void dibuixaNomPantalla(PApplet p5){
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaTitol);
        p5.text("PANTALLA " + pantallaActual, margeH + logoX + imatgeX/2, 2*margeV + logoY/2);
    }

    public void dibuixaMenu(PApplet p5){
        // Zona Sidebar ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(tablaColores.getColorDe(3));
        p5.rect(margeH, 3*margeV + logoY, menuX, menuY);
        p5.fill(tablaColores.getColorDe(5)); p5.textFont(fontsApp.getFontAt(1)); p5.textSize(midaSubtitol);
        p5.text("MENÚ", margeH + menuX/2, margeV + logoY + menuY/2);
    }

    public void dibuixaImatge(PApplet p5){
        p5.fill(tablaColores.getColorDe(4));
        p5.rect( margeH, imatgeV, imatgeX, imatgeY);
        p5.fill(1); p5.textFont(fontsApp.getFontAt(1)); p5.textSize(midaTitol);
        p5.text("IMATGE " +  pantallaActual + "("+pantallaActual.ordinal() +")", margeH + logoX + imatgeX/2, imatgeV + margeV + imatgeY/2);
    }

    public void dibuixaColumna1Mitja(PApplet p5){
        p5.fill(tablaColores.getColorDe(3));
        p5.rect(2*margeH + menuX, 4*margeV + 3*logoY, 3*columnaX + 2*margeH, columnaY/2 );
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaParagraf);
        p5.text("COLUMNA 1", 3*margeH + menuX + columnaX + columnaX/2, 2*margeV + logoY + columnaY/2);
    }

    public void dibuixaColumna1(PApplet p5){
        p5.fill(tablaColores.getColorDe(1));
        p5.rect(2*margeH + menuX, 3*margeV + logoY, 3*columnaX + 2*margeH, columnaY);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaParagraf);
        p5.text("COLUMNA 1", 3*margeH + menuX + columnaX + columnaX/2, 2*margeV + logoY + columnaY/2);
    }

    public void dibuixaColumnes12(PApplet p5){
        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(tablaColores.getColorDe(3));
        p5.rect(2*margeH + menuX, 3*margeV + logoY, columnaX + columnaX/2, columnaY );
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaParagraf);
        p5.text("COLUMNA 1", (menuX + columnaX)-20, 2*margeV + logoY + columnaY/2);

        p5.fill(tablaColores.getColorDe(2));
        p5.rect(4*margeH + menuX + columnaX + columnaX/2, 3*margeV + logoY, columnaX + columnaX/2, columnaY);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaParagraf);
        p5.text("COLUMNA 2", 2*menuX + 2*columnaX, 2*margeV + logoY + columnaY/2);
    }

    public void dibuixaColumnes123(PApplet p5){
        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(tablaColores.getColorDe(2));
        p5.rect(2*margeH + menuX, 3*margeV + logoY, columnaX, columnaY );
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaParagraf);
        p5.text("COLUMNA 1", 2*margeH + menuX + columnaX/2, 2*margeV + logoY + columnaY/2);

        p5.fill(tablaColores.getColorDe(3));
        p5.rect(3*margeH + menuX + columnaX, 3*margeV + logoY, columnaX, columnaY);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaParagraf);
        p5.text("COLUMNA 2", 3*margeH + menuX + columnaX + columnaX/2, 2*margeV + logoY + columnaY/2);

        p5.fill(tablaColores.getColorDe(4));
        p5.rect(4*margeH + menuX + 2*columnaX, 3*margeV + logoY, columnaX, columnaY);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaParagraf);
        p5.text("COLUMN 3", 4*margeH + menuX + 2*columnaX + columnaX/2, 2*margeV + logoY + columnaY/2);
    }

    // DIBUJAR PANTALLA INICIAL
    public void dibuixaImatgeInicial(PApplet p5){
        p5.fill(tablaColores.getColorDe(1));
        p5.rect(2*margeH + logoX, 3*margeV + logoY, imagenIX, imagenIY);
        p5.fill(tablaColores.getColorDe(5)); p5.textFont(fontsApp.getFontAt(2), midaTitol);
        p5.text("Imatge Inicial", 2*margeH + logoX + imagenIX/2, 3*margeV + logoY + imagenIY/2);
    }

    // DIBUJAR PANTALLA LOGIN
    public void dibuixaImatgeLogIn(PApplet p5){
        p5.fill(tablaColores.getColorDe(1));
        p5.rect(6*margeH + usuariX, 3*margeV + logoY, imagenLX, imagenLY);
        p5.fill(tablaColores.getColorDe(5)); p5.textFont(fontsApp.getFontAt(2), midaTitol);
        p5.text("Imatge LogIn", 6*margeH + usuariX + imagenLX/2, 3*margeV + logoY + imagenLY/2);
    }

    public void dibuixaUsuari(PApplet p5){
        p5.fill(tablaColores.getColorDe(3));
        p5.rect(3*margeH, 3*margeV + logoY, usuariX, usuariY);
        p5.fill(tablaColores.getColorDe(5)); p5.textFont(fontsApp.getFontAt(1), midaTitol);
        p5.text("LogIn \n Usuario", 3*margeH + usuariX/2, 3*margeV + logoY + usuariY/2);
    }

}