package sample.RSA;

import java.util.*;

public class RSACryptosystemMethods {
    //Map<Character, Integer> mapLetters = new Map<Character, Integer>();
    public RSACryptosystemMethods() {
    }

    public boolean isPrime(int number) {

        int nb = number-1;
        int div = 1;
        if (number == 1)
           return false;
        else {
            for (int i = 1; i < number; i++) {
                if (number % nb == 0 ) {
                    div++;
                }
                nb--;
            }
        }

        if(div > 2) {
            return false;
        }
        return true;

    }

    public int encryptModule(int p, int q ){
        System.out.println(p*q);

        int produit = p*q;
        System.out.println(produit);
        return produit;
    }

    public int eulerIndic(int p, int q){
        System.out.println((p-1)*(q-1));
        int eulerIndic =  (p-1)*(q-1);
        return eulerIndic;
    }

    public boolean areCoprime(int a, int b) {
        if(pgcd(a, b) == 1){
            return true;
        }
        return false;
    }

    //private key
    public int inverseOf(int e, int mod){
        //x*y = 1 [mod]
        //<=> x*y = mod*k +1
        //<=> y = (mod*k + 1 ) *1/x
       /* int y = 1;
        return (mod +1) / x;*/
        int d = 0;
        int i = 0;
        while(true){
            int x=1+(i*mod);
            if(x%e==0){
                d=x/e;
                break;
            }
            i++;
        }
        return d;
    }

    public int randomPrimeNbr(){
        Random r = new Random();
        int num = r.nextInt();
        if(num < 0 ){
           num = -num;
        }
        if(isPrime(num)){
            return num;
        }
        return randomPrimeNbr();
    }

    public int randomPrimeNbrInf(){
       /* Random r = new Random();
        int num = r.nextInt(max) + max;

        if(isPrime(num)){
            return num;
        }
        return randomPrimeNbrInf(max);*/
        //boolean found = false;
        int i = 0 ;
        while(true){
           // System.out.println(i);
            Random r = new Random();
            int num = r.nextInt(1000) ;

            if(isPrime(num)){
                return num;
            }
           // System.out.println(num);
            i++;
        }
    }


    public int pgcd(int number1, int number2){
        int pgcd = 0;
        for(int i=1; i <= number1 && i <= number2; i++)
        {
            if(number1% i==0 && number2%i==0)
                pgcd = i;
        }
        return pgcd;
    }
    //Encryption :
    public ArrayList<String> transformToStringsChaine(String chaine){

        return null;
    }

    public int[] transformToNbrs(String[] letters){
        return null;
    }

    public int[] encryptNumbers( int[] numbers) {
        return null;
    }

    //Decryption :
    public String[] transformToLetters(int[] numbers){
        return null;
    }

    public int[] decryptNumbers( int[] numbers) {
        return null;
    }





}
