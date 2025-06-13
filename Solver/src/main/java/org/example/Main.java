package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);

        HashMap<Integer, List<Integer>> test = new HashMap<>();

        BufferedReader br = new BufferedReader(reader);

        String tmp = "";

        //Initialisieren der Variablen, in welche Stückweise die Datei gespeichert wird
        String zeile;
        List<Integer> nodes = new ArrayList<>();
        //Zeilenweises lesen der Datei
        while ((zeile = br.readLine()) != null) {
            for (int i = 0; i < zeile.length(); i++) {
                if (Character.isDigit(zeile.charAt(i))) {
                    tmp += zeile.charAt(i);
                }
                if ((!Character.isDigit(zeile.charAt(i)) || (i + 1) > zeile.length() - 1) && !tmp.isEmpty()) {
                    nodes.add(Integer.parseInt(tmp) - 1);
                    tmp = "";
                }
            }
        }

        br.close();
        int GraphSize = nodes.getFirst();
        nodes.removeFirst();
        int edgeSize = nodes.getFirst();
        nodes.removeFirst();

        for (int i = 0; i < nodes.size(); i += 2) {
            if (!test.containsKey(nodes.get(i))) {
                test.put(nodes.get(i), new ArrayList<>());
                test.get(nodes.get(i)).add(nodes.get(i + 1));
            } else {
                test.get(nodes.get(i)).add(nodes.get(i + 1));
            }
            if (!test.containsKey(nodes.get(i + 1))) {
                test.put(nodes.get(i + 1), new ArrayList<>());
                test.get(nodes.get(i + 1)).add(nodes.get(i));
            } else {
                test.get(nodes.get(i + 1)).add(nodes.get(i));
            }
        }
        if(GraphSize <= 100000){
            List<Integer> D = GreedyVote.Greedy_Vote(test);
            System.out.println(D.size());
            for(Integer l : D){
                System.out.println(l+1);
            }
        }else if(edgeSize/GraphSize >= 2){
            List<Integer> D = Greedy.Greedy(test);
            System.out.println(D.size());
            for(Integer l : D){
                System.out.println(l+1);
            }
        }else {
            List<Integer> D = GreedyVote.Greedy_Vote(test);
            System.out.println(D.size());
            for(Integer l : D){
                System.out.println(l+1);
            }
        }
    }
}