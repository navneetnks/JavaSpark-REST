package com.sqldexter.organization;

import com.google.gson.*;
import com.sqldexter.organization.model.Company;
import com.sqldexter.organization.model.Owner;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HOME on 12-02-2016.
 */
public class DataAccess {
    private static final String DATA_KEY="data";
    private static final String MESSAGE_KEY="message";
    private static final Map<String,Object> dataStore=new HashMap<String, Object>();
    public Object getAllCompany(){
        List<Object> companies=null;
        if(dataStore.size()>0)
            companies=new ArrayList<Object>();
        for(String key:dataStore.keySet()){
            companies.add(dataStore.get(key));
        }

        return wrapReturnData(companies,"success");
    }

    public Object createNewCompany(String dataStr, Response response){
        Company company=null;
        try{
            company=new Gson().fromJson(dataStr, Company.class);
        }catch (JsonSyntaxException e){
            response.status(400);
            return wrapReturnData(null,"Json parse error. Not a valid JSON. Message="+
                    e.getMessage());
        }
        if(!dataStore.containsKey(company.getId())) {
            dataStore.put(company.getId(), company);
            response.status(201);
            return wrapReturnData(null, "New Company Successfully created.");
        }else{
            response.status(501);
            return wrapReturnData(null,"Already company exists for id="+company.getId());
        }
    }
    public Object getCompanyDetail(String id,Response response){
        if(dataStore.containsKey(id))
            return wrapReturnData(dataStore.get(id),"success");
        else{
            response.status(404);
            return wrapReturnData(null,"No item found for id="+id);
        }
    }
    public Object updateCompany(String id,String dataStr,Response response){
        if(dataStore.containsKey(id)) {
            Company company=null;
            try{
                company=new Gson().fromJson(dataStr, Company.class);
            }catch (JsonSyntaxException e){
                response.status(400);
                return wrapReturnData(null,"Json parse error. Not a valid JSON. Message="+
                        e.getMessage());
            }
            dataStore.put(id,company);
            response.status(201);
            return wrapReturnData(null, "success");
        }
        else{
            response.status(404);
            return wrapReturnData(null,"No item found for id="+id);
        }
    }

    public Object addOwners(String id,String dataStr,Response response){
        if(dataStore.containsKey(id)) {
            Company company=null;
            try{
                JsonParser parser=new JsonParser();
                JsonArray array=parser.parse(dataStr).getAsJsonArray();
                for(JsonElement jsonOwner:array) {
                    Owner owner = new Owner((JsonObject)jsonOwner);
                    ((Company)dataStore.get(id)).add(owner);
                }
            }
            catch (JsonSyntaxException e){
                response.status(400);
                return wrapReturnData(null,"Json parse error. Not a valid JSON. Message="+
                        e.getMessage());
            }

            response.status(201);
            return wrapReturnData(null, "success");
        }
        else{
            response.status(404);
            return wrapReturnData(null,"No item found for id="+id);
        }
    }

    private Object wrapReturnData(Object inputObj, String message){
        Map<String,Object> wrappedData=new HashMap<String, Object>();
        if(inputObj!=null)
            wrappedData.put(DATA_KEY,inputObj);
        wrappedData.put(MESSAGE_KEY,message);
        return wrappedData;
    }
}
