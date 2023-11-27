/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.service;

import java.util.List;
import qlbh.model.HoaDon;

/**
 *
 * @author congl
 */
public interface HoaDonService {
        public List<HoaDon> getList();
    
    public int createOrUpdate(HoaDon hoaDon);
    
}
