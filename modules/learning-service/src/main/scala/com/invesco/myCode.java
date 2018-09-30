package com.invesco;

import java.io.*;

class myCode
{
    public static void main (String[] args) throws java.lang.Exception
    {

        for(int i = 1; i <= 100; i++) {
            if((i % 3) == 0 & (i % 7) == 0) {
                System.out.println("HappyDays");
            } else if((i % 3) == 0) {
                System.out.println("Happy");
            } else if((i % 7) == 0) {
                System.out.println("Days");
            } else {
                System.out.println(i);
            }
        }
    }
}
