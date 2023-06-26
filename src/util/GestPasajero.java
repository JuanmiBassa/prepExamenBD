package util;

import dao.AenaDAO;
import dao.AenaDaoImpl;

import java.util.Scanner;

public class GestPasajero {
    public static void gestPasajeroProcess() {
        AenaDAO aenaDAO = new AenaDaoImpl();
        String dni;
        String nom;
        String llins;
        String tel;
        String email;

        while (true) {
            System.out.println("***   GEST PASAJERO   ***");
            System.out.println("1) Añadir pasajero");
            System.out.println("2) Modificar pasajero");
            System.out.println("3) Salir");

            System.out.print("Seleciona una opción: ");
            Scanner preg = new Scanner(System.in);
            String resp = preg.nextLine();

            if (resp.equals("1")) {
                while (true) {
                    while (true) {
                        System.out.print("Dni: ");
                        Scanner pregDni = new Scanner(System.in);
                        dni = pregDni.nextLine().toUpperCase();
                        if (!dni.isEmpty()) {
                            if (aenaDAO.readString("passatger", "DNI", dni)) {
                                System.out.println("Este pasajero ya ha sido registrado previamente, ingrese otro.");
                            } else break;
                        }
                    }
                    System.out.print("Nom: ");
                    Scanner pregNom = new Scanner(System.in);
                    nom = pregNom.nextLine();

                    System.out.print("Llinatges: ");
                    Scanner pregLlins = new Scanner(System.in);
                    llins = pregLlins.nextLine();

                    System.out.print("Telefon: ");
                    Scanner pregTel = new Scanner(System.in);
                    tel = pregTel.nextLine();

                    System.out.print("Email: ");
                    Scanner pregEmail = new Scanner(System.in);
                    email = pregEmail.nextLine();

                    aenaDAO.insertP(dni, nom, llins, tel, email);

                    System.out.print("¿Quieres añadir otro pasajero? (Si | No): ");
                    Scanner pregFinal = new Scanner(System.in);
                    String respFinal = pregFinal.nextLine();
                    respFinal = respFinal.toLowerCase();
                    if (respFinal.equals("no")) {
                        break;
                    }
                }
            } else if (resp.equals("2")) {
                while (true) {
                    while (true) {
                        System.out.print("Dni: ");
                        Scanner pregDni = new Scanner(System.in);
                        dni = pregDni.nextLine();
                        if (!dni.isEmpty()) {
                            if (aenaDAO.readString("passatger", "DNI", dni)) {
                                break;
                            } else {
                                System.out.println("El pasajero con DNI: "+dni+" no existe.");
                            }
                        }
                    }
                    System.out.print("Nom: ");
                    Scanner pregNom = new Scanner(System.in);
                    nom = pregNom.nextLine();

                    System.out.print("Llinatges: ");
                    Scanner pregLlins = new Scanner(System.in);
                    llins = pregLlins.nextLine();

                    System.out.print("Telefon: ");
                    Scanner pregTel = new Scanner(System.in);
                    tel = pregTel.nextLine();

                    System.out.print("Email: ");
                    Scanner pregEmail = new Scanner(System.in);
                    email = pregEmail.nextLine();

                    aenaDAO.updateP(dni, nom, llins, tel, email);

                    System.out.print("¿Quieres modificar otro pasajero? (Si | No): ");
                    Scanner pregFinal = new Scanner(System.in);
                    String respFinal = pregFinal.nextLine();
                    respFinal = respFinal.toLowerCase();
                    if (respFinal.equals("no")) {
                        break;
                    }
                }
            } else if (resp.equals("3")) {
                System.out.println("Regresando a la pantalla principal...");
                break;
            } else {
                System.out.println("Respuesta no valida.");
            }
        }
    }
}
