package service;

import dao.ItemDAO;
import java.util.ArrayList;
import model.Item;

import java.util.List;

/**
 *
 * @author Agung
 */
public class ItemService {

    private ItemDAO dao = new ItemDAO();

    public List<Item> getItems(String keyword) {
        List<Item> items = dao.findAll(keyword);
        return items;
    }

    public Item getItemById(int id) {
        return dao.findById(id);
    }

    public boolean insertItem(Item item) {
        return dao.insert(item);
    }

    public boolean updateItem(Item item) {
        return dao.update(item);
    }

    public boolean deleteItem(int id) {
        return dao.delete(id);
    }
}
