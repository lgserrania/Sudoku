/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CLIENTE
 */
public abstract class Variavel {
    
    protected String nome;
    protected String descricao;
    protected List<Object> dominio;
    protected Map<String,LinkedList<Variavel>> variaveisLigadas = new HashMap<>();
    protected boolean alocada = false;
    
    public Variavel(String nome){
        this.nome = nome;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void getDescricao(){
        System.out.println(this.descricao);
    }
    
    public void addDominio(Object valor){
        this.dominio.add(valor);
    }
    
    public void setDominio(LinkedList<Object> dominio){
        this.dominio = dominio;
    }
    
    public void removeDominio(Object valor){
        if(this.dominio.contains(valor)){
            this.dominio.remove(valor);
        }
    }
    
    public void addVariavelLigada(Restricao rest, Variavel var){
        if(this.variaveisLigadas.get(rest.tipo) == null){
            this.variaveisLigadas.put(rest.tipo, new LinkedList<>());
        }
        this.variaveisLigadas.get(rest.tipo).add(var);
    }
    
    public boolean restricaoVar(Restricao rest, Variavel var){
        return this.variaveisLigadas.get(rest.tipo).contains(var);
    }
    
    public void alocar(){
        this.alocada = true;
    }
    
    public void desalocar(){
        this.alocada = false;
    }
    
}
