/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Gustavo
 */
public class SudokuCSP extends CSP{
    
    DiferenteRestricao diferenteRes = new DiferenteRestricao("Diferente");
    
    public SudokuCSP(){
        this.restricoes.add(new DiferenteRestricao("Diferente"));
        
    }
    
    @Override
    public void atribuiForward(Variavel quadro, Object valor){
        this.atribui(quadro, valor);    
        for(Variavel var : quadro.variaveisLigadas.get(diferenteRes.tipo)){
            Quadro quadroVar = (Quadro)var;
            if(!quadroVar.fixo()){
                if(var.dominio.contains(valor)){
                    var.removeDominio(valor);
                }
            }
        }
    }
    
    
    
    @Override
    public void desatribuirForward(Variavel quadro){
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
    
    
    
    @Override
    public void setDominios(){
        for(int i = 1; i <= 9; i++){
            this.dominio.add(i);
        }
        for(Variavel var : this.variaveis){
            for(int i = 0; i < this.dominio.size();i++){
                if(atribuicao.getValor(var) != null){
                    var.dominio.add(atribuicao.getValor(var));
                    break;
                }
                if(diferenteRes.satisfaz(atribuicao, var, this.dominio.get(i))){
                    var.addDominio(this.dominio.get(i));
                }
            }
        }
    }

    @Override
    public void setVariaveisAdjacentes() {
        
        for(Variavel var: this.variaveis){
            Quadro quadro = (Quadro)var;
            for(int i = 0; i < this.variaveis.size(); i++){
                Quadro quadro2 = (Quadro)this.variaveis.get(i);
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

    @Override
    public void imprimeVariaveis() {
        System.out.println("");
        int linha = 0;
        for(Variavel var : variaveis){
            Quadro quadro = (Quadro)var;
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
    
}
