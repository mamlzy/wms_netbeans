package service;

import dao.ItemDAO;
import model.Item;

import java.util.List;

/**
 *
 * @author Agung
 */
public class ItemService {

    private ItemDAO dao = new ItemDAO();

    public List<Item> getItems() {
        return dao.findAll();
    }
    
    public boolean insertItem(Item item){
        return dao.insert(item);
    }
}