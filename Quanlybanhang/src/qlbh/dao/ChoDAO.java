/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlbh.dao;

import qlbh.model.Cho;
import java.util.List;
/**
 *
 * @author congl
 */
public interface ChoDAO {
    public List<Cho> getList();
    public int createOrUpdate(Cho cho);
    public void xoa(int id);
}
