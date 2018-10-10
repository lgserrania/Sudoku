/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CLIENTE
 */
public class Atribuicao {
    
    private Map<Variavel, Object> atribuicoes;
    private boolean falha = false;
    
    public Atribuicao(){
        this.atribuicoes = new HashMap<>();
    }
    
    public void atribuir(Variavel var, Object valor){
        this.atribuicoes.put(var, valor);
    }
    
    public void desatribuir(Variavel var){
        this.atribuicoes.remove(var);
    }
    
    public int size(){
        return atribuicoes.size();
    }
    
    public Object getValor(Variavel var){
        return this.atribuicoes.get(var);
    }
    
    public void falhou(){
        this.falha = true;
    }
    
    public boolean getFalha(){
        return this.falha;
    }
    
    public void setSolucao(Atribuicao atribuicao){
        this.atribuicoes.putAll(atribuicao.atribuicoes);
    }
    
}
