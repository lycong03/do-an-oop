/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlbh.bean.ChoBean;
import qlbh.bean.HoaDonBean;
/**
 *
 * @author congl
 */
public class MenuDAOImpl implements MenuDAO{

    @Override
    public List<HoaDonBean> getListByHoaDon() {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT NgayLapHD, COUNT(*) as TongTien FROM hoadon GROUP BY NgayLapHD;";
        List<HoaDonBean> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonBean hoaDonBean = new HoaDonBean();
                hoaDonBean.setNgayLapHD(rs.getDate("NgayLapHD"));
                hoaDonBean.setTongTien(rs.getInt("TongTien"));
                list.add(hoaDonBean);
            }
            ps.close();
            cons.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<ChoBean> getListByCho() {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT MaDM, COUNT(*) as MaC FROM cho GROUP BY MaDM;";
        List<ChoBean> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChoBean choBean = new ChoBean();
                choBean.setMaDanhMuc(rs.getString("MaDM"));
                choBean.setSoLuongCho(rs.getInt("MaC"));
                list.add(choBean);
            }
            ps.close();
            cons.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    
    
}
