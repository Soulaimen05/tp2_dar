import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             Scanner sc = new Scanner(System.in)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // a) Saisie et envoi des éléments de l'opération
            System.out.print("Premier opérande : ");
            out.println(sc.nextLine());
            System.out.print("Opérateur : ");
            out.println(sc.nextLine());
            System.out.print("Deuxième opérande : ");
            out.println(sc.nextLine());

            // d) Lecture et affichage du résultat
            String resultat = in.readLine();
            System.out.println("Résultat reçu du serveur : " + resultat);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}