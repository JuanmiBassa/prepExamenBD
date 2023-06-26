package util;

import dao.AenaDAO;
import dao.AenaDaoImpl;

import java.util.List;
import java.util.Scanner;

public class ListReservas {
    public static void listReservasProcess() {
        AenaDAO aenaDAO = new AenaDaoImpl();
        String dni;

        System.out.println("***   RESERVAS   ***");

        while (true) {
            System.out.print("DNI: ");
            Scanner pregDni = new Scanner(System.in);
            dni = pregDni.nextLine().toUpperCase();
            if (dni.isEmpty()) {
                System.out.println("El dni no puede estar vacio.");
            } else {
                if (aenaDAO.readString("passatger", "DNI", dni)) {
                    break;
                } else {
                    System.out.println("El dni no existe.");
                }
            }
        }

        imprimirReservas(aenaDAO.listaReservas(dni));
    }

    public static void imprimirReservas(List<String> listaReservas) {
        System.out.println("***   LISTA RESERVAS   ***");
        System.out.println("--------------------");
        if (listaReservas.isEmpty()) {
            System.out.println("No se han encontrado reservas.");
        } else {
            for (String reserva : listaReservas) {
                System.out.println(reserva);
            }
        }
        System.out.println("--------------------");
    }
}
