package proc.avaluacioInterna;

import processing.core.PApplet;

import static proc.avaluacioInterna.Mides.*;
import static proc.avaluacioInterna.Layout.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, LOGIN, SELECCIONAR, HOY, DETALLESHOY, SEMANA, MES, CONFIGURACION, FAVORITOS, CUENTA, INFORMACION};
    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Colors i Fonts de l'APP
    Colors tablaColores;
    Fonts fontsApp;
    Imagenes imagenesApp;

    //MENU
    Botons MHome;
    Botons MHoy;
    Botons MSemana;
    Botons MMes;
    Botons MConfiguracion;

    // PANTALLA INICIAL Y LOGIN
    Botons BLogIn;
    Botons BEntrar;
    InserirText TUsuario;
    InserirText TContraseña;

    // PANTALLA SELECCIONAR
    Botons BHoy, BSemana, BMes;

    // PANTALLA HOY
    Botons BComida, BCena, BPostre, BDesayuno;

    // PANTALLA DETALLES HOY

    RadioBoton BFavorito;
    Boolean Favorito = false;
    InserirText TNombre, TReceta;

    Select Unidades;
    InserirText [] TIngredients;
    LlistaCheck [] Ingredients;

    //PANTALLA SEMANA
    Semanario s;
    Botons d, i;

    //PANTALLA MES
    Calendari c;
    Botons ant, post;

    //PANTALLA CONFIGURACIÓN
    Botons BFavoritos;
    Botons BCuenta;
    Botons BInformación;

    Botons BInicio;

    //PANTALLA CUENTA
    InserirText CUsuario;
    InserirText CContraseña;

    Botons BConfirmar;

    // Constructor de la GUI
    public GUI(PApplet p5, DataBase db){
        pantallaActual = PANTALLA.INICIAL;
        tablaColores = new Colors(p5);   // Constructor dels colors de l'App
        fontsApp = new Fonts(p5);     // Constructor de les fonts de l'App
        imagenesApp = new Imagenes(p5)
;
        BLogIn = new Botons (p5, "LogIn", logInX, margeV, logInH, logInV);
        BLogIn.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BEntrar = new Botons (p5, "Entrar", 200, 550, logInH, textY);
        BEntrar.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));

        BHoy = new Botons(p5, "HOY", 2*margeH + menuX, 3*margeV + 3*logoY, columnaX, columnaY);
        BHoy.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BSemana= new Botons(p5, "SEMANA", 3*margeH + menuX + columnaX, 3*margeV + 3*logoY, columnaX, columnaY);
        BSemana.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(3), tablaColores.getColorDe(3));
        BMes= new Botons(p5, "MES", 4*margeH + menuX + 2*columnaX, 3*margeV + 3*logoY, columnaX, columnaY);
        BMes.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(4), tablaColores.getColorDe(3));

        BComida = new Botons(p5, "COMIDA", 3*margeH + menuX, 3*margeV + 3*logoY, columnaX + columnaX/2, columnaY/2 - 10);
        BComida.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BCena= new Botons(p5, "CENA", 4*margeH + menuX + columnaX + columnaX/2, 3*margeV + 3*logoY, columnaX + columnaX/2, columnaY/2 - 10);
        BCena.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(3), tablaColores.getColorDe(3));
        BPostre = new Botons(p5, "Postre", 3*margeH + menuX, 3*margeV + 3*logoY + columnaY/2 + 5, columnaX + columnaX/2, columnaY/2 - 10);
        BPostre.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BDesayuno= new Botons(p5, "Desayuno", 4*margeH + menuX + columnaX + columnaX/2, 3*margeV + 3*logoY + columnaY/2 + 5, columnaX + columnaX/2, columnaY/2 - 10);
        BDesayuno.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(3), tablaColores.getColorDe(3));


        BFavorito= new RadioBoton(p5, (int)(menuX + columnaX + textX + 3), (int)(3*margeV + 3*logoY + 25), 20);
        BFavorito.setColors(tablaColores.getColorDe(0), p5.color(30, 30, 30), tablaColores.getColorDe(6));


        BFavoritos = new Botons(p5, "Favoritos", 2*margeH, 3*margeV + 3*logoY, columnaX + 50, columnaY + 10);
        BFavoritos.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BCuenta = new Botons(p5, "Mi cuenta", 5*margeH + columnaX + 50, 3*margeV + 3*logoY, columnaX + 50, columnaY + 10);
        BCuenta.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BInformación = new Botons(p5, "Información", 9*margeH + 2*columnaX + 50, 3*margeV + 3*logoY, columnaX + 50, columnaY + 10);
        BInformación.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));


        TUsuario = new InserirText (p5, 200, 350, textX, textY, "Usuario");
        TContraseña = new InserirText (p5, 200, 450, textX, textY, "Contraseña");

        TNombre = new InserirText (p5, 3*margeH + menuX + columnaX, 3*margeV + 3*logoY, textX - 4*margeH, textY, "NOMBRE");
        TReceta = new InserirText (p5, 3*margeH + menuX + columnaX, 4*margeV + 3*logoY + textY, textX - 2*margeH, columnaY - textY, "Receta");

        float x = 4*margeH + menuX + 2*columnaX;
        float y = 4*margeV + 3*logoY + textY;
        Ingredients = new LlistaCheck[6];
        Ingredients[0] = new LlistaCheck(p5, x, y, 37);
        Ingredients[1] = new LlistaCheck(p5, x, y + 2*margeV + 10, 37);
        Ingredients[2] = new LlistaCheck(p5, x, y + 4*margeV + 20, 37);
        Ingredients[3] = new LlistaCheck(p5, x, y + 6*margeV + 30, 37);
        Ingredients[4] = new LlistaCheck(p5, x, y + 8*margeV + 40, 37);
        Ingredients[5] = new LlistaCheck(p5, x, y + 10*margeV + 50, 37);


        TIngredients = new InserirText[6];
        TIngredients[0] = new InserirText(p5, x + 60, y, 200, 37, "Ingrediente 1");
        TIngredients[1] = new InserirText(p5, x + 60, y + 2*margeV + 10, 200, 37, "Ingrediente 2");
        TIngredients[2] = new InserirText(p5, x + 60, y + 4*margeV + 20, 200, 37, "Ingrediente 3");
        TIngredients[3] = new InserirText(p5, x + 60, y + 6*margeV + 30, 200, 37, "Ingrediente 4");
        TIngredients[4] = new InserirText(p5, x + 60, y + 8*margeV + 40, 200, 37, "Ingrediente 5");
        TIngredients[5] = new InserirText(p5, x + 60, y + 10*margeV + 50, 200, 37, "Ingrediente 6");


        String[] unidades = db.getColumnaNomTaulaUNIDADES();
        Unidades = new Select(unidades, 100, 100, 100, 100);

        // MENU
        MHome = new Botons(p5, "Inicio", margeH, 3*margeV + 3*logoY, menuX, textY);
        MHome.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        MHoy = new Botons(p5, "Hoy", margeH, 6*margeV + 3*logoY + 8, menuX, textY);
        MHoy.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        MSemana = new Botons(p5, "Semana", margeH, 10*margeV + 3*logoY, menuX, textY);
        MSemana.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        MMes = new Botons(p5, "Mes", margeH, 14*margeV + 3*logoY - 6, menuX, textY);
        MMes.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        MConfiguracion = new Botons(p5, "Configuracion", margeH, 17*margeV + 3*logoY + 8, menuX, textY);
        MConfiguracion.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));

        //CONFIGURACIÓN
        BInicio = new Botons(p5, "Inicio", margeH, 3*margeV + logoY, menuX - 20, textY);
        BInicio.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));

        //CUENTA
        CUsuario = new InserirText (p5, 600, 350, textX, textY, "Usuario");
        CContraseña = new InserirText (p5, 600, 450, textX, textY, "Contraseña");

        BConfirmar = new Botons(p5, "Confirmar", 600, 550, menuX - 20, textY);
        BConfirmar.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));

        s = new Semanario((int)(2*margeH + menuX), (int)(6*margeV + 2*logoY), (int)(3*columnaX + 2*margeH), (int)columnaY);
        c = new Calendari((int)(2*margeH + menuX), (int)(6*margeV + 2*logoY), (int)(3*columnaX + 2*margeH), (int)(columnaY + logoY + 5));
        ant = new Botons (p5, "anterior", (int)(2*margeH + menuX), (int)(3*margeV + 2*logoY), logInH - 10, textY);
        ant.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        post = new Botons (p5, "posterior", (int)(2*margeH + menuX + logInH), (int)(3*margeV + 2*logoY), logInH - 10, textY);
        post.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        d = new Botons(p5, "detalles", (int)(2*margeH + menuX), (4*logoY + columnaY + 10), 100, textY);
        d.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        i = new Botons(p5, "insertar", (int)(2*margeH + menuX + 110), (4*logoY + columnaY + 10), 100, textY);
        i.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));

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
        dibuixaImatgeLogIn(p5);

        BEntrar.display(p5);
        TUsuario.display(p5);
        TContraseña.display(p5);
    }

    public void dibuixaPantallaSeleccionar(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);

        p5.textFont(fontsApp.getFontAt(1));
        BHoy.display(p5);
        BSemana.display(p5);
        BMes.display(p5);
    }

    public void dibuixaPantallaHoy(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);

        BComida.display(p5);
        BCena.display(p5);
        BPostre.display(p5);
        BDesayuno.display(p5);
    }

    public void dibuixaPantallaDetallesHoy(PApplet p5) {
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);

        BFavorito.display(p5);

        TNombre.display(p5);
        TReceta.display(p5);


        for (int i = 0; i < Ingredients.length; i++){
            Ingredients[i].display(p5);
            TIngredients[i].display(p5);
        }

        Unidades.display(p5);
    }

    public void dibuixaPantallaSemana(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);

        p5.textFont(fontsApp.getFontAt(2));
        s.display(p5);
        d.display(p5);
        i.display(p5);
    }

    public void dibuixaPantallaMes(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);

        p5.textFont(fontsApp.getFontAt(2));
        c.display(p5);
        ant.display(p5);
        post.display(p5);
        d.display(p5);
        i.display(p5);
    }


    public void dibuixaPantallaConfiguración(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaImatge(p5);

        BFavoritos.display(p5);
        BCuenta.display(p5);
        BInformación.display(p5);

        p5.textFont(fontsApp.getFontAt(2));
        BInicio.display(p5);
    }

    public void dibuixaPantallaFavoritos(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaImatge(p5);

        p5.textFont(fontsApp.getFontAt(2));
        BInicio.display(p5);
    }

    public void dibuixaPantallaCuenta(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaImatge(p5);

        p5.textFont(fontsApp.getFontAt(2));
        BInicio.display(p5);

        CUsuario.display(p5);
        CContraseña.display(p5);
        p5.textFont(fontsApp.getFontAt(0), midaParagraf);
        p5.text("Cambiar información de la cuenta", 793, 300);

        BConfirmar.display(p5);
    }

    public void dibuixaPantallaInformacion(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaImatge(p5);

        p5.textFont(fontsApp.getFontAt(2));
        BInicio.display(p5);

        p5.textFont(fontsApp.getFontAt(0), midaParagraf);
        p5.text("Esta aplicación se ha hecho con el fin de tener \n las diferentes recetas del mes en un mismo espacio... ", 750, 200);
    }


    // ZONES DE LA GUI

    public void dibuixaLogo(PApplet p5){
        p5.noStroke();
        p5.fill(tablaColores.getColorDe(4));
        p5.rect(margeH, margeV, logoX, logoY);
        p5.fill(0); p5.textFont(fontsApp.getFontAt(0)); p5.textSize(midaParagraf);
        p5.text("LOGO", margeH + logoX/2, margeV + logoY/2);
    }

    public void dibuixaNomPantalla(PApplet p5){
        p5.fill(0); p5.textFont(fontsApp.getFontAt(2)); p5.textSize(midaTitol);
        p5.text("PANTALLA " + pantallaActual, margeH + logoX + imatgeX/2, 2*margeV + logoY/2);
    }


    public void dibuixaImatge(PApplet p5){
        p5.image(imagenesApp.getImageAt(0), margeH, imatgeV, imatgeX, imatgeY);
    }

    // DIBUJAR PANTALLA INICIAL
    public void dibuixaImatgeInicial(PApplet p5){
        p5.image(imagenesApp.getImageAt(1), 2*margeH + logoX, 3*margeV + logoY, imagenIX, imagenIY);

    }

    // DIBUJAR PANTALLA LOGIN
    public void dibuixaImatgeLogIn(PApplet p5){
        p5.image(imagenesApp.getImageAt(2), 6*margeH + usuariX, 3*margeV + logoY, imagenLX, imagenLY);
    }

    public void dibuixaMenu(PApplet p5){
        p5.text("MENU", margeH + menuX/2, 4*margeV + 2*logoY);

        MHome.display(p5);
        MHoy.display(p5);
        MSemana.display(p5);
        MMes.display(p5);
        MConfiguracion.display(p5);
    }
}