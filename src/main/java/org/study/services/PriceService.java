package org.study.services;

public interface PriceService {

    double findPriceForSelectedRowInHall(int rowNumber, int hallId);

    void addNewPriceForSelectedRowInHall(int rowNumber, double price, int hallId);

    void setNewPriceForSelectedRowInHall(int rowNumber, double price, int hallId);

    void deletePriceForSelectedRowInHall(int rowNumber, int hallId);
}
