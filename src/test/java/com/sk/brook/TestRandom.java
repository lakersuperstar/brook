package com.sk.brook;

import java.util.Random;

/**
 * Created by songk on 17/11/27.
 */
public class TestRandom {

    public static void main(String[] args){
        Random r = new Random();
        for(int i = 0;i<100;i++){
            System.out.println(r.nextInt(10));
        }

    }
}
