package util;

import dao.AenaDAO;
import dao.AenaDaoImpl;

import java.util.Scanner;

public class GestVuelo {
    public static void gestVueloProcess() {
        AenaDAO aenaDAO = new AenaDaoImpl();
        String numVol;
        String origen;
        String desti;

        while (true) {
            System.out.println("***   GEST VUELO   ***");
            System.out.println("1) Añadir vuelo");
            System.out.println("2) Salir");

            System.out.print("Seleciona una opción: ");
            Scanner preg = new Scanner(System.in);
            String resp = preg.nextLine();

            if (resp.equals("1")) {
                while (true) {
                    while (true) {
                        System.out.print("Numero de Vuelo: ");
                        Scanner pregNumVuelo = new Scanner(System.in);
                        numVol = pregNumVuelo.nextLine();
                        if (numVol.isEmpty()) {
                            System.out.println("El numero de vuelo no puede estar vacio.");
                        } else break;
                    }

                    while (true) {
                        System.out.print("Origen: (Siglas) ");
                        Scanner pregOrigen = new Scanner(System.in);
                        origen = pregOrigen.nextLine().toUpperCase();
                        if (origen.isEmpty()) {
                            System.out.println("El origen no puede estar vacio.");
                        } else {
                            if (aenaDAO.readString("aeroport", "codi", origen)) {
                                break;
                            } else System.out.println("Codigo de origen incorrecto, ingrese otro.");
                        }
                    }

                    while (true) {
                        System.out.print("Siglas destino: ");
                        Scanner pregDestino = new Scanner(System.in);
                        desti = pregDestino.nextLine().toUpperCase();
                        if (desti.isEmpty()) {
                            System.out.println("El destino no puede estar vacio.");
                        } else {
                            if (aenaDAO.readString("aeroport", "codi", desti)) {
                                break;
                            } else System.out.println("Codigo de destino incorrecto, ingrese otro.");
                        }
                    }

                    aenaDAO.insertV(numVol, origen, desti);

                    System.out.print("¿Quieres añadir otro vuelo? (Si | No): ");
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
