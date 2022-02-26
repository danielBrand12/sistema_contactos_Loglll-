/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_contactos.contacto;

/**
 *
 * @author daniel
 */
public class Contacto implements Comparable<Contacto>{
    private final int celular;
    private final String nombre;
    
    public Contacto(int celular, String nombre){
        this.celular = celular;
        this.nombre = nombre;
    }
    
    public int getCelular(){
        return celular;
    }
    
    public String getNombre(){
        return nombre;
    }

    @Override
    public int compareTo(Contacto o) {
        return this.celular - o.celular;
    }
}
