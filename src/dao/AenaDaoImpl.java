package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AenaDaoImpl implements AenaDAO{
    @Override
    public boolean readString(String tabla, String columna, String datoBD) {
        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        boolean result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String select = "SELECT `"+columna+"` FROM "+tabla+" WHERE `"+columna+"` = ?";
            PreparedStatement statement = connection.prepareStatement(select);

            statement.setString(1, datoBD);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = true;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public boolean readInt(String tabla, String columna, int datoBD) {
        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        boolean result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String select = "SELECT `"+columna+"` FROM "+tabla+" WHERE `"+columna+"` = ?";
            PreparedStatement statement = connection.prepareStatement(select);

            statement.setInt(1, datoBD);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = true;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public String insertP(String dni, String nom, String llins, String tel, String email) {
        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String select = "INSERT INTO `passatger` (`DNI`, `nom`, `llinatges`, `telefon`, `email`) " +
                    "VALUES ('"+dni+"', '"+nom+"', '"+llins+"', '"+tel+"', '"+email+"');";
            statement.execute(select);
            System.out.println("Pasajero "+nom+"registrado correctamente.");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return url;
    }

    @Override
    public String updateP(String dni, String nom, String llins, String tel, String email) {
        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE `passatger`";
            String select = " SET ";
            boolean columnAdded = false;

            if (!nom.isEmpty()) {
                select += "`nom` = '"+nom+"'";
                columnAdded = true;
            }
            if (!llins.isEmpty()) {
                if (columnAdded) {
                    select += ", ";
                }
                select += "`llinatges` = '"+llins+"'";
                columnAdded = true;
            }
            if (!tel.isEmpty()) {
                if (columnAdded) {
                    select += ", ";
                }
                select += "`telefon` = '"+tel+"'";
                columnAdded = true;
            }
            if (!email.isEmpty()) {
                if (columnAdded) {
                    select += ", ";
                }
                select += "`email` = '"+email+"'";
            }
            if (columnAdded) {
                query += select + " WHERE `passatger`.`DNI` = '"+dni+"';";
            } else {
                query += " WHERE `passatger`.`DNI` = '"+dni+"';";
            }
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            System.out.println("Pasajero "+nom+" actualizado correctamente.");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return url;
    }

    @Override
    public String insertV(String numVuelo, String origen, String desti) {
        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String select = "INSERT INTO `vol` (`numvol`, `data`, `origen`, `desti`) " +
                    "VALUES ('"+numVuelo+"', CURRENT_TIME, '"+origen+"', '"+desti+"');";
            statement.execute(select);
            System.out.println("Vuelo "+numVuelo+" registrado correctamente.");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return url;
    }

    @Override
    public String insertR(int idVol, String dniPassatger, int numPassatgers) {
        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String select = "INSERT INTO `reserva` (`vol`, `passatger`, `numpassatgers`) " +
                    "VALUES ("+idVol+", '"+dniPassatger+"', "+numPassatgers+");";
            statement.execute(select);
            System.out.println("Reserva registrada correctamente.");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return url;
    }

    @Override
    public List<String> listaVuelos(String fecha, String origen) {
        List<String> listaVuelos = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String select = "SELECT * FROM `vol` WHERE data LIKE ? AND origen = ?";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setString(1, "%" + fecha + "%");
            statement.setString(2, origen);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String vuelo = "Numero: " + rs.getString("numvol") + " | " +
                        "Fecha: " + rs.getString("data") + " | " +
                        "Destino: " + rs.getString("desti");
                listaVuelos.add(vuelo);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return listaVuelos;
    }

    @Override
    public List<String> listaPasajeros(String numvol) {
        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        List<String> listaPasajeros = new ArrayList<>();
        String dniPassatger = "";
        int numPassatgers;
        int totalPassatgers = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String select = "SELECT passatger, numpassatgers FROM reserva WHERE vol IN (SELECT id FROM vol WHERE numvol = ?);";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setString(1, numvol);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dniPassatger = rs.getString("passatger");
                numPassatgers = rs.getInt("numpassatgers");

                String selectDataPassatger = "SELECT nom, llinatges, DNI FROM `passatger` WHERE DNI = ?;";
                PreparedStatement statement2 = connection.prepareStatement(selectDataPassatger);
                statement2.setString(1, dniPassatger);
                ResultSet rs2 = statement2.executeQuery();

                if (rs2.next()) {
                    String pasajero = "Nom: " + rs2.getString("nom") + " | " +
                            "Llinatges: " + rs2.getString("llinatges") + " | " +
                            "DNI: " + rs2.getString("DNI") + " | " +
                            "Num passatgers: " + numPassatgers;
                    listaPasajeros.add(pasajero);
                }
            }
            String selectNumPassatger = "SELECT SUM(numpassatgers) FROM reserva WHERE vol IN (SELECT id FROM vol WHERE numvol = ?);";
            PreparedStatement statement3 = connection.prepareStatement(selectNumPassatger);
            statement3.setString(1, numvol);
            ResultSet rs3 = statement3.executeQuery();
            if (rs3.next()) {
                totalPassatgers = rs3.getInt("SUM(numpassatgers)");
            }
            listaPasajeros.add("Total passatgers: " + totalPassatgers);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return listaPasajeros;
    }

    @Override
    public List<String> listaReservas(String dni) {
        String url = "jdbc:mysql://localhost:3306/aena";
        String username = "root";
        String password = "";

        List<String> listaReservas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String select = "SELECT origen, desti, data FROM vol WHERE id IN (SELECT vol FROM `reserva` WHERE passatger = ?);";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setString(1, dni);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                    String reserva = "Origen: " + rs.getString("origen") + " | " +
                            "Desti: " + rs.getString("desti") + " | " +
                            "Data i hora: " + rs.getString("data");
                    listaReservas.add(reserva);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return listaReservas;
    }

}
