import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JButton dificil;
    private JButton facil;
    private JButton medio;
    private JButton sair;
    private JLabel recordes;
    private JLabel scoreFacil;
    private JLabel ScoreMedio;
    private JLabel ScoreDificil;
    private JLabel NomeDificil;
    private JLabel NomeMedio;
    private JLabel NomeFacil;

    private final TabelaRecordes recordesFacil;
    private final TabelaRecordes recordesMedio;
    private final TabelaRecordes recordesDificil;


    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
        sair.addActionListener(this::sairActionPerformed);

        facil.addActionListener(this::btnJogoFacilActionPerformed);
        medio.addActionListener(this::btnJogoMedioActionPerformed);
        dificil.addActionListener(this::btnJogoDificilActionPerformed);

        recordesFacil = new TabelaRecordes();
        recordesMedio = new TabelaRecordes();
        recordesDificil = new TabelaRecordes();
        lerRecordesDoDisco();
    }

    private void recordesFacilActualizado(TabelaRecordes recordes) {
        NomeFacil.setText(recordes.getNome());
        scoreFacil.setText(Long.toString(recordes.getTempo() / 1000));
    }

    private void recordesMedioActualizado(TabelaRecordes recordes) {
        NomeMedio.setText(recordes.getNome());
        ScoreMedio.setText(Long.toString(recordes.getTempo() / 1000));
    }

    private void recordesDificilActualizado(TabelaRecordes recordes) {
        NomeDificil.setText(recordes.getNome());
        ScoreDificil.setText(Long.toString(recordes.getTempo() / 1000));
    }


    private void btnJogoFacilActionPerformed(ActionEvent e) {
        var janela = new janelaJogo(new CampoMinado(9,9, 10));
        janela.setVisible(true);
    }
    private void btnJogoMedioActionPerformed(ActionEvent e) {
        var janela = new janelaJogo(new CampoMinado(21,21, 50));
        janela.setVisible(true);
    }
    private void btnJogoDificilActionPerformed(ActionEvent e) {
        var janela = new janelaJogo(new CampoMinado(100,100, 1000));
        janela.setVisible(true);
    }

    private void sairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }

    private void guardarRecordesDisco() {
        ObjectOutputStream oos;
        try {
            File f = new File(System.getProperty("user.home") + File.separator + "minesfinder.recordes");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(recordesFacil);
            oos.writeObject(recordesMedio);
            oos.writeObject(recordesDificil);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void lerRecordesDoDisco() {
        ObjectInputStream ois;
        try {
            File f = new File(System.getProperty("user.home") + File.separator + "minesfinder.recordes");
            ois = new ObjectInputStream(new FileInputStream(f));
            TabelaRecordes recordesFacil = (TabelaRecordes) ois.readObject();
            TabelaRecordes recordesMedio = (TabelaRecordes) ois.readObject();
            TabelaRecordes recordesDificil = (TabelaRecordes) ois.readObject();
            this.recordesFacil.setRecorde(recordesFacil.getNome(), recordesFacil.getTempo());
            this.recordesMedio.setRecorde(recordesMedio.getNome(), recordesMedio.getTempo());
            this.recordesDificil.setRecorde(recordesDificil.getNome(), recordesDificil.getTempo());
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
