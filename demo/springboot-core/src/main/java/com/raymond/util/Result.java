package com.raymond.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Result : 响应的结果对象
 */
public class Result implements Serializable ,Map<String, Object>{
    private static final long serialVersionUID = 6288374846131788743L;

    protected final Map<String, Object> map=new HashMap<String, Object>();
    /**
     * 信息
     */
    private String message="OK";

    /**
     * 状态码
     */
    private int statusCode=1;

    /**
     * 是否成功
     */
    private boolean success=true;

    public Result(){
    	map.put("message", message);
    	map.put("statusCode", statusCode);
    	map.put("success", success);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        map.put("message", message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        map.put("success", success);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        map.put("statusCode", statusCode);
    }
	@Override
	public void clear() {
		 map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		 return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		 return map.entrySet();
	}

	@Override
	public Object get(Object key) {
		 return map.get(key);
	}

	@Override
	public boolean isEmpty() {
		 return map.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		 return map.keySet();
	}

	@Override
	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		 map.putAll(m);
	}

	@Override
	public Object remove(Object key) {
		 return map.remove(key);
	}

	@Override
	public int size() {
		 return map.size();
	}

	@Override
	public Collection<Object> values() {
		return map.values();
	}
}
