package com.interpolation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewtonInterpolationForm extends JFrame {
    private JTextField txtNumPoints;
    private JTextArea txtXValues;
    private JTextArea txtYValues;
    private JTextArea txtOutput;
    
    public NewtonInterpolationForm() {
        setTitle("Interpolación de Newton");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        JLabel lblNumPoints = new JLabel("Número de puntos:");
        txtNumPoints = new JTextField(5);
        JLabel lblXValues = new JLabel("Valores de x (separados por comas):");
        txtXValues = new JTextArea(2, 20);
        JLabel lblYValues = new JLabel("Valores de y (separados por comas):");
        txtYValues = new JTextArea(2, 20);
        JButton btnCalculate = new JButton("Calcular");
        txtOutput = new JTextArea(5, 30);
        txtOutput.setEditable(false);
        
        add(lblNumPoints);
        add(txtNumPoints);
        add(lblXValues);
        add(new JScrollPane(txtXValues));
        add(lblYValues);
        add(new JScrollPane(txtYValues));
        add(btnCalculate);
        add(new JScrollPane(txtOutput));
        
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateInterpolation();
            }
        });
    }
    
    private void calculateInterpolation() {
        try {
            int n = Integer.parseInt(txtNumPoints.getText());
            String[] xValues = txtXValues.getText().split(",");
            String[] yValues = txtYValues.getText().split(",");
            
            if (xValues.length != n || yValues.length != n) {
                txtOutput.setText("El número de valores de x y y debe ser igual al número de puntos.");
                return;
            }
            
            double[] x = new double[n];
            double[] y = new double[n];
            
            for (int i = 0; i < n; i++) {
                x[i] = Double.parseDouble(xValues[i].trim());
                y[i] = Double.parseDouble(yValues[i].trim());
            }
            
            double[] coefficients = NewtonInterpolation.newtonInterpolation(x, y, n);
            
            StringBuilder output = new StringBuilder();
            output.append("Coeficientes de la interpolación de Newton:\n");
            for (int i = 0; i < coefficients.length; i++) {
                output.append("a[").append(i).append("] = ").append(coefficients[i]).append("\n");
            }
            
            txtOutput.setText(output.toString());
        } catch (Exception e) {
            txtOutput.setText("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewtonInterpolationForm().setVisible(true);
            }
 });;
}
}