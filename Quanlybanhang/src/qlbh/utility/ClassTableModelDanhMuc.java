/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import qlbh.model.DanhMuc;
/**
 *
 * @author congl
 */
public class ClassTableModelDanhMuc {
    public DefaultTableModel setTableDanhMuc(List<DanhMuc> listItem, String[] listColumn) {
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
                DanhMuc danhMuc = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i+1);
                obj[1] = danhMuc.getMaDM();
                obj[2] = danhMuc.getTenDM();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
