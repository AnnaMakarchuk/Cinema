package org.study.commands.adminCommands;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.MovieDto;
import org.study.facade.MovieFacade;
import org.study.factories.FacadeFactory;
import org.study.utils.ParametersNames;

public class ViewCancelledMovieCommand extends AbstractAdminCommand {
    private static final Logger LOG = Logger.getLogger(ViewCancelledMovieCommand.class);

    private MovieFacade movieFacade;

    public ViewCancelledMovieCommand() {
        this.movieFacade = FacadeFactory.getInstance().getMovieFacade();
    }

    /**
     * this method is a command for cancelled movie list
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<MovieDto> cancelledMovieDtoList = movieFacade.getAllMovies(false);
        LOG.info("List of non-active MovieDTO was selected");
        request.setAttribute(ParametersNames.MOVIES, cancelledMovieDtoList);
        return "pages/admin/admin_nonactive_movies.jsp";
    }
}
