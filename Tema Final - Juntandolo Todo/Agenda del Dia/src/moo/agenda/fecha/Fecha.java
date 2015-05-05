package moo.agenda.fecha;

import java.io.Serializable;
import java.util.Formatter;

public class Fecha implements Comparable<Fecha>, Serializable {
    // Representa una fecha como AAAAMMDDHHMM
    private long num;

    public Fecha(int dia, int mes, int anyo) throws FechaException {
        this(dia,mes,anyo,0,0);
    }
    public Fecha(int dia, int mes, int anyo, int hora, int minuto)throws FechaException {
        if(minuto < 0 || minuto > 59) throw new FechaException("Minuto incorrecto: " + minuto);
        if(hora < 0 || hora > 24) throw new FechaException("Hora incorrecta: " + hora);
        if(mes < 0 || mes > 12) throw new FechaException("Mes incorrecto: "+ mes);
        int diaMaximo;
        if(mes == 2) {
            // calculamos si el año es bisiesto
            // Los años divisibles por 4 son bisiestos.
            // Pero no son bisiestos los que se dividen por 100, menos los que se dividen por 400, que sí son bisitestos.
            boolean bisiesto = anyo % 4 == 0
                           && (anyo % 100 !=0 || anyo % 400 == 0);
            if(bisiesto) {
                diaMaximo = 29;
            } else {
                diaMaximo = 28;
            }
        } else if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            diaMaximo = 31;
        } else {
            diaMaximo = 30;
        }
        if(dia < 1 || dia > diaMaximo) throw new FechaException("Dia incorrecto: " + dia);
        num = minuto 
            + hora * 100L 
            + dia  * 10000L
            + mes  * 1000000L
            + anyo * 100000000L;
    }
        
    public int compareTo(Fecha t) {
        // En vez de hacer una resta,
        // lo hacemos así para evitar problemas de pérdida de precisión
        // al operar con longs i retornar un int
        if(num < t.num) {
            return -1;
        } else if(num > t.num) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int getDia() {
        return (int) (num / 10000 % 100);
    }
    public int getMes() {
        return (int) (num / 1000000 % 100);
    }
    public int getAnyo() {
        return (int) (num / 100000000);
    }
    public int getMinuto() {
        return (int) (num % 100);
    }
    public int getHora() {
        return (int) (num / 100 % 100);
    }
    
    public String toString() {        
        // La manera de construir cadenas en esta asignatura es MUY ineficiente
        // en un programa real, deberíamos usar una clase llamada StringBuilder
        // o el método estático String.format(....), parecido al sprintf de C
        String s = getDia() + "/" + getMes() + "/" + getAnyo() + " ";
        if(getHora() < 10) s += "0";
        s += getHora() + ":";
        if(getMinuto() < 10) s += "0";
        s += getMinuto();
        return s;
    }
        
}
