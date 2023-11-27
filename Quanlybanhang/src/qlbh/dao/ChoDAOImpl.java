package qlbh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import qlbh.model.Cho;

/**
 *
 * @author congl
 */
public class ChoDAOImpl implements ChoDAO {

    @Override
    public List<Cho> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM cho";
            List<Cho> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Cho cho = new Cho();
            cho.setMaC(rs.getInt("MaC"));
            cho.setTenC(rs.getString("TenC"));
            cho.setMaDM(rs.getInt("MaDM"));
            cho.setNgaySinhC(rs.getDate("NgaySinhC"));
            cho.setGioiTinhC(rs.getBoolean("GioiTinhC"));
            cho.setCanNang(rs.getString("CanNang"));
            cho.setGiaTien(rs.getInt("GiaTien"));
            cho.setMoTa(rs.getString("MoTa"));
            list.add(cho);
            }
        ps.close();
        rs.close();
        cons.close();
        return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public int createOrUpdate(Cho cho) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO cho(MaC,TenC,MaDM,NgaySinhC,GioiTinhC,CanNang,GiaTien,MoTa) VALUES(?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE"
                    + " MaC = VALUES(MaC), TenC = VALUES(TenC),MaDM = VALUES(MaDM),NgaySinhC = VALUES(NgaySinhC), GioiTinhC = VALUES(GioiTinhC),"
                    + " CanNang = VALUES(CanNang), GiaTien = VALUES(GiaTien), MoTa = VALUES(MoTa);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,cho.getMaC());
            ps.setString(2, cho.getTenC());
            ps.setInt(3,cho.getMaDM());
            ps.setDate(4,cho.getNgaySinhC());
            ps.setBoolean(5, cho.isGioiTinhC());
            ps.setString(6, cho.getCanNang());
            ps.setInt(7, cho.getGiaTien());
            ps.setString(8, cho.getMoTa());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
        ChoDAO choDAO =new ChoDAOImpl();
        System.out.println(choDAO.getList());
    }
    
    @Override
    public void xoa(int id) {
         try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM cho WHERE MaC = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            int rowsAffected = ps.executeUpdate();
            ps.close();
            cons.close();

            if (rowsAffected > 0) {
                JOptionPane.showInternalMessageDialog (null, "Xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy danh mục có ID tương ứng!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi!");
        }
    }
}
