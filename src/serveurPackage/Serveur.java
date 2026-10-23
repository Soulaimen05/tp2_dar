import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        try (ServerSocket serveur = new ServerSocket(1234)) {
            System.out.println("Serveur en attente de connexion...");
            Socket socket = serveur.accept();
            System.out.println("Client connecté : " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // a) Réception des éléments de l'opération
            int op1 = Integer.parseInt(in.readLine());
            String operateur = in.readLine();
            int op2 = Integer.parseInt(in.readLine());
            System.out.println("Reçu : " + op1 + " " + operateur + " " + op2);

            // b) Calcul de l'opération demandée (multiplication)
            int resultat = op1 * op2;

            // c) Envoi du résultat au client
            out.println(resultat);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}