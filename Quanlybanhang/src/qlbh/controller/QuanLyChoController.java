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
import qlbh.model.Cho;
import qlbh.service.ChoService;
import qlbh.service.ChoServiceImpl;
import qlbh.utility.ClassTableModelCho;
import qlbh.view.ChoJFrame;

/**
 *
 * @author congl
 */
public class QuanLyChoController {
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;

    private ChoService choService = null;
    
    private String[] listColumn = {"stt","Mã Chó","Tên Chó","Mã Danh Mục","Ngày sinh chó","Giới tính","Cân Nặng","Giá Tiền","Mô Tả"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyChoController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        
        this.choService = new ChoServiceImpl();
    }
    
public void setDateToTableCho() {
    List<Cho> listItem = choService.getList();
    
    DefaultTableModel model = new ClassTableModelCho().setTableCho(listItem, listColumn);
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
    
    table.getColumnModel().getColumn(1).setMinWidth(80);
    table.getColumnModel().getColumn(1).setMaxWidth(80);
    table.getColumnModel().getColumn(1).setPreferredWidth(80);
    
    table.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectedRowIndex = table.getSelectedRow();
                selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                
                Cho cho = new Cho();
                cho.setMaC((int)model.getValueAt(selectedRowIndex,1));
                cho.setTenC(model.getValueAt(selectedRowIndex, 2).toString());
                cho.setMaDM((int) model.getValueAt(selectedRowIndex,3));
                cho.setNgaySinhC((Date) model.getValueAt(selectedRowIndex, 4));
                cho.setGioiTinhC(model.getValueAt(selectedRowIndex, 5).toString().equalsIgnoreCase("Đực"));
                cho.setCanNang(model.getValueAt(selectedRowIndex, 6).toString());
                cho.setGiaTien((int)model.getValueAt(selectedRowIndex, 7));
                cho.setMoTa(model.getValueAt(selectedRowIndex, 8).toString());
                ChoJFrame frame = new ChoJFrame(cho);
                frame.setTitle("Thong Tin Cho");
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
                ChoJFrame frame = new ChoJFrame(new Cho());
                frame.setTitle("Thong Tin Cho");
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
