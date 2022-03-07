/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_contactos.contacto;

/**
 *
 * @author daniel
 */
public class Contacto{
    private final float celular;
    private final String nombre;
    
    public Contacto(int celular, String nombre){
        this.celular = celular;
        this.nombre = nombre;
    }
    
    public float getCelular(){
        return celular;
    }
    
    public String getNombre(){
        return nombre;
    }

    public float compareTo(Contacto o) {
        float resultado = this.celular - o.celular;
        return resultado;
    }
}
