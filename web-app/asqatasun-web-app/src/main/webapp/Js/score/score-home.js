$(document).ready(function() {

    var scoreSelector = "one-project-score";
    $('.'+scoreSelector).each(function() {
        var scoreElement = d3.select(this).select('div:first-child'), 
            rawScore = scoreElement.text(), 
            score;
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            score = parseInt(rawScore, 10);
            drawScore(
                    d3.select(this), 
                    score, 
                    80, 
                    100, 
                    2, 
                    9, 
                    null, 
                    scoreSelector, 
                    "d3-"+scoreSelector, 
                    true, 
                    null, 
                    null, 
                    -1, 
                    0, 
                    18, 
                    4, 
                    -4, 
                    23,
                    11);
            scoreElement.remove();
        }
    });
});