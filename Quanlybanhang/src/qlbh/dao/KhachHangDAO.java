/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.dao;

import qlbh.model.KhachHang;
import java.util.List;
/**
 *
 * @author congl
 */
public interface KhachHangDAO {
    public List<KhachHang> getList();
    public int createOrUpdate(KhachHang khachHang);
}
