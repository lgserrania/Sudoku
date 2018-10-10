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
public class CSP {
    
    private LinkedList<Quadro> variaveis = new LinkedList<Quadro>();
    private Atribuicao atribuicao = new Atribuicao();
    private DiferenteRestricao diferenteRes = new DiferenteRestricao("diferente");
    private LinkedList<Object> dominio = new LinkedList<>();
    private Atribuicao melhorAtribuicao = new Atribuicao();
    
    public CSP(){
        
    }
    
    public void addVariavel(Quadro quadro){
        this.variaveis.add(quadro);
    }
    
    public void atribui(Quadro quadro, Object valor){
        this.atribuicao.atribuir(quadro, valor);   
        quadro.alocar();
    }
    
    public void atribuiForward(Quadro quadro, Object valor){
        this.atribui(quadro, valor);
        for(Variavel var : quadro.variaveisLigadas.get(diferenteRes.tipo)){
            Quadro quadroVar = (Quadro)var;
            if(!quadroVar.fixo()){
                if(quadroVar.dominio.contains(valor)){
                    quadroVar.removeDominio(valor);
                }
            }
        }
    }
    
    public Object desatribuir(Quadro quadro){
        
        Object valor = this.atribuicao.getValor(quadro);
        this.atribuicao.desatribuir(quadro);
        quadro.desalocar();
        return valor;
    }
    
    public void desatribuirForward(Quadro quadro){
        Object valor = this.desatribuir(quadro);
        for(Variavel var : quadro.variaveisLigadas.get(diferenteRes.tipo)){
            Quadro quadroVar = (Quadro)var;
            if(!quadroVar.fixo()){
                if(diferenteRes.satisfaz(atribuicao, var, valor)){
                    if(!var.dominio.contains(valor))
                        var.addDominio(valor);
                }
            }
        }
    }
    
    public void setMelhorAtribuicao(Atribuicao atribuicao){
        this.melhorAtribuicao = new Atribuicao();
        this.melhorAtribuicao.setSolucao(atribuicao);
    }
    
    public void setVariaveisAdjacentes(){
        for(Quadro quadro: this.variaveis){
            
            for(int i = 0; i < this.variaveis.size(); i++){
                Quadro quadro2 = this.variaveis.get(i);
                if(!quadro.equals(quadro2)){
                    if(verificaQuadrante(quadro.getLinha()) == verificaQuadrante(quadro2.getLinha()) 
                            && verificaQuadrante(quadro.getColuna()) == verificaQuadrante(quadro2.getColuna())){
                        quadro.addVariavelLigada(diferenteRes, quadro2);
                    }else if(quadro.getLinha() == quadro2.getLinha() || quadro.getColuna() == quadro2.getColuna()){
                        quadro.addVariavelLigada(diferenteRes, quadro2);
                    }
                }
            }
        }
    }
    
    public int verificaQuadrante(int valor){
        return valor - (valor % 3);
    }
    
    public void setDominios(){
        for(int i = 1; i <= 9; i++){
            this.dominio.add(i);
        }
        for(Quadro quadro : this.variaveis){
            for(int i = 0; i < this.dominio.size();i++){
                if(atribuicao.getValor(quadro) != null){
                    quadro.dominio.add(atribuicao.getValor(quadro));
                    break;
                }
                if(diferenteRes.satisfaz(atribuicao, quadro, this.dominio.get(i))){
                    quadro.addDominio(this.dominio.get(i));
                }
            }
        }
    }
    
    public Quadro menorDominio(){
        
        Quadro menorQuadro = null;
        int menorDominio = Integer.MAX_VALUE;
        
        for(Quadro quadro : this.variaveis){
            if(!quadro.alocada && !quadro.fixo()){
                if(quadro.dominio.size() < menorDominio){
                    menorQuadro = quadro;
                    menorDominio = quadro.dominio.size();
                }
            }
        }
        
        return menorQuadro;
        
    }
    
    public Quadro variavelLivre(){
        for(Quadro quadro : this.variaveis){
            if(!quadro.alocada){
                return quadro;
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
    
    public void imprimeMelhor(){
        System.out.println("");
        int linha = 0;
        for(Quadro quadro : variaveis){
            if(linha < quadro.getLinha()){
                System.out.println("");
                linha = quadro.getLinha();
            }
            if(this.melhorAtribuicao.getValor(quadro) == null){
                System.out.print("0 ");
            }else{
                System.out.print(this.melhorAtribuicao.getValor(quadro) + " ");
            }
        }
        System.out.println("");
    }
    
    public void imprimeVariaveis(){
        System.out.println("");
        int linha = 0;
        for(Quadro quadro : variaveis){
            if(linha < quadro.getLinha()){
                System.out.println("");
                linha = quadro.getLinha();
            }
            if(this.atribuicao.getValor(quadro) == null){
                System.out.print("0 ");
            }else{
                System.out.print(this.atribuicao.getValor(quadro) + " ");
            }
        }
        System.out.println("");
    }
    
    public int tamDominio(){
        return this.dominio.size();
    }
    
    public LinkedList<Object> getDominio(){
        return this.dominio;
    }
    
}
