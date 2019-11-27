package chattcpcientenaobloqueante;

import java.net.*;
import java.io.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class jokenpoCliente {

    static DataOutputStream out;
    static DataInputStream in;
    Socket s;
    String mensagem;

    public void conection() {
        try {

            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());

        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        }

    }

    public String doOperation(String requisicao) {
        senRequest(requisicao);
        String resposta = getResponse();
        return resposta;
    }

    public void senRequest(String requisicao) {
        try {
            out.writeUTF(requisicao);

        } catch (EOFException e) {
            Logger.getLogger(jokenpoCliente.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(jokenpoCliente.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public String getResponse() {
        try {

            mensagem = in.readUTF();

        } catch (UnknownHostException e) {
            Logger.getLogger(jokenpoCliente.class.getName()).log(Level.SEVERE, null, e);
        } catch (EOFException e) {
            Logger.getLogger(jokenpoCliente.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(jokenpoCliente.class.getName()).log(Level.SEVERE, null, e);
        }

        return mensagem;
    }

    public String mac() {
        String macAddr = null;
        try {
            Enumeration<NetworkInterface> eth = NetworkInterface
                    .getNetworkInterfaces();
            while (eth.hasMoreElements()) {
                NetworkInterface eth0 = eth.nextElement();
                byte[] mac = eth0.getHardwareAddress();
                StringBuilder sb = new StringBuilder();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        String aux = String.format("%02X%s", mac[i],
                                (i < mac.length - 1) ? "-" : "");
                        sb.append(aux);
                    }
                    if (sb.toString().length() <= 18) {
                        macAddr = sb.toString();
                        return macAddr;
                    }
                }
            }
        } catch (SocketException e) {
        }
        return null;
    }

    public void sair() {

        try {

            s.close();

        } catch (IOException ex) {
            Logger.getLogger(jokenpoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
