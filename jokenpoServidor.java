package chattcpservidonaobloqueante;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class jokenpoServidor {
    static List<Socket> jogadores = new ArrayList<Socket>();
    public static void main(String args[]) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                jogadores.add(clientSocket);
                    if (jogadores.size() == 2) {
                        salasDeJogos sala = new salasDeJogos(jogadores.get(0), jogadores.get(1));
                        jogadores.clear();
                        System.out.println("rodando "+jogadores.size());
                    }

            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }

}
