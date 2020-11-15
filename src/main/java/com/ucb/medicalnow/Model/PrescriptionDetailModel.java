package com.ucb.medicalnow.Model;

public class PrescriptionDetailModel {

    private String productName;
    private String productDetail;
    private String productQtty;

    public PrescriptionDetailModel() {
    }

    public PrescriptionDetailModel(String productName, String productDetail, String productQtty) {
        this.productName = productName;
        this.productDetail = productDetail;
        this.productQtty = productQtty;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductQtty() {
        return productQtty;
    }

    public void setProductQtty(String productQtty) {
        this.productQtty = productQtty;
    }

    @Override
    public String toString() {
        return "PrescriptionDetailModel{" +
                "productName='" + productName + '\'' +
                ", productDetail='" + productDetail + '\'' +
                ", productQtty='" + productQtty + '\'' +
                '}';
    }
}
