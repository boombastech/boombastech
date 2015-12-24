package uk.co.boombastech.cache;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class RedisCache<T> implements Cache<String, T> {

	private static final String COLON = ":";

	private final Jedis jedis;

	protected RedisCache(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public T get(String key) {
		Map<String, String> hashValues = jedis.hgetAll(createRedisKey(key));
		return toObject(hashValues);
	}

	public Optional<String> get(String key, String field) {
		List<String> hmget = jedis.hmget(createRedisKey(key), field);
		return hmget.stream().findFirst();
	}

	public void update(String key, String field, String value) {
		jedis.hset(createRedisKey(key), field, value);
	}

	@Override
	public void add(String key, T value) {
		jedis.hmset(createRedisKey(key), toHashValues(value));
	}

	@Override
	public boolean contains(String key) {
		return jedis.exists(createRedisKey(key));
	}

	@Override
	public void remove(String key) {
		jedis.del(createRedisKey(key));
	}

	protected abstract String objectType();

	protected abstract Map<String, String> toHashValues(T value);

	protected abstract T toObject(Map<String, String> hashValues);

	private String createRedisKey(String key) {
		return objectType() + COLON + key;
	}
}