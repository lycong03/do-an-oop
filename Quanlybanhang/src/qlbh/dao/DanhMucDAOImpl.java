package qlbh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import qlbh.model.DanhMuc;

/**
 *
 * @author congl
 */
public class DanhMucDAOImpl implements DanhMucDAO {

    @Override
    public List<DanhMuc> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM danhmuc";
            List<DanhMuc> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.setMaDM(rs.getInt("MaDM"));
            danhMuc.setTenDM(rs.getString("TenDM"));
            list.add(danhMuc);
            
//            System.out.println("abc");
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
    public int createOrUpdate(DanhMuc danhMuc) {
        
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO danhmuc(MaDM,TenDM) VALUES(?, ?) ON DUPLICATE KEY UPDATE  TenDM = VALUES(TenDM);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,danhMuc.getMaDM());
            ps.setString(2, danhMuc.getTenDM());
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
        DanhMucDAO danhMucDAO =new DanhMucDAOImpl();
        System.out.println(danhMucDAO.getList());
        
    }

     @Override
    public void xoa(int id) {
         try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM danhmuc WHERE MaDM = ?";
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
