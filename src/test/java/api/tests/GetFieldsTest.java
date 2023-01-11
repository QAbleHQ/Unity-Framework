package api.tests;

import api.requests.common.ExcelOperation.ExcelReader;
import api.requests.common.TokenUtility.TokenUtility;
import com.codoid.products.exception.FilloException;
import io.unity.performaction.autoweb.testng_logs;
import io.unity.framework.init.base;
import io.unity.framework.readers.DataReader;
import io.unity.performaction.autoapi.RequestBuilder;
import io.unity.performaction.autoapi.ResponseValidator;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GetFieldsTest extends base {

    //---------------------------------- GET THE TEST DATA FOR VALID TOKEN --------------------------------------------//

    @DataProvider(name = "get_fields_with_valid_token_data_provider")
    public Object[][] data_provider() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Get_Fields_Test_case.xlsx", 0);
        return data;
    }
    @Test(dataProvider = "get_fields_with_valid_token_data_provider")
    public void get_fields_with_valid_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String SHaccount2, String Ownership, String Role2, String Status_Code, String Schema) throws ParseException {

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

        logs.test_step("------------------------------------------ GET THE FIELDS FOR ROLE1--------------------------------------------");
        String response = builder.updateRequestObject("Fields/get_fields/get_fields_with_valid_token", pathParameter, queryParameters, header, body);
        JSONObject response1 = builder.performRequest(response);

        ResponseValidator validator = new ResponseValidator(response1);

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
        header1.put("content-type", Content_Type);

        JSONObject body1 = new JSONObject();

        logs.test_step("------------------------------------------ GET THE FIELDS FROM ROLE2--------------------------------------------");
        String response2 = builder.updateRequestObject("Fields/get_fields/get_fields_with_valid_token", pathParameter1, queryParameters1, header1, body1);
        JSONObject response3 = builder.performRequest(response2);

        ResponseValidator validator1 = new ResponseValidator(response3);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator1.statusCodeShouldBe(200);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator1.validateResponseTime((Long)response3.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        validator1.validateSchema(Schema);

        logs.test_step("------------------------------------------ VALIDATE ROLE1 FIELDS ID AND ROLE2 FIELDS ID --------------------------------------------");
        assertThat(new ArrayList<String>(Arrays.asList(validator.getDataFromBody("$.payload[*].id").split(","))).contains(new ArrayList<String>(Arrays.asList(validator1.getDataFromBody("$.payload[*].id").split(","))))).isFalse();

    }


    //---------------------------------- GET THE TEST DATA FOR INVALID TOKEN-------------------------------------------//

    @DataProvider(name = "get_fields_with_invalid_token_data_provider")
    public Object[][] data_provider2() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Get_Fields_Test_case.xlsx", 1);
        return data;
    }

    @Test(dataProvider = "get_fields_with_invalid_token_data_provider")
    public void get_fields_with_invalid_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();


        JSONObject pathParameter = new JSONObject();

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjE4OTQsImlhdCI6MTY2MDc0MTk5NywiZXhwIjoxNjYzMzMzOTk3fQ.1uNPfk7_f45MCaGTIFJdtqYtCVrFhH4H1ZZCc7CSGUU" );
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------ GET THE FIELDS FROM ROLE1--------------------------------------------");
        String response = builder.updateRequestObject("Fields/get_fields/get_fields_with_valid_token", pathParameter, queryParameters, header, body);
        JSONObject response1 = builder.performRequest(response);

        ResponseValidator validator = new ResponseValidator(response1);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response1.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        
try{
validator.validateSchema(Schema);}
  catch(Exception PathNotFoundException){}

    }

    //---------------------------------- GET THE TEST DATA FOR BLANK TOKEN --------------------------------------------//

    @DataProvider(name = "get_fields_with_blank_token_data_provider")
    public Object[][] data_provider3() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Get_Fields_Test_case.xlsx", 2);
        return data;
    }


    @Test(dataProvider = "get_fields_with_blank_token_data_provider")
    public void get_fields_with_blank_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        JSONObject pathParameter = new JSONObject();

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization", "");
        header.put("content-type", Content_Type);


        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------ GET THE FIELDS FROM ROLE1--------------------------------------------");
        String response = builder.updateRequestObject("Fields/get_fields/get_fields_with_valid_token", pathParameter, queryParameters, header, body);
        JSONObject response1 = builder.performRequest(response);

        ResponseValidator validator = new ResponseValidator(response1);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response1.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        
