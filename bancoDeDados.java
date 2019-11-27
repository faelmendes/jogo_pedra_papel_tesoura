package chattcpservidonaobloqueante;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class bancoDeDados {

    String caminho;

    public bancoDeDados(String caminho) {
        this.caminho = caminho;
    }

    public synchronized String busca(String mac1, String mac2) throws IOException {
        try {

            FileInputStream arquivo = new FileInputStream(caminho);
            InputStreamReader input = new InputStreamReader(arquivo);
            try (BufferedReader br = new BufferedReader(input)) {
                String linha;
                do {
                    linha = br.readLine();
                    if (linha != null) {             ////linhas igual a null para
                        String[] buff = linha.split(" ");
                        if (mac1.equals(buff[0]) && mac2.equals(buff[1])) {
                            apagar(linha);       //////
                            br.close();
                            return "1" + " " + buff[2] + " " + buff[3];
                        }
                        if (mac2.equals(buff[0]) && mac1.equals(buff[1])) {
                            apagar(linha);      //////
                            br.close();
                            return "2" + " " + buff[3] + " " + buff[2];
                            
                        }
                    }
                } while (linha != null);
            }///
        } catch (FileNotFoundException ex) {
            Logger.getLogger(bancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(bancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0 + " " + 0 + " " + 0;
    }

    public synchronized void cadastra(String mac_placar) throws IOException {

        FileWriter arquivo;
        try {

            arquivo = new FileWriter(caminho, true);
            try (PrintWriter pr = new PrintWriter(arquivo)) {
                pr.println(mac_placar);
                pr.flush();
                pr.close();
                arquivo.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(bancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static final Logger log = Logger.getLogger(bancoDeDados.class.getName());

    public synchronized void apagar(String linha) throws IOException {
        String comando = "sed -i '/" + linha + "/d' /home/rafael-pc/banco_de_dados/arquivo.txt";
        ArrayList<String> commands = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(comando);

        try {
            ProcessBuilder p = new ProcessBuilder(commands);
            p.start();

        } catch (IOException ioe) {
            log.severe("Erro ao executar comando shell" + ioe.getMessage());
            throw ioe;
        }
    }

}
