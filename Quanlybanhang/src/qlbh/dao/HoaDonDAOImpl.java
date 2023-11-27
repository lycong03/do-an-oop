package qlbh.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import qlbh.model.HoaDon;
/**
 *
 * @author congl
 */
public class HoaDonDAOImpl implements HoaDonDAO {
    @Override
    public List<HoaDon> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM hoadon";
            List<HoaDon> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(rs.getInt("MaHD"));
            hoaDon.setMaC(rs.getInt("MaC"));
            hoaDon.setMaKH(rs.getInt("MaKH"));
            hoaDon.setSoLuong(rs.getInt("SoLuong"));
            hoaDon.setNgayLapHD(rs.getDate("NgayLapHD"));
            hoaDon.setTinhTrangHD(rs.getString("TinhTrangHD"));
            hoaDon.setPTTT(rs.getString("PTTT"));
            hoaDon.setTongTien(rs.getInt("TongTien"));
            list.add(hoaDon);
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
    public int createOrUpdate(HoaDon hoaDon) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO hoadon(MaHD,MaC,MaKH,SoLuong,NgayLapHD,TinhTrangHD,PTTT,TongTien) VALUES(?, ?, ?, ?, ?, ?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE MaHD = VALUES(MaHD), MaC = VALUES(MaC),MaKH = VALUES(MaKH),"
                    + "SoLuong = VALUES(SoLuong), NgayLapHD = VALUES(NgayLapHD), TinhTrangHD = VALUES(TinhTrangHD),"
                    + " PTTT = VALUES(PTTT), TongTien = VALUES(TongTien);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,hoaDon.getMaHD());
            ps.setInt(2, hoaDon.getMaC());
            ps.setInt(3, hoaDon.getMaKH());
            ps.setInt(4, hoaDon.getSoLuong());            
            ps.setDate(5,hoaDon.getNgayLapHD());
            ps.setString(6, hoaDon.getTinhTrangHD());
            ps.setString(7, hoaDon.getPTTT());
            ps.setInt(8, hoaDon.getTongTien());              
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
        HoaDonDAO hoaDonDAO =new HoaDonDAOImpl();
        System.out.println(hoaDonDAO.getList());
    }
}
