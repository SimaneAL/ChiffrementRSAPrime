package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.RSA.RSACryptosystemMethods;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private RSACryptosystemMethods rsa;
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

    }

    @FXML
    void encrypte(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("LEGOOOOO");
        this.rsa = new RSACryptosystemMethods();

    }


    @FXML
    void validate(ActionEvent event) {
        int p = Integer.parseInt(this.p.getText());
        int q = Integer.parseInt(this.q.getText());
        int n = this.rsa.encryptModule(p, q);
        int f = this.rsa.eulerIndic(p,q);
        this.n.setText((String.valueOf(n)));
        this.f.setText((String.valueOf(f)));

       int e = this.rsa.randomPrimeNbrInf();
        int d = this.rsa.inverseOf(e, f);
        System.out.println("d = " +d);
        this.e.setText((String.valueOf(e)));
        this.d.setText((String.valueOf(d)));
        

    }



}
