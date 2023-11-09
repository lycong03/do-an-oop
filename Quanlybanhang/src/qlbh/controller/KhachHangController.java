package qlbh.controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import qlbh.model.KhachHang;

/**
 *
 * @author congl
 */
public class KhachHangController {
    
    private JButton btnSubmit;
    private JTextField jtfMaKH;
    private JTextField jtfTenKH;
    private JDateChooser jdcNgaySinh ;
    private JRadioButton jrdNam ;
    private JRadioButton jrdNu ;
    private JTextField jtfSDT;
    private JTextField jtfEmail;
    private JTextField jtfTKNH;
    private JTextField jtaDiaChi;
    
    private KhachHang khachHang = null;

    public KhachHangController(JButton btnSubmit, JTextField jtfMaKH, JTextField jtfTenKH, JDateChooser jdcNgaySinh, JRadioButton jrdNam, JRadioButton jrdNu, JTextField jtfSDT, JTextField jtfEmail, JTextField jtfTKNH, JTextField jtaDiaChi) {
        this.btnSubmit = btnSubmit;
        this.jtfMaKH = jtfMaKH;
        this.jtfTenKH = jtfTenKH;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jrdNam = jrdNam;
        this.jrdNu = jrdNu;
        this.jtfSDT = jtfSDT;
        this.jtfEmail = jtfEmail;
        this.jtfTKNH = jtfTKNH;
        this.jtaDiaChi = jtaDiaChi;
    }
    

    
    
    public void setView(KhachHang khachHang){
        this.khachHang = khachHang;
        
            jtfMaKH.setText("#" +khachHang.getMaKH());
            jtfTenKH.setText(khachHang.getTenKH());
            jdcNgaySinh.setDate(khachHang.getNgaySinh());
            if(khachHang.isGioiTinh()) {
                jrdNam.setSelected(true);
                jrdNam.setSelected(false);
            }else{
                jrdNam.setSelected(false);
                jrdNam.setSelected(true);
            }
            jtfSDT.setText(khachHang.getSDT());
            jtfEmail.setText(khachHang.getEmail());
            jtfTKNH.setText(khachHang.getTKNH());
            jtaDiaChi.setText(khachHang.getDiaChi());
    }
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0,200,83));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                 btnSubmit.setBackground(new Color(100,221,23));
            }
        });
    }
}
