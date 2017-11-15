package br.ufpr.tads.dac.lol.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the tipo_roupa database table.
 *
 */
@Entity
@Table(name = "tipo_roupa")
@NamedQuery(name = "TipoRoupa.findAll", query = "SELECT t FROM TipoRoupa t")
public class TipoRoupa extends Model<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Byte ativo;

    private String descricao;

    private String nome;

    @Column(name = "prazo_lavagem")
    private Integer prazoLavagem;

    @Column(name = "preco_lavagem")
    private Float precoLavagem;

    //bi-directional many-to-one association to PedidoTipoRoupa
    @OneToMany(mappedBy = "id.tipoRoupa")
    private List<PedidoTipoRoupa> pedidoTiposRoupa;

    public TipoRoupa() {
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

    public Integer getPrazoLavagem() {
        return this.prazoLavagem;
    }

    public void setPrazoLavagem(Integer prazoLavagem) {
        this.prazoLavagem = prazoLavagem;
    }

    public Float getPrecoLavagem() {
        return this.precoLavagem;
    }

    public void setPrecoLavagem(Float precoLavagem) {
        this.precoLavagem = precoLavagem;
    }

    public List<PedidoTipoRoupa> getPedidoTiposRoupa() {
        return this.pedidoTiposRoupa;
    }

    public void setPedidoTiposRoupa(List<PedidoTipoRoupa> pedidoTiposRoupa) {
        this.pedidoTiposRoupa = pedidoTiposRoupa;
    }

    public PedidoTipoRoupa addPedidoTiposRoupa(PedidoTipoRoupa pedidoTiposRoupa) {
        getPedidoTiposRoupa().add(pedidoTiposRoupa);
        pedidoTiposRoupa.setTipoRoupa(this);

        return pedidoTiposRoupa;
    }

    public PedidoTipoRoupa removePedidoTiposRoupa(PedidoTipoRoupa pedidoTiposRoupa) {
        getPedidoTiposRoupa().remove(pedidoTiposRoupa);
        pedidoTiposRoupa.setTipoRoupa(null);

        return pedidoTiposRoupa;
    }

}
