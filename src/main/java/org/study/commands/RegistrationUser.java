package org.study.commands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.adminCommands.UpdateAdministratorCommand;
import org.study.facade.UserFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;
import org.study.utils.StringParser;

public class RegistrationUser implements Command {
    private static final Logger LOG = Logger.getLogger(UpdateAdministratorCommand.class);

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
        String userName = request.getParameter(ParametersNames.NAME);
        String userSurname = request.getParameter(ParametersNames.SURNAME);
        String gender = request.getParameter(ParametersNames.GENDER);
        String userLogin = request.getParameter(ParametersNames.LOGIN);
        String userEMailAddress = request.getParameter(ParametersNames.EMAIL);
        String userPassword = request.getParameter(ParametersNames.PASSWORD);
        if (checkParameters(userName, userSurname, userLogin, userEMailAddress, userPassword)) {
            userFacade.createNewUser(userName, userSurname, gender, userLogin, userEMailAddress, userPassword);
            LOG.info("Add new user was successful");
            return "pages/registration.jsp";
        }
        return "pages/401.jsp";
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

    /**
     * this method always return true for indicated command. This pages is in access for any unregistered user
     */
    @Override
    public boolean checkPermissions(HttpServletRequest request) {
        return true;
    }
}
