package com.interpolation;

import java.util.Scanner;

public class NewtonInterpolation {
    
    // Método para calcular la interpolación de Newton//
    public static double[] newtonInterpolation(double[] x, double[] y, int n) {
        double[][] dividedDifference = new double[n][n];
        
        // Inicializar la primera columna con y[]
        for (int i = 0; i < n; i++) {
            dividedDifference[i][0] = y[i];
        }
        
        // Calcular las diferencias divididas
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dividedDifference[i][j] = (dividedDifference[i + 1][j - 1] - dividedDifference[i][j - 1]) / (x[i + j] - x[i]);
            }
        }
        
        // Extraer los coeficientes de la interpolación
        double[] coefficients = new double[n];
        for (int i = 0; i < n; i++) {
            coefficients[i] = dividedDifference[0][i];
        }
        
        return coefficients;
    }
    
    // Método principal para la ejecución del programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el número de puntos: ");
        int n = scanner.nextInt();
        
        double[] x = new double[n];
        double[] y = new double[n];
        
        System.out.println("Ingrese los valores de x:");
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
        }
        
        System.out.println("Ingrese los valores de y:");
        for (int i = 0; i < n; i++) {
            y[i] = scanner.nextDouble();
        }
        
        double[] coefficients = newtonInterpolation(x, y, n);
        
        System.out.println("Coeficientes de la interpolación de Newton:");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println("a[" + i + "] = " + coefficients[i]);
        }
        
        scanner.close();;
}
}