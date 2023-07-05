package tareaDoce;

public class calculoCirculo {

    public static void main(String[] args) {
        Circulo circulo = new Circulo(5.0);

        System.out.println("Radio del círculo: " + circulo.getRadio());
        System.out.println("Área del círculo: " + circulo.calcularArea());
        System.out.println("Circunferencia del círculo: " + circulo.calcularCircunferencia());
    }
}