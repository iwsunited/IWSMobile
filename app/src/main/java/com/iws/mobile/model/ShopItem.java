package com.iws.mobile.model;

public class ShopItem {
    String id, nama, caption, bv, imgUrl;
    int harga;
    boolean promo, baru;

    public ShopItem(String id, String nama, String caption, String bv, String imgUrl, int harga, boolean promo, boolean baru) {
        this.id = id;
        this.nama = nama;
        this.caption = caption;
        this.bv = bv;
        this.imgUrl = imgUrl;
        this.harga = harga;
        this.promo = promo;
        this.baru = baru;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getBv() {
        return bv;
    }

    public void setBv(String bv) {
        this.bv = bv;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public boolean isPromo() {
        return promo;
    }

    public void setPromo(boolean promo) {
        this.promo = promo;
    }

    public boolean isBaru() {
        return baru;
    }

    public void setBaru(boolean baru) {
        this.baru = baru;
    }
}
