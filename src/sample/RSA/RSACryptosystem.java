package sample.RSA;

public class RSACryptosystem {
    int p,q,n,z,d=0,e,i;

    public void rsa(){
        //prepare keys:

    }

    public void rsaRandomKeys(){
        //prepare keys :

    }

    //private key
    private int privateKey(){
        return d;
    }

    //public key
    public int[] publicKey(){
        int[] publicKey = new int[2];
        publicKey[0] = n;
        publicKey[1] = e;
        return publicKey;
    }
}
