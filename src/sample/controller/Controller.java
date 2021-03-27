package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import sample.RSA.RSACryptosystemMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private RSACryptosystemMethods rsa;
    private File file;
   // private Server server;
   // private ClientHttp client;

    @FXML
    private Label randomP;
    @FXML
    private TilePane tilesMen;
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

    @FXML
    private Label notifs;

    public Controller() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("LEGOOOOO");
        this.rsa = new RSACryptosystemMethods();
        this.file = null;
        this.notifs.setText("Hello ! You can start by choosing two numbers p and q ! Or just press the button calculate random ones to get random ones ");
        this.encryptedmessage.setWrapText(true);
        this.decryptedmessage.setWrapText(true);
        this.notifs.setWrapText(true);
    }

    @FXML
    void decrypte(ActionEvent event) {
        this.notifs.setText("Well wait... The machine will decrypte your message !");
        String msg = encryptedmessage.getText();
        ArrayList<BigInteger> encryptedMsg = rsa.transformToListNumbers(msg);
        ArrayList<BigInteger> list = rsa.decryptNumbers(encryptedMsg,new  BigInteger(this.d.getText()), new  BigInteger(this.n.getText()));
        ArrayList<Character> decryptedMsg = rsa.transformToLetters(list);
        System.out.println(decryptedMsg);
        this.decryptedmessage.setText(rsa.showDecryptedMsg(decryptedMsg));
        this.notifs.setText("Your encrypted message has been decrypted ! You can encrypte it to verify ");

    }
    @FXML
    void decrypteMen(ActionEvent event) {
        this.notifs.setText("Well wait... The machine will decrypte your message !");
        ArrayList<Character> decryptedMsg = rsa.decrypteMen(this.tilesMen);
        System.out.println(decryptedMsg);
        this.decryptedmessage.setText(rsa.showDecryptedMsg(decryptedMsg));
        this.notifs.setText("Your encrypted message has been decrypted ! You can encrypte it to verify ");

    }

    @FXML
    void encrypteMen(ActionEvent event) {
        if(!this.tilesMen.getChildren().isEmpty()){
                this.tilesMen.getChildren().remove(0,this.tilesMen.getChildren().size() );

        }
        this.notifs.setText("Well wait... The machine will encrypte your message !");
        String msg = decryptedmessage.getText();
        ArrayList<Character> listCr =  this.rsa.transformToListString(msg);

        this.rsa.encrypteMen(listCr, this.tilesMen);


        this.notifs.setText("Your message has been encrypted ;) Try to decrypte it ! ");

    }

    @FXML
    void delete(ActionEvent event) {
        if(!this.tilesMen.getChildren().isEmpty()){
            this.tilesMen.getChildren().remove(0,this.tilesMen.getChildren().size() );

        }
    }
    @FXML
    void encrypte(ActionEvent event) {
        this.notifs.setText("Well wait... The machine will encrypte your message !");
        String msg = decryptedmessage.getText();
        ArrayList<Character> listCr =  this.rsa.transformToListString(msg);
        ArrayList<Integer> listEntiers = this.rsa.transformToNbrs(listCr);
        ArrayList<BigInteger> listEncryptedInte = this.rsa.encryptNumbers(listEntiers,new  BigInteger(this.e.getText()), new BigInteger(this.n.getText()));

        this.encryptedmessage.setText(rsa.showEncryptedMsg(listEncryptedInte));
        this.notifs.setText("Your message has been encrypted ;) Try to decrypte it ! ");

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

        this.notifs.setText("Gr8 ! You choose p and q for you, now you just have to enter a message to encrypte it !  ");

    }


    @FXML
    void validateRandom(ActionEvent event) {
        //random p and q
        this.notifs.setText("Well wait... We are looking for random p and q !");
        BigInteger randomP = this.rsa.randomPrimeNbrBig(10);
        BigInteger randomQ = this.rsa.randomPrimeNbrBig(10);

        this.randomP.setText((String.valueOf(randomP)));
        this.randomQ.setText((String.valueOf(randomQ)));
        this.notifs.setText("Cool ! p = " +randomP.toString() +"\n" + "and q = " +randomQ.toString());
        this.p.setText("0");
        this.q.setText("0");
        BigInteger n = this.rsa.encryptModule(randomP, randomQ);
        BigInteger f = this.rsa.eulerIndic(randomP,randomQ);
        this.n.setText((String.valueOf(n)));
        this.f.setText((String.valueOf(f)));

        BigInteger e = this.rsa.randomPrimewith(f);
        BigInteger d = this.rsa.inverseOf(e, f);
        this.e.setText((String.valueOf(e)));
        this.d.setText((String.valueOf(d)));


        this.notifs.setText("Cool !\n p = " +randomP.toString() +"\n" + "and q = " +randomQ.toString() +"\n The machine choose random p and q for you,  note your public and private keys ! " +
                "Now you just have to enter a message to encrypte it ! Or just chose a file  ");

    }
    @FXML
    void choseFile(ActionEvent event){
        FileChooser file = new FileChooser();
        file.setTitle("Open file");
        this.file = file.showOpenDialog(null);
        this.notifs.setText("Great ! You choose " +this.file.getName());
        this.notifs.setText("It's time to encrypte or decrypte the file you choose ! Press the button encrypte/derypte buffer");

    }


    @FXML
    void encrypteBuffer(ActionEvent event) throws FileNotFoundException {
        this.notifs.setText("Well wait...");
        String msg = rsa.readBuffer(this.file.getPath());
        ArrayList<Character> listCr =  this.rsa.transformToListString(msg);
        ArrayList<Integer> listEntiers = this.rsa.transformToNbrs(listCr);
        ArrayList<BigInteger> listEncryptedInte = this.rsa.encryptNumbers(listEntiers,new  BigInteger(this.e.getText()), new BigInteger(this.n.getText()));


        //this.encryptedmessage.setText(rsa.showEncryptedMsg(listEncryptedInte));
        rsa.encrypteBuffer(this.file.getPath(), listEncryptedInte);
        this.notifs.setText("You just encrypte your file, go check " +"\\encryptedBuffer.txt here : " +file.getParent());


    }
    @FXML
    void decrypteBuffer(ActionEvent event) throws FileNotFoundException {
        this.notifs.setText("Well wait...");
        String msg = rsa.readBuffer(this.file.getPath());
        ArrayList<BigInteger> encryptedMsg = rsa.transformToListNumbers(msg);
        ArrayList<BigInteger> list = rsa.decryptNumbers(encryptedMsg,new  BigInteger(this.d.getText()), new  BigInteger(this.n.getText()));
        ArrayList<Character> decryptedMsg = rsa.transformToLetters(list);
        System.out.println(decryptedMsg);
        //this.decryptedmessage.setText(rsa.showDecryptedMsg(decryptedMsg));
        this.rsa.decrypteBuffer(this.file.getPath(), this.rsa.showDecryptedMsg(decryptedMsg));
        this.notifs.setText("Cool ! You just decrypte your file, go check   " +file.getName() +"\\decryptedBuffertxt");


    }
    @FXML
        public void handleDragOver(DragEvent event) {
            if(event.getDragboard().hasFiles()){
                event.acceptTransferModes(TransferMode.ANY);
            }
        }



    @FXML
    void addManW(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'w');

    }
    @FXML
    void addManV(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'v');
    }
    @FXML
    void addManU(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'u');
    }
    @FXML
    void addManT(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 't');
    }
    @FXML
    void addManR(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'r');
    }
    @FXML
    void addManS(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 's');
    }
    @FXML
    void addManQ(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'q');
    }
    @FXML
    void addManP(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'p');
    }
    @FXML
    void addManO(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'o');
    }
    @FXML
    void addManN(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'n');
    }
    @FXML
    void addManM(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'm');
    }
    @FXML
    void addManL(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'l');
    }
    @FXML
    void addManK(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'k');
    }
    @FXML
    void addManJ(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'j');
    }
    @FXML
    void addManI(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'i');
    }
    @FXML
    void addManH(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'h');
    }
    @FXML
    void addManG(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'g');
    }
    @FXML
    void addManF(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'f');
    }
    @FXML
    void addManE(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'e');
    }
    @FXML
    void addManD(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'd');
    }
    @FXML
    void addManC(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'c');
    }
    @FXML
    void addManB(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'b');
    }
    @FXML
    void addManA(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'a');
    }
    @FXML
    void addManX(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'x');
    }
    @FXML
    void addManY(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'y');
    }
    @FXML
    void addManZ(ActionEvent event) throws FileNotFoundException {
        this.rsa.addMenTile(this.tilesMen, 'z');
    }

}
