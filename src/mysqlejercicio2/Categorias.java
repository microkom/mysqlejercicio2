/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlejercicio2;

/**
 *
 * @author DAW
 */
public class Categorias {
    private int idCategoria;
    private String nombre;
    private String descripcion;
    
    public Categorias(int id, String name, String description){
        this.idCategoria=id;
        this.nombre=name;
        this.descripcion=description;
    }
    
    public int getId(){return this.idCategoria;}
    public String getNombre(){return this.nombre;}
    public String getDescripcion(){return this.descripcion;}
    
    public void setId(int id){this.idCategoria=id;}
    public void setNombre(String name){this.nombre=name;}
    public void setDescripcion(String description){this.descripcion=description;}
    
    
}
