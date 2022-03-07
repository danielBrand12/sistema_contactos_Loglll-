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
    private final float celular;
    private final String nombre;
    
    public Contacto(float celular, String nombre){
        this.celular = celular;
        this.nombre = nombre;
    }
    
    public float getCelular(){
        return celular;
    }
    
    public String getNombre(){
        return nombre;
    }

    @Override
    public int compareTo(Contacto o) {
        return (int) (this.celular - o.celular);
    }
}
