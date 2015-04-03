/*
 * determine whether a node is a text node
 */
function isTextNode (elem) {
    if (isElementOfType(elem,'input') && elem.hasAttribute('type') && (elem.getAttribute('type') === 'text')) {
        return true;
    }
    if (isElementOfType(elem,'iframe')) {
        return false;
    }
    if (isElementOfType(elem,'noscript')) {
        return false;
    }
    if (isElementOfType(elem,'script')) {
        return false;
    }
    if (isElementOfType(elem,'style')) {
        return false;
    }
    if (isElementOfType(elem,'img') && elem.hasAttribute('alt') && (elem.getAttribute('alt').trim().length > 0)) {
        return true;
    }
    for (var i=0 ; i<elem.childNodes.length ; i++) {
        if (elem.childNodes[i].nodeName === "#text" && 
            elem.childNodes[i].textContent.trim().length > 0) {
            return true;
        }
    }
    return false;
};

/*
 * Build a unique css Path from the parent path and the current element index
 */
function buildPath (elem, parentPath, index) {
//    if (elem.id) {
//        return "#" + elem.id;
//    }
    var name = getElementName(elem);
    
    if (name === 'body') {
        return name;
    } else {
        name += ':eq(' + index + ')';
        return (parentPath ? parentPath + ' > ' + name : '' + name);
    }
}

/*
 * Replace comma in color string.
 */
function formatColor(color) {
    while (color.indexOf(',') > -1) {
        color = color.replace(',',';');
    }
    return color;
}

/*
 * compute the background color of a node
 */
function getBackgroundColor(elem){
    var bgImg = getStyle(elem,'background-image');
    if (bgImg !== 'none') {
        while (bgImg.indexOf('"') > -1) {
            bgImg = bgImg.replace('"','\'');
        }
        return "background-image:"+bgImg;
    }
    var bgColor = getStyle(elem,'background-color');
    return formatColor(bgColor);
}

/*
 * compute the foreground color of a node
 */
function getForegroundColor(elem){
    var color = getStyle(elem,'color');
    return formatColor(color);;
}

/*
 * determine whether a node is hidden
 */
function isHidden(elem) {
    var isElementHidden = 
        (getStyle(elem, 'display') === 'none') || 
        (getStyle(elem,'visibility') === 'hidden');
    if (! isElementHidden  && ! isElementOfType(getElementName(elem),'html')) {
        return isHidden(elem.parentNode);
    }
    return isElementHidden;
}

/*
 * get the computed style of an element
 */
function getStyle(elem, strCssRule, pseudoSelector){
    var style = "";
    if (elem.currentStyle) {
	style = elem.currentStyle[strCssRule];
    } else if (window.getComputedStyle) {
        style = document.defaultView.getComputedStyle(elem, pseudoSelector).
            getPropertyValue(strCssRule);
    }
    return style;
}

/*
 * get the element name
 */
function getElementName(elem) {
    return elem.tagName.toLowerCase();
}

/*
 * determine whether an element is of a given type
 */
function isElementOfType(elemName, typeName) {
    if (elemName === typeName) {
        return true;
    }
    return false;
}

function isAllowedElement(elem) {
    var tagName = getElementName(elem);
    if (isElementOfType(tagName,'script')) {
        return false;
    }
    if (isElementOfType(tagName,'noscript')) {
        return false;
    }
    if (isElementOfType(tagName,'br')) {
        return false;
    }
    if (isElementOfType(tagName,'svg')) {
        return false;
    }
    if (isElementOfType(tagName,'head')) {
        return false;
    }
    if (isElementOfType(tagName,'style')) {
        return false;
    }
    if (isElementOfType(tagName,'meta')) {
        return false;
    }
    if (isElementOfType(tagName,'link')) {
        return false;
    }
    if (isElementOfType(tagName,'title')) {
        return false;
    }
    if (isElementOfType(tagName,'option')) {
        return false;
    }
    return true;
};

/*
 * extract info from each element of the dom
 */
function extractInfo (elem, parentFgColor, parentBgColor, result, parentPath, elemIndex) {
    if (isAllowedElement(elem)) {
        var path, element={}, bgColor, color, children, focus;
        bgColor = getBackgroundColor(elem);
        if ( (bgColor === 'transparent' || bgColor === 'rgba(0; 0; 0; 0)' )) {
          if (parentBgColor !== null) {
              console.log(parentBgColor);
              bgColor = parentBgColor;
          } else if (isElementOfType(getElementName(elem),'body')) {
              bgColor = 'rgb(255; 255; 255)';
          } 
        }
        console.log(bgColor);
        color = getForegroundColor(elem);
        if ( (color === 'transparent' || color === 'rgba(0; 0; 0; 0)' ) && parentFgColor !== null) {
            color = parentFgColor;
        }
        
        path = buildPath(elem, parentPath, elemIndex);
        
        element={};
        element.path='\"'+path+'\"';
        element.bgColor = '\"'+bgColor+'\"';
        element.isHidden= isHidden(elem);
        element.color = '\"'+color+'\"';
        element.fontSize =  getStyle(elem,'font-size');
        element.fontWeight = getStyle(elem,'font-weight');
        element.textAlign = getStyle(elem,'text-align');
        element.isTextNode = isTextNode(elem);
        elem.focus(); // get the focus
        focus = document.activeElement;
        if (elem === focus) {
            element.isFocusable = true;
            element.outlineColorFocus = '\"'+formatColor(getStyle(elem,'outline-color', 'focus'))+'\"';
            element.outlineStyleFocus = '\"'+getStyle(elem,'outline-style', 'focus')+'\"';
            element.outlineWidthFocus = getStyle(elem,'outline-width', 'focus');
        } else {
            element.isFocusable = false;
        }
        elem.blur(); // release the focus
        result.push(element);

        children = elem.children;
        for (var i = 0; i < children.length; i++) {
            extractInfo(children[i], color, bgColor, result, path, i);
        }
    }
};

/*
 * Get all the elements of the DOM from body, 
 * extracts the textual one and store bg-color, font-size, color and font-weight.
 */
var result = [], element, rootElem, htmlElem, htmlBgColor;
rootElem = document.body;
/*var e = new Date().getTime();*/
if (rootElem.length !== 0) {
    htmlElem = rootElem.parentNode;
    htmlBgColor= getBackgroundColor(htmlElem);
    if ( (htmlBgColor === 'transparent' || htmlBgColor === 'rgba(0; 0; 0; 0)' )) {
      htmlBgColor = 'rgb(255; 255; 255)';
    }
    extractInfo(rootElem, getForegroundColor(htmlElem), htmlBgColor, result, "",0);
}
/*var f = new Date().getTime();
console.log("Execution : "+ (f-e)  + "ms");
console.log(result.length);*/
return result;
