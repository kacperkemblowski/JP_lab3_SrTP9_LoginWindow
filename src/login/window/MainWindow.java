package login.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;

public class MainWindow extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MainWindow frame = new MainWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        });
    }

    public MainWindow() throws HeadlessException {
        this("Login to account");
    }

    public MainWindow(String title) throws HeadlessException {
        super(title);

        //Predefiniowane dane do logowania
        HashMap<String, String> data = new HashMap<>();
        data.put("Student" , "Student");
        data.put("Admin" , "Admin");
        data.put("User" , "User");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,400,400);

        //Labele dla pól do wpisania nazwy użytkownika i hasła
        JLabel loginLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        //Labele określające wynik logowania
        JLabel userNotFoundLabel = new JLabel("User not found in database");
        JLabel signedInLabel = new JLabel("You have successfully signed in. Welcome!");
        JLabel wrongPasswordLabel = new JLabel("Wrong password to the account!");

        //Pole tekstowe do wpisania loginu i pole hasła do wpisania hasła
        JTextField login = new JTextField(12);
        JPasswordField password = new JPasswordField(12);

        //Usytuowanie w panelu labeli i pól tekstowych, labele wyników logowania są początkowo niewidoczne
        loginLabel.setBounds(85,100,100,25);
        login.setBounds(165,100,100,25);

        passwordLabel.setBounds(85,145,100,25);
        password.setBounds(165,145,100,25);

        userNotFoundLabel.setBounds(105,230,300,25);
        userNotFoundLabel.setVisible(false);

        signedInLabel.setBounds(70,230,300,25);
        signedInLabel.setVisible(false);

        wrongPasswordLabel.setBounds(85,230,300,25);
        wrongPasswordLabel.setVisible(false);

        //Przycisk do logowania z mnemonikiem alt+s
        JButton signIn = new JButton("Sign in");
        signIn.setBounds(132,190,100,25);
        signIn.setMnemonic(KeyEvent.VK_S);

        //Nasłuchiwanie kliknięcia przycisku
        signIn.addActionListener(e ->{
            signedInLabel.setVisible(false);
            userNotFoundLabel.setVisible(false);
            wrongPasswordLabel.setVisible(false);

            if (data.containsKey(login.getText())) { //Sprawdzenie czy istnieje podana nazwa użytkownika
                if (Arrays.equals( data.get(login.getText()).toCharArray(), password.getPassword() ) ) { //Konwersja hasła dla danego loginu z bazy danych na tablice znaków i porównanie go z wprowadzonym hasłem przez użytkownika metodą equals
                    panel.setBackground(Color.green); //Zalogowano pomyślnie
                    signedInLabel.setVisible(true);
                }
                else {
                    panel.setBackground(Color.red); //Niepoprawne hasło
                    wrongPasswordLabel.setVisible(true);
                }
            }
            else{
                panel.setBackground(Color.orange); //Użytkownik nie istnieje
                userNotFoundLabel.setVisible(true);
            }
        });

        //Dodanie wszystkich komponentów do paneli
        add(panel);
        panel.add(loginLabel);
        panel.add(passwordLabel);
        panel.add(login);
        panel.add(password);
        panel.add(signIn);
        panel.add(signedInLabel);
        panel.add(userNotFoundLabel);
        panel.add(wrongPasswordLabel);
    }

}
