package br.ufpr.tads.dac.lol.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido extends Model<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private byte cancelado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_cadastro")
	private Date dataHoraCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_cancelamento")
	private Date dataHoraCancelamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_confirmacao_orcamento")
	private Date dataHoraConfirmacaoOrcamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_pagamento")
	private Date dataHoraPagamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_prazo")
	private Date dataHoraPrazo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_realizacao")
	private Date dataHoraRealizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_recebimento")
	private Date dataHoraRecebimento;

	@Column(name="endereco_entrega")
	private String enderecoEntrega;

	@Column(name="entrega_frustrada")
	private byte entregaFrustrada;

	@Column(name="entrega_frustrada_justificativa")
	private String entregaFrustradaJustificativa;

	private byte entregue;

	@Column(name="observacao_cliente")
	private String observacaoCliente;

	@Column(name="observacao_interna")
	private String observacaoInterna;

	@Column(name="orcamento_confirmado")
	private byte orcamentoConfirmado;

	private byte pago;

	private byte realizado;

	private byte recebido;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="funcionario_realizacao_id")
	private Funcionario funcionarioRealizacao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="funcionario_pagamento_id")
	private Funcionario funcionarioPagamento;

	//bi-directional many-to-one association to PedidoTipoRoupa
	@OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoTipoRoupa> pedidoTiposRoupa;

	public Pedido() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getCancelado() {
		return this.cancelado;
	}

	public void setCancelado(byte cancelado) {
		this.cancelado = cancelado;
	}

	public Date getDataHoraCadastro() {
		return this.dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public Date getDataHoraCancelamento() {
		return this.dataHoraCancelamento;
	}

	public void setDataHoraCancelamento(Date dataHoraCancelamento) {
		this.dataHoraCancelamento = dataHoraCancelamento;
	}

	public Date getDataHoraConfirmacaoOrcamento() {
		return this.dataHoraConfirmacaoOrcamento;
	}

	public void setDataHoraConfirmacaoOrcamento(Date dataHoraConfirmacaoOrcamento) {
		this.dataHoraConfirmacaoOrcamento = dataHoraConfirmacaoOrcamento;
	}

	public Date getDataHoraPagamento() {
		return this.dataHoraPagamento;
	}

	public void setDataHoraPagamento(Date dataHoraPagamento) {
		this.dataHoraPagamento = dataHoraPagamento;
	}

	public Date getDataHoraPrazo() {
		return this.dataHoraPrazo;
	}

	public void setDataHoraPrazo(Date dataHoraPrazo) {
		this.dataHoraPrazo = dataHoraPrazo;
	}

	public Date getDataHoraRealizacao() {
		return this.dataHoraRealizacao;
	}

	public void setDataHoraRealizacao(Date dataHoraRealizacao) {
		this.dataHoraRealizacao = dataHoraRealizacao;
	}

	public Date getDataHoraRecebimento() {
		return this.dataHoraRecebimento;
	}

	public void setDataHoraRecebimento(Date dataHoraRecebimento) {
		this.dataHoraRecebimento = dataHoraRecebimento;
	}

	public String getEnderecoEntrega() {
		return this.enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public byte getEntregaFrustrada() {
		return this.entregaFrustrada;
	}

	public void setEntregaFrustrada(byte entregaFrustrada) {
		this.entregaFrustrada = entregaFrustrada;
	}

	public String getEntregaFrustradaJustificativa() {
		return this.entregaFrustradaJustificativa;
	}

	public void setEntregaFrustradaJustificativa(String entregaFrustradaJustificativa) {
		this.entregaFrustradaJustificativa = entregaFrustradaJustificativa;
	}

	public byte getEntregue() {
		return this.entregue;
	}

	public void setEntregue(byte entregue) {
		this.entregue = entregue;
	}

	public String getObservacaoCliente() {
		return this.observacaoCliente;
	}

	public void setObservacaoCliente(String observacaoCliente) {
		this.observacaoCliente = observacaoCliente;
	}

	public String getObservacaoInterna() {
		return this.observacaoInterna;
	}

	public void setObservacaoInterna(String observacaoInterna) {
		this.observacaoInterna = observacaoInterna;
	}

	public byte getOrcamentoConfirmado() {
		return this.orcamentoConfirmado;
	}

	public void setOrcamentoConfirmado(byte orcamentoConfirmado) {
		this.orcamentoConfirmado = orcamentoConfirmado;
	}

	public byte getPago() {
		return this.pago;
	}

	public void setPago(byte pago) {
		this.pago = pago;
	}

	public byte getRealizado() {
		return this.realizado;
	}

	public void setRealizado(byte realizado) {
		this.realizado = realizado;
	}

	public byte getRecebido() {
		return this.recebido;
	}

	public void setRecebido(byte recebido) {
		this.recebido = recebido;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionarioRealizacao() {
		return this.funcionarioRealizacao;
	}

	public void setFuncionarioRealizacao(Funcionario funcionarioRealizacao) {
		this.funcionarioRealizacao = funcionarioRealizacao;
	}

	public Funcionario getFuncionarioPagamento() {
		return this.funcionarioPagamento;
	}

	public void setFuncionarioPagamento(Funcionario funcionarioPagamento) {
		this.funcionarioPagamento = funcionarioPagamento;
	}

	public List<PedidoTipoRoupa> getPedidoTiposRoupa() {
		return this.pedidoTiposRoupa;
	}

	public void setPedidoTiposRoupa(List<PedidoTipoRoupa> pedidoTiposRoupa) {
		this.pedidoTiposRoupa = pedidoTiposRoupa;
	}

	public PedidoTipoRoupa addPedidoTiposRoupa(PedidoTipoRoupa pedidoTiposRoupa) {
		getPedidoTiposRoupa().add(pedidoTiposRoupa);
		pedidoTiposRoupa.setPedido(this);

		return pedidoTiposRoupa;
	}

	public PedidoTipoRoupa removePedidoTiposRoupa(PedidoTipoRoupa pedidoTiposRoupa) {
		getPedidoTiposRoupa().remove(pedidoTiposRoupa);
		pedidoTiposRoupa.setPedido(null);

		return pedidoTiposRoupa;
	}

}