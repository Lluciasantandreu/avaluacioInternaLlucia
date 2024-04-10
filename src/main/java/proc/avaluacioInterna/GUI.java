package proc.avaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

import static javax.swing.SwingConstants.*;
import static proc.avaluacioInterna.Medidas.*;
import static proc.avaluacioInterna.ValoresEstaticos.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, LOGIN, SELECCIONAR, SELECCIONRECETA, INSERIR, SEMANA, MES, CONFIGURACION, RECETAS, CUENTA, INFORMACION, FAVORITOS, DETALLESRECETA};
    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Colors i Fonts de l'APP
    Colores tablaColores;
    Fuentes fontsApp;
    Imagenes imagenesApp;

    //MENU
    Botones MHome;
    Botones MCrear;
    Botones MSemana;
    Botones MMes;
    Botones MConfiguracion;

    // PANTALLA INICIAL Y LOGIN
    Botones BLogIn;
    Botones BEntrar;
    InserirTexto TUsuario;
    InserirTexto TContraseña;
    PopUp p1;

    // PANTALLA SELECCIONAR
    Botones BHoy, BSemana, BMes;

    // PANTALLA INSERIR
    Botones BComida, BCena, BPostre, BDesayuno;
    PopUp p2;

    // PANTALLA DETALLES HOY
    InserirTexto TNombre;
    InserirTexto TDetallesNombre;

    Seleccionador Unidades;
    Seleccionador Unidades1;
    Seleccionador Unidades2;
    Seleccionador Unidades3;
    Seleccionador Unidades4;
    Seleccionador Unidades5;
    InserirTexto[] TCantidades;
    InserirTexto[] TIngredients;
    ListaCheck[] Ingredients;
    Botones BImagen;
    Botones BGuardar;
    Botones BFavorita;

    PImage imagen;
    String titulo = "";

    //PANTALLA SEMANA
    Semanario s;
    Botones d, i, ds;

    //PANTALLA MES
    Calendario c;
    Botones ant, post;

    //PANTALLA CONFIGURACIÓN
    Botones BRecetas;
    Botones BCuenta;
    Botones BInformación;

    Botones BInicio;


    //PANTALLA CUENTA
    InserirTexto CUsuario;
    InserirTexto CContraseña;

    Botones BConfirmar;


    // PANTALLA RECETAS
    TablaPaginada r, f;
    Botones Bant, Bpost;
    Botones Bant1, Bpost1;
    Botones BFavoritos;
    Botones BAtras;

    // Constructor de la GUI
    public GUI(PApplet p5, DataBase db){
        pantallaActual = PANTALLA.INICIAL;
        tablaColores = new Colores(p5);   // Constructor de los colores de la App
        fontsApp = new Fuentes(p5);     // Constructor de las fuentes de la App
        imagenesApp = new Imagenes(p5) // Constructor de las imagenes de la App
;
        BLogIn = new Botones(p5, "LogIn", logInX, margeV, logInH, logInV);
        BLogIn.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BEntrar = new Botones(p5, "Entrar", 200, 550, logInH, textY);
        BEntrar.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BEntrar.setActivado(false);
        p1 = new PopUp(p5, "ALERTA", "El usuario o la contraseña son incorrectos", 400, 300, 500, 300);

        BHoy = new Botones(p5, "NUEVA RECETA", 2*margeH + menuX, 3*margeV + 3*logoY, columnaX, columnaY);
        BHoy.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BSemana= new Botones(p5, "SEMANA", 3*margeH + menuX + columnaX, 3*margeV + 3*logoY, columnaX, columnaY);
        BSemana.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(3), tablaColores.getColorDe(3));
        BMes= new Botones(p5, "MES", 4*margeH + menuX + 2*columnaX, 3*margeV + 3*logoY, columnaX, columnaY);
        BMes.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(4), tablaColores.getColorDe(3));

        BComida = new Botones(p5, "COMIDA", 3*margeH + menuX, 3*margeV + 3*logoY, columnaX + columnaX/2, columnaY/2 - 10);
        BComida.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BCena= new Botones(p5, "CENA", 4*margeH + menuX + columnaX + columnaX/2, 3*margeV + 3*logoY, columnaX + columnaX/2, columnaY/2 - 10);
        BCena.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(3), tablaColores.getColorDe(3));
        BPostre = new Botones(p5, "POSTRE", 3*margeH + menuX, 3*margeV + 3*logoY + columnaY/2 + 5, columnaX + columnaX/2, columnaY/2 - 10);
        BPostre.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BDesayuno= new Botones(p5, "DESAYUNO", 4*margeH + menuX + columnaX + columnaX/2, 3*margeV + 3*logoY + columnaY/2 + 5, columnaX + columnaX/2, columnaY/2 - 10);
        BDesayuno.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(3), tablaColores.getColorDe(3));


        BRecetas = new Botones(p5, "Recetas", 2*margeH, 3*margeV + 3*logoY, columnaX + 50, columnaY + 10);
        BRecetas.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BCuenta = new Botones(p5, "Mi cuenta", 5*margeH + columnaX + 50, 3*margeV + 3*logoY, columnaX + 50, columnaY + 10);
        BCuenta.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BInformación = new Botones(p5, "Información", 9*margeH + 2*columnaX + 50, 3*margeV + 3*logoY, columnaX + 50, columnaY + 10);
        BInformación.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));


        TUsuario = new InserirTexto(p5, 200, 350, textX, textY, "Usuario");
        TContraseña = new InserirTexto(p5, 200, 450, textX, textY, "Contraseña");

        TNombre = new InserirTexto(p5, 3*margeH + menuX + columnaX, 3*margeV + 3*logoY, textX - 4*margeH, textY, "NOMBRE");

        String[] nombre = db.getColumnaNomTaulaRECETA();
        TDetallesNombre = new InserirTexto(p5, 3*margeH + menuX + columnaX, 3*margeV + 3*logoY, textX - 4*margeH, textY, nombre[1]);


        float x = 3*margeH + menuX + columnaX;
        float y = 4*margeV + 3*logoY + textY;
        Ingredients = new ListaCheck[6];
        Ingredients[0] = new ListaCheck(p5, x, y, 37);
        Ingredients[1] = new ListaCheck(p5, x, y + 2*margeV + 10, 37);
        Ingredients[2] = new ListaCheck(p5, x, y + 4*margeV + 20, 37);
        Ingredients[3] = new ListaCheck(p5, x, y + 6*margeV + 30, 37);
        Ingredients[4] = new ListaCheck(p5, x, y + 8*margeV + 40, 37);
        Ingredients[5] = new ListaCheck(p5, x, y + 10*margeV + 50, 37);


        TIngredients = new InserirTexto[6];
        TIngredients[0] = new InserirTexto(p5, x + 60, y, 170, 37, "Ingrediente 1");
        TIngredients[1] = new InserirTexto(p5, x + 60, y + 2*margeV + 10, 170, 37, "Ingrediente 2");
        TIngredients[2] = new InserirTexto(p5, x + 60, y + 4*margeV + 20, 170, 37, "Ingrediente 3");
        TIngredients[3] = new InserirTexto(p5, x + 60, y + 6*margeV + 30, 170, 37, "Ingrediente 4");
        TIngredients[4] = new InserirTexto(p5, x + 60, y + 8*margeV + 40, 170, 37, "Ingrediente 5");
        TIngredients[5] = new InserirTexto(p5, x + 60, y + 10*margeV + 50, 170, 37, "Ingrediente 6");

        TCantidades = new InserirTexto[6];
        TCantidades[0] = new InserirTexto(p5, x + 250, y, 110, 37, "Cantidad");
        TCantidades[1] = new InserirTexto(p5, x + 250, y + 2*margeV + 10, 110, 37, "Cantidad");
        TCantidades[2] = new InserirTexto(p5, x + 250, y + 4*margeV + 20, 110, 37, "Cantidad");
        TCantidades[3] = new InserirTexto(p5, x + 250, y + 6*margeV + 30, 110, 37, "Cantidad");
        TCantidades[4] = new InserirTexto(p5, x + 250, y + 8*margeV + 40, 110, 37, "Cantidad");
        TCantidades[5] = new InserirTexto(p5, x + 250, y + 10*margeV + 50, 110, 37, "Cantidad");


        String[] unidades = db.getColumnaNomTaulaUNIDADES();

        Unidades = new Seleccionador(p5, unidades, x + 380, y, 120, 37);
        Unidades1 = new Seleccionador(p5, unidades, x + 380, y + 2*margeV + 10, 120, 37);
        Unidades2 = new Seleccionador(p5, unidades, x + 380, y + 4*margeV + 20, 120, 37);
        Unidades3 = new Seleccionador(p5, unidades, x + 380, y + 6*margeV + 30, 120, 37);
        Unidades4 = new Seleccionador(p5, unidades, x + 380, y + 8*margeV + 40, 120, 37);
        Unidades5 = new Seleccionador(p5, unidades, x + 380, y + 10*margeV + 50, 120, 37);


        BGuardar = new Botones(p5, "GUARDAR", 4*margeH + 4*menuX + 15, y + 10*margeV + 40, logInH - 10, textY-5);
        BGuardar.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        BFavorita = new Botones(p5, "FAVORITA", 4*margeH + 4*menuX + 15, (3*logoY + columnaY - textY + 20), logInH - 10, textY-5);
        BFavorita.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        BImagen = new Botones(p5, "imagen", (2*margeH + menuX), (4*logoY + columnaY + 10), logInH - 10, textY);
        BImagen.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));

        p2 = new PopUp(p5, "GUARDADO", "La receta se ha guardado correctamente", 400, 300, 500, 300);

        // MENU
        MHome = new Botones(p5, "Inicio", margeH, 3*margeV + 3*logoY, menuX, textY);
        MHome.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        MCrear = new Botones(p5, "Crear nueva receta", margeH, 6*margeV + 3*logoY + 8, menuX, textY);
        MCrear.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        MSemana = new Botones(p5, "Semana", margeH, 10*margeV + 3*logoY, menuX, textY);
        MSemana.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        MMes = new Botones(p5, "Mes", margeH, 14*margeV + 3*logoY - 6, menuX, textY);
        MMes.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        MConfiguracion = new Botones(p5, "Configuracion", margeH, 17*margeV + 3*logoY + 8, menuX, textY);
        MConfiguracion.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));

        //CONFIGURACIÓN
        BInicio = new Botones(p5, "Inicio", margeH, 3*margeV + logoY, menuX - 20, textY);
        BInicio.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));

        //CUENTA
        CUsuario = new InserirTexto(p5, 600, 350, textX, textY, "Usuario");
        CContraseña = new InserirTexto(p5, 600, 450, textX, textY, "Contraseña");

        BConfirmar = new Botones(p5, "Confirmar", 600, 550, menuX - 20, textY);
        BConfirmar.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));

        s = new Semanario((int)(2*margeH + menuX), (int)(6*margeV + 2*logoY), (int)(3*columnaX + 2*margeH), (int)columnaY);
        c = new Calendario((int)(2*margeH + menuX), (int)(6*margeV + 2*logoY), (int)(3*columnaX + 2*margeH), (int)(columnaY + logoY + 5));
        ant = new Botones(p5, "anterior", (int)(2*margeH + menuX), (int)(3*margeV + 2*logoY), logInH - 10, textY);
        ant.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        post = new Botones(p5, "posterior", (int)(2*margeH + menuX + logInH), (int)(3*margeV + 2*logoY), logInH - 10, textY);
        post.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        d = new Botones(p5, "detalles", (int)(2*margeH + menuX), (4*logoY + columnaY + 10), 100, textY);
        d.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        i = new Botones(p5, "insertar", (int)(2*margeH + menuX + 110), (4*logoY + columnaY + 10), 100, textY);
        i.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        ds = new Botones(p5, "detalles", (int)(2*margeH + menuX), (4*logoY + columnaY + 10), 100, textY);
        ds.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));


        String[] headers = {"id", "Nombre", "Imagen", "Tipo", "Dia"};
        String[][] infot = db.visualizaRecetas();
        db.printArray2D(infot);
        float[] colWidths = {20, 50, 40, 30, 35};

        r = new TablaPaginada(6, 5);
        r.setHeaders(headers);
        r.setData(infot);
        r.setColumnWidths(colWidths);

        String[] headersf = {"id", "Nombre", "Usuario"};
        String[][] infotf = db.visualizaRecetas();
        db.printArray2D(infotf);
        float[] colWidthsf = {20, 50, 40, 30, 35};

        f = new TablaPaginada(6, 5);
        f.setHeaders(headersf);
        f.setData(infotf);
        f.setColumnWidths(colWidthsf);

        Bant = new Botones(p5, "anterior", (int)(7*margeH + menuX), (int)(3*margeV + 2*logoY + 430), logInH - 10, textY);
        Bant.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        Bpost = new Botones(p5, "posterior", (int)(7*margeH + menuX + logInH), (int)(3*margeV + 2*logoY + 430), logInH - 10, textY);
        Bpost.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BFavoritos = new Botones(p5, "Ver recetas favoritas", (int)(p5.width - 8*margeH), (int)(4*margeV + 2*logoY), logInH, menuY - 130);
        BFavoritos.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(6), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        BAtras = new Botones(p5, "Atras", margeH, 3*margeV + logoY, menuX - 20, textY);
        BAtras.setColors(tablaColores.getColorDe(3), tablaColores.getColorDe(3), tablaColores.getColorDe(2), tablaColores.getColorDe(0));
        Bant1 = new Botones(p5, "anterior", (int)(7*margeH + menuX), (int)(3*margeV + 2*logoY + 430), logInH - 10, textY);
        Bant1.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
        Bpost1 = new Botones(p5, "posterior", (int)(7*margeH + menuX + logInH), (int)(3*margeV + 2*logoY + 430), logInH - 10, textY);
        Bpost1.setColors(tablaColores.getColorDe(0), tablaColores.getColorDe(1), tablaColores.getColorDe(2), tablaColores.getColorDe(3));
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
        p5.textFont(fontsApp.getFontAt(1));
        p1.display(p5);
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

    public void dibuixaPantallaInserir(PApplet p5) {
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);

        BGuardar.display(p5);
        BFavorita.display(p5);
        BImagen.display(p5);

        TNombre.display(p5);


        for (int i = 0; i < Ingredients.length; i++){
            Ingredients[i].display(p5);
            TIngredients[i].display(p5);
            TCantidades[i].display(p5);
        }

        Unidades5.display(p5);
        Unidades4.display(p5);
        Unidades3.display(p5);
        Unidades2.display(p5);
        Unidades1.display(p5);
        Unidades.display(p5);


        if(imagen!=null){
            p5.pushStyle();
            p5.image(imagen, 2*margeH + menuX, 3*margeV + 3*logoY, columnaX, columnaY);
            p5.textSize(14);
            p5.textAlign(RIGHT);
            p5.fill(0);
            p5.textAlign(CENTER, CENTER);
            p5.text(titulo, 2*margeH + menuX, 3*margeV + 3*logoY, textX, textY);
            p5.popStyle();
        }

        p2.display(p5);
    }

    public void dibuixaPantallaSemana(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaMenu(p5);
        dibuixaImatge(p5);

        p5.textFont(fontsApp.getFontAt(2));
        s.display(p5);
        ds.display(p5);
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

        BRecetas.display(p5);
        BCuenta.display(p5);
        BInformación.display(p5);

        p5.textFont(fontsApp.getFontAt(2));
        BInicio.display(p5);
    }

    public void dibuixaPantallaRecetas(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaImatge(p5);


        p5.textFont(fontsApp.getFontAt(2));
        BInicio.display(p5);
        r.display(p5, 300, 200, 500, 400);
        Bant.display(p5);
        Bpost.display(p5);
        BFavoritos.display(p5);
    }

    public void dibuixaPantallaFavoritos(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaImatge(p5);


        p5.textFont(fontsApp.getFontAt(2));
        BAtras.display(p5);
        r.display(p5, 300, 200, 500, 400);
        Bant.display(p5);
        Bpost.display(p5);
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

    public void dibuixaPantallaDetallesReceta(PApplet p5){
        p5.background(tablaColores.getColorDe(0));
        dibuixaLogo(p5);
        dibuixaNomPantalla(p5);
        dibuixaImatge(p5);

        p5.textFont(fontsApp.getFontAt(2));
        BInicio.display(p5);
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
        p5.textAlign(CENTER);
        p5.text("PANTALLA " + pantallaActual, p5.width/2 - 200, 2*margeV + logoY/2);
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
        p5.text("MENU", 3*margeH, 4*margeV + 2*logoY);

        MHome.display(p5);
        MCrear.display(p5);
        MSemana.display(p5);
        MMes.display(p5);
        MConfiguracion.display(p5);
    }
}