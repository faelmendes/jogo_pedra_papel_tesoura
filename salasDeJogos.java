/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattcpservidonaobloqueante;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class salasDeJogos extends Thread {

    private static DataInputStream in01;
    private static DataOutputStream out01;
    private static DataInputStream in02;
    private static DataOutputStream out02;
    private Socket jogador01;
    private Socket jogador02;
    private bancoDeDados banco;
    private boolean semaforo;

    public salasDeJogos(Socket jogador01, Socket jogador02) throws IOException {
        try {
            this.jogador01 = jogador01;
            this.jogador02 = jogador02;
            in01 = new DataInputStream(jogador01.getInputStream());
            out01 = new DataOutputStream(jogador01.getOutputStream());
            in02 = new DataInputStream(jogador02.getInputStream());
            out02 = new DataOutputStream(jogador02.getOutputStream());
            banco = new bancoDeDados("/home/rafael-pc/banco_de_dados/arquivo.txt");
            semaforo = true;
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            boolean trava = true;
            int vitoria01 = 0;
            int vitoria02 = 0;
            String mac01 = null;
            String mac02 = null;
            while (semaforo) {
                System.out.println(jogador01.hashCode() + " " + jogador02.hashCode());///
                String resposta01 = in01.readUTF();
                String resposta02 = in02.readUTF();
                if (trava) {
                    String[] p01 = resposta01.split(" ");
                    String[] p02 = resposta02.split(" ");
                    mac01 = p01[0];
                    mac02 = p02[0];
                    out01.writeUTF(p02[1]);
                    out02.writeUTF(p01[1]);
                    /*String result = banco.busca(mac01, mac02);
                    String[] placar = result.split(" ");
                    if (placar[0].equals("1")) {
                        vitoria01 = Integer.parseInt(placar[1]);
                        vitoria02 = Integer.parseInt(placar[2]);
                    } else if (placar[0].equals("2")) {
                        vitoria01 = Integer.parseInt(placar[1]);
                        vitoria02 = Integer.parseInt(placar[2]);

                    } else {
                        vitoria01 = Integer.parseInt(placar[1]);
                        vitoria02 = Integer.parseInt(placar[2]);
                    }*/
                    trava = false;
                } else if (resposta01.equals("sair")) {
                    //banco.cadastra(mac01 + " " + mac02 + " " + vitoria01 + " " + vitoria02);/////////
                    out01.writeUTF("sair");
                    out02.writeUTF("sair");
                    sair();
                } else if (resposta02.equals("sair")) {
                    //banco.cadastra(mac01 + " " + mac02 + " " + vitoria01 + " " + vitoria02);
                    out02.writeUTF("sair");
                    out01.writeUTF("sair");
                    sair();
                } else if (resposta01.equals("1") && resposta02.equals("3")) {       ///PEDRA TESOURA
                    vitoria01 += 1;
                    out01.writeUTF("1" + " " + vitoria01 + " " + vitoria02);
                    out02.writeUTF("0" + " " + vitoria02 + " " + vitoria01);
                } else if (resposta02.equals("1") && resposta01.equals("3")) {      ///PEDRA TESOURA
                    vitoria02 += 1;
                    out02.writeUTF("1" + " " + vitoria02 + " " + vitoria01);
                    out01.writeUTF("0" + " " + vitoria01 + " " + vitoria02);
                } else if (resposta01.equals("3") && resposta02.equals("2")) {     ///TESOURA PAPEL
                    vitoria01 += 1;
                    out01.writeUTF("1" + " " + vitoria01 + " " + vitoria02);
                    out02.writeUTF("0" + " " + vitoria02 + " " + vitoria01);
                } else if (resposta02.equals("3") && resposta01.equals("2")) {     ///TESOURA PAPEL
                    vitoria02 += 1;
                    out02.writeUTF("1" + " " + vitoria02 + " " + vitoria01);
                    out01.writeUTF("0" + " " + vitoria01 + " " + vitoria02);
                } else if (resposta01.equals("2") && resposta02.equals("1")) {    ///PAPEL PEDRS
                    vitoria01 += 1;
                    out01.writeUTF("1" + " " + vitoria01 + " " + vitoria02);
                    out02.writeUTF("0" + " " + vitoria02 + " " + vitoria01);
                } else if (resposta02.equals("2") && resposta01.equals("1")) {    ///PAPEL PEDRA
                    vitoria02 += 1;
                    out02.writeUTF("1" + " " + vitoria02 + " " + vitoria01);
                    out01.writeUTF("0" + " " + vitoria01 + " " + vitoria02);
                } else if (resposta02.equals(resposta01)) {
                    out01.writeUTF("2" + " " + vitoria01 + " " + vitoria02);
                    out02.writeUTF("2" + " " + vitoria02 + " " + vitoria01);
                } 
            }

        } catch (IOException ex) {
            Logger.getLogger(salasDeJogos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sair() throws IOException {
        try {
            System.out.println("fechafefefefefefeefefefeffefefefefefefefefefe");
            this.jogador01.close();
            this.jogador02.close();
            semaforo = false;
        } catch (IOException ex) {
            Logger.getLogger(salasDeJogos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
