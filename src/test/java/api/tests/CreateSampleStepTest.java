package api.tests;

import api.requests.common.ExcelOperation.ExcelReader;
import api.requests.common.SequenceUtility.SequenceUtility;
import api.requests.common.TokenUtility.TokenUtility;
import com.codoid.products.exception.FilloException;
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

public class CreateSampleStepTest extends base {
    @DataProvider(name = "create_sample_step_with_valid_token")
    public Object[][] data_provider() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Create_Sequence_Steps_Sample_Test_case.xlsx", 0);
        return data;
    }

    //---------------------------------- GET THE TEST DATA FOR VALID TOKEN --------------------------------------------//

    @Test(dataProvider = "create_sample_step_with_valid_token")
    public void create_sample_step_with_valid_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String SHaccount2, String Ownership, String Role2, String Status_Code, String Schema) throws FilloException {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        ExcelReader reader = new ExcelReader();
        SequenceUtility sequence_utils = new SequenceUtility();

        Map params = new HashMap();
        params.put("Subscription_State", Subscription_State);
        params.put("Plan", Plan);
        params.put("Role", Role1);
        params.put("SHaccount1", SHaccount1);

        Map params1 = new HashMap();
        params1.put("Subscription_State", Subscription_State);
        params1.put("Plan", Plan);
        params1.put("Role", Role2);
        params1.put("SHaccount1", SHaccount2);

        logs.test_step("----------------------------------------- GET THE SEQUENCE FROM ROLE1 --------------------------------------------");
        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", sequence_utils.getSequenceId(reader.getRecordsSet("Credential.xlsx","Sheet1",params1)));

        JSONObject queryParameters = new JSONObject();

        logs.test_step("----------------------------------------- GET THE TOKEN FROM ROLE2 --------------------------------------------");
        JSONObject header = new JSONObject();
        header.put("Authorization",reader.readFromToExcel(new File("src/test/java/data/Credential.xlsx"),"Sheet1",params1,"Token"));
        header.put("content-type", "application/json");

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------CREATE SEQUENCE SAMPLE STEP--------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);

        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(200);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

        logs.test_step("------------------------------------------ VALIDATE SCHEMA --------------------------------------------");
        
