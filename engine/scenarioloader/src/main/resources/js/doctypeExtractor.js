var node = document.doctype;
var html = "<!DOCTYPE "
    + node.name
    + (node.publicId ? ' PUBLIC "' + node.publicId + '"' : '')
    + (!node.publicId && node.systemId ? ' SYSTEM' : '')
    + (node.systemId ? ' "' + node.systemId + '"' : '')
    + '>';
console.log(html);
return html;