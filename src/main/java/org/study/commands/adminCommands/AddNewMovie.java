package org.study.commands.adminCommands;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.facade.MovieFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.StringParser;

public class AddNewMovie implements Command {
    private static final Logger LOG = Logger.getLogger(AddNewMovie.class);

    private MovieFacade movieFacade;
    private StringParser string_parser;

    public AddNewMovie() {
        this.movieFacade = FacadeFactory.getInstance().getMovieFacade();
        this.string_parser = StringParser.getInstance();
    }

    /**
     * this method show all movies and give oportuniti to add movie
     */
    @Override
    public String execute(HttpServletRequest request) {
        String movieName = request.getParameter("name");
        String movieGenre = request.getParameter("movie.genre");
        int movieDuration = Integer.parseInt(request.getParameter("duration"));
        int ageLimit = Integer.parseInt(request.getParameter("age"));
        String movieDescription = request.getParameter("description");

        if (checkParameters(movieName, movieDescription, movieDuration, ageLimit)) {
            movieFacade.createNewMovie(movieName, movieGenre, movieDuration, ageLimit, movieDescription);
            LOG.info("Movie with define parameters was added");
            return "pages/admin/admin_movieadded.jsp";
        } else
            return "pages/errors/401.jsp";
    }

    private boolean checkParameters(String movieName, String movieDescription, int movieDuration, int ageLimit) {
        boolean name = string_parser.checkMovieNameDescription(movieName);
        LOG.info("Movie name checked is " + name);
        boolean description = string_parser.checkMovieNameDescription(movieDescription);
        LOG.info("Movie dutarion checked is " + description);
        boolean duration = string_parser.checkMovieDuration(String.valueOf(movieDuration));
        LOG.info("Movie name is  checked" + duration);
        boolean age = string_parser.checkMovieAge(String.valueOf(ageLimit));
        LOG.info("Movie name is checked" + age);
        return name && description && duration && age;
    }
}
