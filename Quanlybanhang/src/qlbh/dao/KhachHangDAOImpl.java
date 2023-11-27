package qlbh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import qlbh.model.KhachHang;

/**
 *
 * @author congl
 */
public class KhachHangDAOImpl implements KhachHangDAO {

    @Override
    public List<KhachHang> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM khachhang";
            List<KhachHang> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKH(rs.getInt("MaKH"));
            khachHang.setTenKH(rs.getString("TenKH"));
            khachHang.setNgaySinh(rs.getDate("NgaySinh"));
            khachHang.setGioiTinh(rs.getBoolean("GioiTinh"));
            khachHang.setDiaChi(rs.getString("DiaChi"));
            khachHang.setSDT(rs.getString("SDT"));
            khachHang.setEmail(rs.getString("Email"));
            list.add(khachHang);
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
    public int createOrUpdate(KhachHang khachHang) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO khachhang(MaKH,TenKH,NgaySinh,GioiTinh,SDT,Email,DiaChi) VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE MaKH = VALUES(MaKH), TenKH = VALUES(TenKH),NgaySinh = VALUES(NgaySinh), GioiTinh = VALUES(GioiTinh), SDT = VALUES(SDT), DiaChi = VALUES(DiaChi), Email = VALUES(Email);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,khachHang.getMaKH());
            ps.setString(2, khachHang.getTenKH());
            ps.setDate(3,khachHang.getNgaySinh());
            ps.setBoolean(4, khachHang.isGioiTinh());
            ps.setString(5, khachHang.getSDT());
            ps.setString(6, khachHang.getEmail());
            ps.setString(7, khachHang.getDiaChi());
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
        KhachHangDAO khachHangDAO =new KhachHangDAOImpl();
        System.out.println(khachHangDAO.getList());
    }
    @Override
    public void xoa(int id) {
         try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM khachhang WHERE MaKH = ?";
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
