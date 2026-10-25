package serveurPackage;
import model.Operation;

import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        try (ServerSocket serveur = new ServerSocket(1234)) {
            System.out.println("Serveur en attente de connexion...");
            Socket socket = serveur.accept();
            System.out.println("Client connecté : " + socket.getInetAddress());

            // On crée le flux de sortie en premier et on le vide (flush)
            // pour éviter un blocage à l'ouverture des flux objets
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // c) Réception de l'objet
            Operation op = (Operation) in.readObject();

            // d) Récupération des données
            double a = op.getOperande1();
            char operateur = op.getOperateur();
            double b = op.getOperande2();
            System.out.println("Reçu : " + a + " " + operateur + " " + b);

            // e) Calcul
            double resultat = 0;
            switch (operateur) {
                case '+': resultat = a + b; break;
                case '-': resultat = a - b; break;
                case '*': resultat = a * b; break;
                case '/': resultat = a / b; break;
                default:
                    System.out.println("Opérateur inconnu : " + operateur);
            }

            // f) Envoi du résultat
            out.writeObject(resultat);
            out.flush();

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}