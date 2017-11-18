package br.ufpr.tads.dac.lol.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the cliente database table.
 *
 */
@Entity
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
public class Cliente extends Model<Integer> implements Authenticable, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Byte ativo;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String endereco;

    private String nome;

    private String senha;

    private String sexo;

    private String telefone;

    //bi-directional many-to-one association to Pedido
    @OneToMany(mappedBy = "cliente", targetEntity = Pedido.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public Cliente() {
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Byte ativo) {
        this.ativo = ativo;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido addPedido(Pedido pedido) {
        getPedidos().add(pedido);
        pedido.setCliente(this);

        return pedido;
    }

    public Pedido removePedido(Pedido pedido) {
        getPedidos().remove(pedido);
        pedido.setCliente(null);

        return pedido;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public void setUsername(String userName) {
        this.setEmail(userName);
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public void setPassword(String password) {
        this.setSenha(Authenticable.Util.generateHash(String.format("#%d~@~%s#", this.getId(), password)));
    }

}
