package sample;

import sample.RSA.RSACryptosystemMethods;

import java.util.Random;

public class test {
    public static void main(String[] args) {
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

        System.out.println(rsa.isPrime(7));
        System.out.println(rsa.inverseOf(1698332565,1368060 ));
        System.out.println(rsa.areCoprime(109, 1368060));
    }
}
