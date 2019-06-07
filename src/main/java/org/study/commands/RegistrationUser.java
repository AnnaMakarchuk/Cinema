package org.study.commands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.adminCommands.UpdateAdministrator;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.StringParser;

public class RegistrationUser implements Command {
    private static final Logger LOG = Logger.getLogger(UpdateAdministrator.class);

    private UserFacade userFacade;
    private StringParser stringParser;

    public RegistrationUser() {
        this.userFacade = FacadeFactory.getInstance().getUserFacade();
        this.stringParser = StringParser.getInstance();
    }

    /**
     * this command receive new user datas and create user in database
     */
    @Override
    public String execute(HttpServletRequest request) {
        String userName = request.getParameter("name");
        String userSurname = request.getParameter("surname");
        String gender = request.getParameter("gender");
        String userLogin = request.getParameter("login");
        String userEMailAddress = request.getParameter("email");
        String userPassword = request.getParameter("password");
        if (checkParameters(userName, userSurname, userLogin, userEMailAddress, userPassword)) {
            userFacade.createNewUser(userName, userSurname, gender, userLogin, userEMailAddress, userPassword);
            LOG.info("Add new user was successful");
            return "pages/registration.jsp";
        }
        return "pages/errors/401.jsp";
    }

    private boolean checkParameters(String userName, String userSurname, String userLogin,
                                    String userEMailAddress, String userPassword) {
        boolean name = stringParser.checkNameSurname(userName);
        LOG.info("Name checked is " + name);
        boolean surname = stringParser.checkNameSurname(userSurname);
        LOG.info("Surname checked is " + surname);
        boolean login = stringParser.checkLoginPassword(userLogin);
        LOG.info("Login checked is " + login);
        boolean password = stringParser.checkLoginPassword(userPassword);
        LOG.info("Password checked is " + password);
        boolean email = stringParser.checkEMail(userEMailAddress);
        LOG.info("Email checked is " + email);
        return name && surname && login && password && email;
    }
}
