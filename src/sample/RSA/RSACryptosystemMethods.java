package sample.RSA;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class RSACryptosystemMethods {
    Map<Character, Integer> mapLetters = new HashMap<Character, Integer>();

    public RSACryptosystemMethods() {

    }

    public boolean isPrime(BigInteger number) {

        BigInteger nb = BigInteger.valueOf( number.intValue() - 1);
        BigInteger div = BigInteger.valueOf(1);
        if (number.intValue() == 1)
            return false;
        else {
            for (int i = 1; i < number.intValue(); i++) {
                if (number.intValue() % nb.intValue() == 0) {
                    div =  BigInteger.valueOf( div.intValue()+1);
                }
                nb =  BigInteger.valueOf( nb.intValue()-1);
            }
        }

        if (div.intValue() > 2) {
            return false;
        }
        return true;

    }

    public BigInteger encryptModule(BigInteger p, BigInteger q) {

        return p.multiply(q);
    }

    public BigInteger eulerIndic(BigInteger p, BigInteger q) {
        BigInteger pPrime = BigInteger.valueOf(p.intValue()-1);
        BigInteger qPrime = BigInteger.valueOf(q.intValue()-1);
        return pPrime.multiply(qPrime);
    }

    public boolean areCoprime(BigInteger a, BigInteger b) {
        return pgcd(a, b).intValue() == 1;
    }

    //private key
    public BigInteger inverseOf(BigInteger e, BigInteger mod) {
        //x*y = 1 [mod]
        //<=> x*y = mod*k +1
        //<=> y = (mod*k + 1 ) *1/x
       /* int y = 1;
        return (mod +1) / x;*/
       /* BigInteger d = BigInteger.valueOf(0);
        BigInteger i = BigInteger.valueOf(0);
        while (true) {
            BigInteger x = BigInteger.valueOf(1 + (i.multiply(mod).intValue()) );
            if (x.mod(e).intValue()  == 0) {
                d = x.divide(e);
                break;
            }
            i = BigInteger.valueOf(i.intValue()+1);
        }
        return d;*/

        //return e.modInverse(mod);

        int i = 2;
        while(true){
            BigInteger indice = BigInteger.valueOf(i);
            BigInteger mult = e.multiply(indice);
            if(mult.mod(mod).intValue() == 1){
                return indice;
            }
            i++;
        }
    }

    public BigInteger randomPrimeNbr() {
        while (true) {
            BigInteger x = new BigInteger(100, new Random());
            System.out.println(x);
            if (isPrime(x)) {
                return x;
            }
        }
    }

    public BigInteger randomPrimeNbrInf(int bits) {
        while (true) {
            BigInteger x = new BigInteger(bits, new Random());
            System.out.println(x);
            if (isPrime(x)) {
                return x;
            }
        }
    }
    public BigInteger randomPrimewith(BigInteger a) {
        while (true) {

            BigInteger x = new BigInteger(a.bitLength(), new Random());
            System.out.println(x);
            if (isPrime(x) && areCoprime(x,a)) {
                return x;
            }
            // System.out.println(num);
        }
    }


    public BigInteger pgcd(BigInteger number1, BigInteger number2) {
        BigInteger pgcd = BigInteger.valueOf(0);
        for (int i = 1; i <= number1.intValue() && i <= number2.intValue(); i++) {
            if (number1.intValue() % i == 0 && number2.intValue() % i == 0)
                pgcd = BigInteger.valueOf(i);
        }
        return pgcd;
    }

    //Encryption :
    public ArrayList<Character> transformToListString(String chaine) {
       //chaine = chaine.replaceAll(" ", "");
        System.out.println(chaine);
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < chaine.length(); i++) {
            list.add(chaine.charAt(i));
        }
        return list;
    }

    public ArrayList<Integer> transformToNbrs(ArrayList<Character> carlist) {
        ArrayList<Integer> integerlist = new ArrayList<Integer>();
        for (Character c : carlist) {
            int ascii = (int) c;
            integerlist.add(ascii);
            System.out.println(ascii);
        }
        return integerlist;
    }

    public ArrayList<BigInteger> encryptNumbers(ArrayList<Integer> numbers, BigInteger e, BigInteger n) {
        ArrayList<BigInteger> cryptedrlist = new ArrayList<BigInteger>();
        BigInteger num = BigInteger.valueOf(0);
        for (Integer a : numbers) {
            BigInteger aPrime = BigInteger.valueOf(a);
           num = (aPrime.pow(e.intValue())).mod(n);
                 //  aPrime.modPow(e, n);

            System.out.println("e" +e.intValue());
            System.out.println("n= " +n.intValue());
            System.out.println("a " +a);
            System.out.println("num = " +num.intValue());
            cryptedrlist.add(num);
        }
        return cryptedrlist;
    }

    public String showEncryptedMsg(ArrayList<BigInteger> list){
       String listS = "";
        for(BigInteger num : list) {
            listS += num.toString() +" ";
            //listS += num.toString() ;
        }
        return listS;
    }
    
    //Decryption :

    public ArrayList<BigInteger> transformToListNumbers(String chaine) {
        String num = "";
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        int indice =0;
        while( indice < chaine.length() ){
            if(chaine.charAt(indice) == ' ' ) {
                list.add(new BigInteger(num));
                System.out.println("hum" +num);
                num ="";
                indice++;
            }
            else if( indice == chaine.length()-1){
                num += chaine.charAt(indice);
                list.add(new BigInteger(num));
                num ="";
                indice++;
            }

            else {
                num += chaine.charAt(indice);
                indice++;
            }
        }
        return list;
    }

    public ArrayList<BigInteger> decryptNumbers(ArrayList<BigInteger> numbers, BigInteger d, BigInteger n) {
        ArrayList<BigInteger> decryptedrlist = new ArrayList<BigInteger>();
        BigInteger num = BigInteger.valueOf(0);

        for (BigInteger a : numbers) {
            BigInteger aPrime = BigInteger.valueOf(a.intValue());
            num = (aPrime.pow(d.intValue())).mod(n);
                    //aPrime.modPow(d, n);
            decryptedrlist.add(num);
        }
        return decryptedrlist;
    }
    public ArrayList<Character> transformToLetters1(ArrayList<BigInteger> numbers) {
        ArrayList<Character> list = new ArrayList<Character>();
        Object[] set =  mapLetters.keySet().toArray();
        for (BigInteger a : numbers) {
            System.out.println(a);
            Character car = (Character) set[a.intValue()-1];
            list.add(car);
            System.out.println("le car est : " +car);
        }
        return list;
    }

    public ArrayList<Character> transformToLetters(ArrayList<BigInteger> numbers) {
        ArrayList<Character> list = new ArrayList<Character>();
        char car;
        for (BigInteger a : numbers) {
            System.out.println(a);
            car = (char) a.intValue();
            list.add(car);
            System.out.println("le car est : " +car);
        }
        return list;
    }

    public String showDecryptedMsg(ArrayList<Character> list){
        StringBuilder listS = new StringBuilder();
        for(Character c : list) {
            listS.append(c.toString());
        }
        return listS.toString();
    }


    //read a buffer
    public String readBuffer(String fileName) {
        BufferedReader in = null;
        String line;
        int i = 0;
        StringBuilder msg = new StringBuilder();
       // fileName =
        try {
            in = new BufferedReader(new FileReader(fileName));

            //le buffer lit ligne par ligne
            while ((line = in.readLine()) != null) {
                line.replaceAll(" ", "");
                msg.append(line);
                //}
                i++;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg.toString();
    }

    public void encrypteBuffer(String fileName, ArrayList<BigInteger> list) throws FileNotFoundException {

        File file = new File(fileName);
        String name = file.getParent() +"\\EncryptedBuffers\\encrypted" +file.getName();
        System.out.println(name);
        File file1 = new File(name);
        PrintWriter writer = new PrintWriter(file1.getName());

        String listS = "";
        for(BigInteger num : list) {
            listS += num.toString() +" ";
            //listS += num.toString() ;
        }
            if(writer.checkError()){
                throw new FileNotFoundException(file1.getName());
            }
            else{

                    writer.println(listS);

            }


            writer.close();

    }

    public void decrypteBuffer(String fileName, String decryptedMsg) throws FileNotFoundException {

        File file = new File(fileName);
        String name = file.getParent() +"\\DecryptedBuffers\\decrypted" +file.getName();
        System.out.println(name);
        File file1 = new File(name);
        PrintWriter writer = new PrintWriter(file1.getName());


        if(writer.checkError()){
            throw new FileNotFoundException(file1.getName());
        }
        else{
            writer.println(decryptedMsg);
        }


        writer.close();

    }

}




