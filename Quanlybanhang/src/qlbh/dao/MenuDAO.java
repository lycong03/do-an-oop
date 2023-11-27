/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.dao;

import java.util.List;
import qlbh.bean.ChoBean;
import qlbh.bean.HoaDonBean;

/**
 *
 * @author congl
 */
public interface MenuDAO {
    
    public List<HoaDonBean> getListByHoaDon();
    
    public List<ChoBean> getListByCho();
    
}
