#set($class.package = 'org.tanaguru.webapp.test')
#set($testPrefix = 'Test')
#set($name = $model.name.replace('-', '_'))
#set($test = $model.test.replace('.', '_'))
#set($class.name = "t$name$testPrefix")

package $class.package;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.webapp.test.data.KrashtestResult;

public class $class.name extends AbstractTanaguruOnlineTest {

    private String siteName = "$model.name";
    private String[] url = {"$model.url"};
    private String testName = "$model.test";

    /**
     * Default constructor
     */
    public $class.name (){
        super();
    }

    public void test$test$model.result () {
        String response = launchTanaguru(siteName, url, true);
        String krashtestResult = computeWebappResult(response);
        if (krashtestResult.equals(KrashtestResult.SUCCESS.toString())) {
            String functionnalResult = computeTestResult(testName);
            TestSolution testSolution = TestSolution.$model.result;
            assertEquals(testSolution.toString(), functionnalResult);
        } else {
            assertEquals(KrashtestResult.SUCCESS.toString(),
                    krashtestResult.toString());
        }
    }

}