/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author CLIENTE
 */
public abstract class Restricao {
    
    protected String tipo;
    
    public Restricao(String tipo){
        this.tipo = tipo;
    }
    
    public abstract boolean satisfaz(Atribuicao atr, Variavel var, Object valor);
  
    public String getTipo(){
        return tipo;
    }
    
}
