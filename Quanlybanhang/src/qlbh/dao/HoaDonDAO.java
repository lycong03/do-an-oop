/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.dao;
import qlbh.model.HoaDon;
import java.util.List;
/**
 *
 * @author congl
 */
public interface HoaDonDAO {
        public List<HoaDon> getList();
        public int createOrUpdate(HoaDon hoaDon);
}
