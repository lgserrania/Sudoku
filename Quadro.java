/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.LinkedList;

/**
 *
 * @author CLIENTE
 */
public class Quadro extends Variavel{
    
    private final int linha;
    private final int coluna;
    private boolean fixed = false;
    
    
    public Quadro(String nome, int linha, int coluna) {
        super(nome);
        this.linha = linha;
        this.coluna = coluna;
        this.dominio = new LinkedList<>();
    }
    
    public int getLinha(){
        return linha;
    }
    
    public int getColuna(){
        return coluna;
    }
    
    public void setDominio(LinkedList<Object> dominio){
        this.dominio = dominio;
    }
    
    public void addVariavelLigada(DiferenteRestricao rest, Quadro quadro){
        if(this.variaveisLigadas.get(rest.tipo) == null){
            this.variaveisLigadas.put(rest.tipo, new LinkedList<>());
        }
        this.variaveisLigadas.get(rest.tipo).add(quadro);
    }
    
    public void fixar(){
        this.fixed = true;
    }
    
    public boolean fixo(){
        return this.fixed;
    }
    
}
