package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.PriceDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.services.PriceService;

public class PriceServiceImpl implements PriceService {

    /**
     * create instance of DAO class with type Price
     */
    private static final PriceDAO INSTANCE = (PriceDAO) DAOFactory.createDAO(DAOType.PRICE).create();
    private static final Logger LOG = Logger.getLogger(PriceServiceImpl.class);

    /**
     * this method find established price for concrete hall and price
     *
     * @param rowNumber
     * @param hallId
     * @return
     */
    @Override
    public double findPriceForSelectedRowInHall(int rowNumber, int hallId) {
        double price = INSTANCE.get(rowNumber, hallId);
        LOG.info("PriceService method for find price get price " + price);
        return price;
    }

    /**
     * this method create new price for selected row in hall
     *
     * @param rowNumber
     * @param price
     * @param hallId
     */
    @Override
    public void addNewPriceForSelectedRowInHall(int rowNumber, double price, int hallId) {
        INSTANCE.create(rowNumber, price, hallId);
        LOG.info("Price Service added new price");
    }

    /**
     * this method set new price for selected row in hall
     *
     * @param rowNumber
     * @param price
     * @param hallId
     */
    @Override
    public void setNewPriceForSelectedRowInHall(int rowNumber, double price, int hallId) {
        INSTANCE.update(rowNumber, price, hallId);
        LOG.info("Price Service set new price for row " + rowNumber + " in hall " + hallId);
    }

    /**
     * this method delete price in case mistake data
     *
     * @param rowNumber
     * @param hallId
     */
    @Override
    public void deletePriceForSelectedRowInHall(int rowNumber, int hallId) {
        INSTANCE.delete(rowNumber, hallId);
        LOG.info("Price Service delete price for row " + rowNumber + " in hall " + hallId);
    }
}
