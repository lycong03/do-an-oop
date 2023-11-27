/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.service;

import java.util.List;
import qlbh.model.DanhMuc;
/**
 *
 * @author congl
 */
public interface DanhMucService {
    
        public List<DanhMuc> getList();
    public int createOrUpdate(DanhMuc danhMuc);
    public void xoa(int id);
}
