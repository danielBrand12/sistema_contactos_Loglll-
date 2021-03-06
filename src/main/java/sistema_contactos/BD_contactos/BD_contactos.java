/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_contactos.BD_contactos;

import arbolNario.listaGeneralizada.ArbolNarioListaGeneralizada;
import arbolNario.listaGeneralizada.NodoNario;
import sistema_contactos.contacto.Contacto;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel
 */
public class BD_contactos {
    
    public ArbolNarioListaGeneralizada contactos;
    
    public BD_contactos() throws FileNotFoundException{
        contactos = new ArbolNarioListaGeneralizada();
        File data = new File("contactos.txt");
        Scanner scan = new Scanner(data);
        if(scan.hasNextLine()){
            int bandera = 0;
            while(scan.hasNextLine()){
                String[] info_contacto = scan.nextLine().split(":");
                if(Integer.parseInt(info_contacto[0]) == 1){
                    if(bandera != Integer.parseInt(info_contacto[1])){
                        bandera = Integer.parseInt(info_contacto[1]);
                        contactos.insertarDato(Integer.parseInt(info_contacto[0]), Float.parseFloat(info_contacto[1]), Float.parseFloat(info_contacto[2]));
                    }else{
                        contactos.insertarDato(Integer.parseInt(info_contacto[0]) + 1, Float.parseFloat(info_contacto[1]), Float.parseFloat(info_contacto[2]));
                    }
                }else{
                    contactos.insertarDato(Integer.parseInt(info_contacto[0]) + 1, Float.parseFloat(info_contacto[1]), Float.parseFloat(info_contacto[2]));
                }
            }
        }
    }
    
    public void guardarDatos(){
        
        System.out.println(System.getProperty("user.dir"));
        Stack stack = new Stack();
        NodoNario recorrido = contactos.getRaiz().getLiga();
        StringBuilder str = new StringBuilder();
        
        while(recorrido != null){
            
            if(recorrido.getSw() == 1){
                stack.push(recorrido);
                recorrido = (NodoNario)recorrido.getDato();               
            } 
            
            // Buscamos los padres de los nodos, para ello empezamos por descartar los nodos de nivel 1, pues a estos no se les asigna uno.
            if(contactos.buscarNivel((int)recorrido.getDato()) != 1) {
                    
                // Obtenemos el ??ltimo nodo guardado en el stack 
                NodoNario nodoAnterior = (NodoNario)stack.peek();
                
                // Si el ??ltimo nodo guardado en el stack coincide con el nodo actual, entonces el padre ser?? el que se encuentre en la
                // segunda posici??n del stack
                if(nodoAnterior.getDato() == recorrido) {
                        
                    // Creo un nodo temporal para guardar el ??ltimo nodo del stack y poder ingresarlo nuevamente al stack luego
                    NodoNario nodoTemporal = (NodoNario)stack.pop();
                    // Selecciono el elemento padre
                    NodoNario x = (NodoNario)stack.peek();
                    NodoNario padre = (NodoNario)x.getDato();
                    //Ingreso nuevamente el nodo temporal al stack
                    stack.push(nodoTemporal);
                        
                    // A??adimos los datos al stringBuilder
                    str.append(contactos.buscarNivel((int)padre.getDato()));
                    str.append(":");
                    str.append(padre.getDato());
                    str.append(":");
                    str.append(recorrido.getDato());
                    str.append("\n");
                        
                } else { // De lo contrario, el padre ser?? el ??ltimo nodo guardado
                        
                    // Selecciono el elemento padre
                    NodoNario x = (NodoNario)stack.peek();
                    NodoNario padre = (NodoNario)x.getDato();
                        
                    // A??adimos los datos al stringBuilder
                    str.append(contactos.buscarNivel((int)padre.getDato()));
                    str.append(":");
                    str.append(padre.getDato());
                    str.append(":");
                    str.append(recorrido.getDato());
                    str.append("\n");
                        
                }
            }   
          
            if(recorrido.getLiga() == null && !stack.isEmpty()){
                recorrido = (NodoNario)stack.pop();
            }
            
            recorrido = recorrido.getLiga();
        }
  
        // Escribimos en el txt
        try {
            FileWriter myWriter = new FileWriter("contactos.txt");
            myWriter.write(str.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ingresarDato(int nivel, float celRaiz, float cel){
        contactos.insertarDato(nivel, celRaiz, cel);
    }
}
