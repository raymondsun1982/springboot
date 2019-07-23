package com.raymond.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

public class JsonUtil {
	public static String toJSONString(Object object, SerializerFeature... features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr[] = features;
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			SerializerFeature feature = arr[i];
			serializer.config(feature, true);
		}

		serializer.getValueFilters().add(new ValueFilter() {
			public Object process(Object obj, String s, Object value) {
				if (null != value) {
					if (value instanceof Date) {
						return new SimpleDateFormat("").format(value);
					}
					return value;
				} else {
					return "";
				}
			}
		});
		serializer.write(object);
		s = out.toString();
		out.close();
		return s;
	}
}
