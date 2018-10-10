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
public class DiferenteRestricao extends Restricao{
    
    public DiferenteRestricao(String tipo){
        super(tipo);
    }

    @Override
    public boolean satisfaz(Atribuicao atr,Variavel var, Object valor) {       
        for(Variavel var2 : var.variaveisLigadas.get(this.tipo)){            
            if(atr.getValor(var2) == valor){
                return false;
            }           
        }
        return true;
    }
    
}
