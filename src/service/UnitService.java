/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UnitDAO;
import java.util.List;
import model.Unit;

/**
 *
 * @author Agung
 */
public class UnitService {
    private UnitDAO dao = new UnitDAO();

    public List<Unit> getUnits() {
        return dao.findAll();
    }
}
