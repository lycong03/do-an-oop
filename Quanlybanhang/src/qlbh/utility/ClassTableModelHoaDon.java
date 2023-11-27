/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import qlbh.model.HoaDon;
/**
 *
 * @author congl
 */
public class ClassTableModelHoaDon {

        public DefaultTableModel setTableHoaDon(List<HoaDon> listItem, String[] listColumn) {
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
                HoaDon hoaDon = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i+1);
                obj[1] = hoaDon.getMaHD();
                obj[2] = hoaDon.getMaC();
                obj[3] = hoaDon.getMaKH();
                obj[4] = hoaDon.getSoLuong();
                obj[5] = hoaDon.getNgayLapHD();
                obj[6] = hoaDon.getTinhTrangHD();
                obj[7] = hoaDon.getPTTT();
                obj[8] = hoaDon.getTongTien();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
