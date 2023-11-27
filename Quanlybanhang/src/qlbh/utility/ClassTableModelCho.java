/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import qlbh.model.Cho;
/**
 *
 * @author congl
 */
public class ClassTableModelCho {
    public DefaultTableModel setTableCho(List<Cho> listItem, String[] listColumn) {
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
                Cho cho = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i+1);
                obj[1] = cho.getMaC();
                obj[2] = cho.getTenC();
                obj[3] = cho.getMaDM();
                obj[4] = cho.getNgaySinhC();
                obj[5] = cho.isGioiTinhC()== true ? "Đực":"Cái";
                obj[6] = cho.getCanNang();
                obj[7] = cho.getGiaTien();
                obj[8] = cho.getMoTa();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
