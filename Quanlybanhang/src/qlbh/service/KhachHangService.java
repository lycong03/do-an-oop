package qlbh.service;

import java.util.List;
import qlbh.model.KhachHang;
/**
 *
 * @author congl
 */
public interface KhachHangService {
    
    public List<KhachHang> getList();
    public int createOrUpdate(KhachHang khachHang);
}
