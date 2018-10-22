/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author CLIENTE
 */
public abstract class CSP {
    
    protected LinkedList<Variavel> variaveis = new LinkedList<>();
    protected Atribuicao atribuicao = new Atribuicao();
    protected LinkedList<Object> dominio = new LinkedList<>();
    protected LinkedList<Restricao> restricoes = new LinkedList<>();
    
    public CSP(){
        
    }
    
    public void addVariavel(Variavel var){
        this.variaveis.add((Quadro)var);
    }
    
    public void atribui(Variavel variavel, Object valor){
        this.atribuicao.atribuir(variavel, valor);   
        variavel.alocar();
    }
    
    public void atribuiForward(Variavel variavel, Object valor){
        this.atribui(variavel, valor);
        for(Restricao res : this.restricoes){
            for(Variavel var : variavel.variaveisLigadas.get(res.tipo)){
                if(!res.satisfaz(atribuicao, var, valor)){
                    var.removeDominio(valor);
                }
            }
        }
    }

    public Object desatribuir(Variavel quadro){
        
        Object valor = this.atribuicao.getValor(quadro);
        this.atribuicao.desatribuir(quadro);
        quadro.desalocar();
        return valor;
    }
    
    public void desatribuirForward(Variavel variavel){
        
        HashMap<Variavel,LinkedList<Object>> valido = new HashMap<>();
        boolean teste = false;
        
        Object valor = this.desatribuir(variavel);
        for(Restricao res : this.restricoes){
            for(Variavel var : variavel.variaveisLigadas.get(res.tipo)){
                if(res.satisfaz(atribuicao, var, valor)){
                    if(valido.containsKey(var)){
                        if(!var.dominio.contains(valor))
                            valido.get(var).add(valor);
                    }else{
                        if(!var.dominio.contains(valor)){
                            valido.put(var, new LinkedList<>());
                            valido.get(var).add(valor);
                        }
                    }
                }else{
                    if(valido.containsKey(var)){
                        if(valido.get(var).contains(valor)){
                            valido.get(var).remove(valor);
                        }
                    }
                }
            }
        }
        for(Variavel var : valido.keySet()){
            for(Object val : valido.get(var)){
                var.addDominio(val);
            }
        }
    }
    
    public abstract void setVariaveisAdjacentes();
    
    public abstract void setDominios();
    
    public Variavel menorDominio(){
        
        Variavel menorVar = null;
        int menorDominio = Integer.MAX_VALUE;
        
        for(Variavel var : this.variaveis){
            if(!var.alocada){
                if(var.dominio.size() < menorDominio){
                    menorVar = var;
                    menorDominio = var.dominio.size();
                }
            }
        }
        
        return menorVar;
        
    }
    
    public Variavel variavelLivre(){
        for(Variavel var : this.variaveis){
            if(!var.alocada){
                return var;
            }
        }
        return null;
    }
    
    public Atribuicao getAtribuicao(){
        return this.atribuicao;
    }
    
    public int numVariaveis(){
        return this.variaveis.size();
    }
    
    public abstract void imprimeVariaveis();
        
    
    public int tamDominio(){
        return this.dominio.size();
    }
    
    public LinkedList<Object> getDominio(){
        return this.dominio;
    }
    
}
