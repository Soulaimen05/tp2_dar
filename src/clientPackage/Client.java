package clientPackage;
import model.Operation;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             Scanner sc = new Scanner(System.in)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // b) Saisie et envoi de l'objet Operation
            System.out.print("Premier opérande : ");
            double a = Double.parseDouble(sc.nextLine());
            System.out.print("Opérateur (+, -, *, /) : ");
            char operateur = sc.nextLine().charAt(0);
            System.out.print("Deuxième opérande : ");
            double b = Double.parseDouble(sc.nextLine());

            out.writeObject(new Operation(a, operateur, b));
            out.flush();

            // g) Lecture et affichage du résultat
            double resultat = (Double) in.readObject();
            System.out.println("Résultat reçu du serveur : " + resultat);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}