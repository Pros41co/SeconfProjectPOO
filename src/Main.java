import java.awt.dnd.DragSourceMotionListener;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.time.Duration;
import java.util.Scanner;

class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected LocalTime horaEntrada;
    protected LocalTime horaSalida;
    protected boolean parqueado;
    protected int precioPorHora;

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

    public int getPrecioPorHora(){
        return precioPorHora;
    }

}

class Automovil extends Vehiculo{
    private String tipoCombustible;

    public Automovil(String placa, String marca, String modelo, LocalTime horaEntrada, String tipoCombustible) {
        super(placa, marca, modelo, horaEntrada);
        this.tipoCombustible = tipoCombustible;
        this.precioPorHora = 10000;
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
        this.precioPorHora = 1000;
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
        this.precioPorHora = 100000;
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

    private double calcularPrecio(Vehiculo vehiculo) {
        int precioHora = vehiculo.getPrecioPorHora();
        Duration duration = Duration.between(vehiculo.getHoraEntrada(), vehiculo.getHoraSalida());
        long minutos = duration.toMinutes();
        int horas = (int) Math.ceil(minutos / 60.0); // Redondeo al alza

        return horas * precioHora;
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
            return calcularPrecio(vehiculo);
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

class Menu{

    Parqueadero parqueadero = new Parqueadero();

    Scanner scanner = new Scanner(System.in);

    int option;

    do{
        System.out.println(ColoresConsola.CYAN + "Bienvenido al sistema del parqueadero" + ColoresConsola.RESET);
        System.out.println("Elige una opción \n");
        System.out.println("[1] Ingresar un vehículo");
        System.out.println("[2] Registrar Salida de Vehículo");
        System.out.println("[3] Consultar estado del parqueadero");
        System.out.println("[4] Salir del programa");

        try{
            option = scanner.nextInt();
        }catch (Exception e){
            System.out.println(ColoresConsola.CYAN + "Elige una opción válida. [Número del 1 al 4]" + ColoresConsola.RESET);
            scanner.nextLine();
        }


        switch (option){
            case 1:
                int option1;
                do{
                    System.out.println("Indica el tipo de vehículo \n");
                    System.out.println("[1] Moto");
                    System.out.println("[2] Automovil");
                    System.out.println("[3] Camión");
                    System.out.println("[4] Salir");

                    while (true){
                        try{
                            option1 = scanner.nextInt();
                            break;
                        }catch (Exception e){
                            System.out.println(ColoresConsola.CYAN + "Elige una opción válida. [Número del 1 al 4]" + ColoresConsola.RESET);
                            scanner.nextLine();
                        }
                    }


                    String placa;
                    String modelo;
                    String marca;

                    switch (option1){
                        case 1:
                            int cilindraje;
                            System.out.println("Ingresa la placa del vehículo");
                            placa = scanner.next();
                            System.out.println("Ingresa el modelo del vehículo");
                            modelo = scanner.next();
                            System.out.println("Ingresa la marca del vehículo");
                            marca = scanner.next();
                            System.out.println("Ingresa el cilindraje");
                            while (true){
                                try{
                                    cilindraje = scanner.nextInt();
                                    break;
                                }catch (Exception e){
                                    System.out.println("Ingresa un valo válido");
                                }
                            }
                            Motocicleta moto = new Motocicleta(placa, marca, modelo, LocalTime.now(), cilindraje);
                            parqueadero.registrarEntrada(moto);
                            break;
                        case 2:
                            String tipoCombustible;
                            System.out.println("Ingresa la placa del vehículo");
                            placa = scanner.next();
                            System.out.println("Ingresa el modelo del vehículo");
                            modelo = scanner.next();
                            System.out.println("Ingresa la marca del vehículo");
                            marca = scanner.next();
                            System.out.println("Ingresa el tipo de combustible");
                            while (true){
                                try{
                                    tipoCombustible = scanner.next();
                                    break;
                                }catch (Exception e){
                                    System.out.println("Ingresa un valo válido");
                                }
                            }
                            Automovil carro = new Automovil(placa, marca, modelo, LocalTime.now(), tipoCombustible);
                            parqueadero.registrarEntrada(carro);
                            break;
                        case 3:
                            double capacidad;
                            System.out.println("Ingresa la placa del vehículo");
                            placa = scanner.next();
                            System.out.println("Ingresa el modelo del vehículo");
                            modelo = scanner.next();
                            System.out.println("Ingresa la marca del vehículo");
                            marca = scanner.next();
                            System.out.println("Ingresa la capacidad del vehículo");
                            while (true){
                                try{
                                    capacidad = scanner.nextInt();
                                    break;
                                }catch (Exception e){
                                    System.out.println("Ingresa un valor válido");
                                }
                            }
                            Camion camion = new Camion(placa, marca, modelo, LocalTime.now(), capacidad);
                            parqueadero.registrarEntrada(camion);
                            break;
                        case 4:
                            break;
                    }

                }while (option1 != 4);
            case 2:
                int posicion;
                System.out.println("Inserta la posición del vehículo a retirar");
                parqueadero.consultarEstado();
                posicion = scanner.nextInt();
                parqueadero.registrarSalida(posicion);
                break;
            case 3:
                parqueadero.consultarEstado();
            case 4:
                break;
        }while (option != 4);


    }
}

public class Main {
    public static void main(String[] args) {

    }
}