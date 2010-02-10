package org.opens.tanaguru.client;

import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.reference.NomenclatureCssUnitImpl;
import org.opens.tanaguru.entity.reference.StandardMessage;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.factory.reference.NomenclatureElementFactory;
import org.opens.tanaguru.entity.factory.reference.NomenclatureFactory;
import org.opens.tanaguru.entity.factory.reference.StandardMessageFactory;
import org.opens.tanaguru.entity.factory.reference.TestFactory;
import org.opens.tanaguru.entity.dao.reference.NomenclatureDAO;
import org.opens.tanaguru.entity.dao.reference.StandardMessageDAO;
import org.opens.tanaguru.entity.dao.reference.TestDAO;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.css.sac.LexicalUnit;

/**
 * 
 * @author ADEX
 */
public class ImportImpl {

    private static String RULE_ARCHIVE_NAME = "AccessiWeb";
    private static String RULE_PACKAGE_NAME = "org.opens.tanaguru.rule.accessiweb.";

    private static Test createTest(String code, TestFactory testFactory) {
        Test test = testFactory.create();

        test.setCode(code);
        test.setRuleArchiveName(RULE_ARCHIVE_NAME);
        test.setRuleClassName(RULE_PACKAGE_NAME + "Rule" + code);

        return test;
    }

