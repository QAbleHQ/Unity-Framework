package api.tests;

import api.requests.common.ExcelOperation.ExcelReader;
import api.requests.common.TokenUtility.TokenUtility;
import io.unity.performaction.autoweb.testng_logs;
import io.unity.framework.init.base;
import io.unity.framework.readers.DataReader;
import io.unity.performaction.autoapi.RequestBuilder;
import io.unity.performaction.autoapi.ResponseValidator;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GetAccountTest extends base {
    @DataProvider(name = "get_account_with_valid_token_data_provider")
    public Object[][] data_provider() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Get_Accounts_Test_case.xlsx", 0);
        return data;
    }

    //---------------------------------- GET THE TEST DATA FOR VALID TOKEN --------------------------------------------//

    @Test(dataProvider = "get_account_with_valid_token_data_provider")
    public void get_account_with_valid_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String SHaccount2, String Ownership, String Role2, String Status_Code, String Schema){
        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        ExcelReader reader = new ExcelReader();

        Map params = new HashMap();
        params.put("Subscription_State", Subscription_State);
        params.put("Plan", Plan);
        params.put("Role", Role1);
        params.put("SHaccount1", SHaccount1);
        JSONObject pathParameter = new JSONObject();

        JSONObject queryParameters = new JSONObject();

        logs.test_step("----------------------------------------- GET THE TOKEN FROM ROLE1 --------------------------------------------");
        JSONObject header = new JSONObject();
        header.put("Authorization",reader.readFromToExcel(new File("src/test/java/data/Credential.xlsx"),"Sheet1",params,"Token"));
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------ GET THE ACCOUNT FROM ROLE1 --------------------------------------------");
        String update_file = builder.updateRequestObject("Accounts/get_accounts/get_accounts", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);

        ResponseValidator validator = new ResponseValidator(response);

        Map params1 = new HashMap();
        params1.put("Subscription_State", Subscription_State);
        params1.put("Plan", Plan);
        params1.put("Role", Role2);
        params1.put("SHaccount1", SHaccount2);
        JSONObject pathParameter1 = new JSONObject();

        JSONObject queryParameters1 = new JSONObject();

        logs.test_step("----------------------------------------- GET THE TOKEN FROM ROLE2 --------------------------------------------");
        JSONObject header1 = new JSONObject();
        header1.put("Authorization",reader.readFromToExcel(new File("src/test/java/data/Credential.xlsx"),"Sheet1",params1,"Token"));
        header1.put("Content_Type", Content_Type);

        JSONObject body1 = new JSONObject();

        logs.test_step("------------------------------------------ GET THE ACCOUNT FROM ROLE2 --------------------------------------------");
        String update_file_role2 = builder.updateRequestObject("Accounts/get_accounts/get_accounts", pathParameter1, queryParameters1, header1, body1);
        JSONObject response_role2 = builder.performRequest(update_file_role2);

        ResponseValidator validator1 = new ResponseValidator(response_role2);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator1.statusCodeShouldBe(200);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator1.validateResponseTime((Long)response_role2.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        validator1.validateSchema(Schema);

    }

    //---------------------------------- GET THE TEST DATA FOR INVALID TOKEN-------------------------------------------//

    @DataProvider(name = "get_account_with_invalid_token_data_provider")
    public Object[][] data_provider2() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Get_Accounts_Test_case.xlsx", 1);
        return data;
    }

    @Test(dataProvider = "get_account_with_invalid_token_data_provider")
    public void get_account_with_invalid_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {
        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        JSONObject pathParameter = new JSONObject();
        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjE4OTQsImlhdCI6MTY2MDc0MTk5NywiZXhwIjoxNjYzMzMzOTk3fQ.1uNPfk7_f45MCaGTIFJdtqYtCVrFhH4H1ZZCc7CSGUU" );
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------ GET THE ACCOUNT FROM ROLE1 --------------------------------------------");
        String update_file = builder.updateRequestObject("Accounts/get_accounts/get_accounts", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);

        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        
try{
validator.validateSchema(Schema);}
  catch(Exception PathNotFoundException){}

    }

    //---------------------------------- GET THE TEST DATA FOR BLANK TOKEN --------------------------------------------//

    @DataProvider(name = "get_account_with_blank_token_data_provider")
    public Object[][] data_provider3() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Get_Accounts_Test_case.xlsx", 2);
        return data;
    }


    @Test(dataProvider = "get_account_with_blank_token_data_provider")
    public void get_account_with_blank_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        JSONObject pathParameter = new JSONObject();

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------GET THE ACCOUNT FROM ROLE1--------------------------------------------");
        String update_file = builder.updateRequestObject("Accounts/get_accounts/get_accounts", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);

        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        
try{
validator.validateSchema(Schema);}
  catch(Exception PathNotFoundException){}
    }

    @DataProvider(name = "get_account_with_expired_token_data_provider")
    public Object[][] data_provider4() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Get_Accounts_Test_case.xlsx", 3);
        return data;
    }


    @Test(dataProvider = "get_account_with_expired_token_data_provider")
    public void get_account_with_expired_token(String Token_Type,String Token, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        JSONObject pathParameter = new JSONObject();

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",Token);
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------GET THE ACCOUNT FROM ROLE1--------------------------------------------");
        String update_file = builder.updateRequestObject("Accounts/get_accounts/get_accounts", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);

        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        
try{
validator.validateSchema(Schema);}
  catch(Exception PathNotFoundException){}
    }

    @Test
    public void get_account_with_different_content_type() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        JSONObject queryParameters = new JSONObject();
        JSONObject body = new JSONObject();
        JSONObject header = new JSONObject();

        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/zip");

        logs.test_step("------------------------------------------GET THE ACCOUNT--------------------------------------------");
        String update_file = builder.updateRequestObject("Accounts/get_accounts/get_accounts", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }


    @Test
    public void get_account_with_different_method_name() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        JSONObject queryParameters = new JSONObject();
        JSONObject body = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/json");

        logs.test_step("------------------------------------------GET THE ACCOUNT--------------------------------------------");
        String update_file = builder.updateRequestObject("Accounts/get_accounts/get_accounts_with_different_method", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(404);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }
}
