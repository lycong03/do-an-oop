/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import qlbh.model.HoaDon;
import qlbh.service.HoaDonService;
import qlbh.service.HoaDonServiceImpl;
import qlbh.utility.ClassTableModelHoaDon;
import qlbh.view.HoaDonJFrame;
/**
 *
 * @author congl
 */
public class QuanLyHoaDonController {
        private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;

    private HoaDonService hoaDonService = null;
    
    private String[] listColumn = {"stt","Mã hóa đơn","Mã chó","Mã khách hàng","Số lượng","Ngày lập HĐ","Tình trạng HĐ","PTTT","Tổng tiền"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyHoaDonController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        
        this.hoaDonService = new HoaDonServiceImpl();
    }
    
public void setDateToTable() {
    List<HoaDon> listItem = hoaDonService.getList();
    
    DefaultTableModel model = new ClassTableModelHoaDon().setTableHoaDon(listItem, listColumn);
    JTable table = new JTable(model);
    
    rowSorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(rowSorter);
    
    jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            String text = jtfSearch.getText();
            if(text.trim().length() == 0) {
                rowSorter.setRowFilter(null);
            }else{
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            String text = jtfSearch.getText();
            if(text.trim().length() == 0) {
                rowSorter.setRowFilter(null);
            }else{
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    });
    
    table.getColumnModel().getColumn(0).setMinWidth(0);
    table.getColumnModel().getColumn(0).setMaxWidth(0);
    table.getColumnModel().getColumn(0).setPreferredWidth(0);
    
    table.getColumnModel().getColumn(1).setMinWidth(100);
    table.getColumnModel().getColumn(1).setMaxWidth(100);
    table.getColumnModel().getColumn(1).setPreferredWidth(100);
    
    table.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectedRowIndex = table.getSelectedRow();
                selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD((int) model.getValueAt(selectedRowIndex,1));
                hoaDon.setMaC((int) model.getValueAt(selectedRowIndex, 2));
                hoaDon.setMaKH((int) model.getValueAt(selectedRowIndex, 3));
                hoaDon.setSoLuong((int) model.getValueAt(selectedRowIndex, 4));
                hoaDon.setNgayLapHD((Date) model.getValueAt(selectedRowIndex, 5));
                hoaDon.setTinhTrangHD(model.getValueAt(selectedRowIndex, 6).toString());
                hoaDon.setPTTT(model.getValueAt(selectedRowIndex, 7).toString());
                hoaDon.setTongTien((int) model.getValueAt(selectedRowIndex, 8));
                HoaDonJFrame frame = new HoaDonJFrame(hoaDon);
                frame.setTitle("Thong Tin Hoa Don");
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        }
    });
    
    table.getTableHeader().setFont(new Font("Arrial",Font.BOLD, 14));
    table.getTableHeader().setPreferredSize(new Dimension(100,50));
    table.setRowHeight(50);
    table.validate();
    table.repaint();
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.getViewport().add(table);
    scrollPane.setPreferredSize(new Dimension(1000,400));
    
    jpnView.removeAll();
    jpnView.setLayout(new BorderLayout());
    jpnView.add(scrollPane);
    jpnView.validate();
    jpnView.repaint();
}
    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HoaDonJFrame frame = new HoaDonJFrame(new HoaDon());
                frame.setTitle("Thong Tin Hoa Don");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0,200,83));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                 btnAdd.setBackground(new Color(100,221,23));
            }
        });
    }
    
}
