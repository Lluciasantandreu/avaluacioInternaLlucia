package proc.avaluacioInterna;

import java.sql.*;

public class DataBase {

    // Variable de connexió a la BBDD
    Connection c;

    // Variable de consulta
    Statement query;

    // Dades de connexió (user, password, nom de la base de dades)
    String user, password, databaseName;

    // Estat de la connexió
    boolean connectat = false;

    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+databaseName+"?useTimezone=true&serverTimezone=UTC", user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Retorna el número de files d'una taula
    public int getNumRowsTaula(String nomTaula){
        try {
            ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM "+ nomTaula );
            rs.next();
            int numRows = rs.getInt("n");
            System.out.println("FILES: "+numRows);
            return numRows;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna el número de columnes d'una taula de la base de dades
    public int getNumColsTaula(String nomTaula){
        try {
            String q = "SELECT count(*) as n FROM information_schema.columns WHERE table_name ='"+ nomTaula +"' AND table_schema='"+databaseName+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            rs.next();
            int numCols = rs.getInt("n");
            return numCols;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna les dades d'una taula en concret
    public String[][] visualizaRecetas(){
        int numFiles = getNumRowsTaula("RECETA");
        int numCols  = 5;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT * FROM RECETA");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("idRECETA"));
                info[nr][1] = rs.getString("nombre");
                info[nr][2] = rs.getString("imagen");
                info[nr][3] = rs.getString("tipo");
                info[nr][4] = String.valueOf(rs.getString("dia"));
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] visualizaFavoritas(){
        int numFiles = getNumRowsTaula("RECETA_FAVORITA");
        int numCols  = 2;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT * FROM RECETA_FAVORITA");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("RECETA_idRECETA"));
                info[nr][1] = rs.getString("USUARIO_idUSUARIO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /*public String[][] visualizaIngredientesReceta(String r){
        int numFiles = 2;
        int numCols  = 4;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM INGREDIENTES_RECETA WHERE receta = '"+r+"'");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = rs.getString("ingredientes");
                info[nr][1] = String.valueOf(rs.getInt("receta"));
                info[nr][2] = rs.getString("cantidad");
                info[nr][3] = String.valueOf(rs.getInt("unidades"));
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }*/

    public String[][] addIngredientesReceta(String i, String r, String c, String u){
        int numFiles = 7;
        int numCols  = 4;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "INSERT INTO `INGREDIENTES_RECETA` (`ingredientes`, `receta`, `cantidad`, `unidades`) VALUES ('"+i+"', '"+r+"', '"+c+"', '"+u+"');");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("receta"));
                info[nr][1] = rs.getString("ingredientes");
                info[nr][2] = rs.getString("cantidad");
                info[nr][3] = String.valueOf(rs.getInt("unidades"));
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    // Retorna les dades de la columna NOM de la taula UNITAT
    public String[] getColumnaNomTaulaUNIDADES(){
        int numFiles = getNumRowsTaula("UNIDADES");
        String[] info = new String[numFiles];
        try {
            String q = "SELECT nombre FROM UNIDADES ORDER BY nombre ASC";
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            int nr = 0;
            while (rs.next()) {
                info[nr] = rs.getString("nombre");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[] getColumnaNomTaulaRECETA(){
        int numFiles = getNumRowsTaula("RECETA");
        String[] info = new String[numFiles];
        try {
            String q = "SELECT nombre FROM RECETA ORDER BY nombre ASC";
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            int nr = 0;
            while (rs.next()) {
                info[nr] = rs.getString("nombre");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public boolean isValidUser(String userName, String password){
        String q = "SELECT COUNT(*) AS n FROM USUARIO WHERE idUSUARIO = '"+userName+"' AND password='"+password+"'";
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n")==1;
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }



    void addReceta(String nombre, String tipo, Date d){
        String df = d.getYear()+"-"+d.getMonth()+"-"+d.getDay();
        System.out.println(df);
        String q = "INSERT INTO RECETA (nombre, imagen, tipo, dia) VALUES (' "+ nombre + "', NULL, '" + tipo + "', '" + df + "')";
        System.out.println(q);
        try {
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    void addRecetaImagen(String nombre, String tipo, String imagen, Date d){
        String df = d.getYear()+"-"+d.getMonth()+"-"+d.getDay();
        System.out.println(df);
        String q = "INSERT INTO RECETA (nombre, imagen, tipo, dia) VALUES (' "+ nombre + "', '"+ imagen+"', '" + tipo + "', '" + df + "')";
        System.out.println(q);
        try {
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    void addIngredientes(String nombre){
        String q = "INSERT INTO INGREDIENTES (idINGREDIENTES) VALUES ('"+ nombre + "')";
        System.out.println(q);
        try {
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public String getClaveFromTabla(String nombreTabla, String nombreColumnaClave, String nombreColumnaValor, String valorColumna){
        try {
            String queryText = "SELECT " + nombreColumnaClave + " AS clave FROM " + nombreTabla+ " WHERE " +nombreColumnaValor+ " = '"+valorColumna+"'";
            System.out.println(queryText);
            ResultSet rs = query.executeQuery(queryText);
            rs.next();
            System.out.println(rs.getString("clave"));
            return rs.getString("clave");
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    void addRecetaFavorita(String id, String usuario){
        String q = "INSERT INTO RECETA_FAVORITA (RECETA_idRECETA, USUARIO_idUSUARIO) VALUES ('"+id+"', '"+usuario+"')";
        System.out.println(q);
        try {
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    void updateInfoTablaUsuario(String id, String nombre, String contra){
        try {
            String q = "UPDATE USUARIO SET idUSUARIO='"+nombre+"', password='"+contra+"' WHERE idUSUARIO='"+id+"'";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static String formataFechaEsp(String fechaEntrada){

        String y = fechaEntrada.split("-")[0];
        String m = fechaEntrada.split("-")[1];
        String d = fechaEntrada.split("-")[2];

        return d+"/"+m+"/"+y;
    }

    public static String formataFechaEng(String fechaEntrada){

        String y = fechaEntrada.split("/")[2];
        String m = fechaEntrada.split("/")[1];
        String d = fechaEntrada.split("/")[0];

        return y+"-"+m+"-"+d;
    }

    void printArray2D(String[][] info){
        for(int f=0; f<info.length; f++){
            for(int c=0; c<info[f].length; c++) {
                System.out.print(info[f][c]+"\t");
            }
            System.out.println();
        }
    }
}