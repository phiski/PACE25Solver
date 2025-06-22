package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GreedyRev {
    public static HashMap<Integer, List<Integer>> G;
    public static int[] intArr;
    public static int[] coveredby;
    public static boolean[] uniquely;
    public static List<Integer> D;

    public static int ChooseVertex(int start){
        while((start < D.size()) && uniquely[D.get(start)]){
            start++;
        }
        if(start >= D.size()){
            return -1;
        }else{
            int min = G.get(D.get(start)).size();
            for(int a : D){
                if(G.get(a).size() == min && !uniquely[a]){
                    return a;
                }
            }
        }
        return -1;
    }

    public static void Adjust(int vi){
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
        D = new ArrayList<>();
        List<Integer> zero = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        List<Integer> three = new ArrayList<>();
        List<Integer> four = new ArrayList<>();
        List<Integer> five = new ArrayList<>();
        List<Integer> six = new ArrayList<>();
        List<Integer> seven = new ArrayList<>();
        List<Integer> eight = new ArrayList<>();
        List<Integer> nine = new ArrayList<>();
        List<Integer> other = new ArrayList<>();

        for(int i = 0; i < intArr.length; i++){
            int temp = G.get(i).size();
            if(temp == 0) {
                zero.add(i);
            }else if(temp == 1){
                one.add(i);
            }else if(temp == 2){
                two.add(i);
            }else if(temp == 3){
                three.add(i);
            }else if(temp == 4){
                four.add(i);
            }else if(temp == 5){
                five.add(i);
            }else if(temp == 6){
                six.add(i);
            }else if(temp == 7){
                seven.add(i);
            }else if(temp == 8){
                eight.add(i);
            }else if(temp == 9){
                nine.add(i);
            }else{
                other.add(i);
            }
        }

        D.addAll(one);
        D.addAll(two);
        D.addAll(three);
        D.addAll(four);
        D.addAll(five);
        D.addAll(six);
        D.addAll(seven);
        D.addAll(eight);
        D.addAll(nine);
        D.addAll(other);
        D.addAll(zero);

        coveredby = new int[G.size()];
        uniquely = new boolean[G.size()];

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
            v = ChooseVertex(start);
            for (int j = 0; j < D.size(); j++) {
                if (D.get(j) == v) {
                    index = j;
                    break;
                }
            }
            if(v != -1){
                D.remove(index);
                Adjust(v);
            }
        }while(v != -1 && ((System.currentTimeMillis() - t1) <= 295000));
        return D;
    }
}
