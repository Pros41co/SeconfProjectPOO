import java.awt.dnd.DragSourceMotionListener;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;


class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected LocalTime horaEntrada;
    protected LocalTime horaSalida;
    protected boolean parqueado;

    public Vehiculo(String placa, String marca, String modelo, LocalTime horaEntrada) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = horaEntrada;
    }



    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setPlaca(String newplaca) {
        this.placa = newplaca;
    }

    public void setMarca(String newmarca) {
        this.marca = newmarca;
    }

    public void setModelo(String newmodelo) {
        this.modelo = newmodelo;
    }

    public void setHoraEntrada(LocalTime newhora) {
        this.horaEntrada = newhora;
    }

    public void setHoraSalida(LocalTime newhora) {
        this.horaSalida = newhora;
    }

    public void setParqueado(boolean estado){
        this.parqueado = estado;
    }
}

class Automovil extends Vehiculo{
    private String tipoCombustible;

    public Automovil(String placa, String marca, String modelo, LocalTime horaEntrada, String tipoCombustible) {
        super(placa, marca, modelo, horaEntrada);
        this.tipoCombustible = tipoCombustible;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }
}

class Motocicleta extends Vehiculo {
    private int cilindraje;

    public Motocicleta(String placa, String marca, String modelo, LocalTime horaEntrada, int cilindraje) {
        super(placa, marca, modelo, horaEntrada);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }
}

class Camion extends Vehiculo {
    double capacidadCarga;

    public Camion(String placa, String marca, String modelo, LocalTime horaEntrada, double capacidadCarga) {
        super(placa, marca, modelo, horaEntrada);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga(){
        return capacidadCarga;
    }

    public void setCapacidadCarga(double newcapacidad){
        this.capacidadCarga = newcapacidad;
    }
}

class Parqueadero {
    private List<Vehiculo> vehiculos = new ArrayList<>();

    public void registrarEntrada(Vehiculo vehiculo) {
        vehiculo.setParqueado(true);
        vehiculos.add(vehiculo);
        System.out.println(ColoresConsola.GREEN + "Se ha ingresado al parqueadero el vehículo con placa [" + vehiculo.getPlaca() + "]"
        + ColoresConsola.RESET);
        System.out.println(ColoresConsola.BLUE + "Hora de registro del vehículo: [" + vehiculo.getHoraEntrada() + "]" +
                ColoresConsola.RESET);
    }

    private calcularPrecio(Motocicleta moto){

    }

    private calcularPrecio(Camion camion){

    }

    private calcularPrecio(Camion Automovil){

    }

    public double registrarSalida(int index) {
        Vehiculo vehiculo = vehiculos.get(index);
        LocalTime entrada = vehiculo.getHoraEntrada();
        LocalTime salida = vehiculo.getHoraSalida();
        if (entrada.isAfter(salida)) {
            vehiculo.setHoraSalida(LocalTime.now());
            System.out.println(ColoresConsola.BLUE + "Hora de salida del vehículo: [" + vehiculo.getHoraSalida() + "]" +
                    ColoresConsola.RESET);
            vehiculo.setParqueado(false);
        }else{
            System.out.println(ColoresConsola.RED + "Vehículo no se encuentra en el parqueadero" + ColoresConsola.RESET);
            return 0;
        }
    }

    private int getVehiculosParqueados(){
        int cantidad = 0;
        for (Vehiculo vehiculo: vehiculos){
            if (vehiculo.parqueado){
                cantidad++;
            }
        }
        return cantidad;
    }

    public void consultarEstado(){
            System.out.println(ColoresConsola.BLUE + "Cantidad de vehículos registrados [" + vehiculos.size() + "]" +
                    ColoresConsola.RESET);
            System.out.println(ColoresConsola.BLUE + "Cantidad de vehículos en el parqueadero [" + getVehiculosParqueados() +
                    "]" + ColoresConsola.RESET);
    }
}



class ColoresConsola {
    public static final String RESET = "\u001B[0m";  // Resetear color
    public static final String RED = "\u001B[31m";   // Rojo
    public static final String GREEN = "\u001B[32m"; // Verde
    public static final String YELLOW = "\u001B[33m";// Amarillo
    public static final String BLUE = "\u001B[34m";  // Azul
    public static final String PURPLE = "\u001B[35m";// Morado
    public static final String CYAN = "\u001B[36m";  // Cian
}


public class Main {
    public static void main(String[] args) {

    }
}