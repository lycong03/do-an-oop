package qlbh.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import qlbh.model.DanhMuc;
import qlbh.service.DanhMucService;
import qlbh.service.DanhMucServiceImpl;

/**
 *
 * @author congl
 */
public class DanhMucController {
    
    private JButton btnSubmit;
    private JTextField jtfMaDM;
    private JTextField jtfTenDM;
    private JLabel jlbMsg;
    
    private JButton btnDel;
    
    private DanhMuc danhMuc = null;
    
    private DanhMucService danhMucService = null;

    public DanhMucController(JButton btnSubmit, JTextField jtfMaDM, JTextField jtfTenDM,JLabel jlbMsg,JButton btnDel) {
        this.btnSubmit = btnSubmit;
        this.jtfMaDM = jtfMaDM;
        this.jtfTenDM = jtfTenDM;
        this.jlbMsg = jlbMsg;
        this.btnDel=btnDel;
        
        this.danhMucService = new DanhMucServiceImpl();
    }
    

    
    
    public void setView(DanhMuc danhMuc){
        this.danhMuc = danhMuc;
        
            jtfMaDM.setText(String.valueOf(danhMuc.getMaDM()));
            jtfTenDM.setText(danhMuc.getTenDM());
            
            setEvent();
    }
    
    
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                if(jtfTenDM.getText().length() == 0) {
                    
                    jlbMsg.setText("Vui long nhap du lieu bat buoc!");
                }else {
                 
                    danhMuc.setTenDM(jtfTenDM.getText());
                    
                    int lastID = danhMucService.createOrUpdate(danhMuc);
                    if(lastID > 0) {
                        danhMuc.setMaDM(lastID);
                        
                        jtfMaDM.setText("#" + danhMuc.getMaDM());
                     jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");   
                    }
                    
//                    System.out.println(danhMuc.getMaDM());
                }
            }catch (Exception ex) {
                jlbMsg.setText(ex.toString());
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

        });
    }
    public void xoa() {
        btnDel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (jtfMaDM.getText().length() == 0) {
                        jlbMsg.setText("Vui long nhap du lieu bat buoc!");
                    }
                    else {
                        String idValue = jtfMaDM.getText();
                        
                        if (idValue.startsWith("#")) {
                            idValue = idValue.substring(1);
                        }
                        int id = Integer.parseInt(idValue);
                        danhMucService.xoa(id);
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
