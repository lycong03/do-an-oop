package qlbh.service;

import java.util.List;
import qlbh.dao.DanhMucDAO;
import qlbh.dao.DanhMucDAOImpl;
import qlbh.model.DanhMuc;

/**
 *
 * @author congl
 */
public class DanhMucServiceImpl implements DanhMucService {

    private DanhMucDAO danhMucDAO = null;
    
    public DanhMucServiceImpl() {
        this.danhMucDAO = new DanhMucDAOImpl();
    }
    @Override
    public List<DanhMuc> getList() {
        return danhMucDAO.getList();
    }

    @Override
    public int createOrUpdate(DanhMuc danhMuc) {
        return danhMucDAO.createOrUpdate(danhMuc);
    }
    
     @Override
    public void xoa(int id) {
        danhMucDAO.xoa(id);
    }
    
}