try{
validator.validateSchema(Schema);}
  catch(Exception PathNotFoundException){}
    }

    @DataProvider(name = "get_fields_with_expired_token_data_provider")
    public Object[][] data_provider4() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Get_Fields_Test_case.xlsx", 3);
        return data;
    }


    @Test(dataProvider = "get_fields_with_expired_token_data_provider")
    public void get_fields_with_expired_token(String Token_Type,String Token, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        JSONObject pathParameter = new JSONObject();

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",Token);
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------ GET THE FIELDS FROM ROLE1--------------------------------------------");
        String response = builder.updateRequestObject("Fields/get_fields/get_fields_with_valid_token", pathParameter, queryParameters, header, body);
        JSONObject response1 = builder.performRequest(response);

        ResponseValidator validator = new ResponseValidator(response1);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response1.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        
try{
validator.validateSchema(Schema);}
  catch(Exception PathNotFoundException){}
    }

    @DataProvider(name = "get_fields_with_valid_prospect_type_data_provider")
    public Object[][] data_provider5() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Prospect_Type_FUNCTIONAL_TEST_CASE.xlsx", 0);
        return data;
    }


    @Test(dataProvider = "get_fields_with_valid_prospect_type_data_provider")
    public void get_fields_with_valid_prospect_type(String Id,String Filter_Name, String Value, String status_code) throws FilloException {

        testng_logs logs = new testng_logs();

        RequestBuilder builder = new RequestBuilder();
        TokenUtility get_token = new TokenUtility();

        JSONObject pathParameter = new JSONObject();

        JSONObject queryParameter = new JSONObject();
        queryParameter.put("ProspectType",Value);

        JSONObject header = new JSONObject();
        header.put("Authorization",get_token.getTokenFromUser());
        header.put("content-type","application/json");

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------ GET THE FIELDS--------------------------------------------");
        String update_file = builder.updateRequestObject("Fields/get_fields/get_fields_with_valid_token",pathParameter,queryParameter,header, body);
        JSONObject response = builder.performRequest(update_file);

        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(200);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }

    @DataProvider(name = "get_fields_with_invalid_prospect_type_data_provider")
    public Object[][] data_provider6() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Prospect_Type_FUNCTIONAL_TEST_CASE.xlsx", 1);
        return data;
    }


    @Test(dataProvider = "get_fields_with_invalid_prospect_type_data_provider")
    public void get_fields_with_invalid_prospect_type(String Id,String Filter_Name, String Value, String status_code) throws FilloException {

        testng_logs logs = new testng_logs();

        RequestBuilder builder = new RequestBuilder();
        TokenUtility get_token = new TokenUtility();

        JSONObject pathParameter = new JSONObject();

        JSONObject queryParameter = new JSONObject();
        queryParameter.put("ProspectType",Value);

        JSONObject header = new JSONObject();
        header.put("Authorization",get_token.getTokenFromUser());
        header.put("content-type","application/json");

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------GET THE FIELDS--------------------------------------------");
        String update_file = builder.updateRequestObject("Fields/get_fields/get_fields_with_valid_token",pathParameter,queryParameter,header, body);
        JSONObject response = builder.performRequest(update_file);

        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }

    @Test
    public void get_fields_with_different_content_type() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();
        JSONObject pathParameter = new JSONObject();
        JSONObject queryParameters = new JSONObject();

        JSONObject body = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/zip");

        logs.test_step("------------------------------------------GET THE FIELDS--------------------------------------------");
        String update_file = builder.updateRequestObject("Fields/get_fields/get_fields_with_valid_token", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }


    @Test
    public void get_fields_with_different_method_name() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();
        JSONObject pathParameter = new JSONObject();
        JSONObject queryParameters = new JSONObject();

        JSONObject body = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/json");

        logs.test_step("------------------------------------------GET THE FIELDS--------------------------------------------");
        String update_file = builder.updateRequestObject("Fields/get_fields/get_fields_with_different_method", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }



}
