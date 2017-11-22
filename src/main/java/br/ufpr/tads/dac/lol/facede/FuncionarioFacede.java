package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.dao.FuncionarioDao;
import br.ufpr.tads.dac.lol.model.Funcionario;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.criterion.Example;

/**
 *
 * @author Lucas
 */
public class FuncionarioFacede extends CrudFacede<Funcionario> {

    private FuncionarioDao dao;

    @Override
    protected void beforeSave(Funcionario model, Dao<Funcionario> dao) throws ValidationException {
        boolean inserting = model.getId() == null;
        Map<String, String> messages = new HashMap<>();

        // Validação do nome
        if (!ValidationUtil.checkText(model.getNome())) {
            messages.put("nome", "Nome inválido");
        }

        if ("00000000000000000000000000000000".length() != model.getSenha().length()) {
            model.setPassword(model.getSenha());
        }

        // Validação do Email
        if (!ValidationUtil.checkEmail(model.getEmail())) {
            messages.put("email", "Email inválido");
        } else {
            // Verifica se já não existe nenhum funcionario com o email cadastrado
            Funcionario funcionario = new Funcionario();
            funcionario.setEmail(model.getEmail());
            
            List<Funcionario> list = super.list(Example.create(funcionario), null, null, null).getList();
            list.stream()
                .filter((found) -> (inserting || !found.getId().equals(model.getId())))
                .forEachOrdered((item) -> {
                    messages.put("email", "Já existe um funcionario cadastrado com esse email");
                });
        }
    }

    @Override
    protected void beforeDelete(Funcionario model, Dao<Funcionario> dao) throws IllegalOperationException {
    }

    @Override
    protected Dao<Funcionario> getDao() {
        if (dao == null) {
            dao = new FuncionarioDao();
        }
        return dao;
    }

}
