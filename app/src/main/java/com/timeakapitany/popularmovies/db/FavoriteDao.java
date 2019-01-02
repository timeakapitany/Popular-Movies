package com.timeakapitany.popularmovies.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.timeakapitany.popularmovies.movie.Movie;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorites(Movie... movies);

    @Delete
    void deleteFavorites(Movie... movies);

    @Query("SELECT * FROM Movie")
    List<Movie> loadAllFavorites();

    @Query("SELECT EXISTS (SELECT 1 FROM Movie WHERE id = :movieId )")
    Boolean isFavorite(Integer movieId);
}
