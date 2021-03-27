package sample.tests;

import javafx.scene.image.ImageView;
import sample.RSA.RSACryptosystemMethods;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class test {
    public static void main(String[] args) throws IOException {
       /*Random r = new Random();
       int num = r.nextInt();
       if(num<0 ){
           System.out.println(-num);
       }
        else{
             System.out.println(num);
       }*/
       // System.out.println((int)Math.random());

        RSACryptosystemMethods rsa = new RSACryptosystemMethods();

      /*  System.out.println(rsa.pgcd(146666,77));
        System.out.println(rsa.inverseOf(7, 4992));*/

        /*System.out.println(rsa.isPrime(7));
        System.out.println(rsa.inverseOf(1698332565,1368060 ));
        System.out.println(rsa.areCoprime(109, 1368060));
   */
        //System.out.println(rsa.transformToListString("ebda"));
        //System.out.println(rsa.transformToNbrs(rsa.transformToListString("ebda")));
/*
        String s = "si mane";
        System.out.println(s.trim());
        System.out.println(s.replaceAll(" ", ""));*/
/*
        BigInteger b = new BigInteger(100, new Random());
        System.out.println(b);*/

        //System.out.println(rsa.pgcd(BigInteger.valueOf(3), BigInteger.valueOf(7)));
       /* System.out.println(b.modInverse(BigInteger.valueOf(13)));
        File file = new File("src\\sample\\buffers\\buffer.txt");

        System.out.println(file.getName());*/
       /* System.out.println("21 is prime: " +rsa.isPrime2(21));
        System.out.println("21 is prime : " +rsa.isPrime1(21));

        System.out.println("216546352 is prime : " +rsa.isPrime2(216546352));
        System.out.println("216546352 is prime : " +rsa.isPrime1(216546352));
*/
        //BigInteger a= new BigInteger(1000, new Random());
       /* BigInteger x = new BigInteger(50, new Random());
        System.out.println("le x " +x);
        System.out.println("val de x : " +x.intValue());
        if(x.intValue()<0){
            x = BigInteger.valueOf(-x.intValue());
        }*/

        //BigInteger a = BigInteger.probablePrime(80, new Random());
       //System.out.println("a : " +a);
      // System.out.println(a.intValue());
        //System.out.println("le a est prime  is prime : " +rsa.isPrime(a));

        //BigInteger a = rsa.randomPrimeNbrInf(30);
        //System.out.println("x value 2 : " +x.intValue());
        //System.out.println("le x est prime ? " +x.intValue() +" is prime : " +rsa.isPrime(x));
/*
        BigInteger n = new BigInteger("2");
       System.out.println(rsa.isPrime(n));
        n = new BigInteger("7");
        System.out.println(rsa.isPrime(n));
        n = new BigInteger("21");
       System.out.println(rsa.isPrime(n));
        n = new BigInteger("100");
        System.out.println(rsa.isPrime(n));
        n = new BigInteger("21");
        System.out.println("21: " +rsa.isPrime(n));
        n = new BigInteger("950366028030047");
        System.out.println(rsa.isPrime(n));
        n = new BigInteger("54678456785457848756427845543452453644");
        System.out.println(n.intValue());
        System.out.println(n);
        int nom = -n.intValue();
        System.out.println(Math.pow(nom, 10));
        System.out.println(rsa.isPrime(n));
        n = BigInteger.probablePrime(1000, new Random());
        System.out.println(rsa.isPrime(n));



        String number = "98745983758375038735444346";
         n = new BigInteger("-54678456785457848756427845543452453642");
        BigInteger decimal = new BigInteger(number);

        System.out.println(decimal.add(n));
        *//*String a = decimal.toString();
        System.out.println(a.charAt(a.length()-1));


        System.out.println(a);*//*
        //int result = a.charAt(a.length()-1);
       // int result= Integer.parseInt(String.valueOf(a.charAt(a.length()-1)));
        //System.out.println(result);
        //result--;
        //System.out.println("2 " +result);
       // a = a.substring(0, a.length()-1);
        //a+=result;
        //System.out.println("result : " +a);
        //32 par 32 bits pour les BigInteger et gérer le débordement
        //gérer les données à travers les fichiers*/



        ImageView img = new ImageView();
        img = rsa.imageDe('b');

        System.out.println(img.getStyle());

    }
}
