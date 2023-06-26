package dao;

import java.util.List;

public interface AenaDAO {
    public boolean readString(String tabla, String columna, String datoBD);
    public boolean readInt(String tabla, String columna, int datoBD);
    public String insertP(String dni, String nom, String llins, String tel, String email);
    public String updateP(String dni, String nom, String llins, String tel, String email);
    public String insertV(String numVuelo, String origen, String desti);
    public String insertR(int idVol, String dniPassatger, int numPassatgers);
    public List<String> listaVuelos(String fecha, String origen);
    public List<String> listaPasajeros(String numvol);
    public List<String> listaReservas(String dni);
}
