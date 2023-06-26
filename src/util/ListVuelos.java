package util;

import dao.AenaDAO;
import dao.AenaDaoImpl;

import java.util.List;
import java.util.Scanner;

public class ListVuelos {
    public static void listVuelosProcess() {
        AenaDAO aenaDAO = new AenaDaoImpl();
        String fecha;
        String origen;

        System.out.println("***   VUELOS   ***");

        while (true) {
            System.out.print("Fecha: (Y-M-D) ");
            Scanner pregFecha = new Scanner(System.in);
            fecha = pregFecha.nextLine().toUpperCase();
            if (fecha.isEmpty()) {
                System.out.println("La fecha no puede estar vacia.");
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
                } else {
                    System.out.println("Las siglas de origen no existen.");
                }
            }
        }

        imprimirVuelos(aenaDAO.listaVuelos(fecha, origen));
    }

    public static void imprimirVuelos(List<String> listaVuelos) {
        System.out.println("***   LISTA VUELOS   ***");
        System.out.println("--------------------");
        if (listaVuelos.isEmpty()) {
            System.out.println("No se han encontrado vuelos.");
        } else {
            for (String vuelo : listaVuelos) {
                System.out.println(vuelo);
            }
        }
        System.out.println("--------------------");
    }
}
