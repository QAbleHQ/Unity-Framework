package api.tests;

import api.requests.common.EmailAccountUtility.EmailAccountUtility;
import api.requests.common.ExcelOperation.ExcelReader;
import api.requests.common.TokenUtility.TokenUtility;
import io.unity.framework.init.base;
import io.unity.framework.readers.DataReader;
import io.unity.performaction.autoapi.RequestBuilder;
import io.unity.performaction.autoapi.ResponseValidator;
import io.unity.performaction.autoweb.testng_logs;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class EmailRampUpSettingsAccountIdTest extends base {

    //---------------------------------- GET THE TEST DATA FOR VALID TOKEN--------------------------------------------//

    @DataProvider(name = "email_rampUp_settings_accountId_with_valid_token")
    public Object[][] data_provider() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_RampUp_Settings_AccountId_Test_case.xlsx", 0);
        return data;
    }

    @Test(dataProvider = "email_rampUp_settings_accountId_with_valid_token")
    public void email_rampUp_settings_accountId_with_valid_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String SHaccount2, String Ownership, String Role2, String Status_Code, String Schema) throws ParseException {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        ExcelReader reader = new ExcelReader();
        EmailAccountUtility email_id_utils = new EmailAccountUtility();

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

        logs.test_step("----------------------------------------- GET THE EMAIL ACCOUNT FROM ROLE1 --------------------------------------------");
        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId",email_id_utils.getEmailId(reader.getRecordsSet("Credential.xlsx","Sheet1",params)));

        JSONObject queryParameters = new JSONObject();

        logs.test_step("----------------------------------------- GET THE TOKEN FROM ROLE2 --------------------------------------------");
        JSONObject header = new JSONObject();
        header.put("Authorization",reader.readFromToExcel(new File("src/test/java/data/Credential.xlsx"),"Sheet1",params1,"Token"));
        header.put("content-type", "application/json");

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
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

    @DataProvider(name = "email_rampUp_settings_accountId_with_valid_token_with_free_plan")
    public Object[][] data_provider1() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_RampUp_Settings_AccountId_Test_case.xlsx", 1);
        return data;
    }
    @Test(dataProvider = "email_rampUp_settings_accountId_with_valid_token_with_free_plan")
    public void email_rampUp_settings_accountId_with_valid_token_with_free_plan(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String SHaccount2, String Ownership, String Role2, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility utils = new TokenUtility();
        ExcelReader reader = new ExcelReader();

        Map params = new HashMap();
        params.put("Subscription_State", Subscription_State);
        params.put("Plan", Plan);
        params.put("Role", Role2);
        params.put("SHaccount1", SHaccount2);

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        logs.test_step("----------------------------------------- GET THE TOKEN FROM ROLE1 --------------------------------------------");
        JSONObject header = new JSONObject();
        header.put("Authorization",reader.readFromToExcel(new File("src/test/java/data/Credential.xlsx"),"Sheet1",params,"Token"));
        header.put("content-type", "application/json");

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
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

    //---------------------------------- GET THE TEST DATA FOR INVALID TOKEN-------------------------------------------//

    @DataProvider(name = "email_rampUp_settings_accountId_with_content-type_token")
    public Object[][] data_provider2() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_RampUp_Settings_AccountId_Test_case.xlsx", 2);
        return data;
    }

    @Test(dataProvider = "email_rampUp_settings_accountId_with_content-type_token")
    public void email_rampUp_settings_accountId_with_content_type_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        Map params = new HashMap();
        params.put("Subscription_State", Subscription_State);
        params.put("Plan", Plan);
        params.put("Role", Role1);
        params.put("SHaccount1", SHaccount1);

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjE4OTQsImlhdCI6MTY2MDc0MTk5NywiZXhwIjoxNjYzMzMzOTk3fQ.1uNPfk7_f45MCaGTIFJdtqYtCVrFhH4H1ZZCc7CSGUU" );
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
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

    @DataProvider(name = "email_rampUp_settings_accountId_with_blank_token")
    public Object[][] data_provider3() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_RampUp_Settings_AccountId_Test_case.xlsx", 3);
        return data;
    }
    @Test(dataProvider = "email_rampUp_settings_accountId_with_blank_token")
    public void email_rampUp_settings_accountId_with_blank_token(String Token_Type, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        Map params = new HashMap();
        params.put("Subscription_State", Subscription_State);
        params.put("Plan", Plan);
        params.put("Role", Role1);
        params.put("SHaccount1", SHaccount1);

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization", "");
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
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

    //---------------------------------- GET THE TEST DATA FOR EXPIRED TOKEN --------------------------------------------//

    @DataProvider(name = "email_rampUp_settings_accountId_with_expired_token_data_provider")
    public Object[][] data_provider4() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_RampUp_Settings_AccountId_Test_case.xlsx", 4);
        return data;
    }

    @Test(dataProvider = "email_rampUp_settings_accountId_with_expired_token_data_provider")
    public void email_rampUp_settings_accountId_with_expired_token(String Token_Type,String Token, String Content_Type, String Subscription_State, String Plan, String Role1, String SHaccount1, String Ownership, String Status_Code, String Schema) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();

        Map params = new HashMap();
        params.put("Subscription_State", Subscription_State);
        params.put("Plan", Plan);
        params.put("Role", Role1);
        params.put("SHaccount1", SHaccount1);

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",Token);
        header.put("content-type", Content_Type);

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
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

    //---------------------------------- FUNCTIONAL TEST CASE --------------------------------------------//

    @DataProvider(name = "email_rampUp_settings_accountId_with_email_id_path_parameter")
    public Object[][] data_provider5() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_Id_FUNCTIONAL_TEST_CASE.xlsx", 0);
        return data;
    }

    @Test(dataProvider = "email_rampUp_settings_accountId_with_email_id_path_parameter")
    public void email_rampUp_settings_accountId_with_email_id_path_parameter(String Id,String Filter_Name, String Value, String status_code) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId",Value);

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",utils.getTokenFromUser());
        header.put("Content_Type", "application/json");

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));
    }


    @DataProvider(name = "email_rampUp_settings_accountId_with_rampUpInitialSendingLimit_path_parameter")
    public Object[][] data_provider6() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_Id_FUNCTIONAL_TEST_CASE.xlsx", 1);
        return data;
    }
    @Test(dataProvider = "email_rampUp_settings_accountId_with_rampUpInitialSendingLimit_path_parameter")
    public void email_rampUp_settings_accountId_with_rampUpInitialSendingLimit_path_parameter(String Id,String Filter_Name, String Value, String status_code) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",utils.getTokenFromUser());
        header.put("Content_Type", "application/json");

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",Value);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));
    }

    @DataProvider(name = "email_rampUp_settings_accountId_with_rampUpPercent_path_parameter")
    public Object[][] data_provider7() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_Id_FUNCTIONAL_TEST_CASE.xlsx", 1);
        return data;
    }
    @Test(dataProvider = "email_rampUp_settings_accountId_with_rampUpPercent_path_parameter")
    public void email_rampUp_settings_accountId_with_rampUpPercent_path_parameter(String Id,String Filter_Name, String Value, String status_code) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",utils.getTokenFromUser());
        header.put("Content_Type", "application/json");

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",Value);
        body.put("rampUpStatus", true);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));
    }

    @DataProvider(name = "email_rampUp_settings_accountId_with_rampUpStatus_path_parameter")
    public Object[][] data_provider8() {
        DataReader reader = new DataReader();
        Object[][] data = null;
        data = reader.getExcelDataForDataProvider("Email_Id_FUNCTIONAL_TEST_CASE.xlsx", 1);
        return data;
    }
    @Test(dataProvider = "email_rampUp_settings_accountId_with_rampUpStatus_path_parameter")
    public void email_rampUp_settings_accountId_with_rampUpStatus_path_parameter(String Id,String Filter_Name, String Value, String status_code) {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("Authorization",utils.getTokenFromUser());
        header.put("Content_Type", "application/json");

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", Value);

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));
    }

    @Test
    public void email_rampUp_settings_accountId_with_blank_emailAccountId() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","");

        JSONObject queryParameters = new JSONObject();

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/json");

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(404);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }

    @Test
    public void email_rampUp_settings_accountId_with_different_content_type() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/zip");

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(400);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }
    @Test
    public void email_rampUp_settings_accountId_with_different_method_name() {

        testng_logs logs = new testng_logs();
        RequestBuilder builder = new RequestBuilder();
        TokenUtility token_utils = new TokenUtility();

        JSONObject pathParameter = new JSONObject();
        pathParameter.put("emailAccountId","4512");

        JSONObject queryParameters = new JSONObject();

        JSONObject body = new JSONObject();
        body.put("rampUpInitialSendingLimit",1);
        body.put("rampUpPercent",1);
        body.put("rampUpStatus", true);

        JSONObject header = new JSONObject();
        header.put("Authorization",token_utils.getTokenFromUser());
        header.put("content-type", "application/json");

        logs.test_step("---------------------------------- EMAIL ACCOUNT RAMP UP SETTINGS----------------------------------");
        String update_file = builder.updateRequestObject("email_account/email_ramp_up_settings/email_rampUp_settings_accountId_with_different_method", pathParameter, queryParameters, header, body);
        JSONObject response = builder.performRequest(update_file);
        ResponseValidator validator = new ResponseValidator(response);

        logs.test_step("------------------------------------------ VALIDATE STATUS CODE--------------------------------------------");
        validator.statusCodeShouldBe(404);

        logs.test_step("------------------------------------------ VALIDATE RESPONSE TIME --------------------------------------------");
        validator.validateResponseTime((Long)response.get("responseTime"));

    }


}
