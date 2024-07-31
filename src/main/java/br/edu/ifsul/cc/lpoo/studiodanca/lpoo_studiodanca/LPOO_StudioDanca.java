/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca;

import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.dao.PersistenciaJPA;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Modalidade;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.view.JpanelTelaModalidades;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 *
 * @author vanessalagomachado
 */
public class LPOO_StudioDanca {


    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        
         SwingUtilities.invokeLater(() -> {
            PersistenciaJPA jpa = new PersistenciaJPA();
            try {
                // Busca todas as modalidades do banco de dados
                List<Modalidade> modalidades = jpa.buscarTodasModalidades();

                // Cria o JFrame principal
                JFrame frame = new JFrame("Modalidades Cadastradas");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);

                // Adiciona o JPanel com a lista de modalidades ao JFrame
                JpanelTelaModalidades painelModalidades = new JpanelTelaModalidades(modalidades);
                frame.add(painelModalidades);

                // Exibe o JFrame
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao buscar modalidades: " + e.getMessage());
            } finally {
                jpa.fecharConexao();
            }
        });
                
    }
}
