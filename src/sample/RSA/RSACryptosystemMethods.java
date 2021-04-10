package sample.RSA;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RSACryptosystemMethods {
    //les clefs du chiffrement
    BigInteger p,q,e,n,d;

    public RSACryptosystemMethods() {
        this.p = BigInteger.ZERO;
        this.q = BigInteger.ZERO;
        this.e = BigInteger.ZERO;
        this.n = BigInteger.ZERO;
        this.d = BigInteger.ZERO;

    }

//ARITHMETIQUE

    //METHODE QUI VERIFIE SI NUMBER EST PREMIER
    //si le nombre est grand la methode prends + de temps
    public boolean isPrime(BigInteger number) {
        BigInteger nb = number.add(new BigInteger("-1"));
        BigInteger div = new BigInteger("1");
        BigInteger zero = BigInteger.ZERO;
        BigInteger deux = new BigInteger("2");

        //1 n est pas premier
        if (number.intValue() == 1) {
            return false;
        }
        //2 est le seul pair premier
        else if(number.intValue() == 2) {
            return true;

        }else if(number.mod(deux).equals(zero)) { //si c est pair alors not prime
                return false;

        } else{ //sinon

            while( nb.compareTo(zero) > 0 && div.intValue() <= 2){
                //si nb divise number ou l inverse
                if (number.mod(nb).intValue() == 0) {
                    div =  div.add(new BigInteger("1"));
                }
                //soustraction de 1
                nb = nb.add(new BigInteger("-1"));
            }
          
        }
        //nbr de diviseur
        return div.intValue() <= 2;

    }

    //methode estPrime() fonctionne sur les petits nombre
    //pas utilisee dans notre programme
    public boolean isPrime1(int number) {
        int nb = number -1;
        int div = 1;
        if (number== 1)
            return false;
        else {
            for (int i = 1; i < number; i++) {
                if (number % nb== 0) {
                    div++;
                }
                nb --;
            }
        }
        return div<= 2;
    }

    //2 methodes qui verifie si deux nbrs sont premiers entre eux

    //avec le pgcd = 1
    public boolean areCoprime1(BigInteger a, BigInteger b) {
        return pgcd(a, b).intValue() == 1;
    }

    //sans pgcd = 1
    public boolean areCoprime(BigInteger a, BigInteger b) {
        if(this.isPrime(a)  && !a.mod(b).equals(BigInteger.ZERO)){
            return true;
        }else if(this.isPrime(b)  && !b.mod(a).equals(BigInteger.ZERO)){
            return true;
        }
        return false;
    }




    //calcul de pgcd : plus grand diviseur commun à l aide des restes (euclide)
    public BigInteger pgcd(BigInteger a, BigInteger b) {
        //d'apres l algo d euclide le pgcd est le dernier reste non nul
            BigInteger r;
            r = a.divide(b);
            if (r.equals(BigInteger.ZERO))
                return b;
            else
                return pgcd (b, r);

    }

//CALCUL DES CLEFS

    //private key d : calcul d inverse de e modulo mod avec l algo d euclide/bezout
    public BigInteger inverseOf(BigInteger e, BigInteger mod) {
        int i = 2;
        BigInteger indice;
        BigInteger mult;
        //si le produit de e par indice est equiv a 1 modulo mod donc indice est l inverse
        while(true){
            indice = BigInteger.valueOf(i);
            mult = e.multiply(indice);
            if(mult.mod(mod).intValue() == 1){
                return indice;
            }
            i++;
        }
    }


    //publique
    public BigInteger encryptModule(BigInteger p, BigInteger q) {
        return p.multiply(q);
    }
    public BigInteger eulerIndic(BigInteger p, BigInteger q) {
        BigInteger pPrime = p.add(new BigInteger("-1"));
        BigInteger qPrime =  q.add(new BigInteger("-1"));
        return pPrime.multiply(qPrime);
    }

//GENERATION DES NOMBRES PREMIERS POUR LE USER
    public BigInteger randomPrimeNbrBig(int l) {
        return BigInteger.probablePrime(l, new Random());

    }

    public BigInteger randomPrimewith(BigInteger a) {
        while (true) {
            BigInteger x = BigInteger.probablePrime(5, new Random());
            System.out.println(x);
            if( areCoprime(x,a)) {
                System.out.println("ok");
                return x;
            }
        }
    }



//Chiffrement / Encryption :

    //etape 1 :
    //methode qui prend le texte saisie et le tansforme en liste de caracteres
    public ArrayList<Character> transformToListString(String chaine) {
        System.out.println(chaine);
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < chaine.length(); i++) {
            list.add(chaine.charAt(i));
        }
        return list;
    }

    //etape 2 :
    //methode qui transforme cette liste de char en liste des entiers (ASCII)
    public ArrayList<Integer> transformToNbrs(ArrayList<Character> carlist) {
        ArrayList<Integer> integerlist = new ArrayList<Integer>();
        for (Character c : carlist) {
            int ascii = (int) c;
            integerlist.add(ascii);
            System.out.println(ascii);
        }
        return integerlist;
    }

    //etape 3 :
    //methode qui chiffre en utilisant les clés : nmbr puissance e mod n
    public ArrayList<BigInteger> encryptNumbers(ArrayList<Integer> numbers) {
        ArrayList<BigInteger> cryptedrlist = new ArrayList<BigInteger>();
        BigInteger num = BigInteger.valueOf(0);
        System.out.println("e" +e);
        System.out.println("n= " +n);
        for (Integer a : numbers) {
            BigInteger aPrime = BigInteger.valueOf(a);
           num = aPrime.modPow(this.getE(), this.getN());
            System.out.println("a " +a);
            System.out.println("num = " +num);
            cryptedrlist.add(num);
        }
        return cryptedrlist;
    }

    //methode qui affiche le code chiffré dans la zone dans texte
    public String showEncryptedMsg(ArrayList<BigInteger> list){
       String listS = "";
        for(BigInteger num : list) {
            listS += num.toString() +" ";
        }
        return listS;
    }


