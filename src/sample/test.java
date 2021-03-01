package sample;

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

        BigInteger b = new BigInteger(100, new Random());
        System.out.println(b);

        //System.out.println(rsa.pgcd(BigInteger.valueOf(3), BigInteger.valueOf(7)));
        System.out.println(b.modInverse(BigInteger.valueOf(13)));
        File file = new File("src\\sample\\buffers\\buffer.txt");

        System.out.println(file.getName());
    }
}
