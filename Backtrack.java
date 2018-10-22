/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CLIENTE
 */
public class Backtrack {
    
    public Backtrack(CSP csp){
        buscaBacktrack(csp);
    }
    
    public Atribuicao buscaBacktrack(CSP csp){
        Atribuicao atribuicao = csp.getAtribuicao();
        return this.backtrackRecursivo(atribuicao, csp);
    }
    
    public Atribuicao backtrackRecursivo(Atribuicao atribuicao, CSP csp){
//        System.out.println("Tamanho da solução: " + atribuicao.size());

        if(atribuicao.size() == csp.numVariaveis()){
            csp.imprimeVariaveis();
            return atribuicao;
        }
        Variavel quadro = csp.menorDominio();
        if(quadro.dominio.isEmpty()){
            atribuicao.falhou();
            return atribuicao;
        }
        for(int i = 0; i < csp.tamDominio(); i++){
            if(quadro.dominio.contains(csp.getDominio().get(i))){
                int num = (int)csp.getDominio().get(i);
                csp.atribuiForward(quadro, num);
                atribuicao = backtrackRecursivo(atribuicao, csp);
                if(atribuicao.getFalha() == false) return atribuicao;
                csp.desatribuirForward(quadro);
            }
        }
        
        atribuicao.falhou();
        
        return atribuicao;
    }
    
    
    
}
