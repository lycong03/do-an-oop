/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.service;

import java.util.List;
import qlbh.dao.HoaDonDAO;
import qlbh.dao.HoaDonDAOImpl;
import qlbh.model.HoaDon;

/**
 *
 * @author congl
 */
public class HoaDonServiceImpl implements HoaDonService {
    private HoaDonDAO hoaDonDAO = null;

    public HoaDonServiceImpl() {
        this.hoaDonDAO = new HoaDonDAOImpl();
    }

    @Override
    public List<HoaDon> getList() {
        return hoaDonDAO.getList();
    }

    @Override
    public int createOrUpdate(HoaDon hoaDon) {
        return hoaDonDAO.createOrUpdate(hoaDon);
    }
}
