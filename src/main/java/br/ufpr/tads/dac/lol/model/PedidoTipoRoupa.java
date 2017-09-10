package br.ufpr.tads.dac.lol.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the pedido_tipo_roupa database table.
 * 
 */
@Entity
@Table(name="pedido_tipo_roupa")
@NamedQuery(name="PedidoTipoRoupa.findAll", query="SELECT p FROM PedidoTipoRoupa p")
public class PedidoTipoRoupa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedidoTipoRoupaPK id;

	private int quantidade;

	@Column(name="valor_unitario")
	private BigDecimal valorUnitario;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	private Pedido pedido;

	//bi-directional many-to-one association to TipoRoupa
	@ManyToOne
	@JoinColumn(name="tipo_roupa_id")
	private TipoRoupa tipoRoupa;

	public PedidoTipoRoupa() {
	}

	public PedidoTipoRoupaPK getId() {
		return this.id;
	}

	public void setId(PedidoTipoRoupaPK id) {
		this.id = id;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public TipoRoupa getTipoRoupa() {
		return this.tipoRoupa;
	}

	public void setTipoRoupa(TipoRoupa tipoRoupa) {
		this.tipoRoupa = tipoRoupa;
	}

}