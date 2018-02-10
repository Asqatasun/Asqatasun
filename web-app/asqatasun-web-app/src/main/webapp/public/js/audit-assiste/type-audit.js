/**
 * 
 */
function gereCheckBox() {
	
	if (document.getElementById('functionalityMapPAGES1').checked == false
			&& document.getElementById('functionalityMapUPLOAD1').checked == false
			&& document.getElementById('functionalityMapSCENARIO1').checked == false) {
		document.getElementById('functionalityMapMANUAL1').setAttribute(
				'disabled', 'disabled');
		document.getElementById('functionalityMapMANUAL1').checked = false;

	} else {
		document.getElementById('functionalityMapMANUAL1').removeAttribute(
				'disabled');

	}
}