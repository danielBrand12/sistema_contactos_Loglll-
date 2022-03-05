/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_contactos.BD_contactos;

import arbolNario.listaGeneralizada.ArbolNarioListaGeneralizada;
import sistema_contactos.contacto.Contacto;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel
 */
public class BD_contactos {
    
    ArbolNarioListaGeneralizada contactos;
    
    public BD_contactos() throws FileNotFoundException{
        contactos = new ArbolNarioListaGeneralizada();
        File data = new File("contactos.txt");
        Scanner scan = new Scanner(data);
        if(scan.hasNextLine()){
            while(scan.hasNextLine()){
                //Arreglar esto
                String[] info_contacto = scan.nextLine().split(":");
                Contacto contacto = new Contacto(Integer.parseInt(info_contacto[0]),info_contacto[1]);
                contactos.insertarDato(contacto);
            }
        }
    }
    public void guardarDatos(){
        
    }
}
