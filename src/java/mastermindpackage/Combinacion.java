/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mastermindpackage;

import java.util.Random;

/**
 *
 * @author dawmi
 */
public class Combinacion {

    final String[] COLORES = {"rojo", "verde", "azul", "amarillo", "naranja", "rosa"};
    public String combinacion[] = new String[4];

    public Combinacion(String color1, String color2, String color3, String color4) {
        this.combinacion[0] = color1;
        this.combinacion[1] = color2;
        this.combinacion[2] = color3;
        this.combinacion[3] = color4;
    }

    public Combinacion() {
        Random rd = new Random();
        for (int i = 0; i < 4; i++) {
            this.combinacion[i] = COLORES[rd.nextInt(COLORES.length)];
        }
    }

    public String arrToString(String[] arr) {
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            res += arr[i] + ", ";
        }
        return res;
    }

    @Override
    public String toString() {
        return "Combinacion{" + "COLORES=" + arrToString(COLORES) + ", combinacion=" + arrToString(combinacion) + '}';
    }

}
