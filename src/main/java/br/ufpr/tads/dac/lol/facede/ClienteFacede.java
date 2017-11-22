package br.ufpr.tads.dac.lol.facede;

import br.ufpr.tads.dac.lol.dao.ClienteDao;
import br.ufpr.tads.dac.lol.dao.Dao;
import br.ufpr.tads.dac.lol.model.Cliente;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Example;

/**
 *
 * @author Lucas
 */
public class ClienteFacede extends CrudFacede<Cliente> {

    private static Dao<Cliente> dao;

    @Override
    protected void beforeSave(Cliente model, Dao<Cliente> dao) throws ValidationException {
        boolean inserting = model.getId() == null;
        Map<String, String> messages = new HashMap<>();

        // Validação do nome
        if (!ValidationUtil.checkText(model.getNome())) {
            messages.put("nome", "Nome inválido");
        }

        if ("00000000000000000000000000000000".length() != model.getSenha().length()) {
            model.setPassword(model.getSenha());
        }

        // Validação do CPF
        if (!ValidationUtil.checkCpf(model.getCpf())) {
            messages.put("cpf", "CPF inválido");
        } else {
            // Verifica se já não existe nenhum cliente com o cpf cadastrado
            Cliente cliente = new Cliente();
            cliente.setCpf(model.getCpf());
            List<Cliente> list = super.list(Example.create(cliente), null, null, null).getList();

            list.stream()
                    .filter((found) -> (inserting || !found.getId().equals(model.getId())))
                    .forEachOrdered((item) -> {
                        messages.put("cpf", "Já existe um cliente cadastrado com esse cpf");
                    });
        }

        // Validação do email
        if (!ValidationUtil.checkEmail(model.getEmail())) {
            messages.put("email", "Email inválido");
        } else {
            // Verifica se já não existe nenhum cliente com o email cadastrado
            Cliente cliente = new Cliente();
            cliente.setEmail(model.getEmail());
            List<Cliente> list = super.list(Example.create(cliente), null, null, null).getList();

            list.stream()
                    .filter((found) -> (inserting || !found.getId().equals(model.getId())))
                    .forEachOrdered((item) -> {
                        messages.put("email", "Já existe um cliente cadastrado com esse email");
                    });
        }

        // Validação do endereco
        if (!ValidationUtil.checkText(model.getEndereco())) {
            messages.put("endereco", "Endereço inválido");
        }

        if (!messages.isEmpty()) {
            throw new ValidationException(messages);
        }
    }

    @Override
    protected void beforeDelete(Cliente model, Dao<Cliente> dao) throws IllegalOperationException {

    }

    @Override
    protected Dao<Cliente> getDao() {
        if (dao == null) {
            dao = new ClienteDao();
        }
        return dao;
    }
}
