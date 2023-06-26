package util;

import java.util.List;
import java.util.Scanner;

public class Aena {
    public static void main(String[] args) {
        while (true) {
        System.out.println("***   AENA   ***");
        System.out.println("1) Gestionar pasajeros");
        System.out.println("2) Gestionar vuelos");
        System.out.println("3) Gestionar reservas");
        System.out.println("4) Listar vuelos");
        System.out.println("5) Listar pasajeros");
        System.out.println("6) Listar reservas");
        System.out.println("7) Salir");

            System.out.print("Seleciona una opción: ");
            Scanner preg = new Scanner(System.in);
            String resp = preg.nextLine();

            if (resp.equals("1")) {
                GestPasajero.gestPasajeroProcess();
            } else if (resp.equals("2")) {
                GestVuelo.gestVueloProcess();
            } else if (resp.equals("3")) {
                GestReserva.gestReservaProcess();
            } else if (resp.equals("4")) {
                ListVuelos.listVuelosProcess();
            } else if (resp.equals("5")) {
                ListPasajeros.listPasajerosProcess();
            } else if (resp.equals("6")) {
                ListReservas.listReservasProcess();
            } else if (resp.equals("7")) {
                System.out.println("Saliendo del sistema...");
                break;
            } else {
                System.out.println("Respuesta no valida.");
            }
        }
    }
}
