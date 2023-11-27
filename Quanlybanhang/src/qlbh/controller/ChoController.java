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
import qlbh.model.Cho;
import qlbh.service.ChoService;
import qlbh.service.ChoServiceImpl;

/**
 *
 * @author congl
 */
public class ChoController {
    
    private JButton btnSubmit;
    private JTextField jtfMaC;
    private JTextField jtfTenC;
    private JTextField jtfMaDM;
    private JDateChooser jdcNgaySinhC ;
    private JRadioButton jrdDuc ;
    private JRadioButton jrdCai ;
    private JTextField jtfCanNang;
    private JTextField jtfGiaTien;
    private JTextArea jtaMoTa;
    private JLabel jlbMsg;
    
    private JButton btnDel;
    
    private Cho cho = null;
    
    private ChoService choService = null;

    public ChoController(JButton btnSubmit, JTextField jtfMaC, JTextField jtfTenC, JTextField jtfMaDM, JDateChooser jdcNgaySinhC, JRadioButton jrdDuc, JRadioButton jrdCai, JTextField jtfCanNang, JTextField jtfGiaTien, JTextArea jtaMoTa, JLabel jlbMsg,JButton btnDel) {
        this.btnSubmit = btnSubmit;
        this.jtfMaC = jtfMaC;
        this.jtfTenC = jtfTenC;
        this.jtfMaDM = jtfMaDM;
        this.jdcNgaySinhC = jdcNgaySinhC;
        this.jrdDuc = jrdDuc;
        this.jrdCai = jrdCai;
        this.jtfCanNang = jtfCanNang;
        this.jtfGiaTien = jtfGiaTien;
        this.jtaMoTa = jtaMoTa;
        this.jlbMsg = jlbMsg;
        this.btnDel=btnDel;
        this.choService = new ChoServiceImpl();
    }
 
    public void setView(Cho cho){
        this.cho = cho;
        
            jtfMaC.setText("#"+cho.getMaC());
            jtfTenC.setText(cho.getTenC());
            jtfMaDM.setText(""+cho.getMaDM());
            jdcNgaySinhC.setDate(cho.getNgaySinhC());
            if (cho.isGioiTinhC()) {
            jrdDuc.setSelected(true);
        } else {
            jrdCai.setSelected(true);
        }
            jtfCanNang.setText(cho.getCanNang());
            jtfGiaTien.setText(cho.getGiaTien() + "");
            jtaMoTa.setText(cho.getMoTa());
    }
    
   public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfTenC.getText().length() == 0 || jdcNgaySinhC.getDate() == null) {
                    jlbMsg.setText("Vui long nhap du lieu bat buoc!");
                }else {
                    String maDM = String.valueOf(cho.getMaDM());
                    jtfMaDM.setText(maDM);
                    cho.setTenC(jtfTenC.getText());            
                    cho.setNgaySinhC(covertDateToDateSql(jdcNgaySinhC.getDate()));
                    if (cho.isGioiTinhC()) {
            jrdDuc.setSelected(true);
        } else {
            jrdCai.setSelected(true);
        }
                    cho.setCanNang(jtfCanNang.getText());
                    cho.setGiaTien(Integer.parseInt(jtfGiaTien.getText()));
                    cho.setMoTa(jtaMoTa.getText());
                    int lastID = choService.createOrUpdate(cho);
                    if(lastID > 0) {
                        cho.setMaC(lastID);
                        
                        jtfMaC.setText("#" + cho.getMaC());
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
                    if (jtfMaC.getText().length() == 0) {
                        jlbMsg.setText("Vui long nhap du lieu bat buoc!");
                    }
                    else {
                        String idValue = jtfMaC.getText();
                        
                        if (idValue.startsWith("#")) {
                            idValue = idValue.substring(1);
                        }
                        int id = Integer.parseInt(idValue);
                        choService.xoa(id);
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
