import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class janelaJogo extends JFrame {
    private JPanel janelaJogoFacil; // painel do jogo. O nome é definido no modo Design, em "field name"
    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;


    public janelaJogo(CampoMinado campoMinado) {
        this.campoMinado = campoMinado;
        var nrLinhas = campoMinado.getNrLinhas();
        var nrColunas = campoMinado.getNrColunas();

        this.botoes = new BotaoCampoMinado[nrLinhas][nrColunas];

        janelaJogoFacil.setLayout(new GridLayout(nrLinhas, nrColunas));

        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                botoes[linha][coluna] = new BotaoCampoMinado(linha, coluna);
                botoes[linha][coluna].addActionListener(this::btnCampoMinadoActionPerformed);
                janelaJogoFacil.add(botoes[linha][coluna]);
            }


            setContentPane(janelaJogoFacil);
            // Destrói esta janela, removendo-a completamente da memória.
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
            pack();
            setVisible(true);


        }

        }
        public void btnCampoMinadoActionPerformed (ActionEvent e){
            var botao = (BotaoCampoMinado) e.getSource();
            int x = botao.getLinha();
            int y = botao.getColuna();
            campoMinado.revelarQuadricula(x, y);
            botoes[x][y].setEstado(campoMinado.getEstadoQuadricula(x,y));
            actualizarEstadoBotoes();
            if (campoMinado.isJogoTerminado()) {
                if (campoMinado.isJogadorDerrotado())
                    JOptionPane.showMessageDialog(null, "Oh, rebentou uma mina",
                            "Perdeu!", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Parabéns. Conseguiu descobrir todas as minas em "+ (campoMinado.getDuracaoJogo()/1000)+" segundos","Vitória", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }

        }

    private void actualizarEstadoBotoes() {
        for (int x = 0; x < campoMinado.getNrLinhas(); x++) {
            for (int y = 0; y < campoMinado.getNrColunas(); y++) {
                botoes[x][y].setEstado(campoMinado.getEstadoQuadricula(x, y));
            }
        }
    }
}
