package vista;

import java.util.Objects;

public class vistaCuentas {
    private String iban;
    private String swift;
    private String estado;
    private String fechaApertura;
    private String fechaCierre;
    private String clasificacion;


    //Segregada
    private Double comision;

    //Cuenta Referencia
    private String ibanRef;
    private String nombreBanco;
    private String sucursal;
    private String pais;
    private Double Saldo;
    private String fechaAperturaRef;
    private String estadoRef;
    private String divisa;


    public String getIbanRef() {
        return this.ibanRef;
    }

    public void setIbanRef(String ibanRef) {
        this.ibanRef = ibanRef;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSwift() {
        return this.swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaApertura() {
        return this.fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getFechaCierre() {
        return this.fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getClasificacion() {
        return this.clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Double getComision() {
        return this.comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public String getNombreBanco() {
        return this.nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getSucursal() {
        return this.sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Double getSaldo() {
        return this.Saldo;
    }

    public void setSaldo(Double Saldo) {
        this.Saldo = Saldo;
    }

    public String getFechaAperturaRef() {
        return this.fechaAperturaRef;
    }

    public void setFechaAperturaRef(String fechaAperturaRef) {
        this.fechaAperturaRef = fechaAperturaRef;
    }

    public String getEstadoRef() {
        return this.estadoRef;
    }

    public void setEstadoRef(String estadoRef) {
        this.estadoRef = estadoRef;
    }

    public String getDivisa() {
        return this.divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof vistaCuentas)) {
            return false;
        }
        vistaCuentas vistaCuentas = (vistaCuentas) o;
        return Objects.equals(iban, vistaCuentas.iban) && Objects.equals(swift, vistaCuentas.swift) && Objects.equals(estado, vistaCuentas.estado) && Objects.equals(fechaApertura, vistaCuentas.fechaApertura) && Objects.equals(fechaCierre, vistaCuentas.fechaCierre) && Objects.equals(clasificacion, vistaCuentas.clasificacion) && Objects.equals(comision, vistaCuentas.comision) && Objects.equals(nombreBanco, vistaCuentas.nombreBanco) && Objects.equals(sucursal, vistaCuentas.sucursal) && Objects.equals(pais, vistaCuentas.pais) && Objects.equals(Saldo, vistaCuentas.Saldo) && Objects.equals(fechaAperturaRef, vistaCuentas.fechaAperturaRef) && Objects.equals(estadoRef, vistaCuentas.estadoRef) && Objects.equals(divisa, vistaCuentas.divisa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, swift, estado, fechaApertura, fechaCierre, clasificacion, comision, nombreBanco, sucursal, pais, Saldo, fechaAperturaRef, estadoRef, divisa);
    }


    @Override
    public String toString() {
        return "{" +
            " iban='" + getIban() + "'" +
            ", swift='" + getSwift() + "'" +
            ", estado='" + getEstado() + "'" +
            ", fechaApertura='" + getFechaApertura() + "'" +
            ", fechaCierre='" + getFechaCierre() + "'" +
            ", clasificacion='" + getClasificacion() + "'" +
            ", comision='" + getComision() + "'" +
            ", nombreBanco='" + getNombreBanco() + "'" +
            ", sucursal='" + getSucursal() + "'" +
            ", pais='" + getPais() + "'" +
            ", Saldo='" + getSaldo() + "'" +
            ", fechaAperturaRef='" + getFechaAperturaRef() + "'" +
            ", estadoRef='" + getEstadoRef() + "'" +
            ", divisa='" + getDivisa() + "'" +
            "}";
    }

    public vistaCuentas() {
    }


}
