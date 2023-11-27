/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.controller;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import qlbh.model.HoaDon;
import qlbh.service.HoaDonService;
import qlbh.service.HoaDonServiceImpl;
/**
 *
 * @author congl
 */
public class HoaDonController {
    
    private JButton btnSubmit;
    private JTextField jtfMaHD;
    private JTextField jtfMaKH;
    private JTextField jtfMaC;
    private JTextField jtfSoLuong;
    private JDateChooser jdcNgayLapHD;
    private JTextField jtfTinhTrangHD;
    private JTextField jtfPTTT;
    private JTextField jtfTongTien;
    private JLabel jlbMsg;
    
    private HoaDon hoaDon = null;
    
    private HoaDonService hoaDonService = null;

    public HoaDonController(JButton btnSubmit, JTextField jtfMaHD, JTextField jtfMaKH, JTextField jtfMaC, JTextField jtfSoLuong, JDateChooser jdcNgayLapHD, JTextField jtfTinhTrangHD, JTextField jtfPTTT, JTextField jtfTongTien, JLabel jlbMsg) {
        this.btnSubmit = btnSubmit;
        this.jtfMaHD = jtfMaHD;
        this.jtfMaKH = jtfMaKH;
        this.jtfMaC = jtfMaC;
        this.jtfSoLuong = jtfSoLuong;
        this.jdcNgayLapHD = jdcNgayLapHD;
        this.jtfTinhTrangHD = jtfTinhTrangHD;
        this.jtfPTTT = jtfPTTT;
        this.jtfTongTien = jtfTongTien;
        this.jlbMsg = jlbMsg;
        
        this.hoaDonService = new HoaDonServiceImpl();
    }

    
    

    
    
    public void setView(HoaDon hoaDon){
        this.hoaDon = hoaDon;
        
            jtfMaHD.setText("#"+hoaDon.getMaHD());
            jtfMaC.setText(""+hoaDon.getMaC());
            jtfMaKH.setText(""+hoaDon.getMaKH());
            jtfSoLuong.setText(""+hoaDon.getSoLuong());
            jdcNgayLapHD.setDate(hoaDon.getNgayLapHD());
            jtfTinhTrangHD.setText(hoaDon.getTinhTrangHD());
            jtfPTTT.setText(hoaDon.getPTTT());
            jtfTongTien.setText("" +hoaDon.getTongTien());
    }
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfMaC.getText().length() == 0 || jdcNgayLapHD.getDate() == null) {
                    jlbMsg.setText("Vui long nhap du lieu bat buoc!");
                }else {
                    int maC = Integer.parseInt(jtfMaC.getText());
                    int maKH = Integer.parseInt(jtfMaKH.getText());
                    int soLuong = Integer.parseInt(jtfSoLuong.getText());
                    int tongTien = Integer.parseInt(jtfTongTien.getText());
                    hoaDon.setMaC(maC);
                    hoaDon.setMaKH(maKH);
                    hoaDon.setSoLuong(soLuong);
                    hoaDon.setNgayLapHD(covertDateToDateSql(jdcNgayLapHD.getDate()));
                    hoaDon.setTinhTrangHD(jtfTinhTrangHD.getText());
                    hoaDon.setPTTT(jtfPTTT.getText());
                    hoaDon.setTongTien(tongTien);
                    int lastID = hoaDonService.createOrUpdate(hoaDon);
                    if(lastID > 0) {
                        hoaDon.setMaHD(lastID);
                        
                        jtfMaHD.setText("#" + hoaDon.getMaHD());
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
}
