package es.uma.proyecto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PACCOUNT_CUENTAREF_FK {
    @ManyToOne
    @JoinColumn(name = "CUENTA_REF_FK", nullable = false)
    private String cuenta_referencia;

    @ManyToOne
    @JoinColumn(name = "POLLED_ACCOUNT_FK", nullable = false)
    private String pooled_account;
}
