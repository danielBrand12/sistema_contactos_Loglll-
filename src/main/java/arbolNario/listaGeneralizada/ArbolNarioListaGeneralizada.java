package arbolNario.listaGeneralizada;

import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo logica 3
 */
public class ArbolNarioListaGeneralizada {

    NodoNario raiz = new NodoNario(null);

    public ArbolNarioListaGeneralizada() {
    }
    
    //prueba de que los algoritmos funcionan correctamente
    public static void main(String[] args) {
        ArbolNarioListaGeneralizada a = new ArbolNarioListaGeneralizada();
        a.insertarDato(1, 1, 2);
        a.insertarDato(1, 3, 4);
        a.insertarDato(2, 1, 5);
        a.insertarDato(2, 1, 6);
        a.insertarDato(3, 6, 7);
        System.out.println(a.buscarNivel(7));
    }
    
    public void insertarDato(int nivel, int celContactoRaiz, int celContacto){
        switch (nivel) {
            case 1 -> insertarDatoNivel1(celContactoRaiz, celContacto);
            case 2 -> insertarDatoNivel2(celContactoRaiz,celContacto);                      
            case 3 -> insertarDatoNivel3(celContactoRaiz, celContacto);
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
        if(recorrido == null){
            raiz.setLiga(x);
        }else{
            //Se recorre el primer nivel del árbol Nario y se inserta el nuevo contacto al final de la lista
            while(true){
                if(recorrido.getLiga() == null){
                    recorrido.setLiga(x);
                    break;
                }
                recorrido = recorrido.getLiga();
            }
        }
        
    }
    
    public void insertarDatoNivel2(int celContactoRaiz, int celContacto){
        int checkNivel = buscarNivel(celContactoRaiz);
        if(checkNivel == 0){
            JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en la base de datos.");
        }else if(checkNivel != 1){
            JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en el nivel 1.");
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
    
    public void insertarDatoNivel3(int celContactoRaiz, int celContacto){
        int checkNivel = buscarNivel(celContactoRaiz);
        if(checkNivel == 0){
            JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en la base de datos.");
        }else if(checkNivel != 2){
            JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en el nivel 2.");
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
        }
    }

    public NodoNario getRaiz() {
        return raiz;
    }

}
