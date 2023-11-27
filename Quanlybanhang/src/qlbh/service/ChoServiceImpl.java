package qlbh.service;

import java.util.List;
import qlbh.dao.ChoDAO;
import qlbh.dao.ChoDAOImpl;
import qlbh.model.Cho;

/**
 *
 * @author congl
 */
public class ChoServiceImpl implements ChoService {

    private ChoDAO choDAO = null;
    
    public ChoServiceImpl() {
        this.choDAO = new ChoDAOImpl();
    }
    @Override
    public List<Cho> getList() {
        return choDAO.getList();
    }

    @Override
    public int createOrUpdate(Cho cho) {
        return choDAO.createOrUpdate(cho);
    }

    @Override
    public void xoa(int id) {
        choDAO.xoa(id);
    }
    
}
