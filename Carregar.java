/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CLIENTE
 */
public class Carregar {
    
    private SudokuCSP csp;
    private int numSudoku;
    private final int dimensao = 9;
    
    public Carregar(){
        try {
            lerArquivo();
        } catch (IOException ex) {
            Logger.getLogger(Carregar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void lerArquivo() throws FileNotFoundException, IOException{
        FileReader arq = new FileReader("instances.sudoku");
        BufferedReader lerArq = new BufferedReader(arq);
        Scanner scanner = new Scanner(System.in);
        //Pega o número de sudokus
        String linha = lerArq.readLine();
        this.numSudoku = Integer.parseInt(linha);
        
        for(int instancia = 0; instancia < this.numSudoku; instancia++){
            
            System.out.println("################ Instância " + (instancia + 1) + " ################");
            
            this.csp = new SudokuCSP();
            
            for(int i = 0; i < dimensao; i++){
                linha = lerArq.readLine();
                String[] partes = linha.split(" ");
                for(int j = 0; j < dimensao; j++){
                    Quadro quadro = new Quadro(i + "-" + j, i, j);
                    this.csp.addVariavel(quadro);
                    if(Integer.parseInt(partes[j]) > 0){
                        this.csp.atribui(quadro, Integer.parseInt(partes[j]));
                        quadro.alocar();
                        quadro.fixar();
                    }
                }
            }
            
            this.csp.setVariaveisAdjacentes();
            this.csp.setDominios();
            Backtrack back = new Backtrack(this.csp);
            lerArq.readLine();
            System.out.println("\n");
        }
    }
    
}
