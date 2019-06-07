package org.study.services.impl;

import org.apache.log4j.Logger;
import org.study.DAO.AdministratorDAO;
import org.study.factories.DAOFactory;
import org.study.factories.DAOType;
import org.study.models.Administrator;
import org.study.services.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
    /**
     * create instance of DAO interface with type ADMINISTRATOR
     */
    private static final AdministratorDAO INSTANCE =
            (AdministratorDAO) DAOFactory.createDAO(DAOType.ADMINISTRATOR).create();
    private static final Logger LOG = Logger.getLogger(AdministratorServiceImpl.class);

    /**
     * this method show concrete admin
     *
     * @param adminId
     * @return user
     */
    @Override
    public Administrator viewAdmin(int adminId) {
        Administrator administrator = INSTANCE.get(adminId);
        LOG.info("AdministratorService get admin " + administrator.getUserLogin());
        return administrator;
    }

    /**
     * this method register new admin
     *
     * @param administrator
     */
    @Override
    public void registerNewAdmin(Administrator administrator) {
        INSTANCE.create(administrator);
        LOG.info("AdministratorService registered new admin " + administrator.getUserLogin());
    }

    /**
     * this method update admin data
     *
     * @param administrator
     */
    @Override
    public void updateAdminInformation(Administrator administrator) {
        INSTANCE.update(administrator);
        LOG.info("AdministratorService update data of admin " + administrator.getUserLogin());
    }

    /**
     * this method delete admin from the cinema base
     *
     * @param administrator
     */
    @Override
    public void deleteAdmin(Administrator administrator) {
        INSTANCE.delete(administrator);
        LOG.info("AdministratorService delete admin " + administrator.getUserLogin());
    }
}
