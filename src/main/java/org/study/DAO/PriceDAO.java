package org.study.DAO;

public interface PriceDAO {

    double get(int rowNumber, int hallId);

    void create(int rowNumber, double price, int hallId);

    void update(int rowNumber, double price, int hallId);

    void delete(int rowNumber, int hallId);

}
