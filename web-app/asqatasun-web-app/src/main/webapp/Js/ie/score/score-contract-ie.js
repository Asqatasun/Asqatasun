$(document).ready(function() {
    
    var scoreSelector = "act-score",
        projectScoreElement, 
        score;

    $('.'+scoreSelector).each(function() {
        var scoreElement = $(this).find('div:first-child'), 
            rawScore = scoreElement.text(),
            score;
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            score = parseInt(rawScore, 10);
            drawScore(
                    d3.select(this), // selection
                    score,  //score
                    14, // width
                    14, // height
                    5, //radius
                    2, // outerRadius
                    5, // translateX
                    8, // translateY
                    null, 
                    scoreSelector, 
                    "d3-ie-"+scoreSelector, 
                    false, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
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
    
    scoreSelector = "project-score";
    projectScoreElement = $('#'+scoreSelector);
    if (projectScoreElement.length>0) {
        scoreElement = projectScoreElement.find('div:first-child');
        rawScore = scoreElement.text();
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            score = parseInt(rawScore, 10);
            if (score===0) {
                score=1;
            }
            drawScore(
                        d3.select(projectScoreElement[0]), // selection
                        score,  //score
                        160, // width
                        160, // height
                        75, //radius
                        17, // outerRadius
                        80, // translateX
                        80, // translateY
                        "d3-ie-"+scoreSelector, //Id of div parent
                        null, 
                        null, 
                        true, // addText
                        "OpenSansLight",
                        "13px",
                        0, // tanaguruMeterXOffset
                        -30, // tanaguruMeterXOffset
                        "OpenSansSemibold",
                        "50px",
                        0, // scoreXOffset
                        10, // scoreYOffset
                        "OpenSansRegular",
                        "21px",
                        38, // percentXOffset
                        18, // percentYOffset 
                        -6, // maxScoreXOffset
                        45, // maxScorePercentXOffset
                        22); 
            scoreElement.remove();
        }
    }
    
});