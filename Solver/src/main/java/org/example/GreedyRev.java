package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GreedyRev {
    public static HashMap<Integer, List<Integer>> G;
    public static int[] intArr;

    public static int ChooseVertex(List<Integer> D, boolean[] uniquely, int start){
        List<Integer> s = new ArrayList<>();

        while((start < D.size()) && uniquely[D.get(start)]){
            start++;
        }

        if(start >= D.size()){
            return -1;
        }else{
            int min = G.get(D.get(start)).size();
            for(int a : D){
                if(G.get(a).size() == min && !uniquely[a]){
                    s.add(a);
                    break;
                }
            }
        }
        return s.get((int)(Math.random() * s.size()));
    }

    public static void Adjust(List<Integer> D, int vi, int[] coveredby, boolean[] uniquely){
        coveredby[vi]--;
        for(int vj : G.get(vi)) {
            if(coveredby[vi] == 1 && D.contains(vj)){
                uniquely[vj] = true;
            }
            coveredby[vj]--;
            if(coveredby[vj] == 1){
                if(D.contains(vj)){
                    uniquely[vj] = true;
                }else{
                    for(int vk : G.get(vj)){
                        if(D.contains(vk)){
                            uniquely[vk] = true;
                        }
                    }
                }
            }
        }
    }

    public static List<Integer> Greedy_Rev(HashMap<Integer, List<Integer>> g){
        double t1 = System.currentTimeMillis();

        G = g;

        intArr = new int[G.size()];
        int tmp = G.size() -1;
        for(int i = 0; i < G.size() ; i++){
            intArr[i] = tmp - i;
        }
        System.out.println("hier");

        List<Integer> D = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        List<Integer> three = new ArrayList<>();
        List<Integer> four = new ArrayList<>();
        List<Integer> other = new ArrayList<>();

        for(int i = 0; i < intArr.length; i++){
            int temp = G.get(i).size();
            if(temp == 1){
                one.add(i);
            }else if(temp == 2){
                two.add(i);
            }else if(temp == 3){
                three.add(i);
            }else if(temp == 4){
                four.add(i);
            }else if(temp > 4){
                other.add(i);
            }
        }

        D.addAll(one);
        D.addAll(two);
        D.addAll(three);
        D.addAll(four);
        D.addAll(other);

        int[] coveredby = new int[G.size()];
        boolean[] uniquely = new boolean[G.size()];

        for(int i = 0; i < G.size(); i++){
            coveredby[i] = G.get(i).size()+1;
            if(coveredby[i] == 1){
                uniquely[i] = true;
            }else{
                uniquely[i] = false;
            }
        }

        int v;
        int start = 0;
        int index = -1;

        do{
            v = ChooseVertex(D, uniquely, start);
            for (int j = 0; j < D.size(); j++) {
                if (D.get(j) == v) {
                    index = j;
                    break;
                }
            }
            if(v != -1){
                D.remove(index);
                Adjust(D, v, coveredby, uniquely);
            }
        }while(v != -1 && ((System.currentTimeMillis() - t1) <= 290000));
        return D;
    }
}
