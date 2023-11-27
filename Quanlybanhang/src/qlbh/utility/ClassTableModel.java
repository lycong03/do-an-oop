package qlbh.utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import qlbh.model.KhachHang;

/**
 *
 * @author congl
 */
public class ClassTableModel {
    
    public DefaultTableModel setTableKhachHang(List<KhachHang> listItem, String[] listColumn) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0) {
            for(int i = 0; i < rows;i++){
                KhachHang khachHang = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i+1);
                obj[1] = khachHang.getMaKH();
                obj[2] = khachHang.getTenKH();
                obj[3] = khachHang.getNgaySinh();
                obj[4] = khachHang.isGioiTinh() == true ? "Nam":"Nữ";
                obj[5] = khachHang.getSDT();
                obj[6] = khachHang.getDiaChi();
                obj[7] = khachHang.getEmail();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
