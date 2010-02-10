package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Pour chaque lien image sans titre de lien, l'alternative textuelle (contenu
 * de l'attribut alt ou texte entre <object> et </object>) de l'image est-elle
 * explicite hors contexte ?
 * 
 * @author ndebeissat
 */
public class Rule0601b extends AbstractPageRuleImplementation {

    /**
     * <h1>Classification</h1> semi-décidable <h1>Algo</h1> <h2>Sélection</h2>
     * lien image : lien dont le contenu entre <a href="..."> et </a> est
     * uniquement constitué d'une image. Image : fichier de type graphique
     * (format : gif, jpg, png...) incorporé dans un contenu Web par une des
     * techniques suivantes :
     * <ul>
     * <li>img</li>
     * <li>input type="image"</li>
     * <li>object type="image/..."</li>
     * </ul>
     * Sélection des alternatives textuelles pour les liens contenant une balise
     * img ou une balise input type="image". Sélection de la balise a pour le
     * lien contenant une balise object type="image/..." <h2>Vérification</h2>
     * On utilise les nomenclatures LinkTextBlacklist et LinkTextWhitelist. <h1>
     * Résultats</h1> pas de lien image : NOT_APPLICABLE lien image avec une
     * alternative textuelle en black list : FAILED lien image avec une
     * alternative en white list : PASSED lien image avec une alternative non
     * listée : NMI
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Nomenclature blacklist = nomenclatureLoaderService.loadByCode("LinkTextBlacklist");
        Nomenclature whitelist = nomenclatureLoaderService.loadByCode("LinkTextWhitelist");

        sspHandler.beginSelection().domXPathSelectNodeSet(
                "//A[@href]/IMG/@alt|//A/INPUT[@type = 'image']/@alt|//A[@href][OBJECT[starts-with(@type, 'image/')]]");

        TestSolution checkResult = sspHandler.checkTextContentValue(blacklist.getValueList(), whitelist.getValueList());

        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getSSP().getPage(), checkResult, sspHandler.getRemarkList());

        return result;
    }
}
