$(document).ready(function() {
    
    var scoreSelector = "audit-score",
        projectScoreElement,
        projectRawScore, 
        score;

    projectScoreElement = d3.select('#'+scoreSelector);

    if (projectScoreElement !== null) {
        projectRawScore = projectScoreElement.select('div:first-child').text();
        if (projectRawScore != 'Echec' || projectRawScore != 'Fail') {
            score = parseInt(projectRawScore, 10);
            drawScore(
                        projectScoreElement, 
                        score, 
                        160, // width
                        160,  // height
                        2.1, //divider
                        17,  // radius
                        "d3-"+scoreSelector, //Id of div parent
                        null, 
                        null, 
                        true, // addText
                        -44, // tanaguruMeterXOffset
                        -28, // tanaguruMeterXOffset
                        -1, // scoreXOffset
                        5, // scoreYOffset
                        36, // percentXOffset
                        16, // percentYOffset 
                        -5, // maxScoreXOffset
                        45, // maxScorePercentXOffset
                        null); 
            projectScoreElement.select('div').remove();
        }
    }
    
});