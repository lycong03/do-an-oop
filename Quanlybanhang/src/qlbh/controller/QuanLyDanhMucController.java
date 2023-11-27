package qlbh.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import qlbh.model.DanhMuc;
import qlbh.service.DanhMucService;
import qlbh.service.DanhMucServiceImpl;
import qlbh.utility.ClassTableModelDanhMuc;
import qlbh.view.DanhMucJFrame;

/**
 *
 * @author congl
 */
public class QuanLyDanhMucController {
    
    private JPanel jpnViewDM;
    private JButton btnAddDM;
    private JTextField jtfSearchDM;

    private DanhMucService danhMucService = null;
    
    private String[] listColumn = {"STT","Mã Danh Mục","Tên Danh Mục"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyDanhMucController(JPanel jpnViewDM, JButton btnAddDM, JTextField jtfSearchDM) {
        this.jpnViewDM = jpnViewDM;
        this.btnAddDM = btnAddDM;
        this.jtfSearchDM = jtfSearchDM;
        
        this.danhMucService = new DanhMucServiceImpl();
    }
    
public void setDateToTableDanhMuc() {
    List<DanhMuc> listItem = danhMucService.getList();
    
    DefaultTableModel model = new ClassTableModelDanhMuc().setTableDanhMuc(listItem, listColumn);
    JTable table = new JTable(model);
    
    rowSorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(rowSorter);
    
    jtfSearchDM.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            String text = jtfSearchDM.getText();
            if(text.trim().length() == 0) {
                rowSorter.setRowFilter(null);
            }else{
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            String text = jtfSearchDM.getText();
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
    
    table.getColumnModel().getColumn(1).setMinWidth(200);
    table.getColumnModel().getColumn(1).setMaxWidth(200);
    table.getColumnModel().getColumn(1).setPreferredWidth(200);
    
    table.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectedRowIndex = table.getSelectedRow();
                selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setMaDM((int) model.getValueAt(selectedRowIndex,1));
                danhMuc.setTenDM(model.getValueAt(selectedRowIndex, 2).toString());
                DanhMucJFrame frame = new DanhMucJFrame(danhMuc);
                frame.setTitle("Thong Tin Danh Muc");
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
    
    jpnViewDM.removeAll();
    jpnViewDM.setLayout(new BorderLayout());
    jpnViewDM.add(scrollPane);
    jpnViewDM.validate();
    jpnViewDM.repaint();
}
    public void setEvent() {
        btnAddDM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DanhMucJFrame frame = new DanhMucJFrame(new DanhMuc());
                frame.setTitle("Thong Tin Danh Muc");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAddDM.setBackground(new Color(0,200,83));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                 btnAddDM.setBackground(new Color(100,221,23));
            }
        });
    }
}
