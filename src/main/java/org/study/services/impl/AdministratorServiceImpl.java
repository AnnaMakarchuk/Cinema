package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.dao.AdministratorDao;
import org.study.factories.DaoFactory;
import org.study.models.Administrator;
import org.study.services.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
    private static final Logger LOG = Logger.getLogger(AdministratorServiceImpl.class);

    private AdministratorDao administratorDao;

    public AdministratorServiceImpl() {
        this.administratorDao = DaoFactory.getInstance().getAdministratorDao();
    }

    /**
     * this method show concrete admin
     */
    @Override
    public Administrator viewAdmin(int adminId) {
        Administrator administrator = administratorDao.get(adminId);
        LOG.info("AdministratorService get admin with login " + administrator.getUserLogin());
        return administrator;
    }

    /**
     * this method update admin data
     */
    @Override
    public Administrator getUpdateAdministrator(int adminId, String adminLogin, String adminPassword) {
        administratorDao.update(adminId, adminLogin, adminPassword);
        LOG.info("AdministratorService updateIsActive data of admin with id " + adminId);
        Administrator administrator = administratorDao.get(adminId);
        LOG.info("AdministratorService getUserById updated admin " + adminId);
        return administrator;
    }
}
