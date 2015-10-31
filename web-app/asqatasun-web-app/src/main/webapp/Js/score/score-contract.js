$(document).ready(function() {
    var scoreSelector = "project-score", 
        projectScoreElement = d3.select('#'+scoreSelector), 
        projectRawScore, 
        score; 

    if (!projectScoreElement.empty()) {
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
                        -46, // tanaguruMeterXOffset
                        -28, // tanaguruMeterXOffset
                        -1, // scoreXOffset
                        5, // scoreYOffset
                        36, // percentXOffset
                        15, // percentYOffset 
                        -5, // maxScoreXOffset
                        45, // maxScorePercentXOffset
                        22); 
            projectScoreElement.select('div').remove();
        }
    }
    scoreSelector = "act-score";
    $('.'+scoreSelector).each(function() {
        var scoreElement = d3.select(this).select('div:first-child'), 
            rawScore = scoreElement.text(), 
            score;
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            score = parseInt(rawScore, 10);
            drawScore(
                    d3.select(this), 
                    score, 
                    12, 
                    12, 
                    2, 
                    2, 
                    null, 
                    scoreSelector, 
                    "d3-"+scoreSelector, 
                    false, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null);
            scoreElement.remove();
            $(this).append(rawScore);
        }
    });
});