package br.ufpr.tads.dac.lol.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tipo_roupa database table.
 * 
 */
@Entity
@Table(name="tipo_roupa")
@NamedQuery(name="TipoRoupa.findAll", query="SELECT t FROM TipoRoupa t")
public class TipoRoupa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte ativo;

	private String descricao;

	private String nome;

	@Column(name="prazo_lavagem")
	private int prazoLavagem;

	@Column(name="preco_lavagem")
	private BigDecimal precoLavagem;

	//bi-directional many-to-one association to PedidoTipoRoupa
	@OneToMany(mappedBy="tipoRoupa")
	private List<PedidoTipoRoupa> pedidoTipoRoupas;

	public TipoRoupa() {
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

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrazoLavagem() {
		return this.prazoLavagem;
	}

	public void setPrazoLavagem(int prazoLavagem) {
		this.prazoLavagem = prazoLavagem;
	}

	public BigDecimal getPrecoLavagem() {
		return this.precoLavagem;
	}

	public void setPrecoLavagem(BigDecimal precoLavagem) {
		this.precoLavagem = precoLavagem;
	}

	public List<PedidoTipoRoupa> getPedidoTipoRoupas() {
		return this.pedidoTipoRoupas;
	}

	public void setPedidoTipoRoupas(List<PedidoTipoRoupa> pedidoTipoRoupas) {
		this.pedidoTipoRoupas = pedidoTipoRoupas;
	}

	public PedidoTipoRoupa addPedidoTipoRoupa(PedidoTipoRoupa pedidoTipoRoupa) {
		getPedidoTipoRoupas().add(pedidoTipoRoupa);
		pedidoTipoRoupa.setTipoRoupa(this);

		return pedidoTipoRoupa;
	}

	public PedidoTipoRoupa removePedidoTipoRoupa(PedidoTipoRoupa pedidoTipoRoupa) {
		getPedidoTipoRoupas().remove(pedidoTipoRoupa);
		pedidoTipoRoupa.setTipoRoupa(null);

		return pedidoTipoRoupa;
	}

}