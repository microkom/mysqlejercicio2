/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import static mysqlejercicio2.Methods.line;

/**
 *
 * @author DAW
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String RESET = "\033[0m";
        final String ROJO = "\033[31;1m";
        final String VERDE = "\033[32m";
        final String AZUL = "\033[34m";
        final String BOLD = "\033[4m";

        String leftAlignFormat = "\t|                %-20s                   |";

        Scanner sc = new Scanner(System.in);
        boolean entryOk = false;
        int id = 0;
        String nombre = null;
        String descripcion = null;

        Categorias categoria = new Categorias(id, nombre, descripcion);

        Conexion login = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        int retorno = 0;

        try {
            con = login.conectar();

            System.out.println("\t" + line(60, "-"));
            String textoAux = String.format(leftAlignFormat, "REGISTRO DE CATEGORIAS ");
            System.out.println(textoAux);
            System.out.println("\t" + line(60, "-"));

            // captura por teclado de id - num entero
            do {
                try {
                    System.out.print("\tidCategoría a actualizar: ");
                    categoria.setId(Integer.parseInt(sc.nextLine()));
                    if (categoria.getId() < 1) {
                        Exception error1 = new Exception("Debe ser mayor que cero");
                        throw error1;
                    }

                    //CONSULTA DE LOS DATOS INSERTADOS
                    stmt = con.prepareStatement("SELECT * FROM Categorias WHERE idCategoria=?");

                    stmt.setInt(1, categoria.getId());
                    ResultSet rs = stmt.executeQuery();
                    if (!(rs.next())) {
                        entryOk = false;
                        Exception errorStmt = new Exception(ROJO+"Esa categoria no existe"+RESET);
                        throw errorStmt;
                    }

                    while (rs.next()) {
                        System.out.println("\tnnn" + rs.getInt(1) + " " + rs.getString(2) + " \t" + rs.getString(3));
                    }
                    if (rs != null) {
                        rs.close();
                    }
                    entryOk = true;

                } catch (NumberFormatException err) {
                    System.out.println("\tDato ingresado no válido. " + err.getMessage());
                    entryOk = false;
                } catch (Exception err) {
                    System.out.println("\tDato ingresado no válido. " + err.getMessage());
                    entryOk = false;
                } 

            } while (!entryOk);

            // captura por teclado de Description - String
            do {
                try {
                    System.out.print("\tDescripción: ");
                    categoria.setDescripcion(sc.nextLine());
                    if (categoria.getDescripcion().length() < 10) {
                        Error error1 = new Error("\t\tAl menos 10 caracteres");
                        throw error1;
                    }
                    entryOk = true;
                } catch (Exception err) {
                    System.out.println(err.getMessage());
                    entryOk = false;
                }

            } while (!entryOk);

            //ACTUALIZACION
            stmt = con.prepareStatement("UPDATE categorias SET Descripcion=? WHERE IdCategoria=?");
            stmt.setString(1, categoria.getDescripcion());
            stmt.setInt(2, categoria.getId());
            //stmt.executeUpdate();
            retorno = stmt.executeUpdate();

            /*
            // INSERCION DE DATOS EN BASE DE DATOS
            stmt = con.prepareStatement("INSERT INTO Categorias VALUES(?,?,?)");
            stmt.setInt(1, categoria.getId());
            stmt.setString(2, categoria.getNombre());
            stmt.setString(3, categoria.getDescripcion());
            retorno = stmt.executeUpdate();
            if (retorno > 0) {
                System.out.println("\t" + retorno + " registro ejecutado");
            }
             */
            //CONSULTA DE LOS DATOS INSERTADOS
            stmt = con.prepareStatement("SELECT * FROM Categorias WHERE idCategoria=?");
            stmt.setInt(1, categoria.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("\t" + rs.getInt(1) + " " + rs.getString(2) + " \t" + rs.getString(3));
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            //cierre de stmt 
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception err4) {
                System.out.println(err4.getMessage());
            }
            //desconexion base de datos
            try {
                login.desconectar(con);
                System.out.println("\tConexión cerrada");
            } catch (Exception err5) {
                System.out.println("\tBase de datos aun conectada. " + err5.getMessage());
            }
        }
    }

}
