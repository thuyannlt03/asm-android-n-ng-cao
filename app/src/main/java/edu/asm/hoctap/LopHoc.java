package edu.asm.hoctap;

public class LopHoc {
    private int _id;
    private String TenLop;

    public LopHoc(String tenLop) {
        TenLop = tenLop;
    }

    public LopHoc(int _id, String tenLop) {
        this._id = _id;
        TenLop = tenLop;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }
}
