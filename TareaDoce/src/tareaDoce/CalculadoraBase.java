package tareaDoce;

import java.awt.*;
import javax.management.loading.PrivateClassLoader;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;



	public class CalculadoraBase extends JFrame implements ActionListener{
		private JTextField textField;
		    private double numero1;
		    private String operacion;
		
		
		public CalculadoraBase() {
			setTitle("calculadora");
			setSize(300,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			getContentPane().setLayout(new BorderLayout());
			
			textField= new JTextField();
			textField.setEditable(false);
			getContentPane().add(textField, BorderLayout.NORTH);
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(4,4));
			
			String [] botones = {
					"7", "8", "9", "/",
		            "4", "5", "6", "*",
		            "1", "2", "3", "-",
		            "0", ".", "=", "+"
		        };
			for(String boton : botones) {
					JButton button = new JButton(boton);
					button.addActionListener(this);
					panel.add(button);
					
			}
			getContentPane().add(panel, BorderLayout.CENTER);
			
			JLabel lblNewLabel = new JLabel("Calculadora");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblNewLabel);
		}
		public void actionPerformed(ActionEvent e) {
			String valorBoton = ((JButton) e.getSource()).getText();

	        if (valorBoton.matches("[0-9.]")) {
	            textField.setText(textField.getText() + valorBoton);
	        } else if (valorBoton.equals("+")) {
	            numero1 = Double.parseDouble(textField.getText());
	            operacion = "+";
	            textField.setText("");
	        } else if (valorBoton.equals("-")) {
	            numero1 = Double.parseDouble(textField.getText());
	            operacion = "-";
	            textField.setText("");
	        } else if (valorBoton.equals("*")) {
	            numero1 = Double.parseDouble(textField.getText());
	            operacion = "*";
	            textField.setText("");
	        } else if (valorBoton.equals("/")) {
	            numero1 = Double.parseDouble(textField.getText());
	            operacion = "/";
	            textField.setText("");
	        } else if (valorBoton.equals("=")) {
	            double numero2 = Double.parseDouble(textField.getText());
	            double resultado = calcular(numero1, numero2, operacion);
	            textField.setText(String.valueOf(resultado));
	        }
	    }

	    private double calcular(double num1, double num2, String oper) {
	        double resultado = 0;

	        switch (oper) {
	            case "+":
	                resultado = num1 + num2;
	                break;
	            case "-":
	                resultado = num1 - num2;
	                break;
	            case "*":
	                resultado = num1 * num2;
	                break;
	            case "/":
	                if (num2 != 0) {
	                    resultado = num1 / num2;
	                } else {
	                    JOptionPane.showMessageDialog(this, "Error: División entre cero", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                break;
	        }

	        return resultado;
	    }

	    public static void main(String[] args) {
	    	SwingUtilities.invokeLater(() -> {
	            CalculadoraBase calculadora = new CalculadoraBase();
	            calculadora.setVisible(true);
	        });
	    }
	}
