package qlbh.controller;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import qlbh.bean.DanhMucBean;
import qlbh.view.KhachHangJPanel;
import qlbh.view.TrangChuJPanel;
import qlbh.view.ChoJPanel;
import qlbh.view.HoaDonJPanel;
import qlbh.view.DanhMucJPanel;


/**
 *
 * @author congl
 */
public class ChuyenManHinhController {
    
    private JPanel root;
    private String kindSelected = "";
    
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96,100,191));
        jlbItem.setBackground(new Color(96,100,191));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }
    
    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for(DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEven(item.getKind(),item.getJpn(),item.getJlb()));
        }
    }
    
    class LabelEven implements MouseListener{
        
        private JPanel node;
        
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEven(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind) {
                case "TrangChu" -> node = new TrangChuJPanel();
                case "KhachHang" -> node = new KhachHangJPanel();
                case "Cho" -> node = new ChoJPanel();
                case "DanhMuc" -> node = new DanhMucJPanel();
                case "HoaDon" -> node = new HoaDonJPanel();
                default -> { node = new TrangChuJPanel();
                }
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackgroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96,100,191));
            jlbItem.setBackground(new Color(96,100,191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96,100,191));
            jlbItem.setBackground(new Color(96,100,191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(kindSelected.equalsIgnoreCase(kind)) {
            jpnItem.setBackground(new Color(76,175,80));
            jlbItem.setBackground(new Color(76,175,80));
            }
        }
        
    }
    
    private void setChangeBackgroud(String kind) {
        for(DanhMucBean item: listItem) {
            if(item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(96,100,191));
                item.getJlb().setBackground(new Color(96,100,191));
            }else {
                item.getJpn().setBackground(new Color(76,175,80));
                item.getJlb().setBackground(new Color(76,175,80));                
            }
        }
    }
}
