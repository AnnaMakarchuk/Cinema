package org.study.commands.adminCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.facade.MovieFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;
import org.study.utils.StringParser;

public class AddNewMovieCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(AddNewMovieCommand.class);

    private MovieFacade movieFacade;
    private StringParser stringParser;

    public AddNewMovieCommand() {
        this.movieFacade = FacadeFactory.getInstance().getMovieFacade();
        this.stringParser = StringParser.getInstance();
    }

    /**
     * this method show all movies and give oportuniti to add movie
     */
    @Override
    public String execute(HttpServletRequest request) {
        String movieName = request.getParameter(ParametersNames.MOVIE_NAME);
        String movieGenre = request.getParameter(ParametersNames.MOVIE_GENRE);
        int movieDuration = Integer.parseInt(request.getParameter(ParametersNames.DURATION));
        int ageLimit = Integer.parseInt(request.getParameter(ParametersNames.AGE));
        String movieDescription = request.getParameter(ParametersNames.DESCRIPTION);
        if (isNulls(movieName, movieDescription)) {
            return "pages/client/client_account_update.jsp";
        } else if (checkParameters(movieName, movieDescription, movieDuration, ageLimit)) {
            movieFacade.createNewMovie(movieName, movieGenre, movieDuration, ageLimit, movieDescription);
            LOG.info("Movie with define parameters was added");
            return "pages/admin/admin_movieadded.jsp";
        } else
            return "pages/401.jsp";
    }

    private boolean checkParameters(String movieName, String movieDescription, int movieDuration, int ageLimit) {
        boolean name = stringParser.checkMovieNameDescription(movieName);
        LOG.info("Movie name checked is " + name);
        boolean description = stringParser.checkMovieNameDescription(movieDescription);
        LOG.info("Movie dutarion checked is " + description);
        boolean duration = stringParser.checkMovieDuration(String.valueOf(movieDuration));
        LOG.info("Movie name is  checked" + duration);
        boolean age = stringParser.checkMovieAge(String.valueOf(ageLimit));
        LOG.info("Movie name is checked" + age);
        return name && description && duration && age;
    }

    private boolean isNulls(String movieName, String movieDescription) {
        return Objects.isNull(movieName) || Objects.isNull(movieDescription);
    }
}
