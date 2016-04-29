package com.equitygroupfoundation.databundleusage;

import java.util.Date;

/**
 * Created by sebichondo on 29/04/2016.
 */
public class DataBundles {
    public String ProductType;
    public double ActivitionPrice;
    public Date ActivationDate;
    public Date ExpirationDate;
    public boolean Status;

    public DataBundles(String productType, double activitionPrice, Date activationDate, Date expirationDate, boolean status) {
        ProductType = productType;
        ActivitionPrice = activitionPrice;
        ActivationDate = activationDate;
        ExpirationDate = expirationDate;
        Status = status;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public double getActivitionPrice() {
        return ActivitionPrice;
    }

    public void setActivitionPrice(double activitionPrice) {
        ActivitionPrice = activitionPrice;
    }

    public Date getActivationDate() {
        return ActivationDate;
    }

    public void setActivationDate(Date activationDate) {
        ActivationDate = activationDate;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        ExpirationDate = expirationDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "DataBundles{" +
                "ProductType='" + ProductType + '\'' +
                ", ActivitionPrice=" + ActivitionPrice +
                ", ActivationDate=" + ActivationDate +
                ", ExpirationDate=" + ExpirationDate +
                ", Status=" + Status +
                '}';
    }
}
