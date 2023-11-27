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
    private int MaHD;
    private int MaC;
    private int MaKH;
    private int SoLuong;
    private Date NgayLapHD;
    private String TinhTrangHD;
    private String PTTT;
    private int TongTien;

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaC() {
        return MaC;
    }

    public void setMaC(int MaC) {
        this.MaC = MaC;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
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

    public String getTinhTrangHD() {
        return TinhTrangHD;
    }

    public void setTinhTrangHD(String TinhTrangHD) {
        this.TinhTrangHD = TinhTrangHD;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }
    
    
}
