package qlbh.controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import qlbh.model.KhachHang;
import qlbh.service.KhachHangService;
import qlbh.service.KhachHangServiceImpl;

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
    private JTextArea jtaDiaChi;
    private JLabel jlbMsg;
    private JButton btnDel;
    
    private KhachHang khachHang = null;
    
    private KhachHangService khachHangService = null;

    public KhachHangController(JButton btnSubmit, JTextField jtfMaKH, JTextField jtfTenKH, JDateChooser jdcNgaySinh, JRadioButton jrdNam, JRadioButton jrdNu, JTextField jtfSDT, JTextField jtfEmail, JTextArea jtaDiaChi, JLabel jlbMsg, JButton btnDel) {
        this.btnSubmit = btnSubmit;
        this.jtfMaKH = jtfMaKH;
        this.jtfTenKH = jtfTenKH;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jrdNam = jrdNam;
        this.jrdNu = jrdNu;
        this.jtfSDT = jtfSDT;
        this.jtfEmail = jtfEmail;
        this.jtaDiaChi = jtaDiaChi;
        this.jlbMsg = jlbMsg;
        this.btnDel = btnDel;
        
        this.khachHangService = new KhachHangServiceImpl();
    }
    
    

    
    
    public void setView(KhachHang khachHang){
        this.khachHang = khachHang;
        
            jtfMaKH.setText("#"+khachHang.getMaKH());
            jtfTenKH.setText(khachHang.getTenKH());
            jdcNgaySinh.setDate(khachHang.getNgaySinh());
            if(khachHang.isGioiTinh()) {
                jrdNam.setSelected(true);
                jrdNu.setSelected(false);
            }else{
                jrdNu.setSelected(true);
                jrdNam.setSelected(false);
                
            }
            jtfSDT.setText(khachHang.getSDT());
            jtfEmail.setText(khachHang.getEmail());
            jtaDiaChi.setText(khachHang.getDiaChi());
            
            setEvent();
    }
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfTenKH.getText().length() == 0 || jdcNgaySinh.getDate() == null) {
                    jlbMsg.setText("Vui long nhap du lieu bat buoc!");
                }else {
                    khachHang.setTenKH(jtfTenKH.getText());
                    khachHang.setNgaySinh(covertDateToDateSql(jdcNgaySinh.getDate()));
                    khachHang.setGioiTinh(jrdNam.isSelected());
                    khachHang.setSDT(jtfSDT.getText());
                    khachHang.setEmail(jtfEmail.getText());
                    khachHang.setDiaChi(jtaDiaChi.getText());
                    int lastID = khachHangService.createOrUpdate(khachHang);
                    if(lastID > 0) {
                        khachHang.setMaKH(lastID);
                        
                        jtfMaKH.setText("#" + khachHang.getMaKH());
                     jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");   
                    }
                    jlbMsg.setText("cap nhat Thanh cong"); 
                    
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0,200,83));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                 btnSubmit.setBackground(new Color(100,221,23));
            }

            private java.sql.Date covertDateToDateSql(Date d) {
                return new java.sql.Date(d.getTime());
            }
        });
    }
    public void xoa() {
        btnDel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (jtfMaKH.getText().length() == 0) {
                        jlbMsg.setText("Vui long nhap du lieu bat buoc!");
                    }
                    else {
                        String idValue = jtfMaKH.getText();
                        
                        if (idValue.startsWith("#")) {
                            idValue = idValue.substring(1);
                        }
                        int id = Integer.parseInt(idValue);
                        khachHangService.xoa(id);
                    }
                }
                catch (Exception ex) {
                    jlbMsg.setText(ex.toString());
                } 
            }
            @Override
            public void mouseEntered(MouseEvent e) {
               
            }
            @Override
            public void mouseExited(MouseEvent e) {
                
            }

        });
    }
}
