package qlbh.service;

import java.util.List;
import qlbh.dao.KhachHangDAO;
import qlbh.dao.KhachHangDAOImpl;
import qlbh.model.KhachHang;

/**
 *
 * @author congl
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangDAO khachHangDAO = null;
    
    public KhachHangServiceImpl() {
        this.khachHangDAO = new KhachHangDAOImpl();
    }
    @Override
    public List<KhachHang> getList() {
        return khachHangDAO.getList();
    }

    @Override
    public int createOrUpdate(KhachHang khachHang) {
        return khachHangDAO.createOrUpdate(khachHang);
    }
    @Override
    public void xoa(int id) {
        khachHangDAO.xoa(id);
    }
}
