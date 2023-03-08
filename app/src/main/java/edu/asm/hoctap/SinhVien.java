package edu.asm.hoctap;

public class SinhVien {
    private String ten;
    private int mssv,id_lop;

    public SinhVien(String ten, int id_lop) {
        this.ten = ten;
        this.id_lop = id_lop;
    }

    public int getId_lop() {
        return id_lop;
    }

    public void setId_lop(int id_lop) {
        this.id_lop = id_lop;
    }

    public SinhVien(String ten, int mssv, int id_lop) {
        this.ten = ten;
        this.mssv = mssv;
        this.id_lop = id_lop;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }
}

