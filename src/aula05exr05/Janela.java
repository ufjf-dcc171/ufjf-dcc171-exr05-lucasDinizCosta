package aula05exr05;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class Janela extends JFrame {

    /**
     * EXR5) Construa uma aplicação gráfica que calcule o preço final de uma
     * mercadoria após a incidência de impostos. Solicite:
     *  O valor do produto em USD;
     *  A cotação BRL/USD;
     *  Se foi tributado acrescente 60% sobre o valor;
     *  Se há ICMS no estado, acrescente 18% sobre o valor tributado;
     *  Acrescente um botão para realizar o cálculo.
     */
    
    private final JTextField valor = new JTextField(10);
    private final JLabel escritoValor = new JLabel("Valor do produto(USD):");
    private final JTextField cotacao = new JTextField(10);
    private final JLabel escritoCotacao = new JLabel("Cotação USD em R$:");
    private final JCheckBox tributado = new JCheckBox("Tributado");
    private final JCheckBox icms = new JCheckBox("ICMS");
    private final JButton calcular = new JButton("Calcular");
    private double valorTotal = 0.0;
    private final JLabel resultado = new JLabel("Valor Total: ");
    
    public Janela() throws HeadlessException {
        super("Exercicio 5");
        setLayout(new FlowLayout());
        add(escritoValor);
        add(valor);
        add(escritoCotacao);
        add(cotacao);
        add(calcular);
        add(tributado);
        add(icms);
        add(resultado);
        TratarFuncoes tratador = new TratarFuncoes();
        calcular.addActionListener(tratador);
        tributado.addItemListener(tratador);
        icms.addItemListener(tratador);
    }

    private class TratarFuncoes implements ActionListener, ItemListener{

        public TratarFuncoes() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calcular();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            calcular();
        }

        private void calcular() throws NumberFormatException {
            valorTotal = 0.0;
            Double valorIncial = Double.parseDouble(valor.getText());
            Double cotacaoValor = Double.parseDouble(cotacao.getText());
            if(tributado.isSelected()&&icms.isSelected()){
                valorTotal = valorTotal + (valorIncial * cotacaoValor) + valorIncial * 0.60 + valorIncial * 0.18;
            }
            else if(tributado.isSelected()){
                valorTotal = valorTotal + (valorIncial * cotacaoValor) + valorIncial * 0.60;
            }
            else if(icms.isSelected()){
                valorTotal = valorTotal + (valorIncial * cotacaoValor) + valorIncial * 0.18;
            }
            else{
                valorTotal = valorTotal +(valorIncial * cotacaoValor);
            }
            resultado.setText("Valor Total(R$): " + valorTotal);
        }
        
    }

}
