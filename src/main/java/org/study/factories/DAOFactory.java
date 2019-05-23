package org.study.factories;

public class DAOFactory {

    private static final AdministratorDAOFactory administratorDAO = AdministratorDAOFactory.getInstance();
    private static final UserDAOFactory userDAO = UserDAOFactory.getInstance();
    private static final UserRoleDAOFactory userRoleDAO = UserRoleDAOFactory.getInstance();
    private static final SessionScheduleDAOFactory sessionScheduleDAO = SessionScheduleDAOFactory.getInstance();
    private static final HallDAOFactory hallDAO = HallDAOFactory.getInstance();
    private static final MovieDAOFactory movieDAO = MovieDAOFactory.getInstance();
    private static final MovieGenreDAOFactory movieGenreDAO = MovieGenreDAOFactory.getInstance();
    private static final TicketDAOFactory ticketDAO = TicketDAOFactory.getInstance();
    private static final PriceDAOFactory priceDAO = PriceDAOFactory.getInstance();

    public static DAOFactories createDAO(DAOType typeDAO) {
        switch (typeDAO) {
            case ADMINISTRATOR:
                return administratorDAO;
            case USER:
                return userDAO;
            case USERROLE:
                return userRoleDAO;
            case SCHEDULE:
                return sessionScheduleDAO;
            case HALL:
                return hallDAO;
            case MOVIE:
                return movieDAO;
            case MOVIEGENRE:
                return movieGenreDAO;
            case TICKET:
                return ticketDAO;
            case PRICE:
                return priceDAO;
        }
        throw new IllegalArgumentException();
    }
}
