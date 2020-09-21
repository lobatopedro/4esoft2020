package forumSocketsAndThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class SocketClientPingPong {

    private static final int PORT = 8080;
    private static final String SERVER_ADDRESS = "localhost";
    private final Logger logger = Logger.getLogger(SocketClientPingPong.class.getName());
    private static final String COMAND = "ping";

    public static void main(String[] args) throws Exception {

        final SocketClientPingPong client = new SocketClientPingPong();
        client.executador();
    }

    private void executador() throws Exception {

        Integer count = 0;

        List<Long> listaCountTempo = new ArrayList<>();

        while (count < 1000) {

            Long iniciar = System.currentTimeMillis();

            handleComunicacaoServer(COMAND);

            Long terminando = System.currentTimeMillis();

            logger.info(String.format("Inicio: %s. Termino: %s.", iniciar, terminando));

            logger.info(String.format("Media: %s.", terminando - iniciar));

            listaCountTempo.add(terminando - iniciar);

            count++;
        }

        handleComunicacaoServer("Final");

        Collections.sort(listaCountTempo);

        Long menorTempo = listaCountTempo.get(0);
        Long maiorTempo = listaCountTempo.get(listaCountTempo.size() - 1);

        logger.info(String.format("Menor tempo: %s ms", menorTempo));
        logger.info(String.format("Maior tempo: %s ms", maiorTempo));

        double media = listaCountTempo.stream().mapToDouble(Long::doubleValue).average().orElse(0);
        logger.info(String.format("Média: %s ms", media));
    }

    private String handleComunicacaoServer(String comando) throws IOException {

        Socket connection = new Socket(SERVER_ADDRESS, PORT);
        Scanner serverInput = new Scanner(connection.getInputStream());
        PrintWriter serverOutput = new PrintWriter(connection.getOutputStream());

        serverOutput.println(comando);
        serverOutput.flush();

        String response = serverInput.nextLine();

        logger.info(String.format("Retorno: %s", response));
        connection.close();
        return response;
    }
}
