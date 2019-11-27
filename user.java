package chattcpcientenaobloqueante;

import java.util.Scanner;

public class user {

    public static Scanner sc = new Scanner(System.in);
    static jokenpoCliente jogar = new jokenpoCliente();
    static String result;
    static String nome01;
    static String nome02;
    static boolean semaforo = false;
    static boolean trava = true;

    public static void main(String args[]) {
        String help = "\t >======== PEDRA, PAPEL, TESOURA ========< \n"
                + "\\Para iniciar digite: jogar + nome \n"
                + "\\Para jogar escolha e digite: pedra, papel, tesoura\n"
                + "\\Para sair do jogo digite: sair\n"
                + "\t========================================\n";
        System.out.println(help);
        while (true) {
            String parametros = sc.nextLine();
            String Parametros[] = parametros.split(" ");
            switch (Parametros[0]) {
                case "sair":
                    if (semaforo) {
                        semaforo = false;
                        trava=true;
                        result = jogar.doOperation(Parametros[0]);
                        jogar.sair();
                        System.out.println(" SAIU DO JOGO");

                    } else {
                        System.out.println("DIGITE JOGAR");
                    }
                    break;
                case "jogar":
                    if (trava) {
                        if (Parametros.length == 2) {
                            jogar.conection();
                            semaforo = true;
                            trava = false;
                            result = jogar.doOperation(jogar.mac() + " " + Parametros[1]);
                            nome01 = Parametros[1];
                            nome02 = result;
                            System.out.println(nome01 + " vs " + nome02);
                        } else {
                            System.out.println("CAMPO NOME VAZIO");
                        }
                    } else {
                        System.out.println("O JOGADOR ESTA CONECTADO");
                    }
                    break;
                case "pedra": case "Pedra": case "PEDRA":////
                    if (semaforo) {
                        result = jogar.doOperation("1");
                        String msg[] = result.split(" ");
                        switch (msg[0]) {
                            case "0":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " PEDRA  <  PAPEL " + nome02);
                                System.out.println(">>>>>>>> PERDEU <<<<<<<<");
                                break;
                            case "1":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " PEDRA  >  TESOURA " + nome02);
                                System.out.println(">>>>>>>> GANHOU <<<<<<<<");
                                break;
                            case "2":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " PEDRA  =  PEDRA " + nome02);
                                System.out.println(">>>>>>>> EMPATE <<<<<<<<");
                                break;
                            case "sair":
                                System.out.println(" U JOGADOR DESCONECTOU-SE");
                                jogar.sair();
                                semaforo = false;
                                break;
                        }
                    } else {
                        System.out.println("DIGITE JOGAR");
                    }
                    break;
                case "papel":  case "Papel":  case "PAPEL":///
                    if (semaforo) {
                        result = jogar.doOperation("2");
                        String msg[] = result.split(" ");
                        switch (msg[0]) {
                            case "0":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " PAPEL  <  TESOURA " + nome02);
                                System.out.println(">>>>>>>> PERDEU <<<<<<<<");
                                break;
                            case "1":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " PAPEL > PEDRA " + nome02);
                                System.out.println(">>>>>>>> GANHOU <<<<<<<<");
                                break;
                            case "2":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " PAPEL = PAPEL " + nome02);
                                System.out.println(">>>>>>>> EMPATE <<<<<<<<");
                                break;
                            case "sair":
                                System.out.println(" U JOGADOR DESCONECTOU-SE");
                                jogar.sair();
                                semaforo = false;
                                break;
                        }
                    } else {
                        System.out.println("DIGITE JOGAR");
                    }
                    break;
                case "tesoura": case "Tesoura": case "TESOURA"://
                    if (semaforo) {
                        result = jogar.doOperation("3");
                        String msg[] = result.split(" ");
                        switch (msg[0]) {
                            case "0":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " TESOURA  <  PEDRA " + nome02);
                                System.out.println(">>>>>>>> PERDEU <<<<<<<<");
                                break;
                            case "1":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " TESOURA  >  PAPEL " + nome02);
                                System.out.println(">>>>>>>> GANHOU <<<<<<<<");
                                break;
                            case "2":
                                System.out.println("Vitorias" + "[ " + msg[1] + " ]" + " Derrotas" + "[ " + msg[2] + " ]");
                                System.out.println(nome01 + " TESOURA  =  TESOURA " + nome02);
                                System.out.println(">>>>>>>> EMPATE <<<<<<<<");
                                break;
                            case "sair":
                                System.out.println(" U JOGADOR DESCONECTOU-SE");
                                jogar.sair();
                                semaforo = false;
                                break;
                        }
                    } else {
                        System.out.println("DIGITE JOGAR");
                    }
                    break;
                default:
                    System.out.println("INCORRETO");

            }

        }
    }
}
