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
@NamedQuery(name="Funcionario.findAll", query="SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte ativo;

	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;

	private String email;

	@Lob
	private byte[] foto;

	private String nome;

	private String senha;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="funcionarioRealizacao")
	private List<Pedido> pedidosRealizados;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="funcionarioPagamento")
	private List<Pedido> pedidosPagamentos;

	public Funcionario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
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

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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

}