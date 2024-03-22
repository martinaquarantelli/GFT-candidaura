/*
DESCRIZIONE

Dato un array di stringhe E[],
Ogni posizione rappresenta un candidato
Per ogni candidato è indicato una stringa che indica in quali giorni è presente per seguire un corso

Quindi ad esempio
E = ["01", "43", "5", ""]
il candidato 1 c'è i giorni 1 e 2
il candidato 2 i giorni 3 e 4
il candidato 3 il giorno 5
il candidato 4 mai

Il programma deve ritornare il NUMERO MASSIMO di candidati che potranno frequentare il corso
Il corso si può tenere in massimo due giorni

 */

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //example array
        String[] E = new String[]{"01", "2", "045", "167", "06","65"};
        //result should be "5" because in days 0 and 6 can attend 5 candidates

        String[] caso_limite = new String[]{"12", "12", "12", "3"};
        //result should be 4 because on day (1 OR 2) AND can attempt all of the candidates

        //try the execution
        System.out.println(Calculator(E));
        System.out.println(Calculator(caso_limite));

    }

    public static int Calculator(String[] E){

        int result;

        //first I create an array like E but based on days with the number of candidates
        //for the examples the array D should be:
        // for E -> ["024","03","1","","2","25","345","3","",""]
        //for caso_limite -> ["","012","012","3","","","","","",""]

        String[] D = new String[]{"","","","","","","","","",""};

        for(int i = 0; i < 10; i++){
            //for every position in the D array (0 to 9) - the days
            for(int k = 0; k < E.length; k++){
                //for every position in the E array - the employees
                for(int j = 0; j < E[k].length(); j++){
                    //for every character in the employee's String - the day the employee is present
                    String c = String.valueOf(E[k].charAt(j));

                    if(Integer.parseInt(c) == i){
                        D[i] = D[i].concat(String.valueOf(k));
                    }
                }
            }
        }

        System.out.println(Arrays.toString(D));
        //FUNZIONA!

        //then I iterate on this D array to select the day with the highest number of employees
        int max = 0;
        int max_day = 0;
        for(int i = 0; i< 10; i++){
            if(max < D[i].length()){
                max = D[i].length();
                max_day = i;
            }
        }
        //save this number in the result
        result = max;
        //after this selection, I delete from the array the candidates present on the first day
        //so for example from this array
        // D(E) -> ["024","03","1","","2","25","345","3","",""]
        // To this array
        // ["","3","1","","","5","35","3","",""]
        //I deleted 0, 2 and 4 (the candidates already present on day one) in every other position

        //I put the D[max_day] combination in a safe space
        String emps_on_max_day = D[max_day];

        String D_build;


        for(int i = 0; i < 10; i++){
            //for every position in the D array (0 to 9) - the days

            //D_build might be useless, it was part of another idea...
            D_build = D[i];

            for(int j = 0; j < D[i].length(); j++){
                //for every candidate in every day
                for(int k = 0; k < emps_on_max_day.length(); k++){
                    //for every candidate present on the max_day
                    if(D[i].charAt(j) == emps_on_max_day.charAt(k)){
                        //System.out.println(D_build);
                        String replace = D_build.replace(String.valueOf(emps_on_max_day.charAt(k)), "");
                        D_build = replace;
                        //System.out.println(D_build);
                        //System.out.println(replace);
                    }
                }
            }
            D[i] = D_build;
        }

        System.out.println(Arrays.toString(D));

        //I select the second day with more candidates that were not on the first day

        //reset and reuse the variables
        max = 0;
        for(int i = 0; i< 10; i++){
            if(max < D[i].length()){
                max = D[i].length();
            }
        }

        //I add this number to the result
        result = result + max;
        return result;

    }
}