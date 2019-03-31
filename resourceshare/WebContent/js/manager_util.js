var get = function(id){
	return document.getElementById(id);
}

window.onload = function(){
	var scText = get("succeed").innerText;
	var sc = get("succeed");
	var lucency = 1;
	
	sc.style.display = 'none';
	
	var changeLucency = function(){
		sc.style.opacity = lucency;
		if(lucency >= 0){
			lucency -= 0.1;
			setTimeout(changeLucency,100);
		}
	}
	
	if(scText != "" && scText != null){
		sc.style.display = 'block'
		setTimeout(changeLucency,2000);
	}
}