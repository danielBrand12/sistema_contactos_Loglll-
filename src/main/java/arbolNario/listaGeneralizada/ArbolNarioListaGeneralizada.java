/*
 * Copyright 2019 Carlos Alejandro Escobar Marulanda ealejandro101@gmail.com
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the 
 * Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package arbolNario.listaGeneralizada;

import sistema_contactos.BD_contactos.*;
import sistema_contactos.contacto.Contacto;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Escobar
 */
public class ArbolNarioListaGeneralizada {

    NodoNario raiz = new NodoNario(null);

    /**
     * Otro constructor
     *
     * @param raiz
     */
    private ArbolNarioListaGeneralizada(NodoNario raiz) {
        this.raiz = raiz;
    }

    public ArbolNarioListaGeneralizada() {
    }
    
    //prueba de que los algoritmos funcionan correctamente
    public static void main(String[] args) {
        ArbolNarioListaGeneralizada a = new ArbolNarioListaGeneralizada();
        NodoNario x = new NodoNario(2);
        x.setSw(0);
        NodoNario p = new NodoNario(1);
        p.setSw(0);
        p.setLiga(x);
        NodoNario p1 = new NodoNario(p);
        p1.setSw(1);
        a.raiz.setLiga(p1);
        a.insertarDato(1, 3, 4);
        a.insertarDato(2, 1, 5);
        a.insertarDato(2, 1, 6);
        a.insertarDato(3, 6, 7);
        System.out.println(a.buscarNivel(7));
    }
    
    public void insertarDato(int nivel, int celContactoRaiz, int celContacto){
        switch (nivel) {
            case 1 -> insertarDatoNivel1(celContactoRaiz, celContacto);
            case 2 ->                 {
                    int checkNivel = buscarNivel(celContactoRaiz);
                    if(checkNivel == 0){
                        JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en la base de datos.");
                    }else if(checkNivel != 1){
                        JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en el nivel 2.");
                    }else{
                        NodoNario contRaiz = buscarRaiz(celContactoRaiz);
                        NodoNario recorrido = contRaiz.getLiga();
                        NodoNario nuevoCont = new NodoNario(celContacto);
                        nuevoCont.setSw(0);
                        while(true){
                            if(recorrido.getLiga() == null){
                                recorrido.setLiga(nuevoCont);
                                break;
                            }
                            recorrido = recorrido.getLiga();
                        }
                    } 
                    }                      
            case 3 ->                 {
                    int checkNivel = buscarNivel(celContactoRaiz);
                    if(checkNivel == 0){
                        JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en la base de datos.");
                    }else if(checkNivel != 2){
                        JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en el nivel 3.");
                    }else{
                        NodoNario contRaiz = buscarRaiz(celContactoRaiz);
                        if(contRaiz.getSw() == 0){
                            NodoNario a = new NodoNario(celContacto);
                            a.setSw(0);
                            NodoNario b = new NodoNario(celContactoRaiz);
                            b.setSw(0);
                            b.setLiga(a);
                            contRaiz.setDato(b);
                            contRaiz.setSw(1);
                        }else{
                            NodoNario recorrido = contRaiz.getLiga();
                            NodoNario nuevoCont = new NodoNario(celContacto);
                            nuevoCont.setSw(0);
                            while(true){
                                if(recorrido.getLiga() == null){
                                    recorrido.setLiga(nuevoCont);
                                    break;
                                }
                                recorrido = recorrido.getLiga();
                            }
                        }
                    }                      }
            default -> {
            }
        }
    }
    
    public NodoNario buscarRaiz(int contactoRaiz){
        Stack stack = new Stack();
        NodoNario recorrido = raiz.getLiga();
        //NodoNario padreRaiz;
        while(recorrido != null){
            if(recorrido.getSw() == 1){
                stack.push(recorrido);
                recorrido = (NodoNario)recorrido.getDato();
                if((int)recorrido.getDato() == contactoRaiz){
                    return recorrido;
                }
            }else if((int)recorrido.getDato() == contactoRaiz){
                return recorrido;
            } 
            if(recorrido.getLiga() == null && !stack.isEmpty()){
                recorrido = (NodoNario)stack.pop();
            }
            recorrido = recorrido.getLiga();
        }
        
        return null;
    }
    
    public int buscarNivel(int celContacto){
        Stack stack = new Stack();
        NodoNario recorrido = raiz.getLiga();
        int nivel = 1;
        //NodoNario padreRaiz;
        while(recorrido != null){
            if(recorrido.getSw() == 1){
                stack.push(recorrido);
                recorrido = (NodoNario)recorrido.getDato();
                if((int)recorrido.getDato() == celContacto){
                    return nivel;
                }
                nivel++;
            }else if((int)recorrido.getDato() == celContacto){
                return nivel;
            } 
            if(recorrido.getLiga() == null && !stack.isEmpty()){
                recorrido = (NodoNario)stack.pop();
                nivel--;
            }
            recorrido = recorrido.getLiga();
        }
        nivel = 0;
        return nivel;
    }
    
    public void insertarDatoNivel1(int celContacto, int hijoContacto){
        NodoNario recorrido = raiz.getLiga();
        NodoNario dato = new NodoNario(celContacto);
        dato.setSw(0);
        NodoNario hijoDato = new NodoNario(hijoContacto);
        hijoDato.setSw(0);
        dato.setLiga(hijoDato);
        NodoNario x = new NodoNario(dato);
        x.setSw(1);
        //Se recorre el primer nivel del árbol Nario y se inserta el nuevo contacto al final de la lista
        while(true){
            if(recorrido.getLiga() == null){
                recorrido.setLiga(x);
                break;
            }
            recorrido = recorrido.getLiga();
        }
    }

    /**
     * Es una forma de pintar el arbol
     */
    void mostrarPorListas() {
        Stack<NodoNario> migas = new Stack();
        migas.add(raiz);
        while (!migas.empty()) {
            NodoNario pr = migas.pop();
            while (pr != null) {
                if (pr.getSw() == 0) {
                    System.out.print(pr.getDato()); // Operar el recorrido
                } else {
                    NodoNario npr = (NodoNario) pr.getDato(); // Operar
                    migas.add(npr);
                }
                pr = pr.getLiga();
            }
        }
    }


    /**
     * Validar
     */
    public void imprimirXNivel() {
        NodoNario r = raiz;
        Queue<NodoNario> cola = new LinkedList<>();
        while (r != null) {
            if (r.getSw() == 1) {
                NodoNario realHijo = (NodoNario) r.getDato();
                System.out.print(realHijo.getDato()); // operación
                cola.add(realHijo.getLiga());
            } else {
                System.out.print(r.getDato()); // operación
            }
            r = r.getLiga();
            if (r == null && !cola.isEmpty()) {
                r = cola.remove();
            }
        }
    }

    public NodoNario getRaiz() {
        return raiz;
    }

}
