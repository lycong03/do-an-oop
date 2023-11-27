/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.service;

import java.util.List;
import qlbh.model.Cho;
/**
 *
 * @author congl
 */
public interface ChoService {
        public List<Cho> getList();
    public int createOrUpdate(Cho cho);
        public void xoa(int id);
}
