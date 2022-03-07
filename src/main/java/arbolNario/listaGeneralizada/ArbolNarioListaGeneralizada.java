package arbolNario.listaGeneralizada;

import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo logica 3
 */
public class ArbolNarioListaGeneralizada {

    NodoNario raiz = new NodoNario(null);

    public ArbolNarioListaGeneralizada(){
    }
    
    //prueba de que los algoritmos funcionan correctamente
    public static void main(String[] args) {
        ArbolNarioListaGeneralizada a = new ArbolNarioListaGeneralizada();
        a.insertarDato(1, 3, 2);
        a.insertarDato(1, 1, 4);
        a.insertarDato(1, 8, 9);
        a.insertarDato(1,11 ,12);
        //a.insertarDato(3, 12, 7);
        //a.insertarDato(3, 12, 15);
        //a.insertarDato(4, 7, 20);
        //a.insertarDato(4, 7, 21);
        //a.insertarDato(4, 15, 22);
        a.insertarDato(2,3,34);
        a.insertarDato(2,3,35);
        a.insertarDato(2,3,36);
        a.insertarDato(2,3,37);
        a.insertarDato(2, 11, 53);
        a.prueba();
        //a.insertarDato(2, 8, 10);
        //a.insertarDato(3, 2, 13);
        //System.out.println(a.buscarNivel(53));
        //System.out.println(a.buscarRaiz(5));
        //a.prueba();
        ArrayList b = a.hijosNodo(1);
        for (int i = 0; i < b.size(); i++) {
            System.out.println("hijo" + b.get(i));
        }
        
    }
    
    public void prueba(){
      Stack stack = new Stack();
        NodoNario recorrido = raiz.getLiga();
        int nivel = 1;
        //NodoNario padreRaiz;
        while(recorrido != null){
            if(recorrido.getSw() == 0){
                System.out.println("Nodo:" + recorrido.getDato() + " nivel " + nivel);
            }
            if(recorrido.getSw() == 1){
                stack.push(recorrido);
                recorrido = (NodoNario)recorrido.getDato();
                System.out.println("Nodo:" + recorrido.getDato() + " nivel " + nivel);
                nivel++;
            }
            if(recorrido.getLiga() == null && !stack.isEmpty()){
                recorrido = (NodoNario)stack.pop();
                nivel--;
            }
            recorrido = recorrido.getLiga();
        }
    }
    
    public ArrayList<Integer> hijosNodo(int celContacto){
        ArrayList<Integer> hijos = new ArrayList<>();
        NodoNario recorrido = buscarRaizMod(celContacto);
        
        if(recorrido.getSw() == 1){
            recorrido = ((NodoNario)recorrido.getDato()).getLiga();
            while(recorrido != null){
                if(recorrido.getSw() == 1){
                    hijos.add((int)((NodoNario)recorrido.getDato()).getDato());
                }else{
                    hijos.add((int)recorrido.getDato());
                }
                recorrido = recorrido.getLiga();
            }
        }
        return hijos;
    }
    
    public void insertarDato(int nivel, float celContactoRaiz, float celContacto){
        switch (nivel) {
            case 1 -> insertarDatoNivel1(celContactoRaiz, celContacto);
            case 2 -> insertarDatoNivel2(celContactoRaiz, celContacto);                      
            case 3 -> insertarDatoNivel3(celContactoRaiz, celContacto);
            case 4 -> insertarDatoNivel4(celContactoRaiz, celContacto); 
            default -> {    
            }
        }
    }
    
    public NodoNario buscarRaiz(float contactoRaiz){
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
            }else if((float)recorrido.getDato() == contactoRaiz){
                return recorrido;
            } 
            if(recorrido.getLiga() == null && !stack.isEmpty()){
                recorrido = (NodoNario)stack.pop();
            }
            recorrido = recorrido.getLiga();
        }
        
        return null;
    }
    
    public NodoNario buscarRaizMod(float contactoRaiz){
        Stack stack = new Stack();
        NodoNario recorrido = raiz.getLiga();
        //NodoNario padreRaiz;
        while(recorrido != null){
            if(recorrido.getSw() == 1){
                stack.push(recorrido);
                recorrido = (NodoNario)recorrido.getDato();
                if((float)recorrido.getDato() == contactoRaiz){
                    return (NodoNario)stack.pop();
                }
            }else if((float)recorrido.getDato() == contactoRaiz){
                return recorrido;
            } 
            if(recorrido.getLiga() == null && !stack.isEmpty()){
                recorrido = (NodoNario)stack.pop();
            }
            recorrido = recorrido.getLiga();
        }
        
        return null;
    }
    
    public int buscarNivel(float celContacto){
        Stack stack = new Stack();
        NodoNario recorrido = raiz.getLiga();
        int nivel = 1;
        //NodoNario padreRaiz;
        while(recorrido != null){
            if(recorrido.getSw() == 1){
                stack.push(recorrido);
                recorrido = (NodoNario)recorrido.getDato();
                if((float)recorrido.getDato() == celContacto){
                    return nivel;
                }
                nivel++;
            }else if((float)recorrido.getDato() == celContacto && recorrido.getSw() == 0){
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
    
    public void insertarDatoNivel1(float celContacto, float hijoContacto){
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
    
    public void insertarDatoNivel2(float celContactoRaiz, float celContacto){
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
    
    public void insertarDatoNivel3(float celContactoRaiz, float celContacto){
        int checkNivel = buscarNivel(celContactoRaiz);
        if(checkNivel == 0){
            JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en la base de datos.");
        }else if(checkNivel != 2){
            JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en el nivel 2.");
        }else{
            NodoNario contRaiz = buscarRaizMod(celContactoRaiz);
            System.out.println(contRaiz.getDato());
            if(contRaiz.getSw() == 0){
                NodoNario a = new NodoNario(celContacto);
                a.setSw(0);
                NodoNario b = new NodoNario(celContactoRaiz);
                b.setSw(0);
                b.setLiga(a);
                contRaiz.setDato(b);
                contRaiz.setSw(1);
            }else{
                NodoNario recorrido = ((NodoNario)contRaiz.getDato()).getLiga();
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
    public void insertarDatoNivel4(float celContactoRaiz, float celContacto){
        int checkNivel = buscarNivel(celContactoRaiz);
        if(checkNivel == 0){
            JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en la base de datos.");
        }else if(checkNivel != 3){
            JOptionPane.showMessageDialog(null, "El contacto " + celContactoRaiz + " no se encuentra en el nivel 3.");
        }else{
            NodoNario contRaiz = buscarRaizMod(celContactoRaiz);
            if(contRaiz.getSw() == 0){
                NodoNario a = new NodoNario(celContacto);
                a.setSw(0);
                NodoNario b = new NodoNario(celContactoRaiz);
                b.setSw(0);
                b.setLiga(a);
                contRaiz.setDato(b);
                contRaiz.setSw(1);
            }else{
                NodoNario recorrido = ((NodoNario)contRaiz.getDato()).getLiga();
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
