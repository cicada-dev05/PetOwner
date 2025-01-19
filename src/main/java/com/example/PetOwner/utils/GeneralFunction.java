package com.example.PetOwner.utils;

import java.util.Random;

public class GeneralFunction {

    private static final Random random = new Random();

    public static Long generateId(){

        long timeStamp = System.currentTimeMillis();
        int randomValue = random.nextInt(1000);
        return timeStamp + randomValue;
    }

}
