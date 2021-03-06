package br.ufpr.tads.dac.lol.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * The primary key class for the pedido_tipo_roupa database table.
 *
 */
@Embeddable
public class PedidoTipoRoupaPK implements Serializable {
    // default serial version id, required for serializable classes.

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "tipo_roupa_id")
    private TipoRoupa tipoRoupa;

    public PedidoTipoRoupaPK() {
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PedidoTipoRoupaPK)) {
            return false;
        }
        PedidoTipoRoupaPK castOther = (PedidoTipoRoupaPK) other;
        return (Objects.equals(this.getPedido().getId(), castOther.getPedido().getId()))
                && (Objects.equals(this.getTipoRoupa().getId(), castOther.getTipoRoupa().getId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.getPedido().getId();
        hash = hash * prime + this.getTipoRoupa().getId();

        return hash;
    }
}
