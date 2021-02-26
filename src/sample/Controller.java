package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.RSA.RSACryptosystemMethods;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private RSACryptosystemMethods rsa;

    @FXML
    private Label randomP;

    @FXML
    private Label randomQ;


    @FXML
    private TextField p;

    @FXML
    private TextField q;


    @FXML
    private Button decrypte;

    @FXML
    private Button encrypte;

    @FXML
    private Label d;

    @FXML
    private Label e;

    @FXML
    private Label f;

    @FXML
    private TextArea encryptedmessage;

    @FXML
    private TextArea decryptedmessage;

    @FXML
    private Label n;

    public Controller() {
    }


    @FXML
    void decrypte(ActionEvent event) {
        String msg = encryptedmessage.getText();
        ArrayList<BigInteger> encryptedMsg = rsa.transformToListNumbers(msg);
        ArrayList<BigInteger> list = rsa.decryptNumbers(encryptedMsg,new  BigInteger(this.d.getText()), new  BigInteger(this.n.getText()));
        ArrayList<Character> decryptedMsg = rsa.transformToLetters(list);
        System.out.println(decryptedMsg);
        this.decryptedmessage.setText(rsa.showDecryptedMsg(decryptedMsg));
    }

    @FXML
    void encrypte(ActionEvent event) {
        String msg = decryptedmessage.getText();
        ArrayList<Character> listCr =  this.rsa.transformToListString(msg);
        ArrayList<Integer> listEntiers = this.rsa.transformToNbrs(listCr);
        ArrayList<BigInteger> listEncryptedInte = this.rsa.encryptNumbers(listEntiers,new  BigInteger(this.e.getText()), new BigInteger(this.n.getText()));

        this.encryptedmessage.setText(rsa.showEncryptedMsg(listEncryptedInte));
    }
    @FXML
    void encrypteBuffer(ActionEvent event) throws FileNotFoundException {
        String msg = rsa.readBuffer("src\\sample\\buffers\\buffer.txt");
        ArrayList<Character> listCr =  this.rsa.transformToListString(msg);
        ArrayList<Integer> listEntiers = this.rsa.transformToNbrs(listCr);
        ArrayList<BigInteger> listEncryptedInte = this.rsa.encryptNumbers(listEntiers,new  BigInteger(this.e.getText()), new BigInteger(this.n.getText()));

        this.encryptedmessage.setText(rsa.showEncryptedMsg(listEncryptedInte));
        rsa.encrypteBuffer("src\\sample\\buffers\\buffer.txt", listEncryptedInte);


    }
    @FXML
    void decrypteBuffer(ActionEvent event) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("LEGOOOOO");
        this.rsa = new RSACryptosystemMethods();

    }


    @FXML
    void validate(ActionEvent event) {
        //user s choice
        BigInteger p = new BigInteger(this.p.getText());
        BigInteger q = new BigInteger(this.q.getText());
        this.randomP.setText((String.valueOf(0)));
        this.randomQ.setText((String.valueOf(0)));

        BigInteger n = this.rsa.encryptModule(p, q);
        BigInteger f = this.rsa.eulerIndic(p,q);

        this.n.setText((String.valueOf(n)));
        this.f.setText((String.valueOf(f)));

        BigInteger e = this.rsa.randomPrimewith(f);
        BigInteger d = this.rsa.inverseOf(e, f);
        System.out.println("d = " +d);
        this.e.setText((String.valueOf(e)));
        this.d.setText((String.valueOf(d)));
        

    }


    @FXML
    void validateRandom(ActionEvent event) {
        //user s choice
        //BigInteger p = new BigInteger(this.p.getText());
        //BigInteger q = new BigInteger(this.q.getText());

        //random p and q
        BigInteger randomP = this.rsa.randomPrimeNbrInf(10);
        BigInteger randomQ = this.rsa.randomPrimeNbrInf(10);
        this.randomP.setText((String.valueOf(randomP)));
        this.randomQ.setText((String.valueOf(randomQ)));
        this.p.setText("0");
        this.q.setText("0");
        BigInteger n = this.rsa.encryptModule(randomP, randomQ);
        BigInteger f = this.rsa.eulerIndic(randomP,randomQ);
        this.n.setText((String.valueOf(n)));
        this.f.setText((String.valueOf(f)));

        BigInteger e = this.rsa.randomPrimewith(f);
        BigInteger d = this.rsa.inverseOf(e, f);
        System.out.println("d = " +d);
        this.e.setText((String.valueOf(e)));
        this.d.setText((String.valueOf(d)));


    }



}
