import javax.swing.*;
import java.awt.event.ActionEvent;

public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JButton dificil;
    private JButton facil;
    private JButton medio;
    private JButton sair;
    private JLabel recordes;
    private JLabel scoreFacil;



    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
        sair.addActionListener(this::sairActionPerformed);

        facil.addActionListener(this::btnJogoFacilActionPerformed);
        medio.addActionListener(this::btnJogoMedioActionPerformed);
        dificil.addActionListener(this::btnJogoDificilActionPerformed);



    }

    private void btnJogoFacilActionPerformed(ActionEvent e) {
        var janela = new janelaJogo(new CampoMinado(9,9, 10));
        janela.setVisible(true); // se não foi executado no construtor…
    }
    private void btnJogoMedioActionPerformed(ActionEvent e) {
        var janela = new janelaJogo(new CampoMinado(21,21, 50));
        janela.setVisible(true);
    }
    private void btnJogoDificilActionPerformed(ActionEvent e) {
        // TODO
    }

    private void sairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }}
