import javax.swing.*;
import java.awt.*;

public class FormPessoaV1 extends JFrame {
    private JTextField txtNumero, txtNome, txtSexo, txtIdade;
    private JButton btnOk, btnLimpar, btnMostrar, btnSair;
    private Pessoa umaPessoa;

    public FormPessoaV1() {
        setTitle("Exercicio 01 - Versão 1");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Numero:"));
        txtNumero = new JTextField();
        txtNumero.setEditable(false);
        add(txtNumero);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Sexo (M/F):"));
        txtSexo = new JTextField();
        add(txtSexo);

        add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        add(txtIdade);

        btnOk = new JButton("OK");
        btnLimpar = new JButton("Limpar");
        btnMostrar = new JButton("Mostrar");
        btnSair = new JButton("Sair");

        add(btnOk);
        add(btnLimpar);
        add(btnMostrar);
        add(btnSair);

        btnOk.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                String sexoStr = txtSexo.getText().trim().toUpperCase();
                String idadeStr = txtIdade.getText().trim();

                if (nome.isEmpty() || sexoStr.isEmpty() || idadeStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
                    return;
                }

                char sexo = sexoStr.charAt(0);
                if (sexo != 'M' && sexo != 'F') {
                    JOptionPane.showMessageDialog(this, "Sexo deve ser M ou F.");
                    return;
                }

                int idade = Integer.parseInt(idadeStr);

                umaPessoa = new Pessoa(nome, sexo, idade);
                txtNumero.setText(String.valueOf(umaPessoa.getKp()));
                JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Idade deve ser um número inteiro.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        btnLimpar.addActionListener(e -> {
            txtNumero.setText("");
            txtNome.setText("");
            txtSexo.setText("");
            txtIdade.setText("");
        });

        btnMostrar.addActionListener(e -> {
            if (umaPessoa != null) {
                String info = "KP (Total Pessoas): " + umaPessoa.getKp() + "\n" +
                              "Nome: " + umaPessoa.getNome() + "\n" +
                              "Sexo: " + umaPessoa.getSexo() + "\n" +
                              "Idade: " + umaPessoa.getIdade();
                JOptionPane.showMessageDialog(this, info);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma pessoa foi cadastrada ainda.");
            }
        });

        btnSair.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPessoaV1().setVisible(true));
    }
}