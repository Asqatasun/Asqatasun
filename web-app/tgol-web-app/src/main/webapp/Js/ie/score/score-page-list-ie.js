$(document).ready(function() {
    
    var scoreSelector = "audit-score", 
        projectScoreElement = $('#'+scoreSelector), 
        scoreElement, 
        rawScore,
        score;
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