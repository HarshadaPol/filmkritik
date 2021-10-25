package com.Filmkritik.TMDB.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Filmkritik.authservice.service.AuthService;
import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.enumeration.SearchType;
import com.omertron.themoviedbapi.model.collection.Collection;
import com.omertron.themoviedbapi.model.company.Company;
import com.omertron.themoviedbapi.model.keyword.Keyword;
import com.omertron.themoviedbapi.model.list.UserList;
import com.omertron.themoviedbapi.model.media.MediaBasic;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.model.person.PersonFind;
import com.omertron.themoviedbapi.model.tv.TVBasic;
import com.omertron.themoviedbapi.results.ResultList;

@Service
public class MovieService {
	private static final Logger logger = Logger.getLogger(MovieService.class);

	@Autowired
	private TheMovieDbApi movieDB;

	public ResultList<MovieInfo> getTopRatedMovies() throws MovieDbException {
		return movieDB.getTopRatedMovies(1, "en-US");
	}
	
	public ResultList<MovieInfo> getPopularMovies() throws MovieDbException{
		return movieDB.getPopularMovieList(1, "en-US");
	}
	
	public ResultList<MovieInfo> getUpcomingMovies() throws MovieDbException{
		return movieDB.getUpcoming(1, "en-US");
	}
	
	public ResultList<MovieInfo> getNowShowing() throws MovieDbException{
		return movieDB.getNowPlayingMovies(1, "en-US");
	}

	public ResultList<Collection> searchCollection(String query, int pageNo) throws MovieDbException{
		return movieDB.searchCollection(query, pageNo, "en-US");
	}
	
	public ResultList<Company> searchCompany(String query, int pageNo) throws MovieDbException{
		return movieDB.searchCompanies(query, pageNo);
	}
	
	public ResultList<Keyword> searchKeyword(String query, int pageNo) throws MovieDbException{
		return movieDB.searchKeyword(query, pageNo);
	}
	
	public ResultList<UserList> searchList(String query, int pageNo, boolean includeAdult) throws MovieDbException{
		return movieDB.searchList(query, pageNo, includeAdult);
	}
	
	public ResultList<MovieInfo> searchMovie(String query, int pageNo, boolean includeAdult,int searchYr,int releaseYr,SearchType type ) throws MovieDbException{
		return movieDB.searchMovie(query, pageNo,"en-US", includeAdult,searchYr,releaseYr,type);
	}
	
	public ResultList<MediaBasic> searchMulti(String query, int pageNo, boolean includeAdult) throws MovieDbException{
		return movieDB.searchMulti(query, pageNo,"en-US", includeAdult);
	}
	
	public ResultList<PersonFind> searchPeople(String query, int pageNo, boolean includeAdult, SearchType type) throws MovieDbException{
		return movieDB.searchPeople(query, pageNo, includeAdult, type);//(query, pageNo,"en-US", includeAdult, type);
	}
	

	
}
