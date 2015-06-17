#set($class.package = 'org.tanaguru.webapp.test')
#set($testPrefix = 'Test')
#set($name = $model.name.replace('-', '_'))
#set($class.name = "$name$testPrefix")

package $class.package;
import org.tanaguru.webapp.test.data.KrashtestResult;

public class $class.name extends AbstractTanaguruOnlineTest {

    private String siteName = "$model.name";
    private String[] urlTab = {"$model.url0", "$model.url1","$model.url2",
                               "$model.url3", "$model.url4","$model.url5",
                               "$model.url6", "$model.url7", "$model.url8"};

    /**
     * Default constructor
     */
    public $class.name (){
        super();
    }

    public void testWebappResult(){
        assertEquals(KrashtestResult.SUCCESS.toString(), computeWebappResult(
                launchTanaguru(siteName, urlTab, false)));
    }

}