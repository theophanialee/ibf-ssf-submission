package sg.edu.nus.iss.ibfb4ssfassessment.repo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.ibfb4ssfassessment.util.Util;

@Repository
public class MoviesRepo {

    @Autowired
    @Qualifier(Util.REDIS_STRING)
    RedisTemplate<String, String> template;

    HashOperations<String, String, String> hashOps;

    public void saveMovie(Integer movieId, String movieStr) {
        hashOps = template.opsForHash();
        hashOps.putIfAbsent(Util.KEY_MOVIES, Integer.toString(movieId), movieStr);
    }

    // READ (from Redis Map)
    public Map<String, String> getAllMovies() {
        hashOps = template.opsForHash();
        return hashOps.entries(Util.KEY_MOVIES);
    }

    // READ one specific record (from Redis Map)
    public String getMovieById(Integer movieId) {
        hashOps = template.opsForHash();
        return hashOps.get(Util.KEY_MOVIES, Integer.toString(movieId));
    }

    // UPDATE a specific record (in Redis Map)
    public void updateMovie(Integer movieId, String movieStr) {
        hashOps = template.opsForHash();
        hashOps.put(Util.KEY_MOVIES, Integer.toString(movieId), movieStr);
    }

}
