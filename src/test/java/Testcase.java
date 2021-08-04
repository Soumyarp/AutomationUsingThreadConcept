import com.org.base.BaseTest;
import com.org.base.ExtentReportFactory;
import com.org.pageObjects.HomePage;
import com.org.pageObjects.LoginPage;
import com.org.pageObjects.t_a.organization.DepartmentsPage;
import com.org.reusableComponents.DB_Operations;
import com.org.reusableComponents.ExcelOperations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by Soumya
 */
public class Testcase extends BaseTest {
    LoginPage loginPage= new LoginPage();
    HomePage homePage= new HomePage();
    DepartmentsPage departmentsPage= new DepartmentsPage();
    DB_Operations db_operations= new DB_Operations();
    ExcelOperations excelOperations= new ExcelOperations("DepartmentSheet");

    @Test(dataProvider = "departmentData")
    public void testCase1(Object object) throws Throwable {
        HashMap<String,String> testdata=(HashMap<String,String>)object;
       ExtentReportFactory.getInstance().getExtent().info("Data fetched from excel is/are :-"+testdata);

        System.out.println(getPageTitle("Loginpage "));
        loginPage.login(getPropertyValueByKey("username"),getPropertyValueByKey("password"));
        homePage.checkWelcomeTxtisDisplayed();
        Thread.sleep(2000);
        homePage.clickOnMenuIcon("Time & Attendance");
        Thread.sleep(2000);
        homePage.clickOnMenu("Organization");
        Thread.sleep(2000);
        homePage.clickOnSubMenu("Departments");
        Thread.sleep(2000);
        departmentsPage.createDepartment(testdata);
     //   departmentsPage.viewallDept("IrandeptTest");
        Thread.sleep(2000);
        String query="select * from org_department where name ='"+testdata.get("DeptName")+"'";
      HashMap<String, String > sqldata = db_operations.getSqlResult(query);
      String deptName=sqldata.get("name");
      assertEqualsString(testdata.get("DeptName"),deptName,"DB_Dept_Name validation");

    }
  //  @Test
    public void testCase2(){
        System.out.println("Testcase2 executed");
    }

    @DataProvider (name ="departmentData")
    public Object[][] getData() throws Exception {
        Object[][] data = new Object[excelOperations.getRowCount()][1];
        for(int i=1;i<=excelOperations.getRowCount();i++){
            HashMap<String,String> hmap=excelOperations.getTestDataInMap(i);
            data[i-1][0]=hmap;
        }
        return data;
    }

}
