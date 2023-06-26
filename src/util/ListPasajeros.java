package util;

import dao.AenaDAO;
import dao.AenaDaoImpl;

import java.util.List;
import java.util.Scanner;

public class ListPasajeros {
    public static void listPasajerosProcess() {
        AenaDAO aenaDAO = new AenaDaoImpl();
        String numvol;

        System.out.println("***   PASAJEROS   ***");

        while (true) {
            System.out.print("Numero de vuelo: ");
            Scanner pregVuelo = new Scanner(System.in);
            numvol = pregVuelo.nextLine().toUpperCase();
            if (numvol.isEmpty()) {
                System.out.println("El numero de vuelo no puede estar vacio.");
            } else {
                if (aenaDAO.readString("vol", "numvol", numvol)) {
                    break;
                } else {
                    System.out.println("El numero de vuelo no existe.");
                }
            }
        }

        imprimirPasajeros(aenaDAO.listaPasajeros(numvol));
    }

    public static void imprimirPasajeros(List<String> listaPasajeros) {
        System.out.println("***   LISTA PASAJEROS   ***");
        System.out.println("--------------------");
        if (listaPasajeros.isEmpty()) {
            System.out.println("No se han encontrado pasajeros.");
        } else {
            for (String pasajero : listaPasajeros) {
                System.out.println(pasajero);
            }
        }
        System.out.println("--------------------");
    }
}