//Dechiffrement / Decryption :

    //methode ui transfore les nombres en chaine de caractères
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

    //déchiffrer le code : number puissance d (clef privee) modulo n
    public ArrayList<BigInteger> decryptNumbers(ArrayList<BigInteger> numbers) {
        ArrayList<BigInteger> decryptedrlist = new ArrayList<BigInteger>();
        BigInteger num = BigInteger.valueOf(0);

        for (BigInteger a : numbers) {
            BigInteger aPrime = BigInteger.valueOf(a.intValue());
            num = (aPrime.pow(d.intValue())).mod(n);
            decryptedrlist.add(num);
        }
        return decryptedrlist;
    }


    //methode qui transforme la liste des nombres en liste des char
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

    //methode qui affiche le message dechiffre
    public String showDecryptedMsg(ArrayList<Character> list){
        StringBuilder listS = new StringBuilder();
        for(Character c : list) {
            listS.append(c.toString());
        }
        return listS.toString();
    }

//Chiffrement des hommes dansants : Cherlock Holmes -Sir Arthur Conan Doyle

    //methode pour recuperer l image
    private static ImageView getImg(String... paths) {
        return new ImageView(Paths.get(System.getProperty("user.dir"), paths).toUri().toString());
    }
    //methode qui renvoit l image voulue
    public ImageView imageDe(Character c){
        return getImg("/src/sample/pics/" +c +c +".png");
    }

    //chiffrer avec les symboles des hommes dansants
    public void encrypteMen(ArrayList<Character> list, TilePane tile) {
        for (Character c:list ) {
            try {
                tile.getChildren().add(this.imageDe(c));
                }
            catch(InvalidPathException e){
                System.out.println("on peut chiffrer que les char avec les symboles");
            }

        }
    }

    //dechiffrer : les hommes dansants
    //en utilisant les regex :  afin de récupere le char qui correspond à l image
    public ArrayList<Character> decrypteMen(TilePane tile) {
        ArrayList<Character> list = new ArrayList<Character>();
        for (Node c : tile.getChildren() ) {

            ImageView img = (ImageView) c;
            System.out.println("style    " +img.getImage().impl_getUrl().charAt(66));
            System.out.println(img.getImage().impl_getUrl());
            //regex
            String url = img.getImage().impl_getUrl();

            //extract retourne "/pics/c.png" on prend donc le 5e char
            list.add(this.extract(url).charAt(5));
        }
        return list;
    }

    //regex
    public  String extract(String myStr) {
        Pattern p = Pattern.compile("pics/.*$");
        Matcher m = p.matcher(myStr);

        while (m.find()) {
            String theGroup = m.group(0);
            System.out.format("'%s'\n", theGroup);
            return m.group(0);
        }
        return null;
    }



    //read a buffer
    public String readBuffer(String fileName) {
        BufferedReader in = null;
        String line;
        int i = 0;
        StringBuilder msg = new StringBuilder();

        try {
            in = new BufferedReader(new FileReader(fileName));

            //le buffer lit ligne par ligne
            while ((line = in.readLine()) != null) {
                line.replaceAll(" ", "");
                msg.append(line);
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
        File file1 = new File("src\\sample\\buffers\\EncryptedBuffers\\ENCRYPTED" +file.getName());
        PrintWriter writer = new PrintWriter(file1.getName());

        String listS = "";
        for(BigInteger num : list) {
            listS += num.toString() +" ";

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
        File file1 = new File("src\\sample\\buffers\\DecryptedBuffers\\DCRYPTED" +file.getName());
        PrintWriter writer = new PrintWriter(file1.getName());
        if(writer.checkError()){
            throw new FileNotFoundException(file1.getName());
        }
        else{
            writer.println(decryptedMsg);
        }
        writer.close();

    }



    public void addMenTile(TilePane tile, Character c){
        tile.getChildren().add(this.imageDe(c));
    }

//setters ang getters
    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

}




