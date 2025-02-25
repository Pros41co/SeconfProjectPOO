import java.awt.dnd.DragSourceMotionListener;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected LocalTime horaEntrada;

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

class Parqueadero{
    private  List<Vehiculo> vehiculos;

    public Parqueadero() {
        this.vehiculos = new ArrayList<>();
    }

    public void registrarEntrada(Vehiculo vehiculo){
        vehiculo.setHoraEntrada(LocalTime.now());
        vehiculos.add(vehiculo);
        System.out.println(ColoresConsola.CYAN + "Vehículo con placa [" + vehiculo.getPlaca() + "] registrado.");
        System.out.println(ColoresConsola.YELLOW + "Hora de registro" + vehiculo.getHoraEntrada());
    }

    public Vehiculo getVehiculo(String placaVehiculo){
        for (Vehiculo vehiculo: vehiculos){
            if (placaVehiculo.equals(vehiculo.getPlaca())){
                return vehiculo;
            }
        }
        return null;
    }

    private int calcularHoras(LocalTime horaEntrada){
        LocalTime horaSalida = LocalTime.now();
        Duration duracion = Duration.between(horaEntrada, horaSalida);
        Long minutos = duracion.toMinutes();
        int horasTotales = (int) Math.ceil((minutos / 60.0));

        return horasTotales;
    }

    private int calcularPrecio(Vehiculo vehiculo){
        int horas = calcularHoras(vehiculo.getHoraEntrada());
        if (vehiculo instanceof Motocicleta){
            return horas * 1000;
        } else if (vehiculo instanceof Automovil) {
            return horas * 10000;
        } else {
            return horas * 100000;
        }

    }

    public void registrarSalida(String placaVehiculo){
        Vehiculo vehiculo = getVehiculo(placaVehiculo);
        if (vehiculo != null){
            System.out.println("Registrando hora de salida. El precio a pagar es de [" + ColoresConsola.YELLOW +
                    calcularPrecio(vehiculo) + "]" + ColoresConsola.RESET);
            vehiculo.setHoraEntrada(null);
        } else {
            System.out.println(ColoresConsola.RED + "No se ha encontrado el vehículo con la placa indicada" +
                    ColoresConsola.RESET);
        }
    }

   public void mostrarParqueadero(){
        for (Vehiculo vehiculo: vehiculos){
            if (vehiculo.getHoraEntrada() != null)
            System.out.println("Vehículo [" + vehiculo.getPlaca() + "] ->" +  "En parqueadero");
            else {
                System.out.println("Vehículo [" + vehiculo.getPlaca() + "] ->" +  "Ha salido del parqueadero");
            }
        }
   }
}

class MenuUser {
    private final Scanner input = new Scanner(System.in);
    private Parqueadero parqueadero = new Parqueadero();

    private String menuTipoCombustible() {
        int option1 = -1;

        while (true){
            System.out.println("Selecciona el tipo de combustible");
            System.out.println("Selecciona el tipo de vehículo que deseas registrar \n");
            System.out.println(ColoresConsola.YELLOW + "[1] " + ColoresConsola.RESET + "Gasolina");
            System.out.println(ColoresConsola.YELLOW + "[2] " + ColoresConsola.RESET + "Diesel");
            System.out.println(ColoresConsola.YELLOW + "[3] " + ColoresConsola.RESET + "Eléctrico");

            try {
                option1 = input.nextInt();
                input.nextLine();

                switch (option1) {
                    case 1:
                        return "Gasolina";
                    case 2:
                        return "Diesel";
                    case 3:
                        return "Eléctrico";
                    default:
                        System.out.println("Selecciona una opción válida");
                }
            } catch (Exception e){
                System.out.println("Ingresa un valor válido");
                input.nextLine();
            }
        }
    }


    private Vehiculo desplegarOpcionesVehiculo() {
        String placa;
        String marca;
        String modelo;


        int option = -1;

        while (true) {
            System.out.println("Selecciona el tipo de vehículo que deseas registrar \n");
            System.out.println(ColoresConsola.PURPLE + "[1] " + ColoresConsola.RESET + "Automóvil");
            System.out.println(ColoresConsola.PURPLE + "[2] " + ColoresConsola.RESET + "Motocicleta");
            System.out.println(ColoresConsola.PURPLE + "[3] " + ColoresConsola.RESET + "Camión");

            try {
                option = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Ingresa una opción válida");
            }

            switch (option) {
                case 1:
                    String tipoCombustible;
                    tipoCombustible = menuTipoCombustible();
                    System.out.println("Ingresar placa del vehículo");
                    placa = input.next();
                    System.out.println("Ingresar marca del vehículo");
                    marca = input.next();
                    System.out.println("Ingresar modelo del vehículo");
                    modelo = input.next();
                    return new Automovil(placa, marca, modelo, LocalTime.now(), tipoCombustible);
                case 2:
                    int cilindraje;
                    System.out.println("Ingresa el cilindraje del vehículo");
                    cilindraje = input.nextInt();
                    System.out.println("Ingresar placa del vehículo");
                    placa = input.next();
                    System.out.println("Ingresar marca del vehículo");
                    marca = input.next();
                    System.out.println("Ingresar modelo del vehículo");
                    modelo = input.next();
                    return new Motocicleta(placa, marca, modelo, LocalTime.now(), cilindraje);
                case 3:
                    double capacidad;
                    System.out.println("Ingresa la capacidad del vehículo");
                    capacidad = input.nextInt();
                    System.out.println("Ingresar placa del vehículo");
                    placa = input.next();
                    System.out.println("Ingresar marca del vehículo");
                    marca = input.next();
                    System.out.println("Ingresar modelo del vehículo");
                    modelo = input.next();
                    return new Camion(placa, marca, modelo, LocalTime.now(), capacidad);
            }
        }
    }

    public void desplegarMenu() {
        int option = -1;
        Vehiculo vehiculo;

        do {
            System.out.println(ColoresConsola.BLUE + "Bienvenido al menú de usuario del Parqueadero" +
                    ColoresConsola.RESET);
            System.out.println("Seleccione una opción para empezar: \n");
            System.out.println(ColoresConsola.CYAN + "[1] " + ColoresConsola.RESET + "Ingresar vehículo al parqueadero");
            System.out.println(ColoresConsola.CYAN + "[2] " + ColoresConsola.RESET + "Registrar la salida de un vehículo");
            System.out.println(ColoresConsola.CYAN + "[3] " + ColoresConsola.RESET + "Mostrar el estado del parqueadero");
            System.out.println(ColoresConsola.RED + "[4] " + ColoresConsola.RESET + "Finalizar el programa");

            try {
                option = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Ingrese un número válido.");
            }

            switch (option){
                case 1:
                    vehiculo = desplegarOpcionesVehiculo();
                    parqueadero.registrarEntrada(vehiculo);
                    break;
                case 2:
                    String placa;
                    System.out.println("Ingresa la placa del vehículo");
                    placa = input.next();
                    parqueadero.registrarSalida(placa);
                    break;
                case 3:
                    parqueadero.mostrarParqueadero();
                case 4:
                    break;
                default:
                    System.out.println("Coloca una opción válida");
            }
        }while (option != 4);
    }
}


public class Main {
    public static void main(String[] args) {
        MenuUser menu = new MenuUser();
        menu.desplegarMenu();
        System.out.println("Ha salido del programa.");
    }
}