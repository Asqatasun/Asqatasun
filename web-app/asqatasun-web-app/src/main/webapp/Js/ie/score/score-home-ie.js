$(document).ready(function() {
    d3.selectAll('.one-project-score').each(function() {  
        var scoreElement = $(this).find('div:first-child'), 
            rawScore = scoreElement.text(),
            score;
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            score = parseInt(rawScore, 10);
            if (score===0) {
                score=1;
            }
            drawScore(
                d3.select(this), // selection
                score,  //score
                160, // width
                90, // height
                40, //radius
                9, // outerRadius
                65, // translateX
                50, // translateY
                null,  // id
                'one-project-score',  // class to find
                'd3-ie-one-project-score',  // class to replace with
                true, // addText
                null, 
                null, 
                null, 
                null, 
                "OpenSansSemibold",
                "22px",
                0, 
                1,
                "OpenSansLight", 
                "10px",
                17, 
                4,
                -4, 
                22, 
                12);
            scoreElement.remove();
        }
    });
});