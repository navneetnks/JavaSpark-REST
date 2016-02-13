package com.sqldexter.organization;
import spark.*;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
/**
 * Created by Navneet on 12-02-2016.
 */

public class Controller {
    private static final Map<String,Object> dataStore=new HashMap<String, Object>();
    public Controller(final DataAccess apiService){
        // server setting
//        setIpAddress("127.0.0.1");
//        setPort(8082);

        // creating filter for authorized users before handling the request
        Spark.before(new Filter() {
            public void handle(Request request, Response response) throws Exception {
                String method = request.requestMethod();
                if (method.equals("POST") || method.equals("PUT") || method.equals("GET")
                        || method.equals("PATCH") || method.equals("DELETE")) {
                    String authentication = request.headers("Authentication");
                    if (!"PASSWORD".equals(authentication)) {
                        Spark.halt(401, "User Unauthorized");
                    }
                }
            }
        });

//        get list of all companies
        get("/get_all_companies", new Route() {
            public Object handle(Request request, Response response) {
                return apiService.getAllCompany();
            }
        }, JsonUtil.json());

//      create new company
        post("/create_company", new Route() {
            public Object handle(Request request, Response response) {
                return apiService.createNewCompany(request.body(),response);
            }
        },JsonUtil.json());

        get("/company_detail/:id", new Route() {
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                return apiService.getCompanyDetail(id, response);
            }
        },JsonUtil.json());

        put("/update_company/:id", new Route() {
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                return apiService.updateCompany(id, request.body(), response);
            }
        }, JsonUtil.json());

        patch("/add_owners/:id", new Route() {
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                return apiService.addOwners(id, request.body(), response);
            }
        }, JsonUtil.json());


        // setting  response type as "application/json". It facilitates proper parsing
        // of response data at client end
        after(new Filter() {
            public void handle(Request request, Response response) throws Exception {
                response.type("application/json");
            }
        });



//      for CORS(cross origin resource sharing)
        Spark.options("/*", new Route() {
            public Object handle(Request request, Response response) {
                String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                }

                String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                if(accessControlRequestMethod != null){
                    response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                }

                return "OK";
            }
        });
        Spark.before(new Filter() {
            public void handle(Request request, Response response) throws Exception {
                response.header("Access-Control-Allow-Origin", "*");


            }
        });
    }


}
