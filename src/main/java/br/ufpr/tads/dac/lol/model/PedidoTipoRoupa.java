package br.ufpr.tads.dac.lol.model;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the pedido_tipo_roupa database table.
 *
 */
@Entity
@Table(name = "pedido_tipo_roupa")
@NamedQuery(name = "PedidoTipoRoupa.findAll", query = "SELECT p FROM PedidoTipoRoupa p")
public class PedidoTipoRoupa extends Model<PedidoTipoRoupaPK> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EmbeddedId
    private PedidoTipoRoupaPK id;

    private Integer quantidade;

    @Column(name = "valor_unitario")
    private Float valorUnitario;

    public PedidoTipoRoupa() {
    }

    @Override
    public PedidoTipoRoupaPK getId() {
        return this.id;
    }

    @Override
    public void setId(PedidoTipoRoupaPK id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValorUnitario() {
        return this.valorUnitario;
    }

    public void setValorUnitario(Float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Transient
    public Pedido getPedido() {
        return this.getId().getPedido();
    }

    public void setPedido(Pedido pedido) {
        this.getId().setPedido(pedido);
    }

    @Transient
    public TipoRoupa getTipoRoupa() {
        return this.getId().getTipoRoupa();
    }

    public void setTipoRoupa(TipoRoupa tipoRoupa) {
        this.getId().setTipoRoupa(tipoRoupa);
    }

}
