/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_contactos.BD_identificador;

import arbol.binario.listaligada.busqueda.avl.ArbolAVL;
import arbol.binario.listaligada.busqueda.avl.NodoAVL;
import sistema_contactos.contacto.Contacto;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author daniel
 */
public class BD_identificador {
    private ArbolAVL identificador;
    
    /*
    *Carga la Base de datos desde el archivo de texto guardado previamente
    */
    public BD_identificador() throws FileNotFoundException, IOException{
        identificador = new ArbolAVL();
        File data = new File("/home/daniel/Descargas/contactos");
        Scanner scan = new Scanner(data);
        if(scan.hasNextLine()){
            while(scan.hasNextLine()){
                String[] info_contacto = scan.nextLine().split(":");
                Contacto contacto = new Contacto(Integer.parseInt(info_contacto[0]),info_contacto[1]);
                identificador.insertarDato(contacto);
            }
        }
        
    }
    
    public void agregarContacto(Contacto c) throws IOException{
        if("Contacto no encontrado.".equals(buscarContacto(c.getCelular()))){
            identificador.insertarDato(c);
            FileWriter file =  new FileWriter("/home/daniel/Descargas/contactos", true);
            BufferedWriter b = new BufferedWriter(file);
            b.write(c.getCelular()+":"+c.getNombre());
            b.newLine();
            b.close();
            file.close();
        }else{
            JOptionPane.showMessageDialog(null, "El contacto ya está registrado en la base de datos.");
        }
    }
    
    public String buscarContacto(int cel){
        String data;
        Contacto aux = new Contacto(cel,"");
        if(identificador.buscar(aux) != null){
            aux = (Contacto) identificador.buscar(aux).getDato();
            data = "Nombre: " + aux.getNombre() + " Celular: " + aux.getCelular();
            return data;
        }else{
            return "Contacto no encontrado.";
        }
    }
    
    public static void main(String[] args) throws IOException {
        BD_identificador a = new BD_identificador();
        System.out.println("Contacto: " + a.buscarContacto(50046));
        Contacto c = new Contacto(100046,"daniel7");
        a.agregarContacto(c);
    }
}
