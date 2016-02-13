package com.sqldexter.organization;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by HOME on 12-02-2016.
 */
public class JsonUtil {

        public static String toJson(Object object) {

            return new Gson().toJson(object);

        }

        public static ResponseTransformer json() {

            return new ResponseTransformer() {
                public String render(Object model) throws Exception {
                    return JsonUtil.toJson(model);
                }
            };

        }


}
