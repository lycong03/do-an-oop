/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.service;

import java.util.List;
import qlbh.bean.ChoBean;
import qlbh.bean.HoaDonBean;

/**
 *
 * @author congl
 */
public interface MenuService {
    
    public List<HoaDonBean> getListByHoaDon();
    
    public List<ChoBean> getListByCho();
}
