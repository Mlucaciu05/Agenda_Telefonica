import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Daca aveti un cont, va rugam introduceti 1. Daca doriti sa creati un cont, introduceti 0:");
            String n = scanner.next();

            System.out.println("Username:");
            scanner.nextLine();
            String username = scanner.nextLine();
            System.out.println("Password:");
            String password = scanner.nextLine();

            if (n.equals("0")) {
                userManager.register(username, password);
                System.out.println("Contul a fost creat, va rugam sa va logati");
            } else {
                if(userManager.login(username, password)) {
                    Meniu(userManager.getCurrentUser());
                } else {
                    System.out.println("Username-ul sau parola sunt gresite, va rugam incercati din nou.");
                }
            }
        }

    }

    public static void Meniu(User currentUser) {

        while(true) {
            System.out.println("---Meniu---\n1.Vizualizati toate contactele\n2.Introduceti un contact nou\n3.Stergeti un contact");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    currentUser.getAgenda().printContactList();
                    break;

                case "2":
                    System.out.println("Introduceti un contact nou:");
                    System.out.println("Nume:");
                    String nume = scanner.nextLine();
                    System.out.println("Prenume:");
                    String prenume = scanner.nextLine();
                    System.out.println("Nr. Telefon:");
                    String telefon = scanner.nextLine();
                    System.out.println("Email:");
                    String email = scanner.nextLine();
                    currentUser.getAgenda().addContact(new Contact(nume, prenume, email, telefon));
                    break;

                case "3":
                    System.out.println("Introduceti numele contactului pe care doriti sa il stergeti:");
                    System.out.println("Nume:");
                    nume = scanner.nextLine();
                    System.out.println("Prenume:");
                    prenume = scanner.nextLine();

                    ArrayList<Contact> contacteGasite = new ArrayList<>();
                    contacteGasite = currentUser.getAgenda().searchContactNume(nume + " " + prenume);

                    System.out.println("Selectati contactul pe care doriti sa il stergeti:");
                    for (int i = 0; i < contacteGasite.size(); i++) {
                        System.out.println(i+1 + "." + contacteGasite.get(i).toString());
                    }
                    String n = scanner.nextLine();
                    currentUser.getAgenda().removeContact(contacteGasite.get(Integer.parseInt(n) - 1));

            }
        }
    }

}