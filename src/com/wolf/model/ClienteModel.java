package com.wolf.model;

import com.wolf.domain.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Henrique Wolf Brito
 */
public class ClienteModel {
    
    public static List<Cliente> clientes = new ArrayList<>();
    
    private static int codigo = 1;
    
    public static void addCliente(Cliente c){
        c.setCodigo(codigo);
        clientes.add(c);
        codigo++;
    }
    
    public static void removeCliente(Cliente c){
        if(clientes.contains(c)){
            clientes.remove(c);
        }
    }
    
    public static void updateCliente(Cliente c){
        int pos = 0;
        for(int i=0; i<clientes.size(); i++){
            if(clientes.get(i).equals(c)){
                pos  = i;
                break;
            }
        }
        Cliente cOld = clientes.get(pos);
        if(c.getCidade() == null) {
        	c.setCidade(cOld.getCidade());
        }
        c.setCodigo(cOld.getCodigo());
        //clientes.remove(pos);
        clientes.set(pos, c);
    }
    
    public static Cliente getByCodigo(int cod){
        Cliente cli = null;
        for(int i=0; i<clientes.size(); i++){
            if(clientes.get(i).getCodigo() == cod){
                cli = clientes.get(i);
                break;
            }
        }
        return cli;
    }
}
