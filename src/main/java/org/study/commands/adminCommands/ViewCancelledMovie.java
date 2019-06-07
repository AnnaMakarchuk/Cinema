package org.study.commands.adminCommands;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.dto.MovieDto;
import org.study.facade.MovieFacade;
import org.study.factories.FacadeFactory;

public class ViewCancelledMovie implements Command {
    private static final Logger LOG = Logger.getLogger(ViewCancelledMovie.class);

    private MovieFacade movieFacade;

    public ViewCancelledMovie() {
        this.movieFacade = FacadeFactory.getInstance().getMovieFacade();
    }

    /**
     * this method is a command for cancelled movie list
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<MovieDto> cancelledMovieDtoList = movieFacade.getAllMovies(false);
        LOG.info("List of non-active MovieDTO was selected");
        request.setAttribute("movies", cancelledMovieDtoList);
        return "pages/admin/admin_nonactive_movies.jsp";
    }
}
