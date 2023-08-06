package com.example.shopeefood.Model;

public class Taikhoan {
    protected Integer MATK;
    protected String TENTK;
    protected String MATKHAU;
    protected String HOTEN;
    protected int GIOITINH;
    protected String NGAYSINH;
    protected String SDT;
    protected String EMAIL;
    protected String DIACHI;
    protected int QUYEN;

    public Taikhoan() {
    }

    public Taikhoan(String TENTK, String HOTEN, String MATKHAU, int GIOITINH,int QUYEN, String NGAYSINH, String EMAIL, String SDT) {
        this.TENTK = TENTK;
        this.MATKHAU = MATKHAU;
        this.HOTEN = HOTEN;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
        this.SDT = SDT;
        this.EMAIL = EMAIL;
        this.QUYEN = QUYEN;
    }

    public int getQUYEN() {
        return QUYEN;
    }

    public void setQUYEN(int QUYEN) {
        this.QUYEN = QUYEN;
    }

    public Integer getMATK() {
        return MATK;
    }

    public void setMATK(Integer MATK) {
        this.MATK = MATK;
    }

    public String getTENTK() {
        return TENTK;
    }

    public void setTENTK(String TENTK) {
        this.TENTK = TENTK;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public int getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(int GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }
}
