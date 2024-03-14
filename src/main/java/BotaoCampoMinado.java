import javax.swing.*;
import java.awt.*;

public class BotaoCampoMinado extends JButton{

    private int estado;

    private int linha;
    private int coluna;


    public BotaoCampoMinado(int linha, int coluna) {
        this.estado = CampoMinado.TAPADO;
        this.linha = linha;
        this.coluna = coluna;
    }

    public void setEstado(int estado) {//Meter gatos
        this.estado = estado;
        switch (estado) {
            case CampoMinado.VAZIO:
                setText("");
                setBackground(Color.DARK_GRAY);
                break;
            case CampoMinado.TAPADO:
                setText("");
                setBackground(Color.LIGHT_GRAY);
                break;
            case CampoMinado.DUVIDA:
                setText("");
                setBackground(Color.YELLOW);
                break;
            case CampoMinado.MARCADO:
                setText("");
                setBackground(Color.RED);
                break;
            case CampoMinado.REBENTADO:
                setText("");
                setBackground(Color.ORANGE);
                break;
            default:
                setText(String.valueOf(estado));
                setBackground(Color.LIGHT_GRAY);
        }


    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }



}
