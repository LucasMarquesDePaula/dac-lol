package br.ufpr.tads.dac.lol.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the funcionario database table.
 *
 */
@Entity
@NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
public class Funcionario extends Model<Integer> implements Authenticable, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Byte ativo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    private String email;

    private String nome;

    private String senha;

    //bi-directional many-to-one association to Pedido
    @OneToMany(mappedBy = "funcionarioRealizacao")
    private List<Pedido> pedidosRealizados;

    //bi-directional many-to-one association to Pedido
    @OneToMany(mappedBy = "funcionarioPagamento")
    private List<Pedido> pedidosPagamentos;

    private Funcionario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Funcionario() {
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

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Pedido> getPedidosRealizados() {
        return this.pedidosRealizados;
    }

    public void setPedidosRealizados(List<Pedido> pedidosRealizados) {
        this.pedidosRealizados = pedidosRealizados;
    }

    public Pedido addPedidosRealizado(Pedido pedidosRealizado) {
        getPedidosRealizados().add(pedidosRealizado);
        pedidosRealizado.setFuncionarioRealizacao(this);

        return pedidosRealizado;
    }

    public Pedido removePedidosRealizado(Pedido pedidosRealizado) {
        getPedidosRealizados().remove(pedidosRealizado);
        pedidosRealizado.setFuncionarioRealizacao(null);

        return pedidosRealizado;
    }

    public List<Pedido> getPedidosPagamentos() {
        return this.pedidosPagamentos;
    }

    public void setPedidosPagamentos(List<Pedido> pedidosPagamentos) {
        this.pedidosPagamentos = pedidosPagamentos;
    }

    public Pedido addPedidosPagamento(Pedido pedidosPagamento) {
        getPedidosPagamentos().add(pedidosPagamento);
        pedidosPagamento.setFuncionarioPagamento(this);

        return pedidosPagamento;
    }

    public Pedido removePedidosPagamento(Pedido pedidosPagamento) {
        getPedidosPagamentos().remove(pedidosPagamento);
        pedidosPagamento.setFuncionarioPagamento(null);

        return pedidosPagamento;
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
        this.setSenha(Authenticable.Util.generateHash(String.format("#%d~!~%s#", this.getId(), password)));
    }

    @Override
    public String toString() {
        return getNome() + " (" + getId() + ")";
    }
}
