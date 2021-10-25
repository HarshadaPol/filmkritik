package com.Filmkritik.TMDB.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.enumeration.SearchType;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.model.tv.TVBasic;
import com.omertron.themoviedbapi.model.tv.TVInfo;
import com.omertron.themoviedbapi.results.ResultList;
@Service
public class TvService {
	private static final Logger logger = Logger.getLogger(TvService.class);

	@Autowired
	private TheMovieDbApi movieDB;

	public ResultList<TVInfo> getTVTopRated() throws MovieDbException {
		return movieDB.getTVTopRated(1, "en-US");
	}
	
	public ResultList<TVInfo> getTVPopular() throws MovieDbException{
		return movieDB.getTVPopular(1, "en-US");
	}
	
	public TVInfo getLatestTV() throws MovieDbException{
		return movieDB.getLatestTV();
	}
	
	public ResultList<TVInfo> getTVAiringToday() throws MovieDbException{
		return movieDB.getTVAiringToday(1, "en-US", null);
	}
	public ResultList<TVBasic> searchTV(String query, int pageNo, int firstDateYr, SearchType type) throws MovieDbException{
		return movieDB.searchTV(query, pageNo,"en-US", firstDateYr, type);//(query, pageNo,"en-US", includeAdult, type);
	}
}
