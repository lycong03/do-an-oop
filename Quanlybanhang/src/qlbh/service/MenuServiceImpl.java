/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.service;

import java.util.List;
import qlbh.bean.ChoBean;
import qlbh.bean.HoaDonBean;
import qlbh.dao.MenuDAO;
import qlbh.dao.MenuDAOImpl;

/**
 *
 * @author congl
 */
public class MenuServiceImpl implements MenuService {
    
    private MenuDAO menuDAO = null;
    
    public MenuServiceImpl() {
        this.menuDAO = new MenuDAOImpl();
    }


    @Override
    public List<HoaDonBean> getListByHoaDon() {
        return menuDAO.getListByHoaDon();
    }

    @Override
    public List<ChoBean> getListByCho() {
        return menuDAO.getListByCho();
    }

    
}
