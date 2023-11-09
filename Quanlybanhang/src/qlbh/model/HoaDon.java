/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.model;
import java.sql.Date;
/**
 *
 * @author congl
 */
public class HoaDon {
    private String MaHD;
    private String MaC;
    private String MaKH;
    private int SoLuong;
    private Date NgayLapHD;
    private Boolean TinhTrangHD;
    private boolean PTTT;
    private int TongTien;

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaC() {
        return MaC;
    }

    public void setMaC(String MaC) {
        this.MaC = MaC;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Date getNgayLapHD() {
        return NgayLapHD;
    }

    public void setNgayLapHD(Date NgayLapHD) {
        this.NgayLapHD = NgayLapHD;
    }

    public Boolean getTinhTrangHD() {
        return TinhTrangHD;
    }

    public void setTinhTrangHD(Boolean TinhTrangHD) {
        this.TinhTrangHD = TinhTrangHD;
    }

    public boolean isPTTT() {
        return PTTT;
    }

    public void setPTTT(boolean PTTT) {
        this.PTTT = PTTT;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }
    
}