try{
validator.validateSchema(Schema);}
  catch(Exception PathNotFoundException){}
    }

    //---------------------------------- GET THE TEST DATA FOR VALID TOKEN AND FREE PLAN TEST CASES --------------------------------------------//

    @DataProvider(name = "create_sample_step_with_valid_token_free_plan")
    public Object[][] data_provider1() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Create_Sequence_Steps_Sample_Test_case.xlsx", 1);
        return data;
    }


    @Test(dataProvider = "create_sample_step_with_valid_token_free_plan")
    public void create_sample_step_with_valid_token_free_plan(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String SHaccount2, String Ownership, String Role2, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        ExcelReader reader = new ExcelReader();

        Map params = new HashMap();
        params.put("Subscription_State", Subscription_State);
        params.put("Plan", Plan);
        params.put("Role", Role1);
        params.put("SHaccount1", SHaccount1);

        Map params1 = new HashMap();
        params1.put("Subscription_State", Subscription_State);
        params1.put("Plan", Plan);
        params1.put("Role", Role2);
        params1.put("SHaccount1", SHaccount2);

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", "4556");

        JSONObject queryParameters = new JSONObject();

        logs.test_step("----------------------------------------- GET THE TOKEN FROM ROLE1 --------------------------------------------");
        JSONObject header = new JSONObject();
        header.put("Authorization",reader.readFromToExcel(new File("src/test/java/data/Credential.xlsx"),"Sheet1",params1,"Token"));
        header.put("content-type", "application/json");

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------CREATE SEQUENCE SAMPLE STEP--------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step", pathParameter, queryParameters, header, body);
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

    //---------------------------------- GET THE TEST DATA FOR content-type TOKEN-------------------------------------------//
    @DataProvider(name = "create_sample_step_with_content-type_token")
    public Object[][] data_provider2() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Create_Sequence_Steps_Sample_Test_case.xlsx", 2);
        return data;
    }


    @Test(dataProvider = "create_sample_step_with_content-type_token")
    public void create_sample_step_with_content_type_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        Map params1 = new HashMap();
        params1.put("Subscription_State", Subscription_State);
        params1.put("Plan", Plan);
        params1.put("Role", Role1);
        params1.put("SHaccount1", SHaccount1);

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", "4556");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
        header.put("content-type", "application/json");

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------CREATE SEQUENCE SAMPLE STEP--------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step", pathParameter, queryParameters, header, body);
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

    //---------------------------------- GET THE TEST DATA FOR BLANK TOKEN------------------------------------------//
    @DataProvider(name = "create_sample_step_with_blank_token")
    public Object[][] data_provider4() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Create_Sequence_Steps_Sample_Test_case.xlsx", 3);
        return data;
    }

    @Test(dataProvider = "create_sample_step_with_blank_token")
    public void create_sample_step_with_blank_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        Map params1 = new HashMap();
        params1.put("Subscription_State", Subscription_State);
        params1.put("Plan", Plan);
        params1.put("Role", Role1);
        params1.put("SHaccount1", SHaccount1);

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", "4566");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization", "");
        header.put("content-type", "application/json");

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------CREATE SEQUENCE SAMPLE STEP--------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step", pathParameter, queryParameters, header, body);
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

    //---------------------------------- GET THE TEST DATA FOR BLANK TOKEN------------------------------------------//
    @DataProvider(name = "create_sample_step_with_expired_token_data_provider")
    public Object[][] data_provider5() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Create_Sequence_Steps_Sample_Test_case.xlsx", 4);
        return data;
    }

    @Test(dataProvider = "create_sample_step_with_expired_token_data_provider")
    public void create_sample_step_with_expired_token_data_provider(String Token_Type,String Token, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        Map params1 = new HashMap();
        params1.put("Subscription_State", Subscription_State);
        params1.put("Plan", Plan);
        params1.put("Role", Role1);
        params1.put("SHaccount1", SHaccount1);

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", "4566");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",Token);
        header.put("content-type", "application/json");

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------ CREATE SEQUENCE SAMPLE STEP --------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step", pathParameter, queryParameters, header, body);
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


    @DataProvider(name = "create_sample_step_with_different_sequence_id_data_provider")
    public Object[][] data_provider6() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Sequence_Path_Parameter_TEST_CASE.xlsx", 0);
        return data;
    }
    @Test(dataProvider ="create_sample_step_with_different_sequence_id_data_provider")
    public void create_sample_step_with_different_sequence_id_data_provider(String Id,String Filter_Name, String Value, String status_code) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", Value);

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization", utils.getTokenFromUser());
        header.put("content-type", "application/json");

        JSONObject body = new JSONObject();

        logs.test_step("------------------------------------------ CREATE SEQUENCE SAMPLE STEP --------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);

        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }

    @Test
    public void create_sample_step_with_blank_sequence_id() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", "");

        JSONObject queryParameters = new JSONObject();
        JSONObject body = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/json");

        logs.test_step("------------------------------------------GET THE ACCOUNT --------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(404);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }
    @Test
    public void create_sample_step_different_content_type() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", "4566");

        JSONObject queryParameters = new JSONObject();
        JSONObject body = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/zip");

        logs.test_step("------------------------------------------GET THE ACCOUNT --------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }


    @Test
    public void create_sample_step_different_method_name() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("sequenceId", "4566");

        JSONObject queryParameters = new JSONObject();

        JSONObject body = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/json");

        logs.test_step("------------------------------------------GET THE ACCOUNT --------------------------------------------");
        String update_file = builder.updateRequestObject("sequence/create_sample_step/create_sample_step_with_different_method", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }




}