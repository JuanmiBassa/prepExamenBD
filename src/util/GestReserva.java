package util;

import dao.AenaDAO;
import dao.AenaDaoImpl;

import java.util.Scanner;

public class GestReserva {
    public static void gestReservaProcess() {
        AenaDAO aenaDAO = new AenaDaoImpl();
        int idVol = 0;
        String dniPassatger;
        int numPassatgers = 0;

        while (true) {
            System.out.println("***   GEST RESERVA   ***");
            System.out.println("1) Añadir reserva");
            System.out.println("2) Salir");

            System.out.print("Seleciona una opción: ");
            Scanner preg = new Scanner(System.in);
            String resp = preg.nextLine();

            if (resp.equals("1")) {
                while (true) {
                    while (true) {
                        System.out.print("ID vuelo: ");
                        Scanner pregIdV = new Scanner(System.in);
                        idVol = pregIdV.nextInt();
                        if (idVol < 1) {
                            System.out.println("ID de vuelo no valido.");
                        } else {
                            if (aenaDAO.readInt("vol", "id", idVol)) {
                                break;
                            } else System.out.println("Id de vuelo incorrecto, ingrese otro.");
                        }
                    }

                    while (true) {
                        System.out.print("DNI pasajero: ");
                        Scanner pregDniP = new Scanner(System.in);
                        dniPassatger = pregDniP.nextLine().toUpperCase();
                        if (dniPassatger.isEmpty()) {
                            System.out.println("El DNI del pasajero no puede estar vacio.");
                        } else {
                            if (aenaDAO.readString("passatger", "DNI", dniPassatger)) {
                                break;
                            } else System.out.println("DNI de pasajero no encontrado en nuestra base de datos, ingrese otro.");
                        }
                    }

                    while (true) {
                        System.out.print("Numero de pasajeros: ");
                        Scanner pregNumP = new Scanner(System.in);
                        numPassatgers = pregNumP.nextInt();
                        if (numPassatgers < 1) {
                            System.out.println("Numero de pasajeros no valido.");
                        } else break;
                    }

                    aenaDAO.insertR(idVol, dniPassatger, numPassatgers);

                    System.out.print("¿Quieres añadir otra reserva? (Si | No): ");
                    Scanner pregFinal = new Scanner(System.in);
                    String respFinal = pregFinal.nextLine();
                    respFinal = respFinal.toLowerCase();
                    if (respFinal.equals("no")) {
                        break;
                    }
                }
            } else if (resp.equals("2")) {
                System.out.println("Regresando a la pantalla principal...");
                break;
            } else {
                System.out.println("Respuesta no valida.");
            }
        }
    }
}
