package service;

import dao.SupplierDAO;
import java.util.List;
import model.Supplier;

/**
 *
 * @author asus
 */

public class SupplierService {
    private SupplierDAO dao = new SupplierDAO();

    public List<Supplier> getSuppliers(String keyword) {
        List<Supplier> suppliers = dao.findAll(keyword);
        return suppliers;
    }

    public Supplier getSupplierById(int id) {
        return dao.findById(id);
    }

    public boolean insertSupplier(Supplier suppliers) {
        return dao.insert(suppliers);
    }

    public boolean updateSupplier(Supplier suppliers) {
        return dao.update(suppliers);
    }

    public boolean deleteSupplier(int id) {
        return dao.delete(id);
    }
}
