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
import qlbh.model.KhachHang;
import qlbh.service.KhachHangService;
import qlbh.service.KhachHangServiceImpl;
import qlbh.utility.ClassTableModel;
import qlbh.view.KhachHangJFrame;

/**
 *
 * @author congl
 */
public class QuanLyKhachHangController {
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;

    private KhachHangService khachHangService = null;
    
    private String[] listColumn = {"stt","Mã khách hàng","Tên khách hàng","Ngày sinh","Giới tính","SDT","Địa chỉ","email"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyKhachHangController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        
        this.khachHangService = new KhachHangServiceImpl();
    }
    
public void setDateToTable() {
    List<KhachHang> listItem = khachHangService.getList();
    
    DefaultTableModel model = new ClassTableModel().setTableKhachHang(listItem, listColumn);
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
    
    table.getColumnModel().getColumn(1).setMinWidth(120);
    table.getColumnModel().getColumn(1).setMaxWidth(120);
    table.getColumnModel().getColumn(1).setPreferredWidth(80);
    
    table.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectedRowIndex = table.getSelectedRow();
                selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH((int)model.getValueAt(selectedRowIndex,1));
                khachHang.setTenKH(model.getValueAt(selectedRowIndex, 2).toString());
                khachHang.setNgaySinh((Date) model.getValueAt(selectedRowIndex, 3));
                khachHang.setGioiTinh(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                khachHang.setSDT(model.getValueAt(selectedRowIndex, 5).toString());
                khachHang.setEmail(model.getValueAt(selectedRowIndex, 6).toString());
                khachHang.setDiaChi(model.getValueAt(selectedRowIndex, 7).toString());
                KhachHangJFrame frame = new KhachHangJFrame(khachHang);
                frame.setTitle("Thong Tin Khach Hang");
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
                KhachHangJFrame frame = new KhachHangJFrame(new KhachHang());
                frame.setTitle("Thong Tin Khach Hang");
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