    private static void importNomenclature(BeanFactory springBeanFactory) {
        NomenclatureFactory nomenclatureFactory = (NomenclatureFactory) springBeanFactory.getBean("nomenclatureFactory");
        NomenclatureElementFactory nomenclatureElementFactory = (NomenclatureElementFactory) springBeanFactory.getBean("nomenclatureElementFactory");
        NomenclatureDAO nomenclatureDAO = (NomenclatureDAO) springBeanFactory.getBean("nomenclatureDAO");

        Nomenclature formsButtonTypes = nomenclatureFactory.create("FormsButtonTypes");
        formsButtonTypes.addElement(nomenclatureElementFactory.create("button"));
        formsButtonTypes.addElement(nomenclatureElementFactory.create("submit"));
        formsButtonTypes.addElement(nomenclatureElementFactory.create("reset"));
        nomenclatureDAO.saveOrUpdate(formsButtonTypes);

        Nomenclature jsWindowOpen = nomenclatureFactory.create("JSWindowOpen");
        jsWindowOpen.addElement(nomenclatureElementFactory.create("window.open"));
        jsWindowOpen.addElement(nomenclatureElementFactory.create("javascript:window.open"));
        nomenclatureDAO.saveOrUpdate(jsWindowOpen);

        Nomenclature notificationNewWindowWhitelist = nomenclatureFactory.create("NotificationNewWindowWhitelist");
        notificationNewWindowWhitelist.addElement(nomenclatureElementFactory.create("nouvelle fenêtre"));
        notificationNewWindowWhitelist.addElement(nomenclatureElementFactory.create("new window"));
        nomenclatureDAO.saveOrUpdate(notificationNewWindowWhitelist);

        Nomenclature formsButtonWhitelist = nomenclatureFactory.create("FormsButtonWhitelist");
        nomenclatureDAO.saveOrUpdate(formsButtonWhitelist);

        Nomenclature formsButtonBlacklist = nomenclatureFactory.create("FormsButtonBlacklist");
        formsButtonBlacklist.addElement(nomenclatureElementFactory.create("valider"));
        formsButtonBlacklist.addElement(nomenclatureElementFactory.create("go"));
        formsButtonBlacklist.addElement(nomenclatureElementFactory.create("envoyer"));
        formsButtonBlacklist.addElement(nomenclatureElementFactory.create("poster"));
        formsButtonBlacklist.addElement(nomenclatureElementFactory.create("submit"));
        formsButtonBlacklist.addElement(nomenclatureElementFactory.create("inscrire"));
        formsButtonBlacklist.addElement(nomenclatureElementFactory.create("s'inscrire"));
        nomenclatureDAO.saveOrUpdate(formsButtonBlacklist);

        Nomenclature linkTextWhitelist = nomenclatureFactory.create("LinkTextWhitelist");
        linkTextWhitelist.addElement(nomenclatureElementFactory.create("back home"));
        linkTextWhitelist.addElement(nomenclatureElementFactory.create("retour à l'accueil"));
        nomenclatureDAO.saveOrUpdate(linkTextWhitelist);

        Nomenclature linkTextBlacklist = nomenclatureFactory.create("LinkTextBlacklist");
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("plus"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("cliquez ici"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("en savoir plus"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("en savoir plus..."));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("ici"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("lire la suite"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("ce lien"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("there"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("this link"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("here"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("post précédent"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("click here"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("là"));
        linkTextBlacklist.addElement(nomenclatureElementFactory.create("more"));
        nomenclatureDAO.saveOrUpdate(linkTextBlacklist);

        Nomenclature possibleImageTags = nomenclatureFactory.create("PossibleImageTags");
        possibleImageTags.addElement(nomenclatureElementFactory.create("img"));
        possibleImageTags.addElement(nomenclatureElementFactory.create("object"));
        nomenclatureDAO.saveOrUpdate(possibleImageTags);

        Nomenclature relativeCssUnits = nomenclatureFactory.create("RelativeCssUnits");
        relativeCssUnits.addElement(new NomenclatureCssUnitImpl(
                (int) LexicalUnit.SAC_PICA, "pc"));
        relativeCssUnits.addElement(new NomenclatureCssUnitImpl(
                (int) LexicalUnit.SAC_POINT, "pt"));
        relativeCssUnits.addElement(new NomenclatureCssUnitImpl(
                (int) LexicalUnit.SAC_MILLIMETER, "mm"));
        relativeCssUnits.addElement(new NomenclatureCssUnitImpl(
                (int) LexicalUnit.SAC_CENTIMETER, "cm"));
        relativeCssUnits.addElement(new NomenclatureCssUnitImpl(
                (int) LexicalUnit.SAC_INCH, "in"));
        nomenclatureDAO.saveOrUpdate(relativeCssUnits);

        Nomenclature validLanguageCodeNomenclature = nomenclatureFactory.create("ValidLanguageCode");
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ab"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("aa"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("af"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sq"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("am"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ar"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("hy"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("as"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ay"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("az"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ba"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("eu"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("bn"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("dz"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("bh"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("bi"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("br"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("bg"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("my"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("be"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("km"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ca"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("zh"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("zh"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("co"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("hr"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("cs"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("da"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("nl"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("en"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("eo"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("et"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("fo"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("fa"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("fj"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("fi"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("fr"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("fy"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("gl"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("gd"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("gv"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ka"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("de"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("el"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("kl"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("gn"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("gu"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ha"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("he"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("iw"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("hi"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("hu"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("is"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("id"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("in"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ia"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ie"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("iu"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ik"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ga"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("it"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ja"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("jv"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("kn"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ks"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("kk"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("rw"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ky"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("rn"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ko"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ku"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("lo"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("la"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("lv"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("li"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ln"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("lt"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("mk"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("mg"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ms"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ml"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("mt"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("mi"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("mr"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("mo"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("mn"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("na"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ne"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("no"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("oc"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("or"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("om"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ps"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("pl"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("pt"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("pa"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("qu"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("rm"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ro"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ru"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sm"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sg"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sa"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sr"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sh"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("st"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("tn"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sn"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sd"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("si"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ss"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sk"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sl"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("so"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("es"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("su"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sw"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("sv"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("tl"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("tg"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ta"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("tt"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("te"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("th"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("bo"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ti"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("to"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ts"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("tr"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("tk"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("tw"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ug"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("uk"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ur"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("uz"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("vi"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("vo"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("cy"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("wo"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("xh"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("yi"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("ji"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("yo"));
        validLanguageCodeNomenclature.addElement(nomenclatureElementFactory.create("zu"));
        nomenclatureDAO.saveOrUpdate(validLanguageCodeNomenclature);
    }

    private static void importStandardMessage(BeanFactory springBeanFactory) {
        StandardMessageFactory standardMessageFactory = (StandardMessageFactory) springBeanFactory.getBean("standardMessageFactory");
        StandardMessageDAO standardMessageDAO = (StandardMessageDAO) springBeanFactory.getBean("standardMessageDAO");

        StandardMessage attributeMissing = standardMessageFactory.create(
                "AttributeMissing", "The attribute is missing");
        standardMessageDAO.saveOrUpdate(attributeMissing);

        StandardMessage blackListedValue = standardMessageFactory.create(
                "BlackListedValue", "The value is blacklisted");
        standardMessageDAO.saveOrUpdate(blackListedValue);

        StandardMessage verifyValue = standardMessageFactory.create(
                "VerifyValue", "The value needs verification");
        standardMessageDAO.saveOrUpdate(verifyValue);

        StandardMessage childNodeMissing = standardMessageFactory.create(
                "ChildNodeMissing", "The child node is missing");
        standardMessageDAO.saveOrUpdate(childNodeMissing);

        StandardMessage valueEmpty = standardMessageFactory.create(
                "ValueEmpty", "The value is empty");
        standardMessageDAO.saveOrUpdate(valueEmpty);

        StandardMessage notMatchExpression = standardMessageFactory.create(
                "NotMatchExpression", "Regular expression not match");
        standardMessageDAO.saveOrUpdate(notMatchExpression);

        StandardMessage LengthTooLong = standardMessageFactory.create(
                "LengthTooLong", "Length too long");
        standardMessageDAO.saveOrUpdate(LengthTooLong);

        StandardMessage accessKeyNotMatch = standardMessageFactory.create(
                "AccessKeyNotMatch", "AccessKey not match");
        standardMessageDAO.saveOrUpdate(accessKeyNotMatch);

        StandardMessage badUnitType = standardMessageFactory.create(
                "BadUnitType", "Bad unit type");
        standardMessageDAO.saveOrUpdate(badUnitType);

        StandardMessage invalidLanguageCode = standardMessageFactory.create(
                "InvalidLanguageCode", "Invalid language code");
        standardMessageDAO.saveOrUpdate(invalidLanguageCode);
    }

    private static void importTest(BeanFactory springBeanFactory) {
        TestFactory testFactory = (TestFactory) springBeanFactory.getBean("testFactory");
        TestDAO testDAO = (TestDAO) springBeanFactory.getBean("testDAO");

        Set<Test> testSet = new HashSet<Test>();
        testSet.add(createTest("0101a", testFactory));
        testSet.add(createTest("0101b", testFactory));
        testSet.add(createTest("0101e", testFactory));
        testSet.add(createTest("0105b", testFactory));
        testSet.add(createTest("0204a", testFactory));
        testSet.add(createTest("0601a", testFactory));
        testSet.add(createTest("0601b", testFactory));
        testSet.add(createTest("0602a", testFactory));
        testSet.add(createTest("0604d", testFactory));
        testSet.add(createTest("0605c", testFactory));
        testSet.add(createTest("0807a", testFactory));
        testSet.add(createTest("1001a", testFactory));
        testSet.add(createTest("1005a", testFactory));
        testSet.add(createTest("1109a", testFactory));
        testSet.add(createTest("1203c", testFactory));
        testSet.add(createTest("1303a", testFactory));
        testSet.add(createTest("0804a", testFactory));

        testDAO.saveOrUpdate(testSet);
    }

    public static void main(String[] args) {

        ApplicationContext springApplicationContext = new ClassPathXmlApplicationContext(
                "/META-INF/context-import.xml");
        BeanFactory springBeanFactory = springApplicationContext;

        // Nomenclature
        importNomenclature(springBeanFactory);
        // StandardMessage
        importStandardMessage(springBeanFactory);
        // Test
        importTest(springBeanFactory);
    }
}
