package forumSocketsAndThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class SocketHandlerPingPong extends Thread {

    private final Socket socket;
    private final Logger logger = Logger.getLogger(SocketHandlerPingPong.class.getName());
    private PrintWriter saida;

    public SocketHandlerPingPong(Socket socket) {

        logger.info("Usuario conectado!");
        this.socket = socket;
    }

    public void rodar() {

        try {
            saida = new PrintWriter(socket.getOutputStream());
            Scanner input = new Scanner(socket.getInputStream());
            String mensagem = "";

            while (!mensagem.equalsIgnoreCase("Final")) {
                mensagem = input.nextLine();
                logger.info(String.format("Nova mensagem: {}", mensagem));
                mensagemHandlerExecutar(mensagem);
            }

            if (mensagem.equalsIgnoreCase("Final")) {
                sendMensagem("Conexao finalizada!");
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mensagemHandlerExecutar(String comand) throws IOException {

        logger.info(String.format("Novo comando processando: %s", comand));

        if (comand.equalsIgnoreCase("ping")) {
            sendMensagem("pong");
        }

        if (comand.equalsIgnoreCase("end")) {
            sendMensagem("Conexao finalizada!");
            socket.close();
        } else {
            sendMensagem("Nao encontrou caminho");
        }
    }

    public void sendMensagem(String mensagem) {

        try {

            saida.println(mensagem);
            saida.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
