import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.dao.PersistenciaJPA;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Aluno;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Contrato;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.FolhaPagamento;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.FormaPgto;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Modalidade;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Pacote;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Pagamento;
import br.edu.ifsul.cc.lpoo.studiodanca.lpoo_studiodanca.model.Professor;
import java.util.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistenciaObjetos {
    PersistenciaJPA jpa = new PersistenciaJPA();
    
    public TestePersistenciaObjetos() {
    }
    
    @Before
    public void setUp() {
        jpa.conexaoAberta();
    }
    
    @After
    public void tearDown() {
        jpa.fecharConexao();
    }

    @Test
    public void test() throws Exception {
        // Criação das Entidades
        Modalidade m = new Modalidade();
        m.setDescricao("Dança Livre");

        Pacote pct = new Pacote();
        pct.setDescricao("3x");
        pct.setValor(100);
        pct.setModalidade(m);

        Aluno a = new Aluno();
        a.setNome("Juka");
        a.setFone("+55(54) 99999-1234");
        a.setDataPgto(10);

        Contrato c = new Contrato();
        c.setValor(20.678);
        c.setForma_pgto(FormaPgto.PIX);
        c.setAluno(a);
        c.addPacote(pct); // Sincronizar o pacote com o contrato

        Pagamento p = new Pagamento();
        p.setValor(200.00);
        p.setDataVcto(Calendar.getInstance());
        p.setContrato(c);

        Professor prof1 = new Professor();
        prof1.setNome("Joao da Silva");
        prof1.setFone("+55(54)90123-1233");
        prof1.setDataAdmissao(Calendar.getInstance());

        FolhaPagamento f = new FolhaPagamento();
        f.setDataPgto(Calendar.getInstance());
        f.setValorReceber(5000);

        prof1.addFolhaPagamentoMes(f);
        prof1.addModalidade(m);

        // Persistência das Entidades em Ordem Correta
        jpa.persist(m);     // Modalidade deve ser persistida antes de Pacote
        jpa.persist(a);     // Aluno deve ser persistido antes de Contrato
        jpa.persist(prof1); // Professor deve ser persistido antes de FolhaPagamento
        jpa.persist(pct);   // Pacote deve ser persistido antes de Contrato e após Modalidade
        jpa.persist(c);     // Contrato deve ser persistido antes de Pagamento e Pacote
        jpa.persist(p);     // Pagamento deve ser persistido após Contrato
        jpa.persist(f);     // FolhaPagamento deve ser persistida após Professor
    }
}