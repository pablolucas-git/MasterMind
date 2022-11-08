/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mastermindpackage;

/**
 *
 * @author dawmi
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Juego {

    final String[] posiblesResultados = {"negro", "blanco", "nada"};
    public Combinacion solucion;
    public boolean acertado;
    public int numrondas;
    public int ronda;
    public ArrayList<ArrayList<String[]>> rondas;
    public int puntos;
    String jugador;

    public Juego(String jugador) {
        this.solucion = new Combinacion();
        this.acertado = false;
        this.numrondas = 15;
        this.ronda = 0;
        this.jugador = jugador;
        this.puntos = 0;
        this.rondas = new ArrayList<ArrayList<String[]>>();
    }


    private boolean strIsInArray(String str, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    private String[] copyArray(String arr[]) {
        String[] copyArray = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArray[i] = arr[i];
        }
        return copyArray;
    }

    public void checkRespuesta(Combinacion respuesta) {

        ArrayList<String[]> datos_ronda = new ArrayList<String[]>();
        int aciertos = 0;
        int casi = 0;
        String[] resultado = new String[4];
        Arrays.fill(resultado, posiblesResultados[2]);
        String[] solAux = copyArray(this.solucion.combinacion);
        String[] respAux = copyArray(respuesta.combinacion);
        for (int i = 0; i < respAux.length; i++) {
            if (solAux[i].equals(respAux[i])) { //checkAciertos
                aciertos++;
                solAux[i] = ".";
                respAux[i] = ",";
            }
        }
        for (int i = 0; i < respAux.length; i++) {
            for (int j = 0; j < solAux.length; j++) {
                if (solAux[j].equals(respAux[i])) {
                    casi++;
                    solAux[j] = ".";
                    respAux[i] = ",";
                    break;
                }
            }
        }
        int i = 0;
        for (int j = 0; j < aciertos; j++) {
            resultado[i] = posiblesResultados[0];
            i++;
        }
        this.puntos += aciertos * 3;
        System.out.println("Aciertos: " + aciertos);
        System.out.println("Casi: " + casi);
        for (int j = 0; j < casi; j++) {
            resultado[i] = posiblesResultados[1];
            i++;
        }
        this.puntos += casi;

        this.acertado = aciertos == 4;
        datos_ronda.add(0,respuesta.combinacion);
        datos_ronda.add(1, resultado);
        this.rondas.add(ronda, datos_ronda);
        this.ronda++;
    }

}
